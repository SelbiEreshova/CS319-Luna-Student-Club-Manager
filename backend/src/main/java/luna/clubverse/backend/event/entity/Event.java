package luna.clubverse.backend.event.entity;

import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
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

    /**
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    **/

    protected Event() {
    }

    public Event(String name, String description, EventStatus eventStatus, int gePoint, LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline, LocalDate reviewDeadline, int quota, boolean memberOnly, FinanceData financeData) {
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
    }
}
