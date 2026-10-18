package andyzhan.gamebox.domain.game.card;

public abstract class Card
{
    public Card(){}

    protected CardVisibility visibility;

    public CardVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CardVisibility visibility) {
        this.visibility = visibility;
    }
}
