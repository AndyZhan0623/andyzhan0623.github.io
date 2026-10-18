package andyzhan.gamebox.infrastructure.database.mapper.game.blackjack.person;

public class BlackjackDealerMapper
{
    public static andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer toDomain(
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackDealer entity
    ){
        return new andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer(entity.getStandLimit());
    }

    public static andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackDealer toEntity(
            andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer domain
    ){
        return new andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackDealer(domain.getStandLimit());
    }
}
