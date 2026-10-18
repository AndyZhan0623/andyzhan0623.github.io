package andyzhan.gamebox.domain.game.blackjack.core;

import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.domain.game.Deck;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.Mechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.resultmechanic.ResultMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.checkingmechanic.CheckingMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealerturnmechanic.DealerTurnMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealingmechanic.DealingMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.insurancemechanic.DefaultInsuranceMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.playingmechanic.ActionType;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.playingmechanic.PlayingMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackDealer;
import andyzhan.gamebox.domain.game.blackjack.core.person.BlackjackPlayer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BlackjackTest
{
    public static void main(String[] args){

        Mechanic mech = new Mechanic(
                new DealingMechanic(),
                new CheckingMechanic(),
                new DefaultInsuranceMechanic(),
                new PlayingMechanic(),
                new DealerTurnMechanic(),
                new ResultMechanic()
        );

        List<BlackjackPlayer> players = new ArrayList<>();
        for(int i=0;i<2;i++){
            BlackjackPlayer player = new BlackjackPlayer();
            player.setChip(new BigDecimal("100"));
            player.setCurrentBet(new BigDecimal("10"));
            players.add(player);
        }


        GameContext gc = new GameContext(
                players,
                new Deck<>(List.of(
                        new PokerCard("_", "5"),
                        new PokerCard("_", "K"),
                        new PokerCard("_", "3"),
                        new PokerCard("_", "6"),
                        new PokerCard("_", "2"),
                        new PokerCard("_", "A"),
                        new PokerCard("_", "7"),
                        new PokerCard("_", "8"),
                        new PokerCard("_", "5"),
                        new PokerCard("_", "8"),
                        new PokerCard("_", "8"),
                        new PokerCard("_", "A")
                )),
                new BlackjackDealer()
        );

        Blackjack bj = new Blackjack(gc, mech);

        bj.operate();

        bj.operate();

        bj.operate();
        bj.decideOnInsurance(true);
        bj.decideOnInsurance(false);

        bj.operate();
        bj.play(ActionType.HIT);
        bj.operate();
        bj.play(ActionType.HIT);
        bj.play(ActionType.HIT);
        bj.operate();
        bj.play(ActionType.HIT);
        bj.play(ActionType.STAND);

        bj.operate();

        bj.operate();
    }


}
