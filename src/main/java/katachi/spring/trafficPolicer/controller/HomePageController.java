package katachi.spring.trafficPolicer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ProcessService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;

@Controller
public class HomePageController {
	
	@Autowired
	UserService service;
	
	@Autowired
	ProcessService pService;
	
	@GetMapping("/homePage")
	public String showHomePage(Model model) {
		model.addAttribute("pageTitle","HOME");
		
		return "homePage";
	}
}
