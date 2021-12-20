package luna.clubverse.backend.club.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class UploadPhotoRequest {

    private String file;
}
