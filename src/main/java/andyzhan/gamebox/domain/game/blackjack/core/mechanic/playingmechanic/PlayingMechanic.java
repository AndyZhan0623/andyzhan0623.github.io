package andyzhan.gamebox.domain.game.blackjack.core.mechanic.playingmechanic;

import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.domain.game.Deck;
import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;
import andyzhan.gamebox.domain.game.blackjack.core.HandStatus;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;

/**
 * <h1>#A Mechanic in Blackjack</h1>
 * <p>Corresponding game phase <span style="color:white">PLAYING</span>.</p>
 */
public class PlayingMechanic
{
    /**
     * <h3>The Entrance of The Mechanic</h3>
     * <p>This method has to be invoked every time before a player takes actions.</p>
     * @param gc GameContext
     */
    public void enterPlaying(GameContext gc){
        if(gc.getPhase()!=GamePhase.PLAYING){
            System.out.print("\nUnexpected call of \"enterPlaying\" in PlayingMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        while(!canPlay(gc)){
            advance(gc);
        }
    }

    /**
     * <p>This method determines whether the current player can play.</p>
     * @param gc GameContext
     * @return boolean
     */
    public boolean canPlay(GameContext gc){
        BlackjackPlayer player = gc.getCurrentPlayer();
        return player.canAct();
    }

    /**
     * <h3>The Core Method.</h3>
     * @param action ActionType
     * @param gc GameContext
     */
    public void play(ActionType action, GameContext gc){
        if(gc.getPhase()!=GamePhase.PLAYING){
            System.out.print("\nUnexpected call of \"play\" in PlayingMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        switch(action){
            case SURRENDER -> surrender(gc);
            case SPLIT -> split(gc);
            case DOUBLE_DOWN -> doubleDown(gc);
            case HIT -> hit(gc);
            case STAND -> stand(gc);
        }
    }

    /**
     * <p>This method advances the game context.</p>
     * @param gc GameContext
     */
    private void advance(GameContext gc){

        BlackjackPlayer player = gc.getCurrentPlayer();

        if(player.hasNextHand()){
            player.nextHand();
            return;
        }

        if(gc.hasNextPlayer()) {
            player.resetCurrentHand();
            gc.nextPlayer();
            return;
        }

        gc.setPhase(GamePhase.DEALER_TURN);
        gc.resetCurrentPlayer();
    }

    private void surrender(GameContext gc){
        BlackjackPlayer player = gc.getCurrentPlayer();
        player.setCurrentHandStatus(HandStatus.SURRENDERED);
        player.win(player.getCurrentBet().divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP));
        advance(gc);
    }
    private void split(GameContext gc){
        Deck<PokerCard> deck = gc.getDeck();
        BlackjackPlayer player = gc.getCurrentPlayer();
        BlackjackHand hand = player.getCurrentHand();

        player.lose(player.getCurrentBet());
        BlackjackHand anotherHand = new BlackjackHand();
        anotherHand.setBet(player.getCurrentBet());
        player.addHand(player.getCurrentHandIndex()+1, anotherHand);
        PokerCard card = hand.removeHoleCard();
        anotherHand.addCard(card);

        hand.addCard(0, deck.draw());
        anotherHand.addCard(deck.draw());

        hand.setHasActed(true);
        anotherHand.setHasActed(true);
    }
    private void doubleDown(GameContext gc){
        BlackjackPlayer player = gc.getCurrentPlayer();
        player.lose(player.getCurrentBet());
        player.setCurrentBet(player.getCurrentBet().multiply(new BigDecimal("2")));
        hit(gc);
        advance(gc);
    }
    private void hit(GameContext gc){
        BlackjackPlayer player = gc.getCurrentPlayer();
        BlackjackHand hand = player.getCurrentHand();

        player.addCard(gc.getDeck().draw());
        player.updateCurrentHandStatus();

        hand.setHasActed(true);
    }
    private void stand(GameContext gc){
        BlackjackHand hand = gc.getCurrentPlayer().getCurrentHand();

        hand.setHandStatus(HandStatus.STAND);
        advance(gc);

        hand.setHasActed(true);
    }

    /**
     * <p>This method returns an enum set indicating which actions the player can act.</p>
     * <p><span style="color:white">HIT</span> and <span style="color:white">STAND</span> are always available.</p>
     * @param gc GameContext
     * @return EnumSet[ActionType]
     */
    public EnumSet<ActionType> getAvailableActions(GameContext gc){
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);

        if(gc.getPhase()!=GamePhase.PLAYING){
            System.out.println("Actions should only be displayed when playing");
            return actions;
        }

        if(canSurrender(gc)) actions.add(ActionType.SURRENDER);
        if(canSplit(gc)) actions.add(ActionType.SPLIT);
        if(canDoubleDown(gc)) actions.add(ActionType.DOUBLE_DOWN);

        actions.add(ActionType.HIT);
        actions.add(ActionType.STAND);

        return actions;
    }

    private boolean canSplit(GameContext gc){
        BlackjackPlayer player = gc.getCurrentPlayer();
        BlackjackHand hand = player.getCurrentHand();
        return
                hand.isSplittable() &&
                (!hand.getHasActed()) &&
                player.hasChipNotLessThanBet() &&
                player.canAddHand();
    }
    private boolean canDoubleDown(GameContext gc){
        BlackjackPlayer player = gc.getCurrentPlayer();
        BlackjackHand hand = player.getCurrentHand();

        return  (!hand.getHasActed()) &&
                player.hasChipNotLessThanBet();
    }
    private boolean canSurrender(GameContext gc){
        BlackjackHand hand = gc.getCurrentPlayer().getCurrentHand();
        return !hand.getHasActed();
    }

}
