package com.ufc.br.config;

import javax.crypto.EncryptedPrivateKeyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ufc.br.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()

		.antMatchers("/Contato").permitAll()
		.antMatchers("/Galeria").permitAll()
		.antMatchers("/Sobre").permitAll()
		.antMatchers("/index").permitAll()
		.antMatchers("/produto/listar").permitAll()
		.antMatchers("/produto/excluir/*").permitAll()
		.antMatchers("/produto/FormularioProduto").permitAll()
		.antMatchers("/produto/salvar").permitAll()
		.antMatchers("/pessoa/*").permitAll()
		.antMatchers("/PolosCloset").permitAll()
		//.antMatchers("/pessoa/formulario").hasRole("USER") //Somente pessoa com papel "USER" acessa /pessoa/formulario
		.antMatchers("/pessoa/formulario").permitAll()
		//.antMatchers("/pessoa/salvar").hasAnyRole("USER","ADMIN") // Pessoa com papel "USER" ou "ADMIN" acessa /pessoa/salvar
		.antMatchers("/pessoa/salvar").permitAll()
		.antMatchers("/pessoa/listar").permitAll() // /pessoa/listar todo mundo pode acessar
		
		.anyRequest().authenticated() // o resto precisa está autenticado

		.and()
		.formLogin()
		.loginPage("/pessoa/logar") // Esse é o controller que chama nosso formulario
		.permitAll() //permitir acesso para essa url "entrar"

		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		.and()
		.logout()
		.logoutSuccessUrl("/pessoa/logar?logout") // logout sucesso
		.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService())
		.passwordEncoder(new BCryptPasswordEncoder());
		//auth.inMemoryAuthentication()
		//.withUser("pedro").password("123").roles("ADMIN");
		//auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**","/Imagem/**"); // ignora e permite uri's com esses arquivos
	}

}
