package luna.clubverse.backend.user.controller.response;

import lombok.Getter;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;

import java.util.Base64;

@Getter

public class ClubDirectorQueryResponse {
    private String name;
    private String surname;
    private String username;

    private String email;
    private String profilePhoto;

    public ClubDirectorQueryResponse ( final ClubDirector clubDirector)
    {
        this.name = clubDirector.getName();
        this.surname = clubDirector.getLastname();
        this.username = clubDirector.getUsername();

        this.email = clubDirector.getMail();
        this.profilePhoto = "";
        if(clubDirector.getProfilePhoto()!=null){
            this.profilePhoto = Base64.getEncoder().encodeToString(clubDirector.getProfilePhoto());
        }

    }
}