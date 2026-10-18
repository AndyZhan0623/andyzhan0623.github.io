package andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealerturnmechanic;

import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.domain.game.Deck;
import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;
import andyzhan.gamebox.domain.game.blackjack.core.HandStatus;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;

import java.util.List;

/**
 * <h1>#A Mechanic in Blackjack</h1>
 * <p>Corresponding game phase <span style="color:white">DEALER_TURN</span>.</p>
 */
public class DealerTurnMechanic
{
    /**
     * <h3>The Entrance of This Mechanic</h3>
     * <p>This method has to be invoked before <span style="color:yellow">ANY</span> further actions in this mechanic.</p>
     * @param gc GamePhase
     */
    public void enterDealerTurn(GameContext gc){
        if(gc.getPhase()!=GamePhase.DEALER_TURN){
            System.out.print("\nUnexpected call of \"enterDealerTurn\" in DealerTurnMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        if(canAct(gc)) dealerTurn(gc);
        gc.setPhase(GamePhase.RESULT);
    }

    /**
     * <p>This method determines whether the dealerView can act.</p>
     * <p>There may be a possibility that each player gets Blackjack, surrenders, or busts.</p>
     * @param gc GameContext
     * @return boolean
     */
    public boolean canAct(GameContext gc){
        List<BlackjackPlayer> players = gc.getPlayers();
        for(BlackjackPlayer player : players){
            if(!player.haveBlackjack()){
                for(BlackjackHand hand : player.getHands()){
                    if(
                            hand.getHandStatus() != HandStatus.SURRENDERED &&
                            hand.getHandStatus() != HandStatus.BUSTED
                    )
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * <h3>The Core Method.</h3>
     * <p>In this method, the dealerView acts.</p>
     * @param gc GameContext
     */
    private void dealerTurn(GameContext gc){
        if(gc.getPhase()!=GamePhase.DEALER_TURN){
            System.out.print("\nWrong Phase.");
            return;
        }

        BlackjackDealer dealer = gc.getDealer();
        Deck<PokerCard> deck = gc.getDeck();

        dealer.getHand().setHasActed(true);
        while(dealer.shouldHit()) dealer.addCard(deck.draw());

        dealer.getHand().setHandStatus(HandStatus.STAND);
        dealer.getHand().updateHandStatus();
    }
}
