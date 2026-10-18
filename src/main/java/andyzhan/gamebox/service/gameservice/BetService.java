package andyzhan.gamebox.service.gameservice;

import andyzhan.gamebox.domain.game.BetCarrier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BetService<T extends BetCarrier>
{
    public boolean betAllSet(List<T> carriers){
        for(T carrier : carriers){
            if(carrier.getBet()==null) return false;
        }
        return true;
    }

    private void betSetUp(BigDecimal bet, T carrier){
        carrier.setBet(bet);
    }
}
