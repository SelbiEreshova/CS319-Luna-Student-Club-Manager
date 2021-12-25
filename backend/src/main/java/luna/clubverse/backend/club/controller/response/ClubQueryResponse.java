package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.entity.Title;

import java.util.*;

@Getter
public class ClubQueryResponse {

    private Long clubId;
    private String name;
    private String description;
    private List<BoardMemberQueryResponse> boardMembers;
    private String clubDirectorMail;
    private String faultyAdvisorMail;
    private String logoImage;
    private String backgroundImage;

    public ClubQueryResponse(final Club club) {
        this.name = club.getName();
        this.description = club.getDescription();
        this.clubId = club.id();
        this.clubDirectorMail = "";
        this.faultyAdvisorMail = "";
        this.logoImage= "";
        this.backgroundImage = "";

        if(club.getLogoImage() != null){
            this.logoImage = twoDToString(club.getLogoImage());
        }
        if(club.getBackgroundImage() != null){
            this.backgroundImage = twoDToString(club.getBackgroundImage());
        }

        List<BoardMemberQueryResponse> boardMemberList = new ArrayList<>();

        if(club.getClubDirector() != null){
            String clubDirectorFullName = club.getClubDirector().getName() + " " + club.getClubDirector().getLastname();
            String image = twoDToString(club.getClubDirector().getProfilePhoto());
            boardMemberList.add(new BoardMemberQueryResponse(clubDirectorFullName,"Club Director", club.getClubDirector().getId(),image));
            this.clubDirectorMail = club.getClubDirector().getMail();
        }

        if(club.getFacultyAdvisor() != null){
            String facultyAdvisorFullName =  club.getFacultyAdvisor().getName() + " " + club.getFacultyAdvisor().getLastname();
            String image = twoDToString(club.getFacultyAdvisor().getProfilePhoto());
            boardMemberList.add(new BoardMemberQueryResponse(facultyAdvisorFullName,"Faculty Advisor", club.getFacultyAdvisor().getId(),image));
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
                return new BoardMemberQueryResponse(fullname,title.getTitle(),student.getId(),twoDToString(student.getProfilePhoto()));
            }
        }
        return null;

    }

    private String twoDToString(byte[] array){
        return Base64.getEncoder().encodeToString(array);
    }

}
