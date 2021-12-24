package luna.clubverse.backend.authentication;

import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Component
public class AuthorizationLuna {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public AuthorizationLuna(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public boolean authorize(Authentication authentication, String authority, Long clubId ) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("The user with the given username could not found."));

        return user.getAuthorities().contains(new Authority(authority,clubId));
    }

    public boolean authorizeEvent(Authentication authentication, String authority, Long eventId ) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("The user with the given username could not found."));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(()->new EntityNotFoundException("The event with id " + eventId + " is not found"));
        return user.getAuthorities().contains(new Authority(authority,event.getClub().id()));
    }
}
