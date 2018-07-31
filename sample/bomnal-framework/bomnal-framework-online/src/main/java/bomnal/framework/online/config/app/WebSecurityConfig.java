/**
 * Copyright 2017 All rights reserved by 전국렌터카공제조합
 */
package bomnal.framework.online.config.app;

import hone.bom.web.simple.config.app.BaseWebSecurityConfig;
import hone.security.authentication.HoneAuthenticationFailureHandler;
import hone.security.authentication.HoneAuthenticationProvider;
import hone.security.authentication.HoneAuthenticationSuccessHandler;
import hone.security.authentication.HoneLogoutSuccessHandler;
import hone.security.authentication.config.HoneLoginConfigurer;
import hone.security.authentication.config.HoneUserDetailsManagerConfigurer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * {논리명}
 * 
 * <p>
 * {설명}
 * 
 * <p>
 * <b>변경이력:</b>
 * 
 * <pre>
 * - Gildong Hong(gildong) 2017. 8. 8. 초기작성
 * </pre>
 * @author Gildong Hong(gildong)
 *
 */

@Configuration
@Import(value = {BaseWebSecurityConfig.class})
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private HoneAuthenticationProvider authenticationProvider;

    @Autowired
    private HoneAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private HoneAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private HoneLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.apply(new HoneUserDetailsManagerConfigurer<AuthenticationManagerBuilder, UserDetailsService>(authenticationProvider, userDetailsService));
    }

    @Override
    public void configure(final WebSecurity web) {

        String[] ignorePath = new String[]{};

        if (applicationContext.containsBean("fw.security.ignore.path")) {
            @SuppressWarnings("unchecked")
            List<String> ignorePathList = applicationContext.getBean("fw.security.ignore.path", List.class);
            ignorePath = ignorePathList.toArray(new String[ignorePathList.size()]);
        }
        web.ignoring().antMatchers(ignorePath);

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
        .sessionManagement()
        .and()
        .authorizeRequests() //
        .anyRequest().authenticated() //
        .and()
        .apply(new HoneLoginConfigurer<HttpSecurity>())
        .loginProcessingUrl("/security/login") //
        .successHandler(authenticationSuccessHandler) //
        .failureHandler(authenticationFailureHandler) //
        .and()
        .logout()
        .logoutUrl("/security/logout").logoutSuccessHandler(logoutSuccessHandler) //
        .invalidateHttpSession(true)
        .and()
        .csrf().disable()
        .headers().frameOptions().sameOrigin()
        ;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}