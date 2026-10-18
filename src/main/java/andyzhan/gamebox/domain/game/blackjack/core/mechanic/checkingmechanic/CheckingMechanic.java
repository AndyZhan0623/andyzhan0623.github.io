package andyzhan.gamebox.domain.game.blackjack.core.mechanic.checkingmechanic;

import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer;

/**
 * <h1>#A Mechanic in Blackjack</h1>
 * <p>Corresponding game phase <span style="color:white">CHECKING_BLACKJACK</span>.</p>
 */
public class CheckingMechanic
{
    /**
     * <p>The Core Method.</p>
     * @param gc GameContext
     */
    public void checkBlackjack(GameContext gc){

        if(gc.getPhase()!=GamePhase.CHECKING_BLACKJACK){
            System.out.print("\nUnexpected call of \"checkBlackjack\" in CheckingMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        BlackjackDealer dealer = gc.getDealer();
        BlackjackHand dealerHand = dealer.getHand();

        if(dealerHand.hasUpcardOf("A")){
            gc.setPhase(GamePhase.INSURANCE);
            return;
        }

        if(dealer.haveBlackjack()){
            gc.setPhase(GamePhase.RESULT);
            return;
        }

        gc.setPhase(GamePhase.PLAYING);
    }
}
