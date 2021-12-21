package luna.clubverse.backend.user.controller.response;

import lombok.Getter;
import luna.clubverse.backend.user.entity.Student;

@Getter

public class StudentQueryResponse {
    private String name;
    private String surname;
    private String username;
    private int bilkentId;
    private String email;

    public StudentQueryResponse ( final Student student)
    {
        this.name = student.getName();
        this.surname = student.getLastname();
        this.username = student.getUsername();
        this.bilkentId = student.getBilkentId();
        this.email = student.getMail();

    }
}
