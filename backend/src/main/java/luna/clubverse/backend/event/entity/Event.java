package luna.clubverse.backend.event.entity;

import lombok.Getter;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public int getGePoint() {
        return gePoint;
    }

    public void setGePoint(int gePoint) {
        this.gePoint = gePoint;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public LocalDate getReviewDeadline() {
        return reviewDeadline;
    }

    public void setReviewDeadline(LocalDate reviewDeadline) {
        this.reviewDeadline = reviewDeadline;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getRemainingQuota() {
        return remainingQuota;
    }

    public void setRemainingQuota(int remainingQuota) {
        this.remainingQuota = remainingQuota;
    }

    public boolean isMemberOnly() {
        return memberOnly;
    }

    public void setMemberOnly(boolean memberOnly) {
        this.memberOnly = memberOnly;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getNumberEvaluation() {
        return numberEvaluation;
    }

    public void setNumberEvaluation(int numberEvaluation) {
        this.numberEvaluation = numberEvaluation;
    }

    public FinanceData getFinanceData() {
        return financeData;
    }

    public void setFinanceData(FinanceData financeData) {
        this.financeData = financeData;
    }

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
