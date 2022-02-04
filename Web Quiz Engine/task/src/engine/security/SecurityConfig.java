//package engine.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static engine.ApiConfig.API_REGISTER_USER;
//import static engine.ApiConfig.API_ROOT_PATH;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("u0")
////                .password(passwordEncoder.encode("up0"))
////                .roles()
////                .and()
////                .withUser("u1")
////                .password(passwordEncoder.encode("up1"))
////                .roles("REGISTERED_USER")
////                .and()
////                .withUser("u3")
////                .password(passwordEncoder.encode("up3"))
////                .roles("REGISTERED_USER")
////                .and()
////                .withUser("u2")
////                .password(passwordEncoder.encode("up2"))
////                .roles("REGISTERED_USER", "QUIZ_AUTHOR")
////                .and()
////                .withUser("u4")
////                .password(passwordEncoder.encode("up4"))
////                .roles("REGISTERED_USER", "QUIZ_AUTHOR")
////                .and()
////                .passwordEncoder(passwordEncoder);
////
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/actuator/shutdown/**").permitAll()
//                .antMatchers(HttpMethod.POST, API_REGISTER_USER).permitAll()
////                .antMatchers(API_ROOT_PATH + "/**").authenticated()
//                .antMatchers(API_ROOT_PATH + "/**").permitAll()
//                .and()
//                .httpBasic();
//
////        http
////                .authorizeRequests(
////                        request -> request
////                                .antMatchers(HttpMethod.POST, API_REGISTER_USER).permitAll()
////                                .antMatchers(API_ROOT_PATH + "/**").permitAll()
////
////
////                ).formLogin();
////
////        http
////                .authorizeRequests(authorizeRequests ->
////            authorizeRequests.antMatchers(API_REGISTER_USER).anonymous())
////                .authorizeRequests(authorizeRequests ->
////                        authorizeRequests.antMatchers(API_ROOT_PATH + "/**")
////                                .authenticated().anyRequest().permitAll())
////               ;
//
//    }
//
//
//}
