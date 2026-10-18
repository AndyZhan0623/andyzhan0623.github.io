package andyzhan.gamebox.infrastructure.database.entity;

import andyzhan.gamebox.infrastructure.database.converter.UserIdConverter;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UserRelatedEntity extends LongEntity
{
    @Column
    @Convert(converter = UserIdConverter.class)
    protected UserId userId;

    public UserId getUserId() {
        return userId;
    }
    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
