package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.user.controller.request.LoginRequest;
import luna.clubverse.backend.user.controller.request.SignupRequest;
import luna.clubverse.backend.user.controller.response.LoginResponse;
import luna.clubverse.backend.user.service.AuthenticationService;
import luna.clubverse.backend.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login-request")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.login(loginRequest);
        //return authenticationService.login(loginRequest);
    }

    @PutMapping("/signup")
    public MessageResponse signupStudent(@Valid @RequestBody SignupRequest request) {
        return  authenticationService.signupStudent(request.toStudent());
    }

}
