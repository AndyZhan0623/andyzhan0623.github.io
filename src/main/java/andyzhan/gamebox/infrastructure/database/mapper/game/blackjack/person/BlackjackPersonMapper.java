package andyzhan.gamebox.infrastructure.database.mapper.game.blackjack.person;

import andyzhan.gamebox.infrastructure.database.mapper.game.blackjack.BlackjackHandMapper;

import java.util.ArrayList;
import java.util.List;

public class BlackjackPersonMapper
{
    public static void toDomain(
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackPerson entity,
            andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPerson domain
    ){
        List<andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand> hands = new ArrayList<>();
        for(andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand hand : entity.getHands()){
            hands.add(BlackjackHandMapper.toDomain(hand));
        }

        domain.setHands(hands);
        domain.setCurrentHandIndex(entity.getCurrentHandIndex());
        domain.setMaxHandsSize(entity.getMaxHandsSize());
    }

    public static void updateEntity(
            andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPerson domain,
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackPerson entity
    ){
        List<andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand> hands = new ArrayList<>();
        for(andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand domainHand : domain.getHands()){
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand entityHand =
                    new andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand();
            BlackjackHandMapper.updateEntity(domainHand, entityHand);
            hands.add(entityHand);
        }
        entity.setCurrentHandIndex(domain.getCurrentHandIndex());
        entity.setMaxHandsSize(domain.getMaxHandsSize());
    }
}
