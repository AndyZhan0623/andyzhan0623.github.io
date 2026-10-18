package andyzhan.gamebox.infrastructure.database.repository.game.card;

import andyzhan.gamebox.infrastructure.database.entity.game.card.PokerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokerCardRepository extends JpaRepository<PokerCard, Long>
{

}
