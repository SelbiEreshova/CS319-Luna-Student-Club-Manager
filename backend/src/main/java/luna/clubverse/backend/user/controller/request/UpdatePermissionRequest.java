package luna.clubverse.backend.user.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class UpdatePermissionRequest {

    @NotNull
    private final Long clubId;
    @NotNull
    private final Long memberId;
    private final String memberRole;
    private final List<Boolean> memberPermissions;
}
