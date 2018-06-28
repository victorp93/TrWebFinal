package com.ufc.br.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Pessoa implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String CPF;
	private String end;
	private String data_N;
	private String login;
	private String senha;
	public Long getId() {
		return id;

	}
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
			name = "pessoas_roles", 
			joinColumns = @JoinColumn(
					name = "pessoa_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "papel")) 
	private List<Role> roles;





	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getData_N() {
		return data_N;
	}
	public void setData_N(String data_N) {
		this.data_N = data_N;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}