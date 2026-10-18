package andyzhan.gamebox.infrastructure.database.mapper.game.card;

/**
 * <p>Just a util class, no need for @Component to be a bean</p>
 */
public class PokerCardMapper
{
    public static andyzhan.gamebox.domain.game.card.PokerCard toDomain(
            andyzhan.gamebox.infrastructure.database.entity.game.card.PokerCard entity
    ){
        return new andyzhan.gamebox.domain.game.card.PokerCard(entity.getSuit(), entity.getRank(), entity.getVisibility());
    }

    public static andyzhan.gamebox.infrastructure.database.entity.game.card.PokerCard toEntity(
            andyzhan.gamebox.domain.game.card.PokerCard domain
    ){
        return new andyzhan.gamebox.infrastructure.database.entity.game.card.PokerCard(domain.getSuit(), domain.getRank(), domain.getVisibility());
    }
}
