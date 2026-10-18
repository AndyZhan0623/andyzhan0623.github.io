package andyzhan.gamebox.infrastructure.database.repository;

import andyzhan.gamebox.infrastructure.database.entity.User;
import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackPlayer;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId>
{
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
