package kg.attractor.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = """
                select email, password, enabled
                from users
                where email = ?
                """;

        String authQuery = """
                select u.email, a.authority
                from users u
                inner join user_authorities ua on u.id = ua.user_id
                inner join authorities a on ua.authority_id = a.id
                where u.email = ?
                """;

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(authQuery);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher("/logout"))
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request

                        .requestMatchers("/", "/login", "/register", "/vacancies").permitAll()
                        .requestMatchers("/resumes").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/profile", "/profile/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/vacancies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/vacancies/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/vacancies").hasAuthority("CREATE_VACANCY")
                        .requestMatchers(HttpMethod.PUT, "/api/vacancies/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/vacancies/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/resumes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/resumes/category/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/resumes/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/resumes").hasAuthority("CREATE_RESUME")
                        .requestMatchers(HttpMethod.PUT, "/api/resumes/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/resumes/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/images/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/images/**").authenticated()

                        .anyRequest().permitAll()
                );

        return http.build();
    }
}