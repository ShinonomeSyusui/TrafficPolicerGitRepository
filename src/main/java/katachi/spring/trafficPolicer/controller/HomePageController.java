package katachi.spring.trafficPolicer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ProcessService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
/**
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Controller
public class HomePageController {
	
	@Autowired
	UserService service;
	
	@Autowired
	ProcessService pService;
	
	/**
	 * ホーム画面を表示する処理
	 * @param model
	 * @return
	 */
	@GetMapping("/homePage")
	public String showHomePage(Model model) {
		model.addAttribute("pageTitle","HOME");  //ヘッダーへ画面の名前を表示するための処理
		
		return "homePage";
	}
}
