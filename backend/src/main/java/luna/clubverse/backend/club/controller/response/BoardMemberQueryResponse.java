package luna.clubverse.backend.club.controller.response;

import lombok.Getter;

@Getter
public class BoardMemberQueryResponse {
    private String userFullName;
    private String title;
    public BoardMemberQueryResponse(String userFullName, String title) {
        this.title = title;
        this.userFullName = userFullName;
    }

}
