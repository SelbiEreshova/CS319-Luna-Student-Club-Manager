package luna.clubverse.backend.club.service;


import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ClubService {

    private final ClubRepository cLubRepository;

    public ClubService(ClubRepository cLubRepository) {
        this.cLubRepository = cLubRepository;
    }

    public void addClub(Club club) {
        cLubRepository.save(club);
    }

    public Club getEvent(Long clubId) {
        Club clubFromDB = cLubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The club with the id " + clubId + " could not be found."));

        return clubFromDB;
    }
}
