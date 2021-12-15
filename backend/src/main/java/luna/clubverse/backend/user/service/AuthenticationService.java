package luna.clubverse.backend.user.service;

import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.security.JwtUtil;
import luna.clubverse.backend.user.controller.request.LoginRequest;
import luna.clubverse.backend.user.controller.response.LoginResponse;
import luna.clubverse.backend.user.entity.Authority;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.repository.AuthorityRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthenticationService {

    @Value("${spring.jwt.secret-key}")
    private String key;

    private static final String USERNAME_ALREADY_EXIST = "Username %s is being used. Please choose another Username. ";
    private static final String EMAIL_ALREADY_EXIST = "Email %s is registered already. ";
    private static final String STUDENT_AUTHORITY_NONEXIST = "There is no STUDENT authority";
    private static final String ID_ALREADY_EXIST = "Bilkent ID %s is registered already. ";


    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        try{
            Authentication authenticatedToken = authenticationManager.authenticate(token);
            String jwt = JwtUtil.generateToken(authenticatedToken, key);

            User userFromDb = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow();

            return new LoginResponse(MessageType.SUCCESS, "Bearer " + jwt, userFromDb.getId(), userFromDb.getUsertype() );

            //return ("Bearer " + jwt);
        } catch (AuthenticationException ex) {
            return null;
        }

    }

    public MessageResponse signupStudent(Student student) {

        if(userRepository.existsByUsername(student.getUsername())) {
            return new MessageResponse(MessageType.ERROR, USERNAME_ALREADY_EXIST.formatted(student.getUsername()));
        }

        if(userRepository.existsByMail(student.getMail())) {
            return new MessageResponse(MessageType.ERROR, EMAIL_ALREADY_EXIST.formatted(student.getMail()));
        }

        Authority authorityFromDB = authorityRepository.findByAuthority("STUDENT")
                .orElseThrow(()-> (new EntityNotFoundException(STUDENT_AUTHORITY_NONEXIST)));

        student.addAuthority(authorityFromDB);

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        userRepository.save(student);

        return new MessageResponse(MessageType.SUCCESS, "You have signed up successfully");
    }
}
