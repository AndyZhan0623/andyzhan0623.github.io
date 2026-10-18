package andyzhan.gamebox.controller.gamecontroller;

import andyzhan.gamebox.domain.game.GameChoice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import andyzhan.gamebox.service.gameservice.GameService;

import java.util.EnumSet;

@RestController
public class GameController
{
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    private final GameService gameService;

    @GetMapping("/gameChoices")
    public EnumSet<GameChoice> getGameChoices()
    {
        return gameService.getGameChoices();
    }

}
