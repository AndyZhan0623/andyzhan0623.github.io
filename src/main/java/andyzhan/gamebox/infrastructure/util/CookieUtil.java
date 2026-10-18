package andyzhan.gamebox.infrastructure.util;

import org.springframework.http.ResponseCookie;

import java.time.Duration;

public class CookieUtil
{
    public static ResponseCookie refreshTokenCookie(String token){
        return ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .secure(false)
                .path("/refresh")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .build();
    }
}
