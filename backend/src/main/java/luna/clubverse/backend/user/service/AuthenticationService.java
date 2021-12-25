package luna.clubverse.backend.user.service;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.mail.MailManager;
import luna.clubverse.backend.security.JwtUtil;
import luna.clubverse.backend.user.controller.request.ChangePasswordRequest;
import luna.clubverse.backend.user.controller.request.LoginRequest;
import luna.clubverse.backend.user.controller.request.UpdatePermissionRequest;
import luna.clubverse.backend.user.controller.response.LoginResponse;
import luna.clubverse.backend.user.entity.*;
import luna.clubverse.backend.user.repository.AuthorityRepository;
import luna.clubverse.backend.user.repository.StudentRepository;
import luna.clubverse.backend.user.repository.TitleRepository;
import luna.clubverse.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthenticationService {

    @Value("${spring.jwt.secret-key}")
    private String key;

    private static final String USERNAME_ALREADY_EXIST = "Username %s is being used. Please choose another Username. ";
    private static final String EMAIL_ALREADY_EXIST = "Email %s is registered already. ";
    private static final String STUDENT_AUTHORITY_NONEXIST = "There is no STUDENT authority";
    private static final String ID_ALREADY_EXIST = "Bilkent ID %s is registered already. ";


    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthorityRepository authorityRepository;
    private final TitleRepository titleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailManager mailManager;


    public AuthenticationService(UserRepository userRepository, StudentRepository studentRepository, AuthenticationManager authenticationManager, AuthorityRepository authorityRepository, TitleRepository titleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.authenticationManager = authenticationManager;
        this.authorityRepository = authorityRepository;
        this.titleRepository = titleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailManager = MailManager.getMailManager();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        try{
            Authentication authenticatedToken = authenticationManager.authenticate(token);
            String jwt = JwtUtil.generateToken(authenticatedToken, key);

            User userFromDb = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() ->new EntityNotFoundException("User with username " + loginRequest.getUsername() + "is not found"));

            Long clubId = null;
            if(userFromDb instanceof ClubDirector) {
                clubId = ((ClubDirector) userFromDb).getClub().id();
            }else if (userFromDb instanceof  FacultyAdvisor) {
                clubId = ((FacultyAdvisor) userFromDb).getClub().id();
            }

            return new LoginResponse(MessageType.SUCCESS, "Bearer " + jwt, userFromDb.getId(), userFromDb.getUsertype(), clubId );

            //return ("Bearer " + jwt);
        } catch (AuthenticationException ex) {
            return null;
        }

    }

    public MessageResponse signupStudent(Student student) {

        MessageResponse response= isUsernameAndMailUnique(student);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        if (studentRepository.existsByBilkentId(student.getBilkentId())) {
            return new MessageResponse(MessageType.ERROR, "A user with Bilkent ID " + student.getBilkentId() +" is registered already.");
        }

        Authority authorityFromDB = authorityRepository.findByAuthority("STUDENT")
                .orElseThrow(()-> (new EntityNotFoundException(STUDENT_AUTHORITY_NONEXIST)));

        student.addAuthority(authorityFromDB);

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        userRepository.save(student);

        return new MessageResponse(MessageType.SUCCESS, "You have signed up successfully");
    }

    public MessageResponse changePassword(ChangePasswordRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername((String) authentication.getName())
                .orElseThrow(() ->new EntityNotFoundException("User is not found"));

        if(!passwordEncoder.matches(request.getOldPassword(),user.getPassword())) {
            throw new BadCredentialsException("The wrong password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        return new MessageResponse(MessageType.SUCCESS, "Password has changed successfully");
    }

    public MessageResponse createClubDirectorAccount(ClubDirector clubDirector, Club club) {
        MessageResponse response= isUsernameAndMailUnique(clubDirector);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        clubDirector.setClub(club);
        clubDirector.addAuthority("DIRECTOR", club.id());
        clubDirector.addAuthority("EVENT_MANAGEMENT", club.id());
        clubDirector.addAuthority("FINANCE_MANAGEMENT", club.id());
        clubDirector.addAuthority("REVIEW_MEMBER_APPLICATION", club.id());
        clubDirector.addAuthority("REMOVE_MEMBER", club.id());

        /*


        mailManager.sendSimpleMessage(clubDirector.getMail(), "New Account", "Hello, \n \n Your account for Clubverse has been created  \n \n " +
                "Username: " + clubDirector.getUsername() + " \n" +
                "Password:" + clubDirector.getPassword() +  " \n" +
                "\n \n" +
                "Please change your password"
        );

         */


        clubDirector.setPassword(passwordEncoder.encode(clubDirector.getPassword()));

        userRepository.save(clubDirector);


        return new MessageResponse(MessageType.SUCCESS, "Accounts are created successfully");
    }

    public MessageResponse createFacultyAdvisorAccount(FacultyAdvisor facultyAdvisor, Club club) {
        MessageResponse response= isUsernameAndMailUnique(facultyAdvisor);
        if(response.getMessageType().equals(MessageType.ERROR)) {
            return response;
        }

        facultyAdvisor.setClub(club);
        facultyAdvisor.addAuthority("ADVISOR", club.id());


        /*
        mailManager.sendSimpleMessage(facultyAdvisor.getMail(), "New Account", "Hello, \n \n Your account for Clubverse has been created \n \n " +
                "Username: " + facultyAdvisor.getUsername()  + "\n" +
                "Password:" + facultyAdvisor.getPassword() + "\n" +
                "\n \n" +
                "Please change your password"
        );

         */



        facultyAdvisor.setPassword(passwordEncoder.encode(facultyAdvisor.getPassword()));

        userRepository.save(facultyAdvisor);


        return new MessageResponse(MessageType.SUCCESS, "Account is created successfully");
    }

    public MessageResponse isUsernameAndMailUnique(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            return new MessageResponse(MessageType.ERROR, USERNAME_ALREADY_EXIST.formatted(user.getUsername()));
        }

        if(userRepository.existsByMail(user.getMail())) {
            return new MessageResponse(MessageType.ERROR, EMAIL_ALREADY_EXIST.formatted(user.getMail()));
        }

        return new MessageResponse(MessageType.SUCCESS, "");

    }

    public void sendClubRequestToAdvisor(String advisorMail) {
    }

    public MessageResponse updatePermission(UpdatePermissionRequest request) {
        Student student = (Student) userRepository.findById(request.getMemberId())
                .orElseThrow(() ->new EntityNotFoundException("Student with id " + request.getMemberId() + "is not found"));

        List<Authority> authorities = student.updateAuthority(request.getClubId(), request.getMemberPermissions());

        for(Authority authority : authorities) {
            authorityRepository.save(authority);
        }

        Title title = student.updateTitle(request.getClubId(),request.getMemberRole());

        titleRepository.save(title);

        //titleRepository.save(student.updateTitle(request.getClubId(), request.getMemberRole()));
        userRepository.save(student);

        return new MessageResponse(MessageType.SUCCESS, "Permissions are updated");
    }




}
