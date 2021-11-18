package luna.clubverse.backend.filledform.service;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.filledform.entity.FilledForm;
import luna.clubverse.backend.filledform.repository.FilledFormRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Getter
@Transactional
@Service
public class FilledFormService {
    private FilledFormRepository filledFormRepository;
    private ClubRepository cLubRepository;

    public FilledFormService(ClubRepository cLubRepository, FilledFormRepository filledFormRepository) {
        this.cLubRepository = cLubRepository;
        this.filledFormRepository = filledFormRepository;

    }


    public void createFilledForm( Long clubId ,Long studentId, FilledForm filledForm  )
    {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));
        filledForm.setClub(clubFromDB);
        filledForm.setStudentId(studentId);
        filledFormRepository.save(filledForm);

    }



    public FilledForm getFilledForm( Long id  )
    {
        FilledForm filledForm = filledFormRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("The form with the id " + id + " could not be found."));
        return filledForm;

    }
}
