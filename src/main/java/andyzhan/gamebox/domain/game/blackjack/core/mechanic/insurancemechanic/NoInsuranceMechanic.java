package andyzhan.gamebox.domain.game.blackjack.core.mechanic.insurancemechanic;

import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;

/**
 * <h1># A Variety of Mechanic <span style="color:orange">InsuranceMechanic</span></h1>
 * <p>This variety is an empty implementation</p>
 * <p>If a user does not want to consider insurance at all, this variety is adopted.</p>
 */
public class NoInsuranceMechanic implements InsuranceMechanic
{
    @Override
    public boolean isAvailable(GameContext gc){
        return false;
    }

    /**
     * <p>This method is the only working method.</p>
     * @param gc GameContext
     */
    @Override
    public void enterInsurance(GameContext gc){
        if(gc.getPhase()!=GamePhase.INSURANCE){
            System.out.print("\nUnexpected call of \"enterInsurance\" in InsuranceMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        if(gc.getDealer().haveBlackjack()){
            gc.setPhase(GamePhase.RESULT);
            return;
        }

        gc.setPhase(GamePhase.PLAYING);
    }

    @Override
    @Deprecated
    public void decideOnInsurance(boolean buy, GameContext gc){

        if(gc.getPhase()!=GamePhase.INSURANCE){
            System.out.print("\nUnexpected call of \"decideOnInsurance\" in InsuranceMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        //No idea about what to write in a deprecated method.
    }

    @Override
    @Deprecated
    public void buyInsurance(GameContext gc){
        //No idea about what to write in a deprecated method.
    }
}
