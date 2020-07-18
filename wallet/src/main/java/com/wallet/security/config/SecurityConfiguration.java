package com.wallet.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().and().sessionManagement() 
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
		.antMatchers("**")
		.permitAll().anyRequest().authenticated();
		
		// quando receber alguma requisição na API essa classe será chamada
		// estamos pedindo pra desabilitar a segurança de qualquer requisição para qualquer rota
	}
}
