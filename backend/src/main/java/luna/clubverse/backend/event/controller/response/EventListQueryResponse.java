package luna.clubverse.backend.event.controller.response;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.event.entity.Event;

import java.time.LocalDate;

@Getter
@Setter

public class EventListQueryResponse {

    private String eventName;
    private String clubName;

    private int gePoint;
    private LocalDate startDate;

    public EventListQueryResponse(Event event) {
        this.eventName = event.getName();
        this.clubName = event.getClub().getName();
        this.gePoint = event.getGePoint();
        this.startDate = event.getStartDate();
    }

    public EventListQueryResponse(String eventName, String clubName, int gePoint, LocalDate startDate) {
        this.eventName = eventName;
        this.clubName = clubName;
        this.gePoint = gePoint;
        this.startDate = startDate;
    }

    public EventListQueryResponse() {
    }
}