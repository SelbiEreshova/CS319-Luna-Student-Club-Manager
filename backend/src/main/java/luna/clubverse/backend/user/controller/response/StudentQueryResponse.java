package luna.clubverse.backend.user.controller.response;

import lombok.Getter;
import luna.clubverse.backend.user.entity.Student;

import java.util.Base64;

@Getter

public class StudentQueryResponse {
    private String name;
    private String surname;
    private String username;
    private int bilkentId;
    private String email;
    private String profilePhoto;

    public StudentQueryResponse ( final Student student)
    {
        this.name = student.getName();
        this.surname = student.getLastname();
        this.username = student.getUsername();
        this.bilkentId = student.getBilkentId();
        this.email = student.getMail();
        this.profilePhoto = "";
        if(student.getProfilePhoto() !=null){
            this.profilePhoto = Base64.getEncoder().encodeToString(student.getProfilePhoto());
        }

    }
}
