package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import luna.clubverse.backend.user.entity.Student;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public class MemberQueryresponse {

    private final Long memberId;
    private final int memberBilkentId;
    private final String memberEmail;
    private  final  String memberName;
    private final String memberSurname;
    private final String memberRole;
    private final List<Integer> memberPermissions;

    public MemberQueryresponse(Student student, Long clubId, List<Integer> memberPermissions) {
        this.memberId = student.getId();
        this.memberBilkentId = student.getBilkentId();
        this.memberEmail = student.getMail();
        this.memberName = student.getName();
        this.memberSurname = student.getLastname();
        this.memberRole = "";
        this.memberPermissions = memberPermissions;
    }

}
