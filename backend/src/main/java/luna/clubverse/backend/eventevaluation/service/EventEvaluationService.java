package luna.clubverse.backend.eventevaluation.service;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.eventevaluation.entity.EventEvaluation;
import luna.clubverse.backend.eventevaluation.repository.EventEvaluationRepository;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.Null;

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

        if ( getEventEvaluationWithId(eventEvaluation.getEventId(), eventEvaluation.getUserId()) == null ) {

            eventEvaluationRepository.save(eventEvaluation);
        }

    }

    public MessageResponse addEventEvaluationForStudent(EventEvaluation eventEvaluation, Long eventId, Long id)
    {
        Student student = (Student) userRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + id + "is not found"));
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        if(eventFromDB.getAttendedStudents().contains(student)) {
            //  if( student.getAttendedEvents().stream().map(ge))
            if (getEventEvaluationWithId(eventId, id) == null) {
                eventFromDB.addEvaluation(eventEvaluation.getPoint());
                eventRepository.save(eventFromDB);
                eventEvaluationRepository.save(eventEvaluation);
                return new MessageResponse(MessageType.SUCCESS, "Event evaluation is added");
            }
            return new MessageResponse(MessageType.ERROR, "Student already made evaluation") ;
        }
        return new MessageResponse(MessageType.ERROR, "Student is not in the attended list") ;
    }

    public MessageResponse addEventEvaluationForFacultyAdvisor(EventEvaluation eventEvaluation, Long eventId, Long id)
    {
        FacultyAdvisor facultyAdvisor = (FacultyAdvisor) userRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("FacultyAdvisor with id " + id + "is not found"));
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        if(eventFromDB.getAttendedFacultyAdvisors().contains(facultyAdvisor)) {
            //  if( student.getAttendedEvents().stream().map(ge))
            if (getEventEvaluationWithId(eventId, id) == null) {

                eventEvaluationRepository.save(eventEvaluation);
                return new MessageResponse(MessageType.SUCCESS, "Event evaluation is added");
            }
            return new MessageResponse(MessageType.ERROR, "Student already made evaluation") ;
        }
        return new MessageResponse(MessageType.ERROR, "Student is not in the attended list") ;
    }




    public EventEvaluation getEventEvaluation(Long id)
    {
        EventEvaluation eventEvaluation = eventEvaluationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("The event evaluation could not be found."));
        return eventEvaluation;
    }

    public EventEvaluation getEventEvaluationWithId(Long eventId, Long userId)
    {
       // Student student = (Student) userRepository.findById(studentId).orElseThrow(() ->new EntityNotFoundException("Student with id " + studentId + "is not found"));
        EventEvaluation eventEvaluation = eventEvaluationRepository.findByIdandEventId(userId,eventId);
        return eventEvaluation;
    }
}
