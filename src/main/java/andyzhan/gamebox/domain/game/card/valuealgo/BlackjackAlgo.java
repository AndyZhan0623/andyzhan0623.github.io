package andyzhan.gamebox.domain.game.card.valuealgo;

import andyzhan.gamebox.domain.game.cardsetfactory.PokerCardSetFactory;
import java.util.HashMap;

public class BlackjackAlgo implements ValueAlgo<String>
{
    private final HashMap<String, Integer> RANK_TO_VALUE = buildMap();

    private HashMap<String, Integer> buildMap(){
        HashMap<String, Integer> map = new HashMap<>();
        String[] allSuit = PokerCardSetFactory.getAllRank();
        for(int i=0;i<9;i++)
            map.put(allSuit[i], i+2);
        for(int i=9;i<12;i++)
            map.put(allSuit[i], 10);
        map.put(allSuit[12], 11);
        return map;
    }

    @Override
    public int calcCardValue(String rank)
    {
        return RANK_TO_VALUE.get(rank);
    }
}
