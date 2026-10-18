package andyzhan.gamebox.service.gameservice;

import andyzhan.gamebox.domain.game.Deck;
import andyzhan.gamebox.domain.game.blackjack.core.BlackjackHand;
import andyzhan.gamebox.domain.game.blackjack.match.BlackjackRoom;
import andyzhan.gamebox.domain.game.blackjack.match.MatchCondition;
import andyzhan.gamebox.domain.game.blackjack.core.Blackjack;
import andyzhan.gamebox.domain.game.blackjack.core.GameContext;
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
import andyzhan.gamebox.domain.game.card.PokerCard;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import andyzhan.gamebox.presentation.blackjack.GameStateDTO;
import andyzhan.gamebox.infrastructure.database.entity.User;
import andyzhan.gamebox.infrastructure.database.repository.UserRepository;
import andyzhan.gamebox.infrastructure.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BlackjackService
{
    public BlackjackService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;
    private BetService<BlackjackHand> betService;

    private final Map<MatchCondition, BlackjackRoom> MATCH_MAP = new ConcurrentHashMap<>();
    private final Map<User, BlackjackRoom> USER_MAP = new ConcurrentHashMap<>();

    public GameStateDTO match(BigDecimal bet, MatchCondition requestCondition){
        User user = userRepository.findById(SecurityUtil.grabUserId().value()).orElse(null);
        BlackjackRoom room = null;

        putRoom(requestCondition, user, room);

        if(room.getUsers().size()<requestCondition.playerCount()) return null;
        MATCH_MAP.remove(requestCondition);



    }

    private void putRoom(MatchCondition requestCondition, User user, BlackjackRoom room){

        if(!MATCH_MAP.containsKey(requestCondition)){
            room = new BlackjackRoom();
            room.addUser(user);
            MATCH_MAP.put(requestCondition, room);
        }
        else{
            room = MATCH_MAP.get(requestCondition);
            room.addUser(user);
        }

        USER_MAP.put(user, room);
    }

    private GameStateDTO launch(BlackjackRoom room){
        List<BlackjackPlayer> players = new ArrayList<>();

        Blackjack bj = new Blackjack();
    }

    public GameStateDTO matchTest(){
        Blackjack bj = new Blackjack(
                new GameContext(
                        List.of(
                                new BlackjackPlayer(21, new BigDecimal("10"), new User("test", "test", new BigDecimal("100"))),
                                new BlackjackPlayer(21, new BigDecimal("10"),  new User("test", "test", new BigDecimal("100"))),
                                new BlackjackPlayer(21, new BigDecimal("10"),  new User("test", "test", new BigDecimal("100"))),
                                new BlackjackPlayer(21, new BigDecimal("10"),  new User("test", "test", new BigDecimal("100")))
                        ),
                        new Deck<>(List.of(
                                new PokerCard("♠", "5"),
                                new PokerCard("♠", "K"),
                                new PokerCard("♠", "3"),
                                new PokerCard("♠", "6"),
                                new PokerCard("♠", "2"),
                                new PokerCard("♠", "A"),
                                new PokerCard("♠", "7"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "5"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8"),
                                new PokerCard("♠", "8")
                        )),
                        new BlackjackDealer()
                ),
                new Mechanic(
                        new DealingMechanic(),
                        new CheckingMechanic(),
                        new DefaultInsuranceMechanic(),
                        new PlayingMechanic(),
                        new DealerTurnMechanic(),
                        new ResultMechanic()
                )
        );

        bj.deal();
        bj.checkBlackjack();
        bj.play(ActionType.SPLIT);
        bj.play(ActionType.SPLIT);
        bj.play(ActionType.SPLIT);
        bj.play(ActionType.STAND);

        return GameStateDTO.of(
                new UserId(0L),
                List.of("Alice", "Bob", "Cynthia", "Duck"),
                bj.getGc(),
                EnumSet.allOf(ActionType.class),
                false
        );
    }

//    public GameStateDTO buyInsurance(){
//
//    }
}
