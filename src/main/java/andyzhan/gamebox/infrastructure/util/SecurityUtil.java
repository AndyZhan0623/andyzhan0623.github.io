package andyzhan.gamebox.infrastructure.util;

import andyzhan.gamebox.infrastructure.database.id.UserId;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil
{
    public static UserId grabUserId(){
        return (UserId)
                SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
