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
        String userQuery = "select email, password, enabled " +
                "from users " +
                "where email = ?";

        String authQuery = "select u.email, a.authority " +
                "from users u " +
                "inner join user_authorities ua on u.id = ua.user_id " +
                "inner join authorities a on ua.authority_id = a.id " +
                "where u.email = ?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(authQuery);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request

                        .requestMatchers(HttpMethod.POST, "/users").permitAll()

                        .requestMatchers(HttpMethod.GET, "/vacancies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vacancies/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/resumes").hasAuthority("CREATE_RESUME")
                        .requestMatchers(HttpMethod.POST, "/vacancies").hasAuthority("CREATE_VACANCY")

                        .requestMatchers(HttpMethod.PUT, "/users/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/users/**").authenticated()

                        .requestMatchers(HttpMethod.PUT, "/resumes/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/resumes/**").authenticated()

                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/vacancies/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/responses/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/responses/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/images/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/images/**").permitAll()

                        .anyRequest().permitAll()
                );

        return http.build();
    }
}