package com.project.reklamAi.webApi;

import com.project.reklamAi.business.abstracts.UserService;
import com.project.reklamAi.business.requests.UserEmailRequest;
import com.project.reklamAi.business.requests.UserRequest;
import com.project.reklamAi.business.responses.UserEmailResponse;
import com.project.reklamAi.business.responses.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @PutMapping("/{userId}")
    @CrossOrigin(origins = { "*" })
    public ResponseEntity<?> updateUsername(@RequestBody UserRequest userRequest) {
        try{
            //userService.updateUsername(userRequest.getUserId(), userRequest.getNewUserName());
            //return ResponseEntity.ok(new UserResponse("Kullanıcı adı güncellendi"));
            return ResponseEntity.ok(userService.updateUsername(userRequest.getUserId(), userRequest.getNewUserName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponse("Kullanıcı adı güncellenemedi, hata oluştu"));
        }
    }

    @PutMapping("/email/{userId}")
    @CrossOrigin(origins = { "*" })
    public ResponseEntity<?> updateEmail(@RequestBody UserEmailRequest userEmailRequest) {
        try{
            return ResponseEntity.ok(userService.updateEmail(userEmailRequest.getUserId(), userEmailRequest.getNewEmail()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserEmailResponse("Email güncellenemedi, hata oluştu"));
        }
    }
}
