package ex.config;

import ex.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySeurifyConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.
                csrf(csrf->csrf.disable()).
                authorizeHttpRequests(auth-> {
                    auth.requestMatchers("register","signup","signupindb","admindashboard").permitAll();
                    auth.requestMatchers("/user/**").hasAnyRole("USER");
                    auth.requestMatchers("/admin/**").hasAnyRole("ADMIN");
                    auth.anyRequest().authenticated();
                }).
                        formLogin(form->
                        form.loginPage("/login").
                                successHandler(new SuccessHandlerApp()).
                        permitAll()).
                        build();
    }

    @Autowired
    LoginService loginService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(loginService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}