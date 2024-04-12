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


/**
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Controller
public class LicenceSearchController {
	
	@Autowired
	Licence licence;
	
	@Autowired
	ProcessService pService;
	
	@Autowired
	UserService service;
	
	
	/**
	 * 免許証検索フォームを表示する処理
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping("/licenceSearch")
	public String showLicenceSearch(Model model, @ModelAttribute LicenceSearchForm form) {
		model.addAttribute("pageTitle","免許証検索ページ");
		
		return "licenceSearch/licenceSearch";
	}
	
	
	/**
	 * 検索開始ボタンを押したときの処理
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/licenceSearch")
	public String search(Model model,@ModelAttribute @Validated LicenceSearchForm form,BindingResult bindingResult) {
		//免許証番号を入力せずに検索をした時の処理
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMsg","※免許証番号を入力して下さい");
			
			return showLicenceSearch(model, form);
		}
		//入力チェックをして、検索結果画面を表示する処理
		model.addAttribute("pageTitle","検索結果");
		licence = service.getOneLicence(form.getLicenceNumber());
		model.addAttribute("licence",licence);
		
		if (licence != null) {
			model.addAttribute("birthday",pService.parseDateToWareki(licence.getBirthday(), "GGGGyy年MM月dd日"));
			model.addAttribute("expiryDate",pService.parseDateToWareki(licence.getExpiryDate(), "GGGGyy年MM月dd日"));
			model.addAttribute("issueDate",pService.parseDateToWareki(licence.getIssueDate(), "GGGGyy年MM月dd日"));
			System.out.println(licence);
			
			return "licenceSearch/licencesearchResult";
		}else {
			//検索結果がNullだった時の処理
			model.addAttribute("errorMsg","※該当の免許証データが存在しません");
			
			return "licenceSearch/licencesearchResult";
		}
		
	}
	
}
