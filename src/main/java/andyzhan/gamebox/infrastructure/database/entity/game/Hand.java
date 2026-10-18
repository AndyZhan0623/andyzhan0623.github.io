package andyzhan.gamebox.infrastructure.database.entity.game;

import andyzhan.gamebox.infrastructure.database.entity.game.card.Card;
import andyzhan.gamebox.infrastructure.database.entity.LongEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;

import java.util.List;

@MappedSuperclass
public abstract class Hand<T extends Card> extends LongEntity {

    public Hand(){}
    public Hand(List<T> cards){
        this.cards = cards;
    }

    @OneToMany
    @JoinColumn( name = "hand_id" )
    protected List<T> cards;

    public List<T> getCards() {
        return cards;
    }
    public void setCards(List<T> cards) {
        this.cards = cards;
    }
}
