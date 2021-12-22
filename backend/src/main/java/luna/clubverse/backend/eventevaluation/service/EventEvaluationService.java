package luna.clubverse.backend.eventevaluation.service;

import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.eventevaluation.entity.EventEvaluation;
import luna.clubverse.backend.eventevaluation.repository.EventEvaluationRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class EventEvaluationService {
    //private final ClubRepository cLubRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventEvaluationRepository eventEvaluationRepository;

    public EventEvaluationService(EventEvaluationRepository eventEvaluationRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.eventEvaluationRepository = eventEvaluationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }
    public void addEventEvaluation(EventEvaluation eventEvaluation)
    {
       // userRepository.findByUsername( )
    }

    public EventEvaluation getEventEvaluation(Long id)
    {
        EventEvaluation eventEvaluation = eventEvaluationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("The event evaluation could not be found."));
        return eventEvaluation;
    }
}
