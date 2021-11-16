package luna.clubverse.backend.event.service;

import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.financedata.repository.FinanceDataRepository;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.financetable.repository.FinanceTableRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final FinanceDataRepository financeDataRepository;
    private final FinanceTableRepository financeTableRepository;

    public EventService(EventRepository eventRepository, FinanceDataRepository financeDataRepository, FinanceTableRepository financeTableRepository) {
        this.eventRepository = eventRepository;
        this.financeDataRepository = financeDataRepository;
        this.financeTableRepository = financeTableRepository;
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
}
