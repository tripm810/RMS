package com.studycase.springboot.rms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailservice;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailservice).passwordEncoder(passwordEncoder());
	}

	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.httpBasic().and()
	        	.authorizeRequests()
	                	.antMatchers("/").hasRole("MEMBER")
	                	.antMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll()
	                	.and()
	                .formLogin()
	                	.loginPage("/login")
	                    .defaultSuccessUrl("/index")
	                    .failureUrl("/login?error")
	                	.permitAll()
	                	.and()
	                .exceptionHandling().accessDeniedPage("/403");
	        http.csrf().disable(); 
	    }
}
