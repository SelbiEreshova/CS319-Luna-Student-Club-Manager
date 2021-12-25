package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.user.entity.Student;

@Getter
public class MemberCandidateQueryResponse {

    private final Long userId;
    private final int memberBilkentId;
    private final String memberEmail;
    private  final  String memberName;
    private final String memberSurname;

    public MemberCandidateQueryResponse(Student student) {
        this.userId = student.getId();
        this.memberBilkentId = student.getBilkentId();
        this.memberEmail = student.getMail();
        this.memberName = student.getName();
        this.memberSurname = student.getLastname();
    }
}
