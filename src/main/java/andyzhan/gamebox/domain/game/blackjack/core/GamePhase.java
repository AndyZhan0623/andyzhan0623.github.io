package andyzhan.gamebox.domain.game.blackjack.core;

/**
 * <p>An enum indicating the phase of Blackjack</p>
 * <p>Each phase corresponds a mechanic in Blackjack</p>
 */
public enum GamePhase
{
    PLACING_BET,
    DEALING,
    CHECKING_BLACKJACK,
    INSURANCE,
    PLAYING,
    DEALER_TURN,
    RESULT,
    END
}
