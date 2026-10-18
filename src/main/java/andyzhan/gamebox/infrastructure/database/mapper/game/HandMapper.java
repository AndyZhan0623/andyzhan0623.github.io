package andyzhan.gamebox.infrastructure.database.mapper.game;

import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;

public class HandMapper
{
    public static<
            Entity extends andyzhan.gamebox.infrastructure.database.entity.game.card.Card,
            Domain extends andyzhan.gamebox.domain.game.card.Card
    > void toDomain(
            andyzhan.gamebox.infrastructure.database.entity.game.Hand<Entity> entity,
            Function<Entity, Domain> cardMapper,
            andyzhan.gamebox.domain.game.Hand<Domain> domain
    ){
        List<Domain> cards = new ArrayList<>();
        for(Entity card : entity.getCards()){
            cards.add(cardMapper.apply(card));
        }
        domain.setCards(cards);
    }

    public static<
            Domain extends andyzhan.gamebox.domain.game.card.Card,
            Entity extends andyzhan.gamebox.infrastructure.database.entity.game.card.Card
    > void updateEntity(
        andyzhan.gamebox.domain.game.Hand<Domain> domain,
        Function<Domain, Entity> cardMapper,
        andyzhan.gamebox.infrastructure.database.entity.game.Hand<Entity> entity
    ){
        List<Entity> cards = new ArrayList<>();
        for(Domain card : domain.getCards()){
            cards.add(cardMapper.apply(card));
        }
        entity.setCards(cards);
    }
}
