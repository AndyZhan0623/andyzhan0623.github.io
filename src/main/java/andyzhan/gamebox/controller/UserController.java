package andyzhan.gamebox.controller;

import andyzhan.gamebox.infrastructure.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import andyzhan.gamebox.service.UserService;

import java.util.Map;

@RestController
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/user/me/chip")
    public ResponseEntity<Map<String, Object>> getChip() {
        return userService.getChip(SecurityUtil.grabUserId());
    }

    @PostMapping("/user/me/chip/reset")
    public ResponseEntity<Map<String, Object>> resetChip(){
        return userService.resetChip(SecurityUtil.grabUserId());
    }
}