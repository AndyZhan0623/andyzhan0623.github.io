package andyzhan.gamebox.infrastructure.database.repository.game.blackjack;

import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.Blackjack;
import andyzhan.gamebox.infrastructure.database.id.game.blackjack.BlackjackId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackjackRepository extends JpaRepository<Blackjack, BlackjackId>
{

}
