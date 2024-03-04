package com.project.reklamAi.business.concerets;

import com.project.reklamAi.business.abstracts.UserService;
import com.project.reklamAi.business.responses.AuthenticationResponse;
import com.project.reklamAi.business.responses.UserResponse;
import com.project.reklamAi.dataAccess.UserRepository;
import com.project.reklamAi.entities.Role;
import com.project.reklamAi.entities.User;
import com.project.reklamAi.security.JWTService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  JWTService jwtService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserResponse updateUsername(String userId, String newUsername) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı, ID: " + userId));
            user.setUsername(newUsername);
            userRepository.save(user);
            User newUser = userRepository.findById(userId).orElseThrow();

            var userTokenized = User.builder()
                    .id(newUser.getId())
                    .username(newUser.getUsername())
                    .email(newUser.getEmail())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .uploadedAudios(newUser.getUploadedAudios())
                    .role(Role.USER)
                    .build();
            var jwtToken =jwtService.generateToken(userTokenized);
            return UserResponse.builder()
                    .token(jwtToken)
                    .message("Güncellendi")
                    .build();
        } catch (Exception e) {
            return UserResponse.builder()
                    .message("Kullanıcı adı güncellenemedi, hata oluştu: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public AuthenticationResponse updateEmail(String userId, String newEmail) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı, ID: " + userId));
        user.setEmail(newEmail);
        userRepository.save(user);

        User newUser = userRepository.findById(userId).orElseThrow();

        var userTokenized = User.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .uploadedAudios(newUser.getUploadedAudios())
                .role(Role.USER)
                .build();
        var jwtToken =jwtService.generateToken(userTokenized);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
