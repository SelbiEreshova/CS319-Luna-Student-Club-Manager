package luna.clubverse.backend.security;

import luna.clubverse.backend.user.service.AuthenticationProviderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProviderService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration(AuthenticationProviderService authenticationProviderService, JwtRequestFilter jwtRequestFilter) {
        this.authenticationProviderService = authenticationProviderService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login-request").permitAll()
                //.antMatchers("/studentHomePage/{userId}").permitAll()
                .antMatchers("/home").permitAll()

                .antMatchers("/admin.html").permitAll()

                .antMatchers("/event_list").permitAll()
                .antMatchers("/event/event_list").permitAll()
                .antMatchers("/club/add").permitAll()
                .antMatchers("/event/{clubId}/addToClub").permitAll()
                .antMatchers("/admin_event_list").permitAll()
                .antMatchers("/club_event_list/**").permitAll()
                .antMatchers("/event/get/1").permitAll()
                //.antMatchers("/admin.html").permitAll()
                .antMatchers("/app/**").permitAll()
                .antMatchers("/club_home_page_student/{clubId}").permitAll()

                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable()
                .csrf().disable();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
