package andyzhan.gamebox.infrastructure.database.repository.game.blackjack.person;

import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackPlayer;
import andyzhan.gamebox.infrastructure.database.id.game.blackjack.BlackjackPlayerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackjackPlayerRepository extends JpaRepository<BlackjackPlayer, BlackjackPlayerId>
{

}
