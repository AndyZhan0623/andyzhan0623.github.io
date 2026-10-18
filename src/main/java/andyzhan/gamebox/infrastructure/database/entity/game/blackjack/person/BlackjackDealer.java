package andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table
public class BlackjackDealer extends BlackjackPerson
{
    public BlackjackDealer(){}
    public BlackjackDealer(int standLimit){
        this.standLimit = standLimit;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column private int standLimit;

    public int getStandLimit() {
        return standLimit;
    }

    public void setStandLimit(int standLimit) {
        this.standLimit = standLimit;
    }
}
