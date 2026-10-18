package andyzhan.gamebox.infrastructure.database.entity.game.card;

import andyzhan.gamebox.domain.game.card.CardVisibility;
import andyzhan.gamebox.infrastructure.database.entity.LongEntity;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Card extends LongEntity
{
    protected CardVisibility visibility;

    public CardVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CardVisibility visibility) {
        this.visibility = visibility;
    }
}
