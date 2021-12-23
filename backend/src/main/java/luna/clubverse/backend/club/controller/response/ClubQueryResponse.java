package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.entity.Title;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public class ClubQueryResponse {

    private Long clubId;
    private String name;
    private String logo;
    private String description;
    private String facultyAdvisorFullName;
    private String clubDirectorFullName;
    private List<BoardMemberQueryResponse> boardMembers;

    public ClubQueryResponse(final Club club) {
        this.name = club.getName();
        this.logo = club.getLogo();
        this.description = club.getDescription();
        this.clubId = club.id();
        this.facultyAdvisorFullName = club.getFacultyAdvisor().getName() + " " + club.getFacultyAdvisor().getLastname();
        this.clubDirectorFullName = club.getClubDirector().getName() + " " + club.getClubDirector().getLastname();

        List<Student> boardMembersStudentList = club.getMembers().stream().toList();

        List<BoardMemberQueryResponse> boardMembersStudent = new ArrayList<>();

        for (Student student: boardMembersStudentList){
            BoardMemberQueryResponse current = boardMemberCustomFilter(student,clubId);
            if (current!=null){
                boardMembersStudent.add(current);
            }

        }
        this.boardMembers = boardMembersStudent;

    }

    private BoardMemberQueryResponse boardMemberCustomFilter(Student student, Long clubId){
        Set<Title> titles = student.getTitles();

        for(Title title : titles){
            if( !title.getTitle().equals("") && Objects.equals(title.getClubId(), clubId)){
                String fullname = student.getName() + " "+student.getLastname();
                return new BoardMemberQueryResponse(fullname,title.getTitle());
            }
        }
        return null;

    }

}
