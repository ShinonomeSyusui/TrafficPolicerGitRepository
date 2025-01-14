package katachi.spring.traffic_policer.controller;

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
	 * @return loginPageへ遷移する
	 */
	@GetMapping("/loginPage")
	public String showLoginPage() {
		session.invalidate();
		return "loginPage";
	}
}
