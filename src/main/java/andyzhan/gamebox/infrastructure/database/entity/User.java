package andyzhan.gamebox.infrastructure.database.entity;

import andyzhan.gamebox.infrastructure.database.converter.UserIdConverter;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Convert;
import jakarta.persistence.Column;

import java.math.BigDecimal;

@Entity
@Table
public class User
{
    public User(){}

    public User(
        String username,
        String password,
        BigDecimal balance
    ){
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    @Id
    @Convert(converter = UserIdConverter.class)
    private UserId id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(scale = 2)
    private BigDecimal balance;

//    @Column
//    private BigDecimal bet;



    public UserId getId() {
        return id;
    }
    public void setId(UserId id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal chip) {
        if(chip.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("chip is negative");
            return;
        }

        this.balance = chip;
    }
//    public BigDecimal getBet() {
//        return bet;
//    }
//    public void setBet(BigDecimal bet) {
//        this.bet = bet;
//    }
    public void resetBalance(){
        setBalance(new BigDecimal("100"));
    }
}
