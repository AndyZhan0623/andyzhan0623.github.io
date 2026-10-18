package andyzhan.gamebox.domain.game.blackjack.core;

import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.domain.game.Deck;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;
import andyzhan.gamebox.domain.game.cardsetfactory.PokerCardSetFactory;
import andyzhan.gamebox.infrastructure.database.id.UserId;

import java.util.List;

/**
 * <h1>#Context</h1>
 * <p>A field in Blackjack, storing all the information and context the game needs.</p>
 */
public class GameContext
{
    public GameContext(){}

    /**
     * <p>Main Constructor</p>
     */
    public GameContext(
            List<BlackjackPlayer> players
    ){
        if(players == null || players.isEmpty())
            throw new IllegalArgumentException("playerViews must not be null or empty");

        if(players.size() > 6)
            throw new IllegalArgumentException("No more than 6 playerViews allowed");

        this.players = players;
        deck = new Deck<>(new PokerCardSetFactory().cardSet(this.SET_AMOUNT));
        dealer = new BlackjackDealer();
    }

    /**
     *
     */
    public GameContext(
            List<BlackjackPlayer> players,
            BlackjackDealer dealer,
            Deck<PokerCard> deck,
            GamePhase phase,
            int currentPlayerIndex
    ){
        this.players = players;
        this.deck = deck;
        this.dealer = dealer;
        this.phase = phase;
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Copy Constructor
     */
    public GameContext(GameContext gc){
        this.players = List.copyOf(gc.players);
        this.deck = new Deck<>(gc.deck);
        this.phase = gc.phase;
        this.currentPlayerIndex = gc.currentPlayerIndex;
        this.dealer = gc.dealer;
    }

    private final int SET_AMOUNT = 6;
    private final List<BlackjackPlayer> players;
    private final BlackjackDealer dealer;
    private final Deck<PokerCard> deck;
    private GamePhase phase = GamePhase.DEALING;
    private int currentPlayerIndex = 0;

    public int getSET_AMOUNT() {
        return SET_AMOUNT;
    }
    public List<BlackjackPlayer> getPlayers() {
        return players;
    }
    public BlackjackDealer getDealer() {
        return dealer;
    }
    public Deck<PokerCard> getDeck() {
        return deck;
    }

    public GamePhase getPhase() {
        return phase;
    }
    public void setPhase(GamePhase phase) {
        this.phase = phase;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void resetCurrentPlayer() {
        currentPlayerIndex = 0;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * <p>If you use this method, you shall not forget to invoke method <span style="color:white">resetCurrentPlayer</span> afterward.</p>
     */
    public void nextPlayer(){
        currentPlayerIndex++;
    }

    public BlackjackPlayer getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }

    public boolean hasNextPlayer(){
        return currentPlayerIndex < players.size() - 1;
    }

    /**
     * <p>This method return a copy of a player to front-end according to the given value.</p>
     * <p>If the player is not found, this method returns null.</p>
     * @param userId value
     * @return BlackjackPlayer
     */
//    public BlackjackPlayer getPlayer(UserId userId){
//        for(BlackjackPlayer player : players) {
//            if(player.getUserId().equals(userId)) {
//                return new BlackjackPlayer(player);
//            }
//        }
//
//        System.out.print("\nPlayer " + userId + " not found.");
//        return null;
//    }
}
