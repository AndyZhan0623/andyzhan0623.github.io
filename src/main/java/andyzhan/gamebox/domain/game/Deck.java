package andyzhan.gamebox.domain.game;

import andyzhan.gamebox.domain.game.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T extends Card>
{

    public Deck(List<T> cards){
        allCards = new ArrayList<>(cards);
    }
    public Deck(Deck<T> deck){
        allCards = List.copyOf(deck.allCards);
    }

    private List<T> allCards;

    public ArrayList<T> getAllCards() {
        return new ArrayList<>(allCards);
    }

    public void setAllCards(List<T> allCards) {
        this.allCards = allCards;
    }

    public T draw(int index){
        return allCards.remove(index);
    }
    public T draw(){
        return allCards.remove(allCards.size() - 1);
    }

    public void shuffle(){
        Collections.shuffle(allCards);
    }
}
