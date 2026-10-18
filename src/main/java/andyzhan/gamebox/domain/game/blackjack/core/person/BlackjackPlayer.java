package andyzhan.gamebox.domain.game.blackjack.core.person;

import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.HandStatus;
import andyzhan.gamebox.infrastructure.database.entity.User;
import andyzhan.gamebox.infrastructure.database.id.UserId;

import java.math.BigDecimal;

/**
 * <h1>#Symbol of a Player in Blackjack</h1>
 */
public class BlackjackPlayer extends BlackjackPerson
{
    /**
     * <p>For Test Use</p>
     */
    public BlackjackPlayer(){}

    /**
     * <p>Main Constructor</p>
     *
     * @param user User
     */
    public BlackjackPlayer(
            int target,
            BigDecimal bet,
            User user
    ){
        super(target, bet);
        this.chip = user.getBalance();
    }

    /**
     * <p>Copy Constructor</p>
     * @param player BlackjackPlayer
     */
    public BlackjackPlayer(BlackjackPlayer player){
        this.chip = player.chip;
    }

    /**
     * <p>A constructor created for the mapper</p>
     * @param chip chip
     */
    public BlackjackPlayer(BigDecimal chip){
        this.chip = chip;
    }

    private BigDecimal chip;

    public BigDecimal getChip() {
        return chip;
    }
    public void setChip(BigDecimal chip) {
        if(chip.compareTo(BigDecimal.ZERO) < 0){
            System.out.print("\nGiven chip is less than 0\n");
            return;
        }

        this.chip = chip;
    }

    public void setCurrentBet(BigDecimal bet) {
        if(bet.compareTo(chip) > 0){
            System.out.print("\nGiven bet is greater than chip");
            return;
        }
        getCurrentHand().setBet(bet);
    }
    public BigDecimal getCurrentBet() { return getCurrentHand().getBet(); }

    public boolean hasChipNotLessThanBet(){
        return chip.compareTo(getCurrentBet()) >= 0;
    }

    public void win(BigDecimal num){
        chip = chip.add(num);
    }
    public void lose(BigDecimal num){
        chip = chip.subtract(num);
    }

    public BlackjackHand getFirstHand(){
        return hands.get(0);
    }

    public HandStatus getFirstHandStatus(){
        return getFirstHand().getHandStatus();
    }

    public boolean canAct(){
        if(haveBlackjack()) return false;

        for(BlackjackHand hand : hands){
            if(hand.getHandStatus() == HandStatus.PLAYING) return true;
        }

        return false;
    }


}
