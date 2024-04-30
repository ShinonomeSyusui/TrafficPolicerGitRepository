package katachi.spring.traffic_policer.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import katachi.spring.traffic_policer.domain.traffic_policer.service.ProcessService;
import katachi.spring.traffic_policer.domain.traffic_policer.service.UserService;
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
	
	@Autowired
	MessageSource source;
	
	/**
	 * ホーム画面を表示する処理
	 * @param model
	 * @return homePageへ遷移する
	 */
	@GetMapping("/homePage")
	public String showHomePage(Model model) {
		
		model.addAttribute("pageTitle",source.getMessage("home", null, Locale.JAPAN));
		
		return "homePage";
	}
}
