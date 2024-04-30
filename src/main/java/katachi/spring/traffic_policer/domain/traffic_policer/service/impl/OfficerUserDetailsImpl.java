package katachi.spring.traffic_policer.domain.traffic_policer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import katachi.spring.traffic_policer.domain.traffic_policer.model.Officer;
import katachi.spring.traffic_policer.domain.traffic_policer.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Service
@Slf4j
public class OfficerUserDetailsImpl implements UserDetailsService{
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Officer officer = service.LoginOfficer(username);
		if(officer == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		log.debug(officer.toString());
		
		OfficerUserDetails officerUserDetails = new OfficerUserDetails();
		officerUserDetails = modelMapper.map(officer,OfficerUserDetails.class);
		log.debug(officerUserDetails.toString());
		
		return officerUserDetails;
	}
}
