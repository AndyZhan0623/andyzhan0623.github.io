package andyzhan.gamebox.infrastructure.database.mapper.game.blackjack.person;

import andyzhan.gamebox.infrastructure.database.entity.User;
import andyzhan.gamebox.infrastructure.database.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BlackjackPlayerMapper
{
    public BlackjackPlayerMapper(
            UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer toDomain(
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackPlayer entity
    ){


        User user = userRepository.findById(entity.getUserId()).orElse(null);
        andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer domain =
                new andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer(
                user.getBalance()
        );

        BlackjackPersonMapper.toDomain(entity, domain);

        return domain;
    }

    public void updateEntity(
            andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer domain,
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackPlayer entity
    ){
        BlackjackPersonMapper.updateEntity(domain, entity);
    }
}
