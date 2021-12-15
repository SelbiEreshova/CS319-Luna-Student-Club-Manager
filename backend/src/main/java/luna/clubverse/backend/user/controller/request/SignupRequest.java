package luna.clubverse.backend.user.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.Student;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Getter
@RequiredArgsConstructor
public class SignupRequest {

    @NotBlank(message = "First name cannot be empty")
    private final String name;

    @NotBlank(message = "Last name cannot be empty")
    private final String lastname;

    //@NotBlank(message = "Bilkent ID cannot be empty")
    //@Size(min = 8, max = 8, message = "Bilkent ID has not a valid length.")
    private final Integer bilkentId;

    @NotBlank(message = "Please enter a username")
    private final String username;

    @Email(message = "Please enter a valid e-mail")
    @NotBlank(message = "E-mail cannot be empty")
    private final String email;

    @NotBlank(message = "Please enter a password")
    private final String password;

    @AssertTrue(message = "You should signup with a Bilkent mail")
    public boolean isMailBilkentMail() {
        String subStr = email.substring((email.length()-14));
        if (subStr.equals("bilkent.edu.tr")) {
            return true;
        }
        return false;
    }


    public Student toStudent(){
        return new Student(null,username,password , name, email, new HashSet<>(),bilkentId);
    }
}
