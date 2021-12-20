package luna.clubverse.backend.event.entity;

import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.location.entity.Location;

import java.time.LocalDate;
import java.time.LocalTime;

public final class EventBuilder {
    private String name;
    private String description;
    private EventStatus eventStatus;
    private int gePoint;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private LocalDate registrationDeadline;
    private LocalDate reviewDeadline;
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

    public EventBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventBuilder startTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public EventBuilder endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public EventBuilder endTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public EventBuilder registrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
        return this;
    }

    public EventBuilder reviewDeadline(LocalDate reviewDeadline) {
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
        return new Event(name, description, eventStatus, gePoint, startDate, startTime, endDate, endTime, registrationDeadline, reviewDeadline, quota, memberOnly, financeData, location);
    }
}
