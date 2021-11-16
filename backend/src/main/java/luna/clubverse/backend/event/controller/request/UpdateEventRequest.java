package luna.clubverse.backend.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


// Üstteki ikili requestler için şart
@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class UpdateEventRequest {

    @NotNull
    private Long eventID;

    @NotBlank
    private String name;

    private String description;

    private EventStatus eventStatus;

    private int gePoint;

    private String startDate;

    private String endDate;

    private String registrationDeadline;

    private String reviewDeadline;

    private int quota;

    private boolean memberOnly;

    private double amountOfMoney;

    private String explanation;


    public Event toEvent() {
        return new Event(name,description,eventStatus, gePoint, LocalDate.parse(startDate), LocalDate.parse(endDate), LocalDate.parse(registrationDeadline), LocalDate.parse(reviewDeadline), quota, memberOnly, toFinanceData());

    }
    public Long toEventID()
    {
        return eventID;
    }

    public FinanceData toFinanceData() {
        return new FinanceData(amountOfMoney, FinanceDataStatus.OUTCOME, explanation, LocalDate.now()) ;
    }
}