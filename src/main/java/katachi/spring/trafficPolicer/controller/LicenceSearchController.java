package katachi.spring.trafficPolicer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Licence;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ProcessService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
import katachi.spring.trafficPolicer.form.LicenceSearchForm;



@Controller
public class LicenceSearchController {
	
	@Autowired
	Licence licence;
	
	@Autowired
	ProcessService pService;
	
	@Autowired
	UserService service;
	
	@GetMapping("/licenceSearch")
	public String showLicenceSearch(Model model, @ModelAttribute LicenceSearchForm form) {
		model.addAttribute("pageTitle","免許証検索ページ");
		return "licenceSearch/licenceSearch";
	}
	
	@PostMapping("/licenceSearch")
	public String search(Model model,@ModelAttribute @Validated LicenceSearchForm form,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMsg","※免許証番号を入力して下さい");
			return showLicenceSearch(model, form);
		}
		model.addAttribute("pageTitle","検索結果");
		licence = service.getOneLicence(form.getLicenceNumber());
		model.addAttribute("licence",licence);
		if(licence != null) {
			model.addAttribute("birthday",pService.parseDateToWareki(licence.getBirthday(), "GGGGyy年MM月dd日"));
			model.addAttribute("expiryDate",pService.parseDateToWareki(licence.getExpiryDate(), "GGGGyy年MM月dd日"));
			model.addAttribute("issueDate",pService.parseDateToWareki(licence.getIssueDate(), "GGGGyy年MM月dd日"));
			System.out.println(licence);
			return "licenceSearch/licencesearchResult";
		}else {
			model.addAttribute("errorMsg","※該当の免許証データが存在しません");
			return "licenceSearch/licencesearchResult";
		}
		
	}
	
}
