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
    @NotBlank
    private String logo;
    @NotBlank
    private String description;

    @NotBlank
    private String directorName;
    @NotBlank
    private String directorSurname;
    @Email
    private String clubEmail;
    @NotBlank
    private String clubPassword;

    @NotBlank
    private String advisorUsername;
    @NotBlank
    private String advisorPassword;
    @NotBlank
    private String advisorName;
    @NotBlank
    private String advisorSurname;
    @Email
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
