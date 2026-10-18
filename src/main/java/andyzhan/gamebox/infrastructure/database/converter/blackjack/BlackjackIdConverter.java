package andyzhan.gamebox.infrastructure.database.converter.blackjack;

import andyzhan.gamebox.infrastructure.database.id.game.blackjack.BlackjackId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BlackjackIdConverter implements AttributeConverter<BlackjackId, Long>
{
    @Override
    public Long convertToDatabaseColumn(BlackjackId blackjackId) {
        return blackjackId == null ? null : blackjackId.value();
    }

    @Override
    public BlackjackId convertToEntityAttribute(Long blackjackId) {
        return blackjackId == null ? null : new BlackjackId(blackjackId);
    }
}
