package luna.clubverse.backend.club.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;

import javax.validation.constraints.AssertTrue;
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

    @NotBlank(message = "directorName cannot be empty")
    private String directorName;

    @NotBlank(message = "directorSurname cannot be empty")
    private String directorSurname;

    @Email(message = "Please enter a valid e-mail for clubEmail")
    @NotBlank(message = "clubEmail cannot be empty")
    private String clubEmail;

    @NotBlank(message = "clubPassword cannot be empty")
    private String clubPassword;

    @NotBlank(message = "advisorUsername cannot be empty")
    private String advisorUsername;

    @NotBlank(message = "advisorPassword cannot be empty")
    private String advisorPassword;

    @NotBlank(message = "advisorName cannot be empty")
    private String advisorName;

    @NotBlank(message = "advisorSurname cannot be empty")
    private String advisorSurname;

    @Email(message = "Please enter a valid e-mail for advisor")
    @NotBlank(message = "advisorMail cannot be empty")
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

    @AssertTrue(message = "You should signup with a Bilkent mail")
    public boolean isAdvisorMailBilkentMail() {
        String subStr = advisorMail.substring((advisorMail.length()-14));
        if (subStr.equals("bilkent.edu.tr")) {
            return true;
        }
        return false;
    }

    public FacultyAdvisor toFacultyAdvisor() {
        return new FacultyAdvisor(null, advisorUsername, advisorPassword, advisorName, advisorSurname,advisorMail, new HashSet<Authority>());
    }
}
