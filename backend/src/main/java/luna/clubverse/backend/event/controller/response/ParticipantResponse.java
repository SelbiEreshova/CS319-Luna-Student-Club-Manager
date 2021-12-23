package luna.clubverse.backend.event.controller.response;

import lombok.Getter;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.user.entity.Student;

@Getter
public class ParticipantResponse {

    private final Long userId;
    private final String name;
    private final int bilkentId;
    private final boolean isAttended;

    public ParticipantResponse (Student student, Event event) {
        this.userId = student.getId();
        this.name = student.getName() + " " + student.getLastname();
        this.bilkentId = student.getBilkentId();
        this.isAttended = student.getAttendedEvents().contains(event);
    }
}
