package luna.clubverse.backend.club.service;


import luna.clubverse.backend.club.controller.response.ClubListQueryResponse;
import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.club.controller.response.MemberQueryresponse;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.repository.AuthorityRepository;
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

    private final AuthorityRepository authorityRepository;

    public ClubService(ClubRepository cLubRepository, EventRepository eventRepository, StudentRepository studentRepository, AuthorityRepository authorityRepository) {
        this.cLubRepository = cLubRepository;
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
        this.authorityRepository = authorityRepository;
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

    public List<EventListQueryResponse> getEventsOfClub(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        return clubFromDB.getEvents().stream().map(event -> new EventListQueryResponse(event)).toList();
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


    public List<ClubListQueryResponse> getClubList() {
        return cLubRepository.findAll().stream().map(ClubListQueryResponse::new).toList();
    }

    public List<MemberQueryresponse> getMembers(Long clubId) {
        return cLubRepository.findById(clubId)
                .orElseThrow(()-> new EntityNotFoundException("The club with id " + clubId + " is not found"))
                .getMembers()
                .stream()
                .map(member -> new MemberQueryresponse(member, clubId, getPermissionsOfAMember((Student) member,clubId)))
                .toList();
    }

    public List<Integer> getPermissionsOfAMember(Student student ,Long clubId) {
        return authorityRepository.findAllByUsersAndClubId(student, clubId)
                .stream()
                .map(authority -> {switch (authority.getAuthority()) {
                    case("EVENT_MANAGEMENT"): return 1;
                    case("FINANCE_MANAGEMENT"): return 2;
                    case("MEMBERSHIP_MANAGEMENT"): return 3;
                }; return 0;})
                .toList();
            }

    public void directApplicationToClub(Long clubId, Long studentId) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        clubFromDB.addMembers(studentFromDB);

        cLubRepository.save(clubFromDB);
    }
}
