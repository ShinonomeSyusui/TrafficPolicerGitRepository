package katachi.spring.trafficPolicer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginPageController {

	@Autowired
	HttpSession session;
	
	@GetMapping("/loginPage")
	public String showLoginPage() {
		session.invalidate();
		return "loginPage";
	}
}
