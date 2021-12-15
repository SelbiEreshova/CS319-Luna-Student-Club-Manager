package luna.clubverse.backend.event.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.location.entity.Location;
import luna.clubverse.backend.user.entity.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "event")
public class Event extends BaseEntity {

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", eventStatus=" + eventStatus +
                ", gePoint=" + gePoint +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                ", registrationDeadline=" + registrationDeadline +
                ", reviewDeadline=" + reviewDeadline +
                ", quota=" + quota +
                ", remainingQuota=" + remainingQuota +
                ", memberOnly=" + memberOnly +
                ", totalPoint=" + totalPoint +
                ", numberEvaluation=" + numberEvaluation +
                ", financeData=" + financeData +
                ", location=" + location +
                ", club=" + club +
                ", enrolledStudents=" + enrolledStudents +
                ", attendedStudents=" + attendedStudents +
                '}';
    }

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
    //this.totalPoint = 0;
    //        this.numberEvaluation = 0;



    public Event(String name, String description, EventStatus eventStatus, int gePoint, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, LocalDate registrationDeadline, LocalDate reviewDeadline, int quota, boolean memberOnly, FinanceData financeData, Location location) {
        this.name = name;
        this.description = description;
        this.eventStatus = eventStatus;
        this.gePoint = gePoint;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
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

    public void addEnrolledStudent(Student student){

        if(enrolledStudents.contains(student)){
           // throws exception
        }

        enrolledStudents.add(student);
    }
}
