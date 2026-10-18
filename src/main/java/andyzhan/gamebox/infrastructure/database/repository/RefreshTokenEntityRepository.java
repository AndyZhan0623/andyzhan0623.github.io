package andyzhan.gamebox.infrastructure.database.repository;

import andyzhan.gamebox.infrastructure.database.entity.RefreshTokenEntity;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenEntityRepository extends JpaRepository<RefreshTokenEntity, Long>
{
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);
    Optional<RefreshTokenEntity> findByUserId(UserId userId);
    boolean existsByUserId(UserId userId);
    boolean existsByRefreshToken(String refreshToken);
}
