package luna.clubverse.backend.security;

import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.User;
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

        User admin = new User(null, "admin", passwordEncoder.encode("admin"), Set.of(userAuthority,adminAuthority));
        userRepository.save(admin);

        User user = new User(null, "user", passwordEncoder.encode("user"), Set.of(userAuthority));
        userRepository.save(user);

        User user2 = new User(null,"zisan",passwordEncoder.encode("zisan"),Set.of(userAuthority));
        userRepository.save(user2);

        }


}
