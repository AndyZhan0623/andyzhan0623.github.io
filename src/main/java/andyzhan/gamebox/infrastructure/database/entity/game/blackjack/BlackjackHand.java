package andyzhan.gamebox.infrastructure.database.entity.game.blackjack;

import andyzhan.gamebox.domain.game.blackjack.core.HandStatus;
import andyzhan.gamebox.infrastructure.database.entity.game.Hand;
import andyzhan.gamebox.infrastructure.database.entity.game.card.PokerCard;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;

@Entity
@Table
public class BlackjackHand extends Hand<PokerCard>
{
    public BlackjackHand(){}

    public BlackjackHand(
        BigDecimal bet,
        int target,
        boolean hasActed,
        HandStatus status
    ){
        this.bet = bet;
        this.target = target;
        this.hasActed = hasActed;
        this.status = status;
    }

    @Column(scale = 2) private BigDecimal bet;

    @Column private int target;
    @Column private boolean hasActed;

    @Column
    @Enumerated(EnumType.STRING)
    private HandStatus status;

    public BigDecimal getBet() {
        return bet;
    }

    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isHasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }

    public HandStatus getStatus() {
        return status;
    }

    public void setStatus(HandStatus status) {
        this.status = status;
    }
}
