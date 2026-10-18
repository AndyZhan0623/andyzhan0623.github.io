package andyzhan.gamebox.domain.game.blackjack.core.mechanic;

import andyzhan.gamebox.domain.game.blackjack.core.mechanic.resultmechanic.ResultMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.checkingmechanic.CheckingMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealerturnmechanic.DealerTurnMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealingmechanic.DealingMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.insurancemechanic.InsuranceMechanic;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.playingmechanic.PlayingMechanic;

/**
 * <h1># Mechanic</h1>
 * <p>A field in Blackjack, storing all the mechanics that would be used.</p>
 * <p>Since the structure, or the fields, in Mechanic is fixed,</p>
 * <p>the implementation of the player not adopting a certain mechanic is an empty implementation,</p>
 * <p>as seen in InsuranceMechanic.</p>
 * <p>Although I do not come up with other varieties of other mechanic,</p>
 * <p>I still hold this structure for more possibilities in the future.</p>
 * <h3>The main responsibility of these mechanics is to update the game phase in the game context, another field in Blackjack</h3>
 * <h3>Therefore, it is necessary to have empty implementation simply updating them.</h3>
 */
public record Mechanic(
    DealingMechanic dealMech,
    CheckingMechanic chekMech,
    InsuranceMechanic insrMech,
    PlayingMechanic playMech,
    DealerTurnMechanic dTrnMech,
    ResultMechanic reslMech
){

}
