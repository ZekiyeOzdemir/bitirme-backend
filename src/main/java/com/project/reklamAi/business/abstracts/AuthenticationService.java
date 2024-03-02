package com.project.reklamAi.business.abstracts;

import com.project.reklamAi.business.requests.AuthenticationRequest;
import com.project.reklamAi.business.requests.RegisterRequest;
import com.project.reklamAi.business.responses.AuthenticationResponse;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
