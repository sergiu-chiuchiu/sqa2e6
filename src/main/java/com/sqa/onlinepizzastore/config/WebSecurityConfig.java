package com.sqa.onlinepizzastore.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.sqa.onlinepizzastore.services.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private DataSource dataSource;
	
	// instantiates a password encoder
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Setting Service to find User in the database.
        // And Setting PassswordEncoder
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Disable CSRF protection
		http.csrf().disable();
		
		 // The pages that does not require login:
		http.authorizeRequests().antMatchers("/").permitAll();
		
		// /userInfo page requires login as ROLE_USER, ROLE_OPERATOR or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/user").access("hasAnyRole('ROLE_USER', 'ROLE_OPERATOR', 'ROLE_ADMIN')");
		
		//For Operator or Admin
		http.authorizeRequests().antMatchers("/operator", "/auth/privacy").access("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')");
		
		//For ADMIN only
		http.authorizeRequests().antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')");
		
		// When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/auth/403");
		
		// Config for Login Form
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")

			.loginPage("/auth/login")
			.defaultSuccessUrl("/user")
			.failureUrl("/auth/login?error=true")

			.usernameParameter("email")
			.passwordParameter("password")
			 // Config for Logout Page
            .and().logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/logoutSuccessful");
		
		// Config Remember Me.
		http.authorizeRequests().and()
			.rememberMe().tokenRepository(this.persistentTokenRepository())
			.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	}
	
	// Token stored in Table (Persistent_Logins)
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}
	
	// Token stored in Memory (Of Web Server).
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
	}
	
}