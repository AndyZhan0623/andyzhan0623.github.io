package andyzhan.gamebox.domain.game.blackjack.core;

import andyzhan.gamebox.domain.game.Game;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.Mechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.playingmechanic.ActionType;

import java.util.EnumSet;

/**
 * <h1># Running Container of Blackjack</h1>
 * <p>This class is designed as a window between a front-end and the source codes under package blackjack</p>
 * <p>Thus, this class should actually know nothing but its fields: Mechanic and GameContext</p>
 * <p>All this class do is to transfer information and to call mechanics.</p>
 */
public class Blackjack implements Game
{
    public Blackjack(GameContext gc, Mechanic mech){
        this.gc = gc;
        this.mechanic = mech;
    }

    private final GameContext gc;

    private final Mechanic mechanic;

    public GameContext getGc() {
        return new GameContext(gc);
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    /**
     * <h1>The Core Method</h1>
     * <p>This method push the state of the game to the end of the phase or the point where needs inputs.</p>
     */
    public void operate(){

        switch(gc.getPhase()){
            case DEALING -> deal();
            case CHECKING_BLACKJACK -> checkBlackjack();
            case INSURANCE -> enterInsurance();
            case PLAYING -> enterPlaying();
            case DEALER_TURN -> enterDealerTurn();
            case RESULT -> result();
        }

    }



    //DEALING
    public void deal(){
        mechanic.dealMech().deal(gc);
    }
    //DEALING END

    //CHECKING_BLACKJACK
    public void checkBlackjack(){
        mechanic.chekMech().checkBlackjack(gc);
    }
    //CHECKING_BLACKJACK END

    //INSURANCE
    public void enterInsurance(){
        mechanic.insrMech().enterInsurance(gc);
    }

    public void decideOnInsurance(boolean buy){
        mechanic.insrMech().decideOnInsurance(buy, gc);
    }
    //INSURANCE END

    //PLAYING
    public void enterPlaying(){
        mechanic.playMech().enterPlaying(gc);
    }

    public EnumSet<ActionType> getAvailableActions(){
        return mechanic.playMech().getAvailableActions(gc);
    }

    public void play(ActionType action){
        mechanic.playMech().play(action, gc);
    }
    //PLAYING END

    //DEALER_TURN
    public void enterDealerTurn(){
        mechanic.dTrnMech().enterDealerTurn(gc);
    }
    //DEALER_TURN END

    //RESULT
    public void result(){
        mechanic.reslMech().result(gc);
    }
    //RESULT END
}
