package andyzhan.gamebox.service;

import andyzhan.gamebox.infrastructure.database.entity.User;
import andyzhan.gamebox.infrastructure.database.id.UserId;
import andyzhan.gamebox.infrastructure.database.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class UserService
{
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public ResponseEntity<Map<String, Object>> getChip(UserId userId){
        User user = userRepository.findById(userId.value()).orElse(null);

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","User not found"));

        return ResponseEntity.ok(Map.of("chip", user.getChip()));
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> resetChip(UserId userId){
        User user = userRepository.findById(userId.value()).orElse(null);

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","User not found"));

        user.resetChip();

        return ResponseEntity.ok(Map.of("chip", user.getChip()));
    }
}
