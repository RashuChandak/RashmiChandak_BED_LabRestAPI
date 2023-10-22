package com.gl.sfr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.sfr.services.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Bean    
	public UserDetailsService userDetailsSerivce() {
        	 return new UserDetailsServiceImpl();
         }
    @Bean
    public BCryptPasswordEncoder bCryptpasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
   authenticationProvider.setUserDetailsService(userDetailsService());
   authenticationProvider.setPasswordEncoder(bCryptpasswordEncoder());
   return authenticationProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/","/student/save","/student/showFormForAdd","/student/403").hasAnyAuthority("USER","ADMIN")
            .antMatchers("/student/showFormForUpdate","/student/delete").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
            .and()
            .logout().logoutSuccessUrl("/login").permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/student/403")
            .and()
            .cors().and().csrf().disable();
    }
}
