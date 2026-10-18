package andyzhan.gamebox.domain.game.blackjack.core;

import andyzhan.gamebox.domain.game.BetCarrier;
import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.domain.game.card.valuealgo.BlackjackAlgo;
import andyzhan.gamebox.domain.game.card.valuealgo.ValueAlgo;
import andyzhan.gamebox.domain.game.Hand;

import java.math.BigDecimal;

public class BlackjackHand extends Hand<PokerCard>
    implements BetCarrier
{
    /**
     * <p>Null Constructor</p>
     */
    public BlackjackHand(){}

    public BlackjackHand(int target, BigDecimal bet){
        if(target <= 0)
            throw new IllegalArgumentException("target must be greater than 0");

        this.target = target;
        setBet(bet);
    }

    /**
     * <p>Copy Constructor</p>
     * @param hand BlackjackHand
     */
    public BlackjackHand(BlackjackHand hand){
        this.sum = hand.sum;
        this.aceNum = hand.aceNum;
        this.target = hand.target;
        this.hasActed = hand.hasActed;
        this.bet = hand.bet;
        this.handStatus = hand.handStatus;
    }

    /**
     * <p>Sum of the values of poker cards in this hand</p>
     */
    private int sum;

    /**
     * <h4>Number of Aces this hand has.</h4>
     * <p>If this number is greater than zero, the player can soften this hand</p>
     */
    private int aceNum;

    /**
     * <p>Since it is Blackjack, the target, of course, is 21</p>
     * <p>Who knows, maybe someone would like to change this to 24 or whatever.</p>
     */
    private int target = 21;

    private final ValueAlgo<String> va = new BlackjackAlgo();


    /**
     * <p>This is the condition used to evaluate whether the player can surrender on this hand.</p>
     */
    private boolean hasActed;

    /**
     * <p>The bet the player has on this hand.</p>
     */
    private BigDecimal bet;

    private HandStatus handStatus = HandStatus.PLAYING;

    public int getSum() {
        return sum;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    public int getTarget(){
        return target;
    }

    public void calcSum(){
        sum = 0;
        for(PokerCard card : cards){
            sum += va.calcCardValue(card.rank());
            if(card.rank().equals("A"))
                aceNum++;
        }
        for(; sum>target&&aceNum>0 ; aceNum--) sum-=10;
    }

    /**
     * <p>Update handStatus automatically if the hand busts or is Blackjack.</p>
     * <p>If neither of above happens, field <span style="color:white">handStatus</span> remains.</p>
     */
    public void updateHandStatus(){
        if(sum>target){
            handStatus = HandStatus.BUSTED;
            return;
        }

        if(
                va.calcCardValue(getUpCard().rank())
                + va.calcCardValue(getHoleCard().rank())
                == target
        ){
            handStatus = HandStatus.BLACKJACK;
            return;
        }
    }

    public void setHandStatus(HandStatus handStatus){
        this.handStatus = handStatus;
    }

    public HandStatus getHandStatus(){
        return handStatus;
    }

    public boolean getHasActed(){
        return hasActed;
    }
    public void setHasActed(boolean hasActed){
        this.hasActed = hasActed;
    }

    public boolean hasUpcardOf(String rank){
        return cards.get(0).rank().equals(rank);
    }

    public PokerCard getUpCard(){
        return getCard(0);
    }

    public PokerCard getHoleCard(){
        return getCard(1);
    }
    public PokerCard removeHoleCard(){
        return removeCard(1);
    }

    /**
     * <p>This method:</p>
     * <li>Add a poker card into the <span style="color:white">cards</span>(a field declared in the super class of this——<span style="color:orange">Hand</span>).</li>
     * <li>Calculate the <span style="color:white">sum</span> after the card is added.</li>
     * <li>Soften the hand automatically if there are Aces.</li>
     * @param card PokerCard
     */
    @Override
    public void addCard(PokerCard card) {
        cards.add(card);
        sum += va.calcCardValue(card.rank());
        if(card.rank().equals("A"))
            aceNum++;
        if(sum>target&&aceNum>0) {
            sum -= 10;
            aceNum--;
        }
    }

    /**
     * <p>This method:</p>
     * <li>Removes a card out of the field <span style="color:white">cards</span> according to the given index</li>
     * <li>Calculate the <span style="color:white">sum</span> after the card is removed</li>
     * <li>If the card is an Ace, decrease the number of Aces could be used to soften the hand</li>
     * @param cardIndex int
     * @return PokerCard
     */
    @Override
    public PokerCard removeCard(int cardIndex){
        PokerCard card = cards.remove(cardIndex);
        sum -= va.calcCardValue(card.rank());
        if(card.rank().equals("A"))
            aceNum--;
        return card;
    }

    public boolean isSplittable(){
        return getUpCard().rank().equals(getHoleCard().rank());
    }

    public BigDecimal getBet(){
        return bet;
    }
    public void setBet(BigDecimal bet){
        if(bet.compareTo(BigDecimal.ZERO)<=0){
            System.out.print("\nGiven bet is not positive");
            return;
        }

        this.bet = bet;
    }
}
