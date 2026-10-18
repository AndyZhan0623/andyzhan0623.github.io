package andyzhan.gamebox.infrastructure.database.mapper.game.blackjack;

import andyzhan.gamebox.infrastructure.database.mapper.game.HandMapper;
import andyzhan.gamebox.infrastructure.database.mapper.game.card.PokerCardMapper;

public class BlackjackHandMapper
{
    public static andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand toDomain(
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand entity
    ){
        andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand domain = new andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand(
                entity.getTarget(), entity.getBet()
        );

        HandMapper.toDomain(
                entity,
                PokerCardMapper::toDomain,
                domain
        );

        domain.setHasActed(entity.isHasActed());
        domain.calcSum();
        domain.setHandStatus(entity.getStatus());
        return domain;
    }

    public static void updateEntity(
            andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand domain,
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand entity
    ){
        entity.setBet(domain.getBet());
        entity.setHasActed(domain.getHasActed());
        entity.setStatus(domain.getHandStatus());
        entity.setTarget(domain.getTarget());

        HandMapper.updateEntity(
                domain,
                PokerCardMapper::toEntity,
                entity
        );
    }
}
