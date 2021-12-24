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
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
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

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private LocalDateTime registrationDeadline;

    private LocalDateTime reviewDeadline;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToMany
    @JoinTable(name = "event_enrolled_student",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "enrolled_student_id")
    )
    private Set<Student> enrolledStudents;

    @ManyToMany
    @JoinTable(name = "event_attended_student",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attended_student_id")
    )
    private Set<Student> attendedStudents;

    @ManyToMany
    @JoinTable(name = "event_enrolled_fa",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "enrolled_fa_id")
    )
    private Set<FacultyAdvisor> enrolledFacultyAdvisors;

    @ManyToMany
    @JoinTable(name = "event_attended_fa",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attended_fa_id")
    )
    private Set<FacultyAdvisor> attendedFacultyAdvisors;

    protected Event() {
    }

    public Event(String name, String description, EventStatus eventStatus, int gePoint, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDateTime registrationDeadline, LocalDateTime reviewDeadline, int quota, boolean memberOnly, FinanceData financeData, Location location) {
        this.name = name;
        this.description = description;
        this.eventStatus = eventStatus;
        this.gePoint = gePoint;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.registrationDeadline = registrationDeadline;
        this.reviewDeadline = reviewDeadline;
        this.quota = quota;
        this.remainingQuota = quota;
        this.memberOnly = memberOnly;
        this.totalPoint = 0;
        this.numberEvaluation = 0;
        this.financeData = financeData;
        this.location = location;
        this.enrolledStudents = new HashSet<Student>();
        this.attendedStudents = new HashSet<Student>();
    }

    public void update(Event event){
        this.name = event.name;
        this.description = event.description;
        this.eventStatus = event.eventStatus;
        this.gePoint = event.gePoint;
        this.startDateTime = event.startDateTime;
        this.endDateTime = event.endDateTime;
        this.registrationDeadline = event.registrationDeadline;
        this.reviewDeadline = event.reviewDeadline;
        this.quota = event.quota;
        this.remainingQuota = event.remainingQuota;
        this.memberOnly = event.memberOnly;
        this.totalPoint = event.totalPoint;
        this.numberEvaluation = event.numberEvaluation;
        this.financeData.update(event.financeData);
        this.location = event.location;
    }



    public MessageResponse addEnrolledStudent(Student student)  {
        if(isAvailable()){
            remainingQuota--;

            if(enrolledStudents.contains(student)){
                // throws exception
                return new MessageResponse(MessageType.ERROR,"Already enrolled");
            }

            enrolledStudents.add(student);
        }else{
            // throws exception
            return new MessageResponse(MessageType.ERROR,"No empty quota");
        }
        return new MessageResponse(MessageType.SUCCESS,"Success");


    }

    public void deleteEnrolledStudent(Student student) {
        if(enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            remainingQuota++;
        } else {
            // throws exception
        }
    }

    public boolean isEnrolled(Student student) {
        boolean result =  enrolledStudents.contains(student);
        return result;
    }

    public MessageResponse addEnrolledFacultyAdvisor(FacultyAdvisor facultyAdvisor){

        if(isAvailable()){
            remainingQuota--;

            if(enrolledFacultyAdvisors.contains(facultyAdvisor)){
                // throws exception
                return new MessageResponse(MessageType.ERROR,"Already enrolled");
            }
            enrolledFacultyAdvisors.add(facultyAdvisor);
        }else{
            // throws exception
            return new MessageResponse(MessageType.ERROR,"No empty quota");
        }

        return new MessageResponse(MessageType.SUCCESS,"Success");
    }

    public void deleteEnrolledFacultyAdvisor(FacultyAdvisor facultyAdvisor) {
        if(enrolledFacultyAdvisors.contains(facultyAdvisor)) {
            enrolledFacultyAdvisors.remove(facultyAdvisor);
            remainingQuota++;
        } else {
            // throws exception
        }
    }

    public boolean isEnrolled(FacultyAdvisor facultyAdvisor) {
        boolean result =  enrolledFacultyAdvisors.contains(facultyAdvisor);
        return result;
    }

    public boolean isAvailable(){
        return remainingQuota>0;
    }

}
