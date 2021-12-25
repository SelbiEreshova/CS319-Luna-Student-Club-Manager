package luna.clubverse.backend.event.controller.response;

import lombok.Getter;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class  EventQueryResponse {

    private final String name;
    private final String club;
    private final String description;
    private final EventStatus eventStatus;
    private final int gePoint;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate registrationDeadlineDate;
    private final LocalDate reviewDeadlineDate;
    private final LocalTime registrationDeadlineTime;
    private final LocalTime reviewDeadlineTime;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final int quota;
    private final int remainingQuota;
    private final boolean memberOnly;
    private final int totalPoint;
    private final int numberEvaluation;

    private final double amountOfMoney;
    private final String explanation;

    //location
    private final String building;
    private final Boolean inBilkent;
    private final String descriptionLocation;
    private final String classroom;

    public EventQueryResponse(final Event event) {
        this.name = event.getName();
        this.club = event.getClub().getName();
        this.description = event.getDescription();
        this.eventStatus = event.getEventStatus();
        this.gePoint = event.getGePoint();
        this.startDate = event.getStartDateTime().toLocalDate();
        this.endDate = event.getEndDateTime().toLocalDate();
        this.registrationDeadlineDate = event.getRegistrationDeadline().toLocalDate();
        this.registrationDeadlineTime = event.getRegistrationDeadline().toLocalTime();
        this.reviewDeadlineDate = event.getReviewDeadline().toLocalDate();
        this.reviewDeadlineTime = event.getReviewDeadline().toLocalTime();
        this.quota = event.getQuota();
        this.remainingQuota = event.getRemainingQuota();
        this.memberOnly = event.isMemberOnly();
        this.totalPoint = event.getTotalPoint();
        this.numberEvaluation = event.getNumberEvaluation();
        this.startTime = event.getStartDateTime().toLocalTime();
        this.endTime = event.getEndDateTime().toLocalTime();
        this.amountOfMoney = event.getFinanceData().amountOfMoney();
        this.explanation = event.getFinanceData().explanation();
        this.building = event.getLocation().building();
        this.inBilkent = event.getLocation().inBilkent();
        this.descriptionLocation = event.getLocation().description();
        this.classroom = event.getLocation().classroom();
    }
}
