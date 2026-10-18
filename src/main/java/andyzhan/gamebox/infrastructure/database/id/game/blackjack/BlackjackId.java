package andyzhan.gamebox.infrastructure.database.id.game.blackjack;

import andyzhan.gamebox.infrastructure.database.id.UserId;

public record BlackjackId(Long value) {

    public static UserId fromString(String id){
        return new UserId(Long.parseLong(id));
    }

}
