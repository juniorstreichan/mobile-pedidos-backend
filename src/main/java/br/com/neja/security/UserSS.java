package br.com.neja.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.neja.domain.Cliente;
import br.com.neja.domain.enums.Perfil;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {
	}

	public UserSS(Integer id, String email, String senha, Set<Perfil> authorities) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = authorities.stream().map(x -> new SimpleGrantedAuthority(x.getDescicao()))
				.collect(Collectors.toList());
		;
	}

	public UserSS(Cliente cli) {

		this.email = cli.getEmail();
		this.senha = cli.getSenha();
		this.id = cli.getId();
		this.authorities = cli.getPerfis().stream().map(x -> new SimpleGrantedAuthority(x.getDescicao()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {

		return senha;
	}

	@Override
	public String getUsername() {

		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	public boolean hashRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescicao()));
	}

}
