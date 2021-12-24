package luna.clubverse.backend.user.service;


import luna.clubverse.backend.club.controller.response.ClubManagerCheckQueryResponse;
import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.user.controller.response.StudentQueryResponse;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.enums.UserType;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CustomUserService {

    private final String FUTURE = "Future";
    private final String PAST = "Past";
    private final String ONGOING = "Ongoing";
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final EventRepository eventRepository;

    public CustomUserService(UserRepository userRepository, ClubRepository clubRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }


    public List<EventListQueryResponse> getFutureEventsOfStudent(Long userId) {
        Student studentFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + userId + "is not found"));
        return studentFromDB.getEnrolledEvents()
                .stream()
                .filter(event-> checkEventInterval(event.getStartDateTime(), event.getEndDateTime()).equals(FUTURE))
                .map(event -> new EventListQueryResponse(event))
                .toList();

    }

    public String checkEventInterval(LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        LocalDateTime now = LocalDateTime.now();

        if ( startDateTime.compareTo(now) > 0 )
        {
            return FUTURE;
        }
        if ( endDateTime.compareTo(now) < 0 )
        {
            return PAST;
        }


        return ONGOING;

    }

    public List<EventListQueryResponse> getPastEventsOfStudent(Long userId) {
        Student studentFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + userId + "is not found"));
        return studentFromDB.getEnrolledEvents()
                .stream()
                .filter(event-> checkEventInterval(event.getStartDateTime(), event.getEndDateTime()).equals(PAST))
                .map(event -> new EventListQueryResponse(event))
                .toList();

    }



    public List<EventListQueryResponse> getOnGoingEventsOfStudent(Long userId) {
        Student studentFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + userId + "is not found"));
        return studentFromDB.getEnrolledEvents()
                .stream()
                .filter(event-> checkEventInterval(event.getStartDateTime(), event.getEndDateTime()).equals(ONGOING))
                .map(event -> new EventListQueryResponse(event))
                .toList();

    }



    public List<ClubManagerCheckQueryResponse> getClubsOfStudent(Long userId) {
        Student userFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("The user with the id " + userId + " could not be found."));

        return userFromDB.getRegisteredClubs()
                .stream()
                .map(club -> new ClubManagerCheckQueryResponse(club,userId))
                .toList();
    }

    public Student getStudent(Long studentId) {
        Student userFromDB = (Student) userRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        return userFromDB;
    }

    public FacultyAdvisor getFacultyAdvisor(Long id) {
        FacultyAdvisor userFromDB = (FacultyAdvisor) userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("The faculty advisor with the id " + id+ " could not be found."));

        return userFromDB;
    }


    public ClubDirector getClubDirector(Long id) {
        ClubDirector userFromDB = (ClubDirector) userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("The ClubDirector with the id " + id+ " could not be found."));

        return userFromDB;
    }

    public List<StudentQueryResponse> getAllStudents() {
        return userRepository.findAllByUsertypeIs(UserType.STUDENT)
                .stream()
                .map(user -> new StudentQueryResponse((Student) user))
                .toList();
    }
}
