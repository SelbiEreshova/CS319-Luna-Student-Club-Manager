package luna.clubverse.backend.event.controller.response;

import lombok.Getter;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.user.entity.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class EventAndParticipantQueryResponse {

    private final String name;
    private final String club;
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

    private final double amountOfMoney;
    private final String explanation;

    //location
    private final Boolean inBilkent;
    private final String descriptionLocation;

    private final List<ParticipantResponse> participants;

    public EventAndParticipantQueryResponse(final Event event) {
        this.name = event.getName();
        this.club = event.getClub().getName();
        this.description = event.getDescription();
        this.eventStatus = event.getEventStatus();
        this.gePoint = event.getGePoint();
        this.startDate = event.getStartDateTime().toLocalDate();
        this.endDate = event.getEndDateTime().toLocalDate();
        this.registrationDeadline = event.getRegistrationDeadline().toLocalDate();
        this.reviewDeadline = event.getReviewDeadline().toLocalDate();
        this.quota = event.getQuota();
        this.remainingQuota = event.getRemainingQuota();
        this.memberOnly = event.isMemberOnly();
        this.totalPoint = event.getTotalPoint();
        this.numberEvaluation = event.getNumberEvaluation();
        this.startTime = event.getStartDateTime().toLocalTime();
        this.endTime = event.getEndDateTime().toLocalTime();
        this.amountOfMoney = event.getFinanceData().amountOfMoney();
        this.explanation = event.getFinanceData().explanation();
        this.inBilkent = event.getLocation().inBilkent();
        this.descriptionLocation = event.getLocation().description();

        Comparator<Student> compareByName = (Student s1, Student s2) -> s1.getName().compareTo(s1.getName());
        this.participants = event.getEnrolledStudents()
                .stream()
                .sorted(compareByName)
                .map(student -> new ParticipantResponse(student, event))
                .toList();


    }
}
