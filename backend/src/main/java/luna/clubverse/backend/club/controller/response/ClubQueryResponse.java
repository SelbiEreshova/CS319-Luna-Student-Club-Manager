package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;
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
    private List<BoardMemberQueryResponse> boardMembers;
    private String clubDirectorMail;
    private String faultyAdvisorMail;

    public ClubQueryResponse(final Club club) {
        this.name = club.getName();
        this.logo = club.getLogo();
        this.description = club.getDescription();
        this.clubId = club.id();
        this.clubDirectorMail = "";
        this.faultyAdvisorMail = "";


        List<BoardMemberQueryResponse> boardMemberList = new ArrayList<>();

        if(club.getClubDirector() != null){
            String clubDirectorFullName = club.getClubDirector().getName() + " " + club.getClubDirector().getLastname();
            boardMemberList.add(new BoardMemberQueryResponse(clubDirectorFullName,"Club Director", club.getClubDirector().getId()));
            this.clubDirectorMail = club.getClubDirector().getMail();
        }

        if(club.getFacultyAdvisor() != null){
            String facultyAdvisorFullName =  club.getFacultyAdvisor().getName() + " " + club.getFacultyAdvisor().getLastname();
            boardMemberList.add(new BoardMemberQueryResponse(facultyAdvisorFullName,"Faculty Advisor", club.getFacultyAdvisor().getId()));
            this.faultyAdvisorMail = club.getFacultyAdvisor().getMail();
        }

        List<Student> boardMembersStudentList = club.getMembers().stream().toList();

        for (Student student: boardMembersStudentList){
            BoardMemberQueryResponse current = boardMemberCustomFilter(student,clubId);
            if (current!=null){
                boardMemberList.add(current);
            }

        }
        this.boardMembers = boardMemberList;

    }

    private BoardMemberQueryResponse boardMemberCustomFilter(Student student, Long clubId){
        Set<Title> titles = student.getTitles();

        for(Title title : titles){
            if( !title.getTitle().equals("") && Objects.equals(title.getClubId(), clubId)){
                String fullname = student.getName() + " " + student.getLastname();
                return new BoardMemberQueryResponse(fullname,title.getTitle(),student.getId());
            }
        }
        return null;

    }

}
