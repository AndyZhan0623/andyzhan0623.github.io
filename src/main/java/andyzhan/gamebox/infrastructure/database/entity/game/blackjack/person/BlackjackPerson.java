package andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person;

import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.BlackjackHand;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

import java.util.List;

/**
 * I do not assert the id type of this branch because the dealer may merely use Long,
 * while the player may use its own object of value as the id.
 */
@MappedSuperclass
public abstract class BlackjackPerson
{
    public BlackjackPerson(){}
    public BlackjackPerson(
            List<BlackjackHand> hands,
            int currentHandIndex,
            int MAX_HANDS_SIZE
    ){
        this.hands = hands;
        this.currentHandIndex = currentHandIndex;
        this.maxHandsSize = MAX_HANDS_SIZE;
    }

    @OneToMany
    @JoinColumn( name = "person_id" )
    protected List<BlackjackHand> hands;

    @Column protected int currentHandIndex;
    @Column protected int maxHandsSize;

    public List<BlackjackHand> getHands() {
        return hands;
    }

    public void setHands(List<BlackjackHand> hands) {
        this.hands = hands;
    }

    public int getCurrentHandIndex() {
        return currentHandIndex;
    }

    public void setCurrentHandIndex(int currentHandIndex) {
        this.currentHandIndex = currentHandIndex;
    }

    public int getMaxHandsSize() {
        return maxHandsSize;
    }

    public void setMaxHandsSize(int maxHandsSize) {
        this.maxHandsSize = maxHandsSize;
    }
}
