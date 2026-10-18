package andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealingmechanic;

import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.domain.game.Deck;
import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;
import java.util.List;

/**
 * <h1>#A Mechanic in Blackjack</h1>
 * <p>Corresponding game phase <span style="color:white">DEALING</span>.</p>
 */
public class DealingMechanic
{
    /**
     * <h3>The Core Method.</h3>
     * <p>Both roles——the dealerView and playerViews——get their initial hand filled up.</p>
     * <p>A player also loses the bet it has on the hand.</p>
     * @param gc GameContext
     */
    public void deal(GameContext gc){
        if(gc.getPhase()!=GamePhase.DEALING){
            System.out.print("\nUnexpected call of \"deal\" in DealingMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        List<BlackjackPlayer> players = gc.getPlayers();
        BlackjackDealer dealer = gc.getDealer();
        Deck<PokerCard> deck = gc.getDeck();

        deck.shuffle(); //The deck shuffles the cards.

        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
        dealer.updateCurrentHandStatus();

        for(BlackjackPlayer player : players){ //For every player :
            player.lose(player.getCurrentBet());
            player.addCard(deck.draw());
            player.addCard(deck.draw());
            player.updateCurrentHandStatus();
        }

        gc.setPhase(GamePhase.CHECKING_BLACKJACK);
    }
}
