package luna.clubverse.backend.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.entity.EventBuilder;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.location.entity.Location;

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

    @NotNull(message = "The name of the event cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "The status of the event cannot be blank")
    private EventStatus eventStatus;

    @NotNull(message = "The gePoint of the event cannot be blank")
    private int gePoint;

    @NotNull(message = "The startDate of the event cannot be blank")
    private LocalDate startDate;

    @NotNull(message = "The startTime of the event cannot be blank")
    private LocalTime startTime;

    @NotNull(message = "The endDate of the event cannot be blank")
    private LocalDate endDate;

    @NotNull(message = "The endTime of the event cannot be blank")
    private LocalTime endTime;

    @NotNull(message = "The registrationDeadline of the event cannot be blank")
    private LocalDate registrationDeadline;

    @NotNull(message = "The reviewDeadline of the event cannot be blank")
    private LocalDate reviewDeadline;


    @NotNull(message = "The quota of the event cannot be blank")
    private int quota;

    @NotNull(message = "The memberOnly of the event cannot be blank")
    private boolean memberOnly;

    //finance data
    @NotNull(message = "The amountOfMoney of the event cannot be blank")
    private double amountOfMoney;
    private String explanation;

    //location
    private String building;
    private Boolean inBilkent;
    private String descriptionLocation;
    private String classroom;


    public Event toEvent() {
        return new EventBuilder()
                .name(name)
                .description(description)
                .eventStatus(eventStatus)
                .gePoint(gePoint)
                .startDateTime(LocalDateTime.of(startDate,startTime))
                .endDateTime(LocalDateTime.of(endDate,endTime))
                .registrationDeadline(LocalDateTime.of(registrationDeadline,LocalTime.of(0,0)))
                .reviewDeadline(LocalDateTime.of(reviewDeadline,LocalTime.of(0,0)))
                .quota(quota)
                .memberOnly(memberOnly)
                .financeData(toFinanceData())
                .location(toLocation())
                .build();
    }
    public Long toEventID()
    {
        return eventID;
    }

    public FinanceData toFinanceData() {
        return new FinanceData(amountOfMoney, FinanceDataStatus.EXPENSE, explanation, LocalDate.now()) ;
    }

    public Location toLocation() {
        return new Location(inBilkent,building,descriptionLocation,classroom) ;
    }
}