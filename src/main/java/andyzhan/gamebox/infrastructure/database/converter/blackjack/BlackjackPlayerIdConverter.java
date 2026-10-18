package andyzhan.gamebox.infrastructure.database.converter.blackjack;

import andyzhan.gamebox.infrastructure.database.id.game.blackjack.BlackjackPlayerId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BlackjackPlayerIdConverter implements AttributeConverter<BlackjackPlayerId, Long>
{
    @Override
    public Long convertToDatabaseColumn(BlackjackPlayerId playerId) {
        return playerId == null ? null : playerId.value();
    }

    @Override
    public BlackjackPlayerId convertToEntityAttribute(Long playerId) {
        return playerId == null ? null : new BlackjackPlayerId(playerId);
    }
}
