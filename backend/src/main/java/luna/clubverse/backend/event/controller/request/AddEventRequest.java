package luna.clubverse.backend.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.location.entity.Location;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


// Üstteki ikili requestler için şart
@RequiredArgsConstructor // her türlü constract oluşturur yazmaya gerek yok
@Getter
@ToString
public class AddEventRequest {

    @NotNull(message = "The name of the event cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "The status of the event cannot be blank")
    private EventStatus eventStatus;

    private int gePoint;
    private String startDate;
    private String endDate;
    private String registrationDeadline;
    private String reviewDeadline;
    private int quota;
    private boolean memberOnly;

    //finance data
    private double amountOfMoney;
    private String explanation;

    //location
   // private String location;
    private String building;
    private Boolean inBilkent;
    private String descriptionLocation;
    private String classroom;



    public Event toEvent() {
        return new Event(name,description,eventStatus, gePoint, LocalDate.parse(startDate), LocalDate.parse(endDate), LocalDate.parse(registrationDeadline), LocalDate.parse(reviewDeadline), quota, memberOnly, toFinanceData(),toLocation());
    }

    public FinanceData toFinanceData() {
        return new FinanceData(amountOfMoney, FinanceDataStatus.OUTCOME, explanation, LocalDate.now()) ;
    }

    public Location toLocation() {
        return new Location(inBilkent,building,descriptionLocation,classroom) ;
    }
}
