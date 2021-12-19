package luna.clubverse.backend.club.service;


import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClubService {

    private final ClubRepository cLubRepository;

    private final EventRepository eventRepository;

    private final StudentRepository studentRepository;

    public ClubService(ClubRepository cLubRepository, EventRepository eventRepository, StudentRepository studentRepository) {
        this.cLubRepository = cLubRepository;
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
    }

    public Club addClub(Club club) {

        cLubRepository.save(club);
        return club;

    }

    public void updateClub(Long clubId,Club club) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        clubFromDB.update(club);
        cLubRepository.save(clubFromDB);
    }

    public Club getClub(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        return clubFromDB;
    }

    public List<ClubQueryResponse> getAllClub() {
        List<ClubQueryResponse> clubFromDB = cLubRepository.findAll().stream().map(ClubQueryResponse::new).toList();

        return clubFromDB;
    }

    public List<EventQueryResponse> getEventsOfClub(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        return clubFromDB.getEvents().stream().map(event -> new EventQueryResponse(event)).toList();
    }

    public void applyToClub(Long clubId, Long studentId) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        clubFromDB.addAppliedStudents(studentFromDB);

        cLubRepository.save(clubFromDB);
    }

    public void removeFromAppliedStudent(Long clubId, Long studentId, boolean accept) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        // result u nası exception yapcam?
        boolean result = clubFromDB.removeAppliedStudents(studentFromDB);

        if(accept){
            clubFromDB.addMembers(studentFromDB);
        }



        cLubRepository.save(clubFromDB);
    }


}
