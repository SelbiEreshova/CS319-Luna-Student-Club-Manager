package luna.clubverse.backend.user.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import luna.clubverse.backend.common.MessageType;

@RequiredArgsConstructor
@Getter
public class LoginResponse {
    private final MessageType messageType;
    private final String message;
    private final Long userId;
}
