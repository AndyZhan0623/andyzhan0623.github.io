package andyzhan.gamebox.infrastructure.database.mapper.game.blackjack;

import andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealingmechanic.*;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.checkingmechanic.*;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.insurancemechanic.*;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.playingmechanic.*;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.dealerturnmechanic.*;
import andyzhan.gamebox.domain.game.blackjack.core.mechanic.resultmechanic.*;
import andyzhan.gamebox.infrastructure.database.mapper.game.blackjack.mechanic_types.*;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Map;

public class MechanicRegistry
{
    private static final BiMap<DealingMechanicType, DealingMechanic> dealingMap = HashBiMap.create(
            Map.of(DealingMechanicType.DEALING, new DealingMechanic())
    );
    private static final BiMap<Class<? extends DealingMechanic>, DealingMechanicType> invDealingMap =
            buildInverseMap(dealingMap);

    private static final BiMap<CheckingMechanicType, CheckingMechanic> checkingMap = HashBiMap.create(
            Map.of(CheckingMechanicType.CHECKING, new CheckingMechanic())
    );
    private static final BiMap<Class<? extends CheckingMechanic>, CheckingMechanicType> invCheckingMap =
            buildInverseMap(checkingMap);

    private static final BiMap<InsuranceMechanicType, InsuranceMechanic> insuranceMap = HashBiMap.create(
            Map.of(
                    InsuranceMechanicType.DEFAULT_INSURANCE, new DefaultInsuranceMechanic(),
                    InsuranceMechanicType.NO_INSURANCE, new NoInsuranceMechanic()
            )
    );
    private static final BiMap<Class<? extends InsuranceMechanic>, InsuranceMechanicType> invInsuranceMap =
            buildInverseMap(insuranceMap);

    private static final BiMap<PlayingMechanicType, PlayingMechanic> playingMap = HashBiMap.create(
            Map.of(PlayingMechanicType.PLAYING, new PlayingMechanic())
    );
    private static final BiMap<Class<? extends PlayingMechanic>, PlayingMechanicType> invPlayingMap =
            buildInverseMap(playingMap);

    private static final BiMap<DealerTurnMechanicType, DealerTurnMechanic> dealerTurnMap = HashBiMap.create(
            Map.of(DealerTurnMechanicType.DEALER_TURN, new DealerTurnMechanic())
    );
    private static final BiMap<Class<? extends DealerTurnMechanic>, DealerTurnMechanicType> invDealerTurnMap =
            buildInverseMap(dealerTurnMap);

    private static final BiMap<ResultMechanicType, ResultMechanic> resultMap = HashBiMap.create(
            Map.of(ResultMechanicType.RESULT, new ResultMechanic())
    );
    private static final BiMap<Class<? extends ResultMechanic>, ResultMechanicType> invResultMap =
            buildInverseMap(resultMap);


    private static <V, K> BiMap<Class<? extends V>, K> buildInverseMap(BiMap<K, V> biMap){
        BiMap<Class<? extends V>, K> result = HashBiMap.create();

        BiMap<V, K> inverseMap =  biMap.inverse();
        for(V v : inverseMap.keySet()){
            result.put((Class<? extends V>) v.getClass(), inverseMap.get(v));
        }

        return result;
    }

    public static andyzhan.gamebox.domain.game.blackjack.core.mechanic.Mechanic toDomain(
            andyzhan.gamebox.infrastructure.database.entity.game.blackjack.Mechanic mechanic
    ){
        return new andyzhan.gamebox.domain.game.blackjack.core.mechanic.Mechanic(
                dealingMap.get(mechanic.getDealingMechanicType()),
                checkingMap.get(mechanic.getCheckingMechanicType()),
                insuranceMap.get(mechanic.getInsuranceMechanicType()),
                playingMap.get(mechanic.getPlayingMechanicType()),
                dealerTurnMap.get(mechanic.getDealerTurnMechanicType()),
                resultMap.get(mechanic.getResultMechanicType())
        );
    }

    public static andyzhan.gamebox.infrastructure.database.entity.game.blackjack.Mechanic toEntity(
            andyzhan.gamebox.domain.game.blackjack.core.mechanic.Mechanic mechanic
    ){
        return new andyzhan.gamebox.infrastructure.database.entity.game.blackjack.Mechanic(
                invDealingMap.get(mechanic.dealMech().getClass()),
                invCheckingMap.get(mechanic.chekMech().getClass()),
                invInsuranceMap.get(mechanic.insrMech().getClass()),
                invPlayingMap.get(mechanic.playMech().getClass()),
                invDealerTurnMap.get(mechanic.dTrnMech().getClass()),
                invResultMap.get(mechanic.reslMech().getClass())
        );
    }
}
