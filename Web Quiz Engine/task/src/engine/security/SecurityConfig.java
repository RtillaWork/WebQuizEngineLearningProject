package engine.security;

import engine.persistencelayer.UserEntityRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static engine.ApiConfig.API_REGISTER_USER;
import static engine.ApiConfig.API_ROOT_PATH;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserEntityRepositoryService userEntityRepositoryService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEntityRepositoryService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/actuator/shutdown/**").permitAll()
//                .antMatchers("/console/**").permitAll()
                .antMatchers(HttpMethod.POST, API_REGISTER_USER + "/**").permitAll()
                .antMatchers(API_ROOT_PATH + "/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().disable();
//        http.headers().frameOptions().disable();  // h2-console
    }
}