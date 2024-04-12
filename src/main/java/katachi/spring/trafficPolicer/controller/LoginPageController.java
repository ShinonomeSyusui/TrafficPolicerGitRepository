package katachi.spring.trafficPolicer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
/**
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Controller
public class LoginPageController {

	@Autowired
	HttpSession session;
	
	/**
	 * ログイン画面を表示する処理
	 * @return
	 */
	@GetMapping("/loginPage")
	public String showLoginPage() {
		session.invalidate();
		return "loginPage";
	}
}
