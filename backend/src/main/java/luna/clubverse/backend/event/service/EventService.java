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
}