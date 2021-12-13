package luna.clubverse.backend.event.controller.response;

import lombok.Getter;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class  EventQueryResponse {

    private final String name;
    private final String description;
    private final EventStatus eventStatus;
    private final int gePoint;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate registrationDeadline;
    private final LocalDate reviewDeadline;
    private LocalTime startTime;
    private LocalTime endTime;
    private final int quota;
    private final int remainingQuota;
    private final boolean memberOnly;
    private final int totalPoint;
    private final int numberEvaluation;

    public EventQueryResponse(final Event event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.eventStatus = event.getEventStatus();
        this.gePoint = event.getGePoint();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.registrationDeadline = event.getRegistrationDeadline();
        this.reviewDeadline = event.getReviewDeadline();
        this.quota = event.getQuota();
        this.remainingQuota = event.getRemainingQuota();
        this.memberOnly = event.isMemberOnly();
        this.totalPoint = event.getTotalPoint();
        this.numberEvaluation = event.getNumberEvaluation();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
    }
}
