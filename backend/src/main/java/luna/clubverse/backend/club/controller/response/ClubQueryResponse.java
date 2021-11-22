package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;

@Getter
public class ClubQueryResponse {

    private String id;
    private String name;
    private String logo;
    private String description;


    public ClubQueryResponse(final Club club) {
        this.name = club.getName();
        this.logo = club.getLogo();
        this.description = club.getDescription();
    }
}
