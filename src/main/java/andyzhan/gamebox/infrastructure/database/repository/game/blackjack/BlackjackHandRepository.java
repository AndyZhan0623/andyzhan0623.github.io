package andyzhan.gamebox.infrastructure.database.repository.game.blackjack;

import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlackjackHandRepository extends JpaRepository<BlackjackHand, Long>
{

}
