package luna.clubverse.backend.user.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class ChangePasswordRequest {

    @NotEmpty
    private final String oldPassword;

    @NotEmpty
    private final String newPassword;
}
