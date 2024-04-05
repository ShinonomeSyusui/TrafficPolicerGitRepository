package katachi.spring.trafficPolicer.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationAndPointFines;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationRecord;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ViolationDetailsWordService;
import katachi.spring.trafficPolicer.form.ViolationRecordSearchForm;

@Controller
public class ViolationRecordSearchFormController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	TrafficViolationNoticeCreation1Controller creation1Controller;
	
	@Autowired
	UserService service;
	
	@Autowired
	ViolationDetailsWordService violationDetailsWordService;
	
	@GetMapping("violationRecordSearchForm")
	public String showViolationRecordSearchForm(Model model, @ModelAttribute ViolationRecordSearchForm form) {
		session.removeAttribute("searchInfo");
		List<ViolationAndPointFines> violationPointFines = service.getAllViolationPointFines();
		model.addAttribute("violationPointFines",violationPointFines);
		model.addAttribute("pageTitle","過去の違反履歴検索");
		return "violationRecordSearch/violationRecordSearchForm";
	}
	
	@PostMapping(value = "violationRecordSearchForm",params = "back")
	public String returnViolationRecordSearchForm(Model model,@ModelAttribute ViolationRecordSearchForm form) {
		List<ViolationAndPointFines> violationPointFines = service.getAllViolationPointFines();
		model.addAttribute("violationPointFines",violationPointFines);
		model.addAttribute("pageTitle","過去の違反履歴検索");
		ViolationRecordSearchForm vForm = (ViolationRecordSearchForm)session.getAttribute("searchInfo");
		form.setEndDay(vForm.getEndDay());
		form.setStartDay(vForm.getStartDay());
		form.setViolationLocation(vForm.getViolationLocation());
		form.setViolation(vForm.getViolation());
		session.removeAttribute("searchInfo");
		return "violationRecordSearch/violationRecordSearchForm";
	}
	
	@PostMapping("/violationRecordSearchForm")
	public String searchViolationRecord(Model model, @ModelAttribute @Validated ViolationRecordSearchForm form, BindingResult bindingResult) {
		
		ViolationRecordSearchForm reform = (ViolationRecordSearchForm)session.getAttribute("searchInfo");
		if(reform != null) {
			form.setStartDay(reform.getStartDay());
			form.setEndDay(reform.getEndDay());
			form.setViolationLocation(reform.getViolationLocation());
			form.setViolation(reform.getViolation());
			System.out.println(form);
			
			List<ViolationRecord> vRecord = service.getViolationRecord(form);
			model.addAttribute("vRecord",vRecord);
			model.addAttribute("pageTitle","過去の違反履歴検索結果");
			
			return "violationRecordSearch/violationRecordSearchResult";
		}
		
		
		if(form.getStartDay() == null){
			bindingResult.rejectValue("startDay", null,"検索開始日を入力して下さい。" );
			model.addAttribute("startDay","検索開始日を入力して下さい。");
		}
		if(form.getEndDay() == null) {
			bindingResult.rejectValue("endDay", null,"検索終了日を入力してください。" );
			model.addAttribute("endDay","検索終了日を入力してください。");
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMsg","※未入力の項目があります。");
			System.out.println(bindingResult);
			System.out.println(model);
			return showViolationRecordSearchForm(model, form);
		}
		
		Calendar endDays = Calendar.getInstance();
		endDays.setTime(form.getEndDay());
		endDays.add(Calendar.DAY_OF_MONTH, 1);
		
		Date afterReturnEndDay = endDays.getTime();
		
		form.setEndDay(afterReturnEndDay);
		
		session.setAttribute("searchInfo", form);
		
		List<ViolationRecord> vRecord = service.getViolationRecord(form);
		
		if (vRecord.isEmpty()) {
		    model.addAttribute("errorMsg", "※指定された期間内に違反記録は見つかりませんでした。");
		}
		model.addAttribute("pageTitle","過去の違反歴検索結果");
		model.addAttribute("vRecord",vRecord);
		
		System.out.println(vRecord);
		
		return "violationRecordSearch/violationRecordSearchResult";
	}
	
	
	@PostMapping(value = "/violationRecordSearch", params = "id")
	public String searchViolationRecordOne(@RequestParam("id")int id, Model model,@ModelAttribute ViolationRecord vRecord) {
		System.out.println(id);
		if(id != 0) {
			vRecord = service.getViolationRecordDetails(id);
			if(vRecord != null) {
				Map<String,Integer> genderMap = violationDetailsWordService.getGenderMap();
				String genderSelect = violationDetailsWordService.getGender(vRecord.getLicence().getGender());
				String vehicleSelect = violationDetailsWordService.getVehicle(vRecord.getVehicle());
				String heavyTowingVehicleSelect = vRecord.getHeavyTowingVehicleSelect();
				model.addAttribute("genderMap",genderMap);
				model.addAttribute("genderSelect",genderSelect);
				model.addAttribute("vehicleSelect",vehicleSelect);
				model.addAttribute("heavyTowingVehicle",heavyTowingVehicleSelect);
				model.addAttribute("vRecord",vRecord);
				model.addAttribute("publicationTime",parseDateToWareki(vRecord.getPublicationTime(), "GGGGyy年MM月dd日HH時mm分"));
				model.addAttribute("birthday",parseDateToWareki(vRecord.getLicence().getBirthday(), "GGGGyy年MM月dd日"));
				model.addAttribute("warekiIssueDate",parseDateToWareki(vRecord.getLicence().getIssueDate(),"GGGGyy年MM月dd日"));
				model.addAttribute("warekiExpiryDate",parseDateToWareki(vRecord.getLicence().getExpiryDate(), "GGGGyy年MM月dd日"));
				model.addAttribute("warekiViolationTimeDetailes",parseLocalDateTimeToWareki(vRecord.getDateAndTimeOfViolation(), "GGGGyy年MM月dd日HH時mm分頃"));
				model.addAttribute("appearanceDate",parseDateToWareki(vRecord.getAppearanceDate(), "GGGGyy年MM月dd日  午後３時まで"));
				model.addAttribute("speedMsg",creation1Controller.speedingMessage(vRecord.getSpeed(), vRecord.getResultOverSpeed(), vRecord.getLegalSpeed(),vRecord.getOverSpeed()));
				model.addAttribute("pageTitle","過去の違反歴詳細");
				System.out.println(vRecord);
				System.out.println(model);
				System.out.println(vRecord.getSupplementaryColumn());
				return "violationRecordSearch/registrationResults";
			}
		}
		return "topPage";
		
	}
	
	
	
	
	public String parseLocalDateTimeToWareki(LocalDateTime date,String pattern) {
		   String result = null;
		   Locale locale = new Locale("ja","JP","JP");
		   DateFormat warekiFormat = new SimpleDateFormat(pattern, locale);
		   if(Objects.nonNull(date)) {
		       Date convertedDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
		       result = warekiFormat.format(convertedDate);
		   }
		   return result;
		}
	
	/*Date型を和暦など文字列にに変換する処理*/
	public String parseDateToWareki(Date date,String pattern) {
		String result = null;
		Locale locale = new Locale("ja","JP","JP");
		DateFormat warekiFormat = new SimpleDateFormat(pattern ,locale);
		if(Objects.nonNull(date)) {
			result = warekiFormat.format(date);
		}
		return result;
	}
	
}

