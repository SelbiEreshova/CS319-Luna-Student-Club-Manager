package luna.clubverse.backend.user.controller;

/**
import luna.clubverse.backend.user.controller.request.LoginRequest;
import luna.clubverse.backend.user.service.AuthenticationService;
import luna.clubverse.backend.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String  login(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.login(loginRequest);
    }

}
 */
