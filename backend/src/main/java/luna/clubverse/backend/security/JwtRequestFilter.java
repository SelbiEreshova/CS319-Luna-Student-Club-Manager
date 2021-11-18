package luna.clubverse.backend.security;

import luna.clubverse.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Value("${spring.jwt.secret-key}")
    private String key;

    public JwtRequestFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");

        if(authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            String jwtToken = authenticationHeader.substring(7);
            String username = JwtUtil.extractUsername(jwtToken, key);

            UserDetails user = userService.loadUserByUsername(username);

            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                var token = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(token);
            }

        }

        filterChain.doFilter(request,response);
    }
}
