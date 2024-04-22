package katachi.spring.trafficPolicer.domain.trafficPolicer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Officer;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
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
	
	/**
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Officer officer = service.LoginOfficer(username);
		System.out.println(officer);
		if(officer == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		OfficerUserDetails officerUserDetails = new OfficerUserDetails();
		
		officerUserDetails = modelMapper.map(officer,OfficerUserDetails.class);
		
		log.info(officerUserDetails.toString());
		
		return officerUserDetails;
	}

}
