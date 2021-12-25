package luna.clubverse.backend.club.service;


import luna.clubverse.backend.club.controller.response.*;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.repository.AuthorityRepository;
import luna.clubverse.backend.user.repository.StudentRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class ClubService {

    private final ClubRepository cLubRepository;

    private final EventRepository eventRepository;

    private final StudentRepository studentRepository;

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    public ClubService(ClubRepository cLubRepository, EventRepository eventRepository, StudentRepository studentRepository, AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.cLubRepository = cLubRepository;
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    /**
     *
     * @param club is the club which will be added
     * @return the club which will be added
     */
    public Club addClub(Club club) {
        cLubRepository.save(club);
        return club;
    }

    /**
     *
     * @param clubId the id of the club which will be updated
     * @param club which is the updated situation of the club
     */
    public void updateClub(Long clubId,Club club) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        clubFromDB.update(club);
        cLubRepository.save(clubFromDB);
    }

    /**
     *
     * @param clubId the id of the club to get Club
     * @return Club which has the id clubId in the database
     */
    public Club getClub(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        return clubFromDB;
    }

    /**
     *
     * @param clubId id of the club which will be deleted from the database
     * @return Message response that determines success or error
     */
    public MessageResponse deleteClub(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        userRepository.delete(clubFromDB.getClubDirector());
        userRepository.delete(clubFromDB.getFacultyAdvisor());
        cLubRepository.delete(clubFromDB);

        return new MessageResponse(MessageType.SUCCESS, "Club is deleted successfully");
    }

    /**
     *
     * @return  a ClubQueryResponse list for all clubs in database
     */
    public List<ClubQueryResponse> getAllClub() {
        List<ClubQueryResponse> clubFromDB = cLubRepository.findAll().stream().map(ClubQueryResponse::new).toList();

        return clubFromDB;
    }

    /**
     *
     * @param clubId id of the club which events of it will be brought
     * @return EventListQueryResponse for the all events of the specific club with clubId
     */
    public List<EventListQueryResponse> getEventsOfClub(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        return clubFromDB.getEvents().stream().map(event -> new EventListQueryResponse(event)).toList();
    }

    /**
     *
     * @param clubId  the Id of the club which takes an application from student
     * @param studentId the Id of the student which will be added to the appliedStudent array of the club
     */
    public void applyToClub(Long clubId, Long studentId) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        clubFromDB.addAppliedStudents(studentFromDB);

        cLubRepository.save(clubFromDB);
    }

    /**
     *
     * @param clubId
     * @param studentId
     * @param accept
     */
    public void removeFromAppliedStudent(Long clubId, Long studentId, boolean accept) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        boolean result = clubFromDB.removeAppliedStudents(studentFromDB);

        if(accept){
            clubFromDB.addMembers(studentFromDB);
        }

        cLubRepository.save(clubFromDB);
    }

    /**
     *
     * @return ClubListQueryResponse List for all clubs in the database
     */
    public List<ClubListQueryResponse> getClubList() {
        return cLubRepository.findAll().stream().map(ClubListQueryResponse::new).toList();
    }

    /**
     *
     * @param clubId id of the club which of the memberd will be brought
     * @return MemberQueryResponse List for the all members
     */
    public List<MemberQueryresponse> getMembers(Long clubId) {
        return cLubRepository.findById(clubId)
                .orElseThrow(()-> new EntityNotFoundException("The club with id " + clubId + " is not found"))
                .getMembers()
                .stream()
                .map(member -> new MemberQueryresponse(member, clubId, getPermissionsOfAMember((Student) member,clubId)))
                .toList();
    }

    /**
     *
     * @param student Permission of who will be returned
     * @param clubId for the permissions in a specific club
     * @return Integer list for permissions ( they are enumarated)
     */
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

    /**
     *
     * @param clubId for the club which will be applicated
     * @param studentId for the who is going to applying It directly add student as a member
     */
    public void directApplicationToClub(Long clubId, Long studentId) {

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        clubFromDB.addMembers(studentFromDB);

        cLubRepository.save(clubFromDB);
    }

    /**
     *
     * @param clubId for the club
     * @param studentId for the person whose permission is going to returned in ClubManagerClubQueryResponse
     * @return ClubManagerClubQueryResponse for the permmissions and Club ??
     */
    public ClubManagerClubQueryResponse getClubWithPermissions(Long clubId, Long studentId){

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        return new ClubManagerClubQueryResponse(getClub(clubId),getPermissionsOfAMember(studentFromDB,clubId));
    }

    public ClubQueryResponseForStudent getClubForStudent(Long clubId, Long studentId){

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        return new ClubQueryResponseForStudent(getClub(clubId),studentFromDB);
    }

    public void cancelMembership(Long clubId, Long studentId){

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        clubFromDB.removeMembers(studentFromDB);

        cLubRepository.save(clubFromDB);

    }

    public void cancelMembershipForManager(Long clubId, Long studentId){

        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        Student studentFromDB = studentRepository.findById(studentId)
                .orElseThrow(()->new EntityNotFoundException("The student with the id " + studentId + " could not be found."));

        studentFromDB.deleteTitles(clubId);

        studentFromDB.deletePermissions(clubId);

        studentRepository.save(studentFromDB);

        clubFromDB.removeMembers(studentFromDB);

        cLubRepository.save(clubFromDB);

    }

    public MessageResponse changeDescription(Long clubId, String newDescription) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        clubFromDB.setDescription(newDescription);
        cLubRepository.save(clubFromDB);
        return new MessageResponse(MessageType.SUCCESS,"successfully changed");
    }

    public MessageResponse changeLogo(Long clubId,String file){
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));
        byte[] decodedByte = Base64.decodeBase64(file);
        clubFromDB.setLogoImage(decodedByte);
        cLubRepository.save(clubFromDB);
        return new MessageResponse(MessageType.SUCCESS,"successfully upload");

    }


    public List<MemberCandidateQueryResponse> getCandidates(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        return clubFromDB.getAppliedStudents().stream().map(MemberCandidateQueryResponse::new).toList();
    }
}
