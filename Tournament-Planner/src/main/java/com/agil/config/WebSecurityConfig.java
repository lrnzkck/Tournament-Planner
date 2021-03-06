package com.agil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/registration").permitAll()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/teams/**").permitAll()
			.antMatchers("/games/**").permitAll()
			.antMatchers("/game/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/dataprotection").permitAll()
			.antMatchers("/impressum").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/team/**").permitAll()
			.antMatchers("/avatar/**").permitAll()
			.anyRequest().authenticated()
			.and().rememberMe().key("sSecretKey")
			.and()
				.formLogin().loginPage("/login").permitAll()
			.and()
				.logout().logoutSuccessUrl("/login").permitAll()
	//		.and()
	//			.exceptionHandling().accessDeniedPage("/403")
			.and()
				.csrf().disable();
			
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
