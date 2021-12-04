package luna.clubverse.backend.event.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.location.entity.Location;
import luna.clubverse.backend.user.entity.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "event")
public class Event extends BaseEntity {

    private String name;

    private String description;

    private EventStatus eventStatus;

    private int gePoint;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate registrationDeadline;

    private LocalDate reviewDeadline;

    private int quota;

    private int remainingQuota;

    private boolean memberOnly;

    private int totalPoint;

    private int numberEvaluation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "finance_data_id")
    private FinanceData financeData;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToMany
    @JoinTable(name = "event_enrolled_student",
            joinColumns = @JoinColumn(name = "enrolled_student_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Student> enrolledStudents;

    @ManyToMany
    @JoinTable(name = "event_attended_student",
            joinColumns = @JoinColumn(name = "attended_student_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Student> attendedStudents;

    protected Event() {
    }

    public Event(String name, String description, EventStatus eventStatus, int gePoint, LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline, LocalDate reviewDeadline, int quota, boolean memberOnly, FinanceData financeData, Location location) {
        this.name = name;
        this.description = description;
        this.eventStatus = eventStatus;
        this.gePoint = gePoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = registrationDeadline;
        this.reviewDeadline = reviewDeadline;
        this.quota = quota;
        this.remainingQuota = quota;
        this.memberOnly = memberOnly;
        this.totalPoint = 0;
        this.numberEvaluation = 0;
        this.financeData = financeData;
        this.location = location;
    }
}
