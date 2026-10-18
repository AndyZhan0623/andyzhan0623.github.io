package andyzhan.gamebox.infrastructure.database.repository.game.blackjack;

import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.GameContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameContextRepository extends JpaRepository<GameContext, Long>
{

}
