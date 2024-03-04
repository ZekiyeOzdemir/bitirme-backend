package com.project.reklamAi.business.abstracts;

import com.project.reklamAi.business.responses.AuthenticationResponse;
import com.project.reklamAi.business.responses.UserResponse;

public interface UserService {
    public UserResponse updateUsername(String userId, String newUsername);

    public AuthenticationResponse updateEmail(String userId, String newEmail);
}
