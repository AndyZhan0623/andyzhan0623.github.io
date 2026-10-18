package andyzhan.gamebox.presentation.blackjack;

import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.HandStatus;
import andyzhan.gamebox.domain.game.card.PokerCard;

import java.math.BigDecimal;

import java.util.List;

public record HandView(
        BigDecimal bet,
        List<PokerCard> cards,
        HandStatus handStatus
){
    public static HandView from(BlackjackHand hand){
        return new HandView(
                hand.getBet(),
                hand.getCards(),
                hand.getHandStatus()
        );
    }
}
