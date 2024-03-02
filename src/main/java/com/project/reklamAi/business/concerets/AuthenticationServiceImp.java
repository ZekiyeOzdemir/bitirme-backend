package com.project.reklamAi.business.concerets;

import com.project.reklamAi.business.abstracts.AuthenticationService;
import com.project.reklamAi.business.requests.AuthenticationRequest;
import com.project.reklamAi.business.requests.RegisterRequest;
import com.project.reklamAi.business.responses.AuthenticationResponse;
import com.project.reklamAi.dataAccess.UserRepository;
import com.project.reklamAi.entities.Role;
import com.project.reklamAi.entities.User;
import com.project.reklamAi.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .uploadedAudios(request.getUploadedAudios())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) { //login
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
