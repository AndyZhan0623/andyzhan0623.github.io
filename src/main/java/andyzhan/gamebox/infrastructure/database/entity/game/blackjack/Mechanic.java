package andyzhan.gamebox.infrastructure.database.entity.game.blackjack;

import andyzhan.gamebox.infrastructure.database.entity.LongEntity;
import andyzhan.gamebox.infrastructure.database.mapper.game.blackjack.mechanic_types.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table
public class Mechanic extends LongEntity
{
    public Mechanic(){}
    public Mechanic(
            DealingMechanicType dealing,
            CheckingMechanicType checking,
            InsuranceMechanicType insurance,
            PlayingMechanicType playing,
            DealerTurnMechanicType dealerTurn,
            ResultMechanicType result
    ){
        dealingMechanicType = dealing;
        checkingMechanicType = checking;
        insuranceMechanicType = insurance;
        playingMechanicType = playing;
        dealerTurnMechanicType = dealerTurn;
        resultMechanicType = result;
    }

    @Column @Enumerated(EnumType.STRING) private DealingMechanicType dealingMechanicType;
    @Column @Enumerated(EnumType.STRING) private CheckingMechanicType checkingMechanicType;
    @Column @Enumerated(EnumType.STRING) private InsuranceMechanicType insuranceMechanicType;
    @Column @Enumerated(EnumType.STRING) private PlayingMechanicType playingMechanicType;
    @Column @Enumerated(EnumType.STRING) private DealerTurnMechanicType dealerTurnMechanicType;
    @Column @Enumerated(EnumType.STRING) private ResultMechanicType resultMechanicType;

    @OneToOne
    private Blackjack blackjack;

    public CheckingMechanicType getCheckingMechanicType() {
        return checkingMechanicType;
    }
    public void setCheckingMechanicType(CheckingMechanicType checkingMechanicType) {
        this.checkingMechanicType = checkingMechanicType;
    }
    public DealingMechanicType getDealingMechanicType() {
        return dealingMechanicType;
    }
    public void setDealingMechanicType(DealingMechanicType dealingMechanicType) {
        this.dealingMechanicType = dealingMechanicType;
    }
    public InsuranceMechanicType getInsuranceMechanicType() {
        return insuranceMechanicType;
    }
    public void setInsuranceMechanicType(InsuranceMechanicType insuranceMechanicType) {
        this.insuranceMechanicType = insuranceMechanicType;
    }
    public PlayingMechanicType getPlayingMechanicType() {
        return playingMechanicType;
    }
    public void setPlayingMechanicType(PlayingMechanicType playingMechanicType) {
        this.playingMechanicType = playingMechanicType;
    }
    public DealerTurnMechanicType getDealerTurnMechanicType() {
        return dealerTurnMechanicType;
    }
    public void setDealerTurnMechanicType(DealerTurnMechanicType dealerTurnMechanicType) {
        this.dealerTurnMechanicType = dealerTurnMechanicType;
    }
    public ResultMechanicType getResultMechanicType() {
        return resultMechanicType;
    }
    public void setResultMechanicType(ResultMechanicType resultMechanicType) {
        this.resultMechanicType = resultMechanicType;
    }
}
