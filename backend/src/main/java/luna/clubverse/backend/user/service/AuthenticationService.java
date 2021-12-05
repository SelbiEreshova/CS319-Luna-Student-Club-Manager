package luna.clubverse.backend.user.service;

import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.security.JwtUtil;
import luna.clubverse.backend.user.controller.request.LoginRequest;
import luna.clubverse.backend.user.controller.response.LoginResponse;
import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Value("${spring.jwt.secret-key}")
    private String key;

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        try{
            Authentication authenticatedToken = authenticationManager.authenticate(token);
            String jwt = JwtUtil.generateToken(authenticatedToken, key);

            User userFromDb = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow();

            return new LoginResponse(MessageType.SUCCESS, "Bearer " + jwt, userFromDb.getId() );

            //return ("Bearer " + jwt);
        } catch (AuthenticationException ex) {
            return null;
        }

    }
}
