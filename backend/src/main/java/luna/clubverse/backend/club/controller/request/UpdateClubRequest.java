package luna.clubverse.backend.club.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.club.entity.Club;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@ToString
public class UpdateClubRequest {

    @NotNull(message = "The name of the club cannot be blank")
    private String name;
    private String logo;
    private String description;

    public Club toClub() {
        return new Club(name,logo,description);

    }



}