package andyzhan.gamebox.domain.game.blackjack.core.person;

import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.HandStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>An abstract super class for two roles in Blackjack: dealerView and player</p>
 */
public abstract class BlackjackPerson
{
    /**
     * <p>This parameter-less constructor initialize the role.</p>
     * <p>This ensures that the role has at least one nonnull hand, even though there is nothing in it yet.</p>
     */
    public BlackjackPerson(){
        hands = new ArrayList<>();
        hands.add(new BlackjackHand());
        maxHandsSize = 4;
    }

    public BlackjackPerson(int target, BigDecimal bet){
        hands = new ArrayList<>();
        hands.add(new BlackjackHand(target, bet));
        maxHandsSize = 4;
    }

    public BlackjackPerson(
        List<BlackjackHand> hands,
        int currentHandIndex,
        int MAX_HANDS_SIZE
    ){
        this.hands = hands;
        this.currentHandIndex = currentHandIndex;
        this.maxHandsSize = MAX_HANDS_SIZE;
    }

    protected List<BlackjackHand> hands;
    protected int currentHandIndex = 0;

    protected int maxHandsSize;

    public void setHands(List<BlackjackHand> hands){
        this.hands = hands;
    }

    /**
     * <p>A Copy</p>
     * @return List >BlackjackHand<
     */
    public List<BlackjackHand> getHands(){
        return new ArrayList<>(hands);
    }

    public void setCurrentHandIndex(int currentHandIndex) {
        this.currentHandIndex = currentHandIndex;
    }

    public void setMaxHandsSize(int maxHandsSize) {
        this.maxHandsSize = maxHandsSize;
    }

    public int getMaxHandsSize() {
        return maxHandsSize;
    }

    /**
     * <p>Not Copy!</p>
     * @return BlackjackHand
     */
    public BlackjackHand getHand(int index) {
        return hands.get(index);
    }

    public void resetCurrentHand(){
        currentHandIndex = 0;
    }

    public int getCurrentHandIndex() { return currentHandIndex; }

    /**
     * <p>Not Copy!</p>
     * @return BlackjackHand
     */
    public BlackjackHand getCurrentHand(){ return hands.get(currentHandIndex); }

    /**
     * <p>If you use this method, you shall not forget to invoke method <span style="color:white">resetCurrentHand</span> at last.</p>
     */
    public void nextHand(){
        currentHandIndex++;
    }

    public boolean hasNextHand(){
        return currentHandIndex < hands.size()-1;
    }

    public void addCard(PokerCard card){
        getCurrentHand().addCard(card);
    }

    public void addCard(int handIndex, PokerCard card){
        hands.get(handIndex).addCard(card);
    }

    public void addHand(BlackjackHand hand){
        hands.add(hand);
    }

    public void addHand(int index, BlackjackHand hand){
        hands.add(index, hand);
    }

    public HandStatus getCurrentHandStatus(){
        return getCurrentHand().getHandStatus();
    }

    public void updateCurrentHandStatus(){
        getCurrentHand().updateHandStatus();
    }

    public void setCurrentHandStatus(HandStatus handStatus){
        getCurrentHand().setHandStatus(handStatus);
    }

    public boolean haveBlackjack(){
        return getCurrentHand().getHandStatus() == HandStatus.BLACKJACK;
    }

    public boolean canAddHand(){
        return hands.size() < maxHandsSize;
    }
}
