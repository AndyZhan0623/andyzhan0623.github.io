package andyzhan.gamebox.infrastructure.database.entity.game.blackjack;

import andyzhan.gamebox.domain.game.blackjack.core.GamePhase;
import andyzhan.gamebox.infrastructure.database.entity.LongEntity;
import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackPlayer;
import andyzhan.gamebox.infrastructure.database.entity.game.blackjack.person.BlackjackDealer;
import andyzhan.gamebox.infrastructure.database.entity.game.card.PokerCard;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import java.util.List;

@Table
@Entity
public class GameContext extends LongEntity
{
    @OneToMany
    @JoinColumn( name = "game_context_id" )
    private List<BlackjackPlayer> players;

    @OneToOne
    private BlackjackDealer dealer;

    @OneToMany
    @JoinColumn( name = "game_context_id" )
    private List<PokerCard> deck;

    @Column private int setAmount;
    @Column private int currentPlayerIndex;

    @Column
    @Enumerated(EnumType.STRING)
    private GamePhase phase;

    public List<BlackjackPlayer> getPlayers() {
        return players;
    }
    public void setPlayers(List<BlackjackPlayer> players) {
        this.players = players;
    }
    public BlackjackDealer getDealer() {
        return dealer;
    }
    public void setDealer(BlackjackDealer dealer) {
        this.dealer = dealer;
    }
    public List<PokerCard> getDeck() {
        return deck;
    }
    public void setDeck(List<PokerCard> deck) {
        this.deck = deck;
    }
    public int getSetAmount() {
        return setAmount;
    }
    public void setSetAmount(int setAmount) {
        this.setAmount = setAmount;
    }
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    public GamePhase getPhase() {
        return phase;
    }
    public void setPhase(GamePhase phase) {
        this.phase = phase;
    }
}
