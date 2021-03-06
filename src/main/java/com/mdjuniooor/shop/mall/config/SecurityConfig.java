package com.mdjuniooor.shop.mall.config;

import com.mdjuniooor.shop.mall.handler.CustomLoginFailureHandler;
import com.mdjuniooor.shop.mall.handler.CustomLoginSuccessHandler;
import com.mdjuniooor.shop.mall.oauth.CustomOAuth2Provider;
import com.mdjuniooor.shop.mall.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/checkout", "/cart", "/profiles**", "/me/**", "/oauth/me/**", "/question**")
                    .hasAnyAuthority("ROLE_USER", "ROLE_SOCIAL")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .accessDeniedPage("/accessDenied")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/duplicated-login")
                .sessionRegistry(sessionRegistry());

        http.rememberMe()
                .key("remember-me")
                .userDetailsService(customUserDetailsService)
                .tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60*60*24);
    }

    private PersistentTokenRepository getJDBCRepository() {

        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);

        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/");
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() { return new CustomLoginFailureHandler(); }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }// Register HttpSessionEventPublisher

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
