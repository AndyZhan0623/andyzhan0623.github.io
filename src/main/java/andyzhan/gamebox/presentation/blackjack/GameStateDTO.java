package andyzhan.gamebox.presentation.blackjack;

import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.playingmechanic.ActionType;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;
import andyzhan.gamebox.presentation.blackjack.person.DealerView;
import andyzhan.gamebox.presentation.blackjack.person.PlayerView;
import andyzhan.gamebox.infrastructure.database.id.UserId;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public record GameStateDTO(
        UserId userId,
        int seatIndex,
        List<PlayerView> playerViews,
        DealerView dealerView,
        PlayerView currentPlayerView,
        EnumSet<ActionType> availableActions,
        boolean insurance
){
    /**
     * <p>Actually, I should use a list of users instead of the list of usernames.</p>
     * <p>Maybe I will construct a class containing all the info the front-end may need as a layer later</p>
     * @param userId UserId
     * @param usernames List(String)
     * @param gc GameContext
     * @param actions EnumSet(ActionType)
     * @return GameStateDTO
     */
    public static GameStateDTO of(
            UserId userId,
            List<String> usernames,
            GameContext gc,
            EnumSet<ActionType> actions,
            boolean insurance
    ){
        int seatIndex = -1;

        List<BlackjackPlayer> players = gc.getPlayers();
        List<PlayerView> playerViews = new ArrayList<>();
        for (int i=0;i<players.size();i++) {
            List<HandView> hands = new ArrayList<>();
            BlackjackPlayer player = players.get(i);

            if(player.getUserId().equals(userId))
                seatIndex = i;

            for (BlackjackHand hand : player.getHands())
                hands.add(HandView.from(hand));


            playerViews.add(new PlayerView(
                    player.getUserId(),
                    usernames.get(i),
                    hands,
                    player.getCurrentHandIndex(),
                    player.getChip()
            ));
        }

        return new GameStateDTO(
                userId,
                seatIndex,
                playerViews,
                new DealerView(HandView.from(gc.getDealer().getHand())),
                playerViews.get(gc.getCurrentPlayerIndex()),
                actions,
                insurance
        );
    }
}
