package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.user.entity.Student;

@Getter
public class ClubQueryResponseForStudent extends ClubQueryResponse {

    private boolean member;

    public ClubQueryResponseForStudent(Club club, Student student) {
        super(club);
        member = student.getRegisteredClubs().contains(club);

    }
}
