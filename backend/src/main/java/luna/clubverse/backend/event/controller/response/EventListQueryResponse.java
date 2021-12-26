package luna.clubverse.backend.event.controller.response;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.event.entity.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate endDate;
    private LocalTime endTime;
    private String start;
    private String end;
    private String endDateString;
    private String endTimeString;
    private String startTimeString;
    private String startDateString;
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
        this.endDate = event.getEndDateTime().toLocalDate();
        this.endTime = event.getEndDateTime().toLocalTime();
        //this.start = LocalDateTime.of(startDate,startTime);
        //this.finish = LocalDateTime.of(endDate,endTime);
        this.start = event.getStartDateTime().toLocalDate().toString() +" \n "+ event.getStartDateTime().toLocalTime().toString();
        this.startDateString = event.getStartDateTime().toLocalDate().toString();
        this.startTimeString = event.getStartDateTime().toLocalTime().toString();
        this.end = event.getEndDateTime().toLocalDate().toString() +" \n "+ event.getEndDateTime().toLocalTime().toString();
        this.endDateString = event.getEndDateTime().toLocalDate().toString();
        this.endTimeString = event.getEndDateTime().toLocalTime().toString();
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