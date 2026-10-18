package andyzhan.gamebox.infrastructure.database.entity.game.card;

import andyzhan.gamebox.domain.game.card.CardVisibility;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table
public class PokerCard extends Card
{
    public PokerCard(){}

    public PokerCard(String suit, String rank, CardVisibility visibility) {
        this.suit = suit;
        this.rank = rank;
        this.visibility = visibility;
    }

    private String suit;
    private String rank;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
