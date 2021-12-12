package luna.clubverse.backend.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.location.entity.Location;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


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

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    private LocalTime registrationDeadline;

    private LocalTime reviewDeadline;

    private int quota;

    private boolean memberOnly;

    //finance data
    private double amountOfMoney;
    private String explanation;

    //location
    private String building;
    private Boolean inBilkent;
    private String descriptionLocation;
    private String classroom;


    public Event toEvent() {
       return new Event( name, description, eventStatus, gePoint, startDate,  startTime,  endDate,  endTime,  registrationDeadline,  reviewDeadline,  quota,  memberOnly,  toFinanceData(), toLocation() );
    }
    public Long toEventID()
    {
        return eventID;
    }

    public FinanceData toFinanceData() {
        return new FinanceData(amountOfMoney, FinanceDataStatus.OUTCOME, explanation, LocalDate.now()) ;
    }

    public Location toLocation() {
        return new Location(inBilkent,building,descriptionLocation,classroom) ;
    }
}