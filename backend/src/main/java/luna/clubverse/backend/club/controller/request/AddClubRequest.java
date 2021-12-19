package luna.clubverse.backend.club.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;

@RequiredArgsConstructor
@Getter
@ToString
public class AddClubRequest {

    @NotNull(message = "The name of the club cannot be blank")
    private String clubName;
    private String logo;
    private String description;

    private String directorName;
    private String directorSurname;
    private String clubEmail;
    private String clubPassword;

    private String advisorUsername;
    private String advisorPassword;
    private String advisorName;
    private String advisorSurname;
    private String advisorMail;

    public Club toClub() {
        return new Club(clubName,logo,description, toFinanceTable());

    }

    public FinanceTable toFinanceTable(){
        return new FinanceTable();
    }


    public ClubDirector toClubDirector() {
        return new ClubDirector(null, clubName, clubPassword, directorName, directorSurname, clubEmail, new HashSet<Authority>());
    }

    public FacultyAdvisor toFacultyAdvisor() {
        return new FacultyAdvisor(null, advisorUsername, advisorPassword, advisorName, advisorSurname,advisorMail, new HashSet<Authority>());
    }
}
