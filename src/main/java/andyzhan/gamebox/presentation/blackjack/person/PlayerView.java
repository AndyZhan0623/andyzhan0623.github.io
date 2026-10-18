package andyzhan.gamebox.presentation.blackjack.person;

import andyzhan.gamebox.presentation.blackjack.HandView;
import andyzhan.gamebox.infrastructure.database.id.UserId;

import java.math.BigDecimal;
import java.util.List;

public record PlayerView(
    UserId userId,
    String username,
    List<HandView> hands,
    int currentHandIndex,
    BigDecimal chip
) {}
