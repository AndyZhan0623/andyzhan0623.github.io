package andyzhan.gamebox.domain.game;

import andyzhan.gamebox.domain.game.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand<T extends Card>  {

    public Hand(){
        cards = new ArrayList<>();
    }
    public Hand(List<T> cards){
        this.cards = cards;
    }

    protected List<T> cards;

    public List<T> getCards() {
        return cards;
    }
    public T getCard(int index){
        return cards.get(index);
    }
    public void setCards(List<T> cards) {
        this.cards = cards;
    }

    public void addCard(T card){
        cards.add(card);
    }
    public void addCard(int index, T card){
        cards.add(index, card);
    }

    public T removeCard(int index){
        return cards.remove(index);
    }
}
