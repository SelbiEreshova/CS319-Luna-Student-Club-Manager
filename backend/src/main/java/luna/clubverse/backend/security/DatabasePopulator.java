package luna.clubverse.backend.security;

import luna.clubverse.backend.user.entity.*;
import luna.clubverse.backend.user.repository.AuthorityRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
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




    /*

    @Bean
    @Transactional
    public void populateDatabase() {

        //Authority userAuthority = authorityRepository.save(new Authority(null, "USER",1L, new HashSet<>()));
        //Authority adminAuthority = authorityRepository.save(new Authority(null, "ADMIN",2L, new HashSet<>()));


        User admin = new Admin(null, "admin", passwordEncoder.encode("admin"), "admin", "admin", "abc@gmail.com", new HashSet<Authority>());
        admin.addAuthority("ADMIN");
        userRepository.save(admin);



        User manager1 = new Student(null, "manager1", passwordEncoder.encode("manager1"), "name","lastname", "abc@gmail.com", new HashSet<Authority>(), 11111111);
        manager1.addAuthority("STUDENT");

        //manager1.addAuthority("EVENT_MANAGEMENT", 2L);
        //manager1.addAuthority("FINANCE_MANAGEMENT", 1L);
        userRepository.save(manager1);


    }

     */






}
