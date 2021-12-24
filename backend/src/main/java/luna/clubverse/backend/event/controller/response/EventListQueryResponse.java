package luna.clubverse.backend.event.controller.response;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.event.entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter

public class EventListQueryResponse {

    private Long eventId;
    private String eventName;
    private String clubName;

    private int gePoint;
    private LocalDate startDate;
    private LocalTime startTime;

    private int quota;
    private int remainingQuota;
    private String location;


    public EventListQueryResponse(Event event) {
        this.eventId = event.id();
        this.eventName = event.getName();
        this.clubName = event.getClub().getName();
        this.gePoint = event.getGePoint();
        this.startDate = event.getStartDateTime().toLocalDate();
        this.startTime = event.getStartDateTime().toLocalTime();
        this.quota = event.getQuota();
        this.remainingQuota = event.getRemainingQuota();
        this.location = event.getLocation().description();
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