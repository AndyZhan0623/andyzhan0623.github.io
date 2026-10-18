package andyzhan.gamebox.infrastructure.database.id.game.blackjack;

public record BlackjackPlayerId(Long value) {

    public static BlackjackPlayerId fromString(String id){
        return new BlackjackPlayerId(Long.parseLong(id));
    }

}
