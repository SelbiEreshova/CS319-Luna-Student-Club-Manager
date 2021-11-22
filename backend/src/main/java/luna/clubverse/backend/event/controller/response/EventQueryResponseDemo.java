package luna.clubverse.backend.event.controller.response;

import luna.clubverse.backend.event.entity.Event;

import java.time.LocalDate;

public class EventQueryResponseDemo {

    private final String eventName;
    private final String clubName;

    private final int gePoint;
    private final LocalDate startDate;

    public EventQueryResponseDemo(Event event) {
        this.eventName = event.getName();
        this.clubName = event.getClub().getName();
        this.gePoint = event.getGePoint();
        this.startDate = event.getStartDate();
    }
}
