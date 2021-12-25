package luna.clubverse.backend.filledform.service;

import lombok.Getter;
import luna.clubverse.backend.club.controller.response.ApplicationQueryResponse;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.emptyform.entity.EmptyForm;
import luna.clubverse.backend.emptyform.repository.EmptyFormRepository;
import luna.clubverse.backend.filledform.entity.FilledForm;
import luna.clubverse.backend.filledform.repository.FilledFormRepository;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Getter
@Transactional
@Service
public class FilledFormService {
    private FilledFormRepository filledFormRepository;
    private ClubRepository cLubRepository;
    private EmptyFormRepository emptyFormRepository;
    private UserRepository userRepository;

    public FilledFormService(ClubRepository cLubRepository, FilledFormRepository filledFormRepository, EmptyFormRepository emptyFormRepository, UserRepository userRepository) {
        this.cLubRepository = cLubRepository;
        this.filledFormRepository = filledFormRepository;

        this.emptyFormRepository = emptyFormRepository;
        this.userRepository = userRepository;
    }


    public MessageResponse createFilledForm(Long clubId , Long studentId, FilledForm filledForm  )
    {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        EmptyForm emptyForm = emptyFormRepository.findByClub(clubFromDB)
                .orElseThrow(()->new EntityNotFoundException("Empty form is not found"));

        List<String> questions = emptyForm.getQuestions().stream().map(question -> question.substring(0)).toList();
        filledForm.setQuestionsOfFilled(questions);
        filledForm.setClub(clubFromDB);
        filledForm.setStudentId(studentId);
        filledFormRepository.save(filledForm);

        return new MessageResponse(MessageType.SUCCESS,"Your answers are saved successfully");

    }



    public FilledForm getFilledForm( Long id  )
    {
        FilledForm filledForm = filledFormRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("The form with the id " + id + " could not be found."));
        return filledForm;

    }

    public ApplicationQueryResponse getCandidateApplication(Long clubId, Long userId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        FilledForm filledForm = filledFormRepository.findByClubAndStudentId(clubFromDB, userId)
                .orElseThrow(()->new EntityNotFoundException("The form is not found"));

        User student = userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("The user is not found"));
        String name_surname = student.getName() + " " + student.getLastname();

        return new ApplicationQueryResponse(name_surname, filledForm);

    }
}
