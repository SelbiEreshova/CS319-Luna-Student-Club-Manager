package luna.clubverse.backend.event.service;

import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.repository.EventRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void updateEvent( Event updatedEvent,Long id)
    {
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
}
