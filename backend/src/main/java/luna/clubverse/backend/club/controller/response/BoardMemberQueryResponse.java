package luna.clubverse.backend.club.controller.response;

import lombok.Getter;

@Getter
public class BoardMemberQueryResponse {
    private String studentFullName;
    private String title;
    public BoardMemberQueryResponse(String studentFullName, String title) {
        this.title = title;
        this.studentFullName = studentFullName;
    }

}
