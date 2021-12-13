package luna.clubverse.backend.event.service;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.financedata.repository.FinanceDataRepository;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.financetable.repository.FinanceTableRepository;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EventService {

    private final ClubRepository cLubRepository;
    private final EventRepository eventRepository;
    private final FinanceDataRepository financeDataRepository;
    private final FinanceTableRepository financeTableRepository;
    private final UserRepository userRepository;

    public EventService(ClubRepository cLubRepository, EventRepository eventRepository, FinanceDataRepository financeDataRepository, FinanceTableRepository financeTableRepository, UserRepository userRepository) {
        this.cLubRepository = cLubRepository;
        this.eventRepository = eventRepository;
        this.financeDataRepository = financeDataRepository;
        this.financeTableRepository = financeTableRepository;
        this.userRepository = userRepository;
    }

    public void addEvent(Event event) {
        FinanceData data = new FinanceData(12L, FinanceDataStatus.INCOME, "jhsgadfjs", LocalDate.now());
        FinanceTable table = new FinanceTable();

        data.setFinanceTable(table);

        financeTableRepository.save(table);
        financeDataRepository.save(data);
        eventRepository.save(event);
    }

    @Transactional
    public void updateEvent( Event updatedEvent,Long id) {
        Event eventToUpdate = eventRepository.getById(id);
        eventToUpdate.setEventStatus(updatedEvent.getEventStatus());
        eventToUpdate.setDescription(updatedEvent.getDescription());
        eventToUpdate.setName(updatedEvent.getName());
        eventToUpdate.setStartDate(updatedEvent.getStartDate());
        eventToUpdate.setFinanceData(updatedEvent.getFinanceData());
        eventToUpdate.setGePoint(updatedEvent.getGePoint());
        eventToUpdate.setEndDate(updatedEvent.getEndDate());
        eventToUpdate.setQuota(updatedEvent.getQuota());
        eventToUpdate.setNumberEvaluation(updatedEvent.getNumberEvaluation());
        eventToUpdate.setMemberOnly(updatedEvent.isMemberOnly());
        eventToUpdate.setRegistrationDeadline((updatedEvent.getRegistrationDeadline()));
        eventToUpdate.setRemainingQuota(updatedEvent.getRemainingQuota());
        eventToUpdate.setTotalPoint(updatedEvent.getTotalPoint());
        eventToUpdate.setReviewDeadline(updatedEvent.getReviewDeadline());

        eventRepository.save(eventToUpdate);
    }
    public Event getEvent(Long eventId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(()->new EntityNotFoundException("The event with the id " + eventId + " could not be found."));

        return eventFromDB;
    }

    public void changeEventStatus(Long eventId,EventStatus eventStatus){
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(()->new EntityNotFoundException("The event with the id " + eventId + " could not be found."));

        eventFromDB.setEventStatus(eventStatus);

        eventRepository.save(eventFromDB);

    }

    public void addEventToClub(Long clubId, Event event) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));
        event.setClub(clubFromDB);
        eventRepository.save(event);
    }


    //public List<Event> getEventList()
    //{
   //     return eventRepository.findAllEvents();
    //}


    public List<EventListQueryResponse> getAllDemo() {
        return eventRepository.findAll().stream().map(EventListQueryResponse::new).toList();
    }

    public MessageResponse addEnrolledStudent(Long eventId, Long userId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow();

        Student studentFromDB = (Student) userRepository.findById(userId)
                .orElseThrow();

        eventFromDB.addEnrolledStudent(studentFromDB);

        eventRepository.save(eventFromDB);

        return  new MessageResponse(MessageType.SUCCESS, "You enrolled the event  successfully");
    }
}
