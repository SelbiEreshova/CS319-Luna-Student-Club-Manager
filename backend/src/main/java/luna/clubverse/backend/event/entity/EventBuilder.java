package luna.clubverse.backend.event.entity;

import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.location.entity.Location;

import java.time.LocalDateTime;

public final class EventBuilder {
    private String name;
    private String description;
    private EventStatus eventStatus;
    private int gePoint;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime registrationDeadline;
    private LocalDateTime reviewDeadline;
    private int quota;
    private boolean memberOnly;
    private FinanceData financeData;
    private Location location;

    public EventBuilder() {

    }

    public EventBuilder name(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder description(String description) {
        this.description = description;
        return this;
    }

    public EventBuilder eventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
        return this;
    }

    public EventBuilder gePoint(int gePoint) {
        this.gePoint = gePoint;
        return this;
    }

    public EventBuilder startDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    public EventBuilder endDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public EventBuilder registrationDeadline(LocalDateTime registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
        return this;
    }

    public EventBuilder reviewDeadline(LocalDateTime reviewDeadline) {
        this.reviewDeadline = reviewDeadline;
        return this;
    }

    public EventBuilder quota(int quota) {
        this.quota = quota;
        return this;
    }

    public EventBuilder memberOnly(boolean memberOnly) {
        this.memberOnly = memberOnly;
        return this;
    }

    public EventBuilder financeData(FinanceData financeData) {
        this.financeData = financeData;
        return this;
    }

    public EventBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public Event build() {
        return new Event(name, description, eventStatus, gePoint, startDateTime, endDateTime, registrationDeadline, reviewDeadline, quota, memberOnly, financeData, location);
    }
}
