package andyzhan.gamebox.domain.game.cardsetfactory;

import andyzhan.gamebox.domain.game.card.PokerCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokerCardSetFactory implements CardSetFactory<PokerCard>
{
    private static final String[] ALL_SUIT = {"♠", "♣", "♥", "♦"};
    private static final String[] ALL_RANK = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
    };

    public static String[] getAllSuit(){return Arrays.copyOf(ALL_SUIT, ALL_SUIT.length);}
    public static String[] getAllRank(){return Arrays.copyOf(ALL_RANK, ALL_RANK.length);}

    @Override
    public List<PokerCard> cardSet(){
        List<PokerCard> cards = new ArrayList<>();
        for(String suit : ALL_SUIT){
            for(String rank : ALL_RANK){
                cards.add(new PokerCard(suit, rank));
            }
        }
        return cards;
    }
}
