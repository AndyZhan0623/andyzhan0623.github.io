package andyzhan.gamebox.infrastructure.database.repository.game.blackjack.person;

import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackDealer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackjackDealerRepository extends JpaRepository<BlackjackDealer, Long>
{

}
