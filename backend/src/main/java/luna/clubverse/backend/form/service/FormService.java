package luna.clubverse.backend.form.service;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.financedata.repository.FinanceDataRepository;
import luna.clubverse.backend.financetable.repository.FinanceTableRepository;
import luna.clubverse.backend.form.entity.Form;
import luna.clubverse.backend.form.repository.FormRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service
public class FormService {
    private FormRepository formRepository;
    private ClubRepository cLubRepository;

    public FormService(ClubRepository cLubRepository, FormRepository formRepository) {
        this.cLubRepository = cLubRepository;
        this.formRepository = formRepository;

    }


    public void createFormToClub( Long clubId , Form form  )
    {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));
        form.setClub(clubFromDB);
        formRepository.save(form);

    }
}
