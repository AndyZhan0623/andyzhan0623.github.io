package andyzhan.gamebox.domain.game.blackjack.core.mechanic.insurancemechanic;

import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;

/**
 * <h1>#A Mechanic in Blackjack</h1>
 * <p>Corresponding game phase <span style="color:white">INSURANCE</span>.</p>
 */
public interface InsuranceMechanic
{
    /**
     * <h3>The Entrance of This Mechanic</h3>
     * <p>This method will be executed every time when a player takes advance.</p>
     * <p>That said, this method always advance to the available player or to another phase</p>
     * @param gc GameContext
     */
    default void enterInsurance(GameContext gc){
        if(gc.getPhase()!=GamePhase.INSURANCE){
            System.out.print("\nUnexpected call of \"enterInsurance\" in InsuranceMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        while(!isAvailable(gc)){
            advance(gc);
        }
    }

    /**
     * <p>This method determines whether the player can buy Insurance.</p>
     * @param gc GameContext
     * @return boolean
     */
    boolean isAvailable(GameContext gc);

    /**
     * <h3>The Core Method.</h3>
     * <p>This method buys the user the insurance or not according to the given boolean parameter.</p>
     * <p>Instead of being written in <span style="color:white">buyInsurance</span>, method <span style="color:white">advance</span> should be written here.</p>
     * @param buy boolean
     * @param gc GameContext
     */
    void decideOnInsurance(boolean buy, GameContext gc);

    /**
     * <p>If current player decides to buy the insurance, this method is executed.</p>
     * <p>That means that this method may not be executed.</p>
     * <p>Logic method "advance" shall not be written in this method.</p>
     */
    void buyInsurance(GameContext gc);

    default void advance(GameContext gc){

        if(gc.hasNextPlayer()){
            gc.nextPlayer();
            return;
        }

        gc.resetCurrentPlayer();

        if(gc.getDealer().haveBlackjack()){
            gc.setPhase(GamePhase.RESULT);
            return;
        }

        gc.setPhase(GamePhase.PLAYING);
    }
}
