package luna.clubverse.backend.club.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@RequiredArgsConstructor
@Getter
@ToString
public class AddClubRequest {

    @NotBlank(message = "The name of the club cannot be blank")
    private String clubName;

    private String logo;
    private String description;

    @NotBlank(message = "1")
    private String directorName;
    @NotBlank(message = "2")
    private String directorSurname;
    @Email(message = "3")
    private String clubEmail;
    @NotBlank(message = "4")
    private String clubPassword;

    @NotBlank(message = "5")
    private String advisorUsername;
    @NotBlank(message = "6")
    private String advisorPassword;
    @NotBlank(message = "7")
    private String advisorName;
    @NotBlank(message = "8")
    private String advisorSurname;
    @Email(message = "9")
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
