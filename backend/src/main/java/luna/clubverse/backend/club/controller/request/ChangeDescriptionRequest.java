package luna.clubverse.backend.club.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@ToString
public class ChangeDescriptionRequest {
    @NotBlank(message = "newDescription cannot be empty")
    private String newDescription;
}
