package andyzhan.gamebox.service.gameservice;

import andyzhan.gamebox.domain.game.GameChoice;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class GameService
{
    public EnumSet<GameChoice> getGameChoices()
    {
        return EnumSet.allOf(GameChoice.class);
    }
}
