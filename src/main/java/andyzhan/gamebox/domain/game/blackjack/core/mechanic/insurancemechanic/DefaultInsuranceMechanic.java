package andyzhan.gamebox.domain.game.blackjack.core.mechanic.insurancemechanic;

import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <h1># A Variety of Mechanic <span style="color:orange">InsuranceMechanic</span></h1>
 * <p>This mechanic provides the common rules of the insurance in Blackjack</p>
 */
public class DefaultInsuranceMechanic implements InsuranceMechanic
{
    @Override
    public boolean isAvailable(GameContext gc){
        BlackjackPlayer player = gc.getCurrentPlayer();
        return  !player.haveBlackjack() &&
                (player.getChip().compareTo(player.getCurrentBet().
                divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP)) >= 0);
    }

    @Override
    public void decideOnInsurance(boolean buy, GameContext gc){
        if(gc.getPhase()!=GamePhase.INSURANCE){
            System.out.print("\nUnexpected call of \"decideOnInsurance\" in InsuranceMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        if(buy) buyInsurance(gc);
        advance(gc);
    }

    /**
     * <p>Since the player with Blackjack cannot bet insurance,</p>
     * <p>even if the chips of the player change instantly, </p>
     * <p>the player cannot earn an additional bet by a blackjack draw.</p>
     * @param gc GameContext
     */
    @Override
    public void buyInsurance(GameContext gc){

        BlackjackPlayer player = gc.getCurrentPlayer();
        BlackjackDealer dealer = gc.getDealer();

        if (dealer.haveBlackjack()) {
            player.win(player.getCurrentBet());
            return;
        }

        player.lose(player.getCurrentBet().divide(
                new BigDecimal("2"),
                2,
                RoundingMode.HALF_UP
        ));
    }

}
