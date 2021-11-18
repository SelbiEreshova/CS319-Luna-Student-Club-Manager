package luna.clubverse.backend.security;

import luna.clubverse.backend.user.entity.*;
import luna.clubverse.backend.user.repository.AuthorityRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Component
public class DatabasePopulator {

        private final AuthorityRepository authorityRepository;
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public DatabasePopulator(AuthorityRepository authorityRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.authorityRepository = authorityRepository;
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Bean
        @Transactional
        public void populateDatabase() {

        Authority userAuthority = authorityRepository.save(new Authority(null, "USER",1L, new HashSet<>()));
        Authority adminAuthority = authorityRepository.save(new Authority(null, "ADMIN",2L, new HashSet<>()));

        User admin = new Admin(null,"admin",passwordEncoder.encode("admin"),"admin", "abc@gmail.com", new HashSet<Authority>() );
                //null, "admin", passwordEncoder.encode("admin"), new HashSet<Authority>());
        admin.addAuthority("ADMIN");
        userRepository.save(admin);

        User director = new ClubDirector(null, "director", passwordEncoder.encode("director"),"director","abc@gmail.com", new HashSet<Authority>());
        director.addAuthority("PERMISSION_MANAGEMENT", 1L);
        director.addAuthority("EVENT_MANAGEMENT", 1L);
        director.addAuthority("FINANCE_MANAGEMENT", 1L);
        director.addAuthority("REVIEW_MEMBER_APPLICATION", 1L);
        director.addAuthority("REMOVE_MEMBER",1L);
        userRepository.save(director);

        User manager1 = new Student(null,"manager1",passwordEncoder.encode("manager1"), "manager", "abc@gmail.com", new HashSet<Authority>(), 11111111);
        manager1.addAuthority("STUDENT");
        manager1.addAuthority("EVENT_MANAGEMENT", 2L);
        manager1.addAuthority("FINANCE_MANAGEMENT", 1L);
        userRepository.save(manager1);

        User advisor = new FacultyAdvisor(null, "advisor", passwordEncoder.encode("advisor"), "advisor", "abc@gmail.com", new HashSet<Authority>());
        advisor.addAuthority("ADVISOR", 1L);
        userRepository.save(advisor);

        }


}
