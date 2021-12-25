package luna.clubverse.backend.user.controller.response;

import lombok.Getter;
import luna.clubverse.backend.filledform.entity.FilledForm;
import luna.clubverse.backend.filledform.enums.FormStatus;

@Getter
public class ApplicationListQueryResponse {
    private final Long clubId;
    private final String club_name;
    private final FormStatus applicationStatus;

    public ApplicationListQueryResponse(FilledForm filledForm) {
        this.clubId = filledForm.getClub().id();
        this.club_name = filledForm.getClub().getName();
        this.applicationStatus = filledForm.getStatus();
    }
}
