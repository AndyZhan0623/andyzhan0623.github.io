package andyzhan.gamebox.domain.game.blackjack.core.mechanic.resultmechanic;

import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;
import andyzhan.gamebox.domain.game.blackjack.core.HandStatus;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * <h1>#A Mechanic of Blackjack</h1>
 * <p>Corresponding phase <span style="color:orange">RESULT</span> in enum <span style="color:yellow">GamePhase</span>.</p>
 */
public class ResultMechanic
{
    /**
     * <h1 style="color:yellow;font:bold">The Core Method of This Mechanic</h1>
     * <p>This method iterates all hands of all playerViews, calculating the chips.</p>
     */
    public void result(GameContext gc){
        if(gc.getPhase()!=GamePhase.RESULT){
            System.out.print("\nUnexpected call of \"result\" in ResultMechanic.");
            System.out.print("\nCurrent phase is "+gc.getPhase());
            return;
        }

        List<BlackjackPlayer> players = gc.getPlayers();
        BlackjackHand dealerHand = gc.getDealer().getHand();

        for(BlackjackPlayer player : players){
            List<BlackjackHand> playerHands = player.getHands();
            for(BlackjackHand playerHand : playerHands){
                switch(analyzeHand(playerHand, dealerHand)){
                    case WIN -> playerWin(player);
                    case LOSE -> playerLose(player);
                    case DRAW -> playerDraw(player);
                }
            }
        }

        gc.setPhase(GamePhase.END);
    }

    /**
     * <p>This method does what its name suggests: analyzing a hand of a player and the hand of the dealerView.</p>
     */
    private HandResult analyzeHand(BlackjackHand playerHand, BlackjackHand dealerHand){
        if(
                playerHand.getHandStatus() == HandStatus.BLACKJACK &&
                dealerHand.getHandStatus() == HandStatus.BLACKJACK
        ) return HandResult.DRAW;
        if(playerHand.getHandStatus() == HandStatus.BLACKJACK) return HandResult.WIN;
        if(dealerHand.getHandStatus() == HandStatus.BLACKJACK)  return HandResult.LOSE;

        if(playerHand.getHandStatus() == HandStatus.SURRENDERED) return HandResult.LOSE;

        if(playerHand.getHandStatus() == HandStatus.BUSTED) return HandResult.LOSE;
        if(dealerHand.getHandStatus() == HandStatus.BUSTED) return HandResult.WIN;

        if(playerHand.getSum()>dealerHand.getSum()) return HandResult.WIN;
        if(playerHand.getSum()<dealerHand.getSum()) return HandResult.LOSE;

        return HandResult.DRAW;
    }

    /**
     * <p>This method earns the player twice the bet it has on one hand.</p>
     * <p>If the player gets Blackjack, there is an additional half of the bet rewarded.</p>
     */
    private void playerWin(BlackjackPlayer player){
        player.win(player.getCurrentBet().multiply(new BigDecimal("2")));
        if(player.haveBlackjack())
            player.win(player.getCurrentBet().divide(new BigDecimal("2"), 2,  RoundingMode.HALF_UP));
    }

    /**
     * <p>This method does <span style="color:yellow; font: bold">NOTHING</span> since the bet on
     * one hand has already been collected once the hand was created </p>
     */
    private void playerLose(BlackjackPlayer player){
        //A bet on one hand has already been collected at the beginning.
    }

    /**
     * <p>This method earns the player the bet it has on one hand back.</p>
     */
    private void playerDraw(BlackjackPlayer player){
        player.win(player.getCurrentBet());
    }
}

/**
 * <p>A small tool-like enum used only by the main class <span style="color:yellow">ResultMechanic</span></p>
 */
enum HandResult{
    WIN,
    LOSE,
    DRAW
}
