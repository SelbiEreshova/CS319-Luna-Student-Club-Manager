package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;

import java.util.Objects;

@Getter
public class ClubManagerCheckQueryResponse extends ClubQueryResponse {
    private boolean manager;

    public ClubManagerCheckQueryResponse(final Club club, final Long userId) {
        super(club);
        this.manager = false;

        for (BoardMemberQueryResponse boardMember: this.getBoardMembers()){
           if(Objects.equals(userId, boardMember.getUserId())){
               manager=true;
               break;
           }
        }
    }
}
