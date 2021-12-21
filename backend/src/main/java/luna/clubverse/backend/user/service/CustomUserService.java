package luna.clubverse.backend.user.service;


import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomUserService {

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
                .orElseThrow();
        return studentFromDB.getEnrolledEvents()
                .stream()
                .filter(event-> event.getStartDate().compareTo(LocalDate.now()) >= 0)
                .map(event -> new EventListQueryResponse(event))
                .toList();
    }

    public List<ClubQueryResponse> getClubsOfStudent(Long userId) {
        Student userFromDB = (Student) userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("The user with the id " + userId + " could not be found."));

        return userFromDB.getRegisteredClubs()
                .stream()
                .map(ClubQueryResponse::new)
                .toList();
    }

    public Student getStudent(Long studentId) {
        Student userFromDB = (Student) userRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        return userFromDB;
    }

}
