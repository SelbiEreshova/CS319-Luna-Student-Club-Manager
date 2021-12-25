package luna.clubverse.backend.security;

import luna.clubverse.backend.club.repository.ClubRepository;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.repository.EventRepository;
import luna.clubverse.backend.mail.MailManager;
import luna.clubverse.backend.user.entity.*;
import luna.clubverse.backend.user.repository.AuthorityRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;

@Service
@Component
public class DatabasePopulator {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;
    private final MailManager mailManager;

    public DatabasePopulator(AuthorityRepository authorityRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, EventRepository eventRepository, ClubRepository clubRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
        this.mailManager = MailManager.getMailManager();
    }



/*

    @Bean
    @Transactional
    public void populateDatabase() {

        //Authority userAuthority = authorityRepository.save(new Authority(null, "USER",1L, new HashSet<>()));
        //Authority adminAuthority = authorityRepository.save(new Authority(null, "ADMIN",2L, new HashSet<>()));



        User admin = new Admin(null, "admin", passwordEncoder.encode("admin"), "admin", "admin",null, "abc@gmail.com", new HashSet<Authority>());
        admin.addAuthority("ADMIN");
        userRepository.save(admin);



        User manager1 = new Student(null, "manager1", passwordEncoder.encode("manager1"), "name","lastname", null,"abc@gmail.com",new HashSet<Authority>(), 11111111);
        manager1.addAuthority("STUDENT");

        //manager1.addAuthority("EVENT_MANAGEMENT", 2L);
        //manager1.addAuthority("FINANCE_MANAGEMENT", 1L);
        userRepository.save(manager1);



        //Club club = clubRepository.findById(1L).orElseThrow();

        //FinanceData financeData = new FinanceData(0, FinanceDataStatus.EXPENSE, "none",LocalDate.now().minusDays(10));
        //Location location = new luna.clubverse.backend.location.entity.Location(true,"b", "hksjdfsd","102");
        //Event event = new Event("past event","past", EventStatus.PUBLISHED, 0, LocalDate.now().minusDays(10), LocalTime.now().minusHours(10),LocalDate.now().minusDays(7),LocalTime.now().minusHours(3),LocalDate.now().minusDays(12),LocalDate.now().plusDays(2),50,false, financeData, location);

        //event.setClub(club);

        //Student student = (Student) userRepository.findById(2L).orElseThrow();
        //Event event = eventRepository.findById(2L).orElseThrow();
        //event.addEnrolledStudent(student);
        //eventRepository.save(event);


    }

 */




}
