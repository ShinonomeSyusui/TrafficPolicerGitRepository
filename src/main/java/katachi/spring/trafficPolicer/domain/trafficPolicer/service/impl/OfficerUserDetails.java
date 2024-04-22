package katachi.spring.trafficPolicer.domain.trafficPolicer.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 *@author ShinonomeSyusui
 *@version 1.0.0
 */
@Data
public class OfficerUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String controlNumber;
	
	private String password;
	
	private String affiliation;
	
	private String grade;
	
	private String familyName;
	
	private String firstName;
	
	private String authority;
	
	private Collection<GrantedAuthority> authorities;
	
	/**
	 * 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		if (authorities != null) {
			return authorities;
		}
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_USER");
		authorities = new ArrayList<>();
		authorities.add(auth);
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return password;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return controlNumber;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
