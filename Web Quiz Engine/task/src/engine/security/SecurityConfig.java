package engine.security;

import engine.persistencelayer.UserEntityRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
//                .csrf().and().cors().disable()
                .csrf().disable()
                .authorizeRequests()
//                .anyRequest().permitAll()
                .antMatchers(HttpMethod.POST, "/actuator/shutdown/**").permitAll()
//                .antMatchers("/h2-console/").permitAll()
//                .mvcMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, API_REGISTER_USER + "/**").permitAll()
                .antMatchers(API_ROOT_PATH + "/**").authenticated()
//                .antMatchers(API_ROOT_PATH + "/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().disable();
    }


}
