package ru.spb.iac.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/**")
        .authenticated();
		http.csrf().disable();
	}

	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER");
		}
	}
}
