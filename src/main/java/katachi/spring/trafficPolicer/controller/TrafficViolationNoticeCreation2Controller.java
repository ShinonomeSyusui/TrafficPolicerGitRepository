package katachi.spring.trafficPolicer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.VehicleTypeName;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Violation;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationFines;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;

@Controller
public class TrafficViolationNoticeCreation2Controller {
	
	@Autowired
	UserService service;
	
	@GetMapping("trafficViolationNoticeCreation2")
	public String showTrafficViolationNoticeCreation2(Model model) {
		model.addAttribute("pageTitle","交通違反告知書作成2");
		List<VehicleTypeName> vehicleType = service.getVehicleTypeName();
		List<ViolationFines> fines = service.getFinesAll();
		List<Violation> violation = service.getViolationAll();
		/*ArrayList<String> vehicleSize = new ArrayList<>*/
		ArrayList<Integer> speed = new ArrayList<>(Arrays.asList(15, 20, 25, 30, 35, 40, 50));
		model.addAttribute("vehicleType",vehicleType);
		model.addAttribute("speed",speed);
 		model.addAttribute("fines",fines);
		model.addAttribute("violation",violation);
		System.out.println(violation);
		return "/trafficViolationNoticeCreation2";
	}

}
