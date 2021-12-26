package luna.clubverse.backend.club.controller.response;

import lombok.Getter;

@Getter
public class BoardMemberQueryResponse {
    private String userFullName;
    private String title;
    private Long userId;
    private String profileImage;
    public BoardMemberQueryResponse(String userFullName, String title, Long userId, String profileImage) {
        this.title = title;
        this.userFullName = userFullName;
        this.userId = userId;
        this.profileImage = profileImage;
    }

}
