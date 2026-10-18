package andyzhan.gamebox.controller.gamecontroller;

import andyzhan.gamebox.domain.game.blackjack.match.MatchCondition;
import andyzhan.gamebox.presentation.blackjack.GameStateDTO;
import andyzhan.gamebox.service.gameservice.BlackjackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BlackjackController
{
    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    private final BlackjackService blackjackService;

    private record MatchRequest(BigDecimal bet, MatchCondition condition){}

    @PostMapping("/blackjack/match")
    public GameStateDTO match(@RequestBody MatchRequest matchRequest){
        return blackjackService.match(
                matchRequest.bet(),
                matchRequest.condition()
        );
    }
}
