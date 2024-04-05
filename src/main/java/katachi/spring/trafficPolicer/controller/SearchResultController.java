package katachi.spring.trafficPolicer.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;

@Controller
public class SearchResultController {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserService service;

	
}
