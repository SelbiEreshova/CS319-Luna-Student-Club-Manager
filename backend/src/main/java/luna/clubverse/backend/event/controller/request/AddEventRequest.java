package luna.clubverse.backend.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@ToString
public class AddEventRequest {

    @NotBlank
    private String name;

    private String description;

    private EventStatus eventStatus;

    private int gePoint;

    private String startDate;

    private String endDate;

    private String registrationDeadline;

    private String reviewDeadline;

    private int quota;

    private boolean memberOnly;


    public Event toEvent() {
        return new Event(name,description,eventStatus, gePoint, LocalDate.parse(startDate), LocalDate.parse(endDate), LocalDate.parse(registrationDeadline), LocalDate.parse(reviewDeadline), quota, memberOnly);
    }
}
