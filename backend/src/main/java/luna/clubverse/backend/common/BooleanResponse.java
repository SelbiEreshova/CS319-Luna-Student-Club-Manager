package luna.clubverse.backend.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BooleanResponse {

    private final boolean result;
    private final boolean errorExist;
    private final String errorReason;
}
