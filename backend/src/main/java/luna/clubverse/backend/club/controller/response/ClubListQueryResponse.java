package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;

@Getter
public class ClubListQueryResponse {

    private String clubName;
    private String clubEmail;
    private String clubFacultyAdvisor;
    private Long clubId;

    public ClubListQueryResponse(Club club) {
        this.clubName = club.getName();
        this.clubEmail = club.getClubDirector().getMail();
        this.clubFacultyAdvisor = club.getFacultyAdvisor().getName() + " " + club.getFacultyAdvisor().getLastname();
        this.clubId = club.id();
    }

}
