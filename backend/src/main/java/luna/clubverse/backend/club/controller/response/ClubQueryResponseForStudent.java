package luna.clubverse.backend.club.controller.response;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.user.entity.Student;

public class ClubQueryResponseForStudent extends ClubQueryResponse {

    private boolean member;

    public ClubQueryResponseForStudent(Club club, Student student) {
        super(club);
        member = student.getRegisteredClubs().contains(club);

    }
}
