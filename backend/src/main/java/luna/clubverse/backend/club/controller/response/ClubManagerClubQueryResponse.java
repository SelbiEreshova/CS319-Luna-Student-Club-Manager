package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;

import java.util.List;

@Getter
public class ClubManagerClubQueryResponse extends ClubQueryResponse {
    private List<Integer> permissions;

    public ClubManagerClubQueryResponse(final Club club, final List<Integer> permissions) {
        super(club);
        this.permissions = permissions;
    }
}