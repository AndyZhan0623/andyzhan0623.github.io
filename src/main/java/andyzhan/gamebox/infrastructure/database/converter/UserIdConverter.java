package andyzhan.gamebox.infrastructure.database.converter;

import andyzhan.gamebox.infrastructure.database.id.UserId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserIdConverter implements AttributeConverter<UserId, Long>
{
    @Override
    public Long convertToDatabaseColumn(UserId id){
        return id == null ? null : id.value();
    }

    @Override
    public UserId convertToEntityAttribute(Long id){
        return id == null ? null : new UserId(id);
    }
}
