package luna.clubverse.backend.club.service;


import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.entity.Event;
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
}
