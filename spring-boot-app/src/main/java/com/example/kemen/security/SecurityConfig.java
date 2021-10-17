package com.example.kemen.security;

import com.example.kemen.entities.Role;
import com.example.kemen.entities.User;
import com.example.kemen.repos.RoleCrud;
import com.example.kemen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final UserSuccessHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям
    @Autowired
    private UserService userService;
    @Autowired
    private RoleCrud roleCrud;

    public SecurityConfig(@Qualifier("userServiceImp") UserDetailsService userDetailsService, UserSuccessHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/static/**", "/").permitAll()
                .antMatchers("/**", "/admin/list").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .successHandler(successUserHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

    // Необходимо для шифрования паролей
    // В данном примере не используется, отключен
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public PrincipalExtractor principalExtractor() {
        return map -> {
            String googleId = (String) map.get("sub");
            String login = (String) map.get("email");
            String name = (String) map.get("given_name");
            String surname = (String) map.get("family_name");
            String password = "123";
            User user;
            if ((user = userService.getUserByGoogleId(googleId)) != null) {
                return user;
            }
            User user1 = new User(login, name, surname, password, googleId);
            user1.addAuthority(roleCrud.getAuthorityByRole(Role.ROLE_USER));
            userService.addUser(user1);
            return user1;
        };
    }
}
