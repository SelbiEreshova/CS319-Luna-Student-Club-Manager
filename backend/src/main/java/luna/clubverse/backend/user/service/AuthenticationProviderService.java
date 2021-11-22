package luna.clubverse.backend.user.service;

/**
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationProviderService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails user = userService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new BadCredentialsException("The wrong password");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,user.getPassword(), user.getAuthorities());

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(),user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> auth) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth);
    }

}
 */
