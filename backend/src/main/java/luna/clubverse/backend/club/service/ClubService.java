package luna.clubverse.backend.club.service;


import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.repository.EventRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ClubService {

    private final ClubRepository cLubRepository;

    private final EventRepository eventRepository;

    public ClubService(ClubRepository cLubRepository, EventRepository eventRepository) {
        this.cLubRepository = cLubRepository;
        this.eventRepository = eventRepository;
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
