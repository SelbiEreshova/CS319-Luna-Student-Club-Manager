package luna.clubverse.backend.event.service;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.common.BooleanResponse;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.response.EventHomePageResponse;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.financedata.repository.FinanceDataRepository;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.financetable.repository.FinanceTableRepository;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.repository.FacultyAdvisorRepository;
import luna.clubverse.backend.user.repository.StudentRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EventService {

    private final ClubRepository cLubRepository;
    private final EventRepository eventRepository;
    private final FinanceDataRepository financeDataRepository;
    private final FinanceTableRepository financeTableRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final FacultyAdvisorRepository facultyAdvisorRepository;

    public EventService(ClubRepository cLubRepository, EventRepository eventRepository, FinanceDataRepository financeDataRepository, FinanceTableRepository financeTableRepository, UserRepository userRepository, StudentRepository studentRepository, FacultyAdvisorRepository facultyAdvisorRepository) {
        this.cLubRepository = cLubRepository;
        this.eventRepository = eventRepository;
        this.financeDataRepository = financeDataRepository;
        this.financeTableRepository = financeTableRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.facultyAdvisorRepository = facultyAdvisorRepository;
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
        Event eventToUpdate = eventRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + id + " could not be found."));

        eventToUpdate.update(updatedEvent);
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

    public MessageResponse addEventToClub(Long clubId, Event event) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));
        event.setClub(clubFromDB);

        if (clubFromDB.getFinanceTable().addTransaction(event.getFinanceData()))
        {
            event.getFinanceData().setFinanceTable(clubFromDB.getFinanceTable());
            financeDataRepository.save(event.getFinanceData());
            eventRepository.save(event);
            return new MessageResponse(MessageType.SUCCESS, "Event with name "  + event.getName() +" is created successfully");
        }

        return new MessageResponse(MessageType.ERROR, "Event could not be created");
    }


    //public List<Event> getEventList()
    //{
   //     return eventRepository.findAllEvents();
    //}


    public List<EventListQueryResponse> getAllDemo() {
        return eventRepository.findAll().stream().map(EventListQueryResponse::new).toList();
    }

    public List<EventListQueryResponse> getAllEventsForStudent() {
        return eventRepository.findAll().stream().filter(event -> event.getEventStatus() != EventStatus.DRAFT).map(EventListQueryResponse::new).toList();
    }


    public List<EventListQueryResponse> getEventsForClub( Long id) {
        return eventRepository.findNameById(id).stream().map(EventListQueryResponse::new).toList();
        //return cLubRepository.findAll().
    }

    public List<EventListQueryResponse> getEnrolledEventsForStudent(Long id) {
        //return eventRepository.findEventsByStudentId(id).stream().map(EventQueryResponse::new).toList();
        //return cLubRepository.findAll().
        Student student = (Student) userRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + id + "is not found"));
        return student.getEnrolledEvents().stream().map(EventListQueryResponse::new).toList();

    }

    public List<EventListQueryResponse> getEnrolledEventsForFacultyAdvisor(Long id) {
        //return eventRepository.findEventsByStudentId(id).stream().map(EventQueryResponse::new).toList();
        //return cLubRepository.findAll().
        FacultyAdvisor facultyAdvisor = (FacultyAdvisor) userRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("FacultyAdvisor with id " + id + "is not found"));
        return facultyAdvisor.getEnrolledEvents().stream().map(EventListQueryResponse::new).toList();

    }

    public List<EventListQueryResponse> getAttendedEventsForStudent(Long id) {
        //return eventRepository.findEventsByStudentId(id).stream().map(EventQueryResponse::new).toList();
        //return cLubRepository.findAll().
        Student student = (Student) userRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + id + "is not found"));
        return student.getAttendedEvents().stream().map(EventListQueryResponse::new).toList();

    }

    public MessageResponse addEnrolledStudent(Long eventId, Long userId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        Student studentFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + userId + "is not found"));

        MessageResponse mr =  eventFromDB.addEnrolledStudent(studentFromDB);

        eventRepository.save(eventFromDB);

        return  mr;
    }

    public MessageResponse deleteEnrolledStudent(Long eventId, Long userId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        Student studentFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + userId + "is not found"));
        eventFromDB.deleteEnrolledStudent(studentFromDB);

        eventRepository.save(eventFromDB);

        return  new MessageResponse(MessageType.SUCCESS, "You cancelled your enrollment to the event  successfully");
    }

    public BooleanResponse isEnrolledStudent(Long eventId, Long userId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        Student studentFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + userId + "is not found"));

        boolean result = eventFromDB.isEnrolled(studentFromDB);

        String errorReason = checkErrorEventStudent(eventFromDB,studentFromDB);
        boolean error = !errorReason.equals("");

        return new BooleanResponse(result,error,errorReason);

    }

    public String checkErrorEventStudent(Event event, Student student){
        if(event.getEventStatus().equals(EventStatus.CANCELED)){
            return "Event is Cancelled";
        }
        if(event.getRegistrationDeadline().compareTo(LocalDateTime.now()) < 0){
            return "Registration Deadline is passed";
        }
        if(event.isMemberOnly() &&  !student.getRegisteredClubs().contains(event.getClub())){
            return "Event is Member Only";
        }
        return "";
    }

    public String checkErrorEventFA(Event event, FacultyAdvisor facultyAdvisor){
        if(event.getEventStatus().equals(EventStatus.CANCELED)){
            return "Event is Cancelled";
        }
        if(event.getRegistrationDeadline().compareTo(LocalDateTime.now()) < 0){
            return "Registration Deadline is passed";
        }
        if(event.isMemberOnly() &&  !facultyAdvisor.getClub().equals(event.getClub())){
            return "Event is Member Only";
        }
        return "";
    }


    public MessageResponse addEnrolledFacultyAdvisor(Long eventId, Long userId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        FacultyAdvisor faFromDB = (FacultyAdvisor) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("FacultyAdvisor with id " + userId + "is not found"));

        eventFromDB.addEnrolledFacultyAdvisor(faFromDB);

        eventRepository.save(eventFromDB);

        return  new MessageResponse(MessageType.SUCCESS, "You enrolled the event successfully");
    }

    public MessageResponse deleteEnrolledFacultyAdvisor(Long eventId, Long userId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        FacultyAdvisor faFromDB= (FacultyAdvisor) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("FacultyAdvisor with id " + userId + "is not found"));
        eventFromDB.deleteEnrolledFacultyAdvisor(faFromDB);

        eventRepository.save(eventFromDB);

        return  new MessageResponse(MessageType.SUCCESS, "You cancelled your enrollment to the event  successfully");
    }

    public BooleanResponse isEnrolledFacultyAdvisor(Long eventId, Long userId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        FacultyAdvisor faFromDB= (FacultyAdvisor) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + userId + "is not found"));

        boolean result = eventFromDB.isEnrolled(faFromDB);

        String errorReason = checkErrorEventFA(eventFromDB,faFromDB);
        boolean error = !errorReason.equals("");

        return new BooleanResponse(result,error,errorReason);

    }


    public MessageResponse takeAttendance(Long eventId, List<Long> usersId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        Set<Student> students = new HashSet<>(studentRepository.findAllByIdIsIn(usersId));

        eventFromDB.setAttendedStudents(students);

        Set<FacultyAdvisor> advisors = new HashSet<>(facultyAdvisorRepository.findAllByIdIsIn(usersId));

        eventFromDB.setAttendedFacultyAdvisors(advisors);

        eventRepository.save(eventFromDB);

        return new MessageResponse(MessageType.SUCCESS, "Attendance is taken successfully");

    }

    public EventHomePageResponse getButtonsStatus(Long eventId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        if(eventFromDB.getEventStatus().equals(EventStatus.DRAFT)) {
            return new EventHomePageResponse("visible","hidden","visible","visible", "hidden");
        } else if (eventFromDB.getEventStatus().equals(EventStatus.PUBLISHED)) {
            if(eventFromDB.getEndDateTime().compareTo(LocalDateTime.now()) < 0) {
                return new EventHomePageResponse("hidden","hidden","hidden","hidden", "visible");
            } else if (eventFromDB.getStartDateTime().compareTo(LocalDateTime.now()) <= 0) {
                return new EventHomePageResponse("visible","hidden","hidden","hidden", "visible");
            }
            return new EventHomePageResponse("visible","visible","hidden","hidden", "hidden");
        } else {
            return new EventHomePageResponse("hidden","hidden","hidden","hidden", "hidden");
        }
    }

    public MessageResponse cancelEvent(Long eventId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        eventFromDB.setEventStatus(EventStatus.CANCELED);
        FinanceData dataOfEvent = eventFromDB.getFinanceData();
        FinanceData balancedData = new FinanceData(dataOfEvent.amountOfMoney(), FinanceDataStatus.INCOME, "The event with name " + eventFromDB.getName() + " has canceled. Therefore, money is deposited", LocalDate.now());
        balancedData.setFinanceTable(eventFromDB.getFinanceData().financeTable());
        eventFromDB.getFinanceData().financeTable().addTransaction(balancedData);

        financeDataRepository.save(balancedData);
        eventRepository.save(eventFromDB);

        return new MessageResponse(MessageType.SUCCESS, "Event is canceled successfully");

    }

    public MessageResponse deleteEvent(Long eventId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        FinanceData dataOfEvent = eventFromDB.getFinanceData();
        FinanceData balancedData = new FinanceData(dataOfEvent.amountOfMoney(), FinanceDataStatus.INCOME, "The event with name " + eventFromDB.getName() + " has deleted. Therefore, money is deposited", LocalDate.now());
        balancedData.setFinanceTable(eventFromDB.getFinanceData().financeTable());
        eventFromDB.getFinanceData().financeTable().addTransaction(balancedData);

        financeDataRepository.save(balancedData);
        eventRepository.delete(eventFromDB);

        return new MessageResponse(MessageType.SUCCESS, "Event is deleted successfully");
    }

    public MessageResponse publishEvent(Long eventId) {
        Event eventFromDB = eventRepository.findById(eventId)
                .orElseThrow(() ->new EntityNotFoundException("Event with id " + eventId + "is not found"));

        eventFromDB.setEventStatus(EventStatus.PUBLISHED);
        eventRepository.save(eventFromDB);

        return new MessageResponse(MessageType.SUCCESS, "Event is published successfully");
    }
}
