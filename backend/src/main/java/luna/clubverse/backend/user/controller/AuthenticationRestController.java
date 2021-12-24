package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.club.controller.request.AddClubRequest;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.user.controller.request.ChangePasswordRequest;
import luna.clubverse.backend.user.controller.request.LoginRequest;
import luna.clubverse.backend.user.controller.request.SignupStudentRequest;
import luna.clubverse.backend.user.controller.request.UpdatePermissionRequest;
import luna.clubverse.backend.user.controller.response.LoginResponse;
import luna.clubverse.backend.user.service.AuthenticationService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public MessageResponse signupStudent(@Valid @RequestBody SignupStudentRequest request) {
        return  authenticationService.signupStudent(request.toStudent());
    }

    @PostMapping("/change_password")
    public MessageResponse changePassword(@Valid @RequestBody ChangePasswordRequest request) {

        return authenticationService.changePassword(request);
        //return authenticationService.login(loginRequest);
    }

    //@PreAuthorize("hasAuthority('DIRECTOR')")
    @PutMapping("/student/updatePermission")
    public MessageResponse updatePermission(@Valid @RequestBody UpdatePermissionRequest request) {

        return authenticationService.updatePermission(request);
        //return authenticationService.login(loginRequest);
    }

}
