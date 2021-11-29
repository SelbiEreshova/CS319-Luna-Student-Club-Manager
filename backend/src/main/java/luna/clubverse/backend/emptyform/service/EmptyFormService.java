package luna.clubverse.backend.emptyform.service;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.emptyform.entity.EmptyForm;
import luna.clubverse.backend.emptyform.repository.EmptyFormRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service
public class EmptyFormService {
    private final EmptyFormRepository formRepository;
    private final ClubRepository cLubRepository;

    public EmptyFormService(ClubRepository cLubRepository, EmptyFormRepository formRepository) {
        this.cLubRepository = cLubRepository;
        this.formRepository = formRepository;

    }


    public void createFormToClub( Long clubId , EmptyForm emptyForm)
    {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));
        emptyForm.setClub(clubFromDB);
        formRepository.save(emptyForm);

    }

    public EmptyForm getForm(Long clubId)
    {
        EmptyForm emptyForm = formRepository.findById(clubId).
                orElseThrow(()->new EntityNotFoundException("The form with the id " + clubId + " could not be found."));

        return emptyForm;
    }

    public void updateForm( Long id, EmptyForm updatedEmptyForm)
    {
        EmptyForm emptyFormToUpdate = formRepository.getById(id);
        emptyFormToUpdate.updateForm(updatedEmptyForm);
        System.out.println(emptyFormToUpdate.getQuestions());
        formRepository.save(emptyFormToUpdate);
    }

}