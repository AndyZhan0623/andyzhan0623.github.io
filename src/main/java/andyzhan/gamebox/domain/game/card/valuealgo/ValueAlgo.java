package andyzhan.gamebox.domain.game.card.valuealgo;

@FunctionalInterface
public interface ValueAlgo<Context>
{
    int calcCardValue(Context context);
}
