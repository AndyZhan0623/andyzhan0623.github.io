package andyzhan.gamebox.infrastructure.database.entity.game.blackjack;

import andyzhan.gamebox.infrastructure.database.converter.blackjack.BlackjackIdConverter;
import andyzhan.gamebox.infrastructure.database.id.game.blackjack.BlackjackId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Convert;
import jakarta.persistence.OneToOne;

@Entity
@Table
public class Blackjack
{
    @Id
    @Convert( converter = BlackjackIdConverter.class)
    private BlackjackId id;

    @OneToOne private Mechanic mechanic;
    @OneToOne private GameContext gameContext;

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }
}
