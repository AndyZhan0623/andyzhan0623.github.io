package andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person;

import andyzhan.gamebox.infrastructure.database.converter.UserIdConverter;
import andyzhan.gamebox.infrastructure.database.converter.blackjack.BlackjackPlayerIdConverter;
import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.GameContext;
import andyzhan.gamebox.infrastructure.database.id.game.blackjack.BlackjackPlayerId;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ManyToOne;

@Entity
@Table
public class BlackjackPlayer extends BlackjackPerson
{
    @Id
    @Convert(converter = BlackjackPlayerIdConverter.class)
    private BlackjackPlayerId id;

    @Column
    @Convert(converter = UserIdConverter.class)
    private UserId userId;

    @ManyToOne
    private GameContext gameContext;


    public BlackjackPlayerId getId() {
        return id;
    }
    public void setId(BlackjackPlayerId id) {
        this.id = id;
    }
    public UserId getUserId() {
        return userId;
    }
    public void setUserId(UserId userId) {
        this.userId = userId;
    }
    public GameContext getGameContext() {
        return gameContext;
    }
    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }
}
