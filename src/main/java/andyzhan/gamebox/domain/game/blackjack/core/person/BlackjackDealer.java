package andyzhan.gamebox.domain.game.blackjack.core.person;

import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;

public class BlackjackDealer extends BlackjackPerson
{
    public BlackjackDealer(){
        this.STAND_LIMIT = 17;
    }
    public BlackjackDealer(int STAND_LIMIT){
        if(STAND_LIMIT < 0)
            throw new IllegalArgumentException("STAND_LIMIT must be greater than 0");

        this.STAND_LIMIT = STAND_LIMIT;
    }

    private final int STAND_LIMIT;


    public boolean shouldHit(){
        return getHand().getSum() < STAND_LIMIT;
    }

    public BlackjackHand getHand(){ return getCurrentHand(); }

    public int getStandLimit(){ return STAND_LIMIT; }
}
