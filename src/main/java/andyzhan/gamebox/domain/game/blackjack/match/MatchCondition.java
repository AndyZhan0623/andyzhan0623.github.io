package andyzhan.gamebox.domain.game.blackjack.match;

import andyzhan.gamebox.infrastructure.database.mapper.game.blackjack.mechanic_types.InsuranceMechanicType;

public record MatchCondition(
        int playerCount,
        InsuranceMechanicType insrType
){}
