package andyzhan.gamebox.domain.game.cardsetfactory;

import java.util.ArrayList;
import java.util.List;

public interface CardSetFactory<T>
{
    List<T> cardSet();
    default List<T> cardSet(int amount)
    {
        List<T> cards = new ArrayList<>();
        for(int i = 0; i < amount; i++)
            cards.addAll(cardSet());
        return cards;
    }
}
