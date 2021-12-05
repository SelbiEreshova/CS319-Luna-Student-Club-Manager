package luna.clubverse.backend.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MessageResponse {
    private final MessageType messageType;
    private final String message;
}
