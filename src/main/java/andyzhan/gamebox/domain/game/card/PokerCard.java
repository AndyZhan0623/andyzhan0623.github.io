package andyzhan.gamebox.domain.game.card;

public class PokerCard extends Card
{
    public PokerCard(){}
    public PokerCard(String suit, String rank, CardVisibility visibility){
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
