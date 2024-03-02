package com.project.reklamAi.business.abstracts;

import com.project.reklamAi.business.responses.AuthenticationResponse;

public interface UserService {
    public AuthenticationResponse updateUsername(String userId, String newUsername);

    public AuthenticationResponse updateEmail(String userId, String newEmail);
}
