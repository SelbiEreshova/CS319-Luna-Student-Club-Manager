package luna.clubverse.backend.user.controller.response;

import lombok.Getter;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;

@Getter

public class FacultyAdvisorQueryResponse {
    private String name;
    private String surname;
    private String username;

    private String email;

    public FacultyAdvisorQueryResponse ( final FacultyAdvisor facultyAdvisor)
    {
        this.name = facultyAdvisor.getName();
        this.surname = facultyAdvisor.getLastname();
        this.username = facultyAdvisor.getUsername();

        this.email = facultyAdvisor.getMail();

    }
}