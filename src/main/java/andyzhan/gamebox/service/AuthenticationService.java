package andyzhan.gamebox.service;

import andyzhan.gamebox.infrastructure.database.entity.RefreshTokenEntity;
import andyzhan.gamebox.infrastructure.database.entity.User;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import andyzhan.gamebox.infrastructure.database.repository.RefreshTokenEntityRepository;
import andyzhan.gamebox.infrastructure.database.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static andyzhan.gamebox.service.JwtService.generateRefreshToken;
import static andyzhan.gamebox.service.JwtService.generateToken;

import static andyzhan.gamebox.infrastructure.util.CookieUtil.refreshTokenCookie;

@Service
public class AuthenticationService
{
    public AuthenticationService(
            UserRepository userRepository,
            RefreshTokenEntityRepository refreshTokenEntityRepository
    ){
        this.userRepository = userRepository;
        this.refreshTokenEntityRepository = refreshTokenEntityRepository;
    }

    private final UserRepository userRepository;
    private final RefreshTokenEntityRepository refreshTokenEntityRepository;

    private String proceed(User user){
        UserId userId = user.getId();
        String token = generateRefreshToken(userId);

        if(!refreshTokenEntityRepository.existsByUserId(user.getId())){
            RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
            refreshTokenEntity.setUserId(userId);
            refreshTokenEntity.setRefreshToken(token);
            refreshTokenEntityRepository.save(refreshTokenEntity);
            return token;
        }

        RefreshTokenEntity refreshTokenEntity = refreshTokenEntityRepository.findByUserId(userId).orElse(null);

        if(refreshTokenEntity == null){
            System.out.println("Unexpected Error");
            return null;
        }

        refreshTokenEntity.setRefreshToken(token);
        return token;
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> login(String username, String password, HttpServletResponse response){
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(Map.of("error", "Username not found"));
        if(!user.getPassword().equals(password))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(Map.of("error", "Wrong password"));

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                refreshTokenCookie(proceed(user)).toString()
        );

        return ResponseEntity.ok(Map.of("accessToken", generateToken(user.getId())));
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> register(String username, String password, HttpServletResponse response){
        if(userRepository.existsByUsername(username))
            return ResponseEntity.status(HttpStatus.CONFLICT).
                    body(Map.of("error", "The username already exists"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setBalance(new BigDecimal("100"));
        userRepository.save(user);

        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie(proceed(user)).toString());

        return ResponseEntity.ok(Map.of("accessToken", generateToken(user.getId())));
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> refresh(String token, HttpServletResponse response){
        RefreshTokenEntity refreshTokenEntity = refreshTokenEntityRepository.findByRefreshToken(token).orElse(null);

        if(refreshTokenEntity == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(Map.of(
                                "successful", false,
                            "error", "Refresh token not found"
                    ));

        UserId userId = refreshTokenEntity.getUserId();

        if(userId == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(Map.of(
                                "successful", false,
                            "unexpectedError", "Who are you?"
                    ));

        refreshTokenEntity.setRefreshToken(generateRefreshToken(userId));

        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie(refreshTokenEntity.getRefreshToken()).toString());

        return ResponseEntity.ok(Map.of(
                "successful", true,
                "accessToken", generateToken(userId)
        ));
    }
}
