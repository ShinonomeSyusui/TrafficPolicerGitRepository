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
import lombok.extern.slf4j.Slf4j;


/**
 * 過去の取締歴検索フォームの表示と内部処理のクラス
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Controller
@Slf4j
public class ViolationRecordSearchFormController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	TrafficViolationNoticeCreation1Controller creation1Controller;
	
	@Autowired
	UserService service;
	
	@Autowired
	ViolationDetailsWordService violationDetailsWordService;
	
	
	/**
	 * 過去の取締歴検索画面を表示する処理
	 * @param model
	 * @param form
	 * @return 過去の取締歴検索フォームへ遷移
	 */
	@GetMapping("violationRecordSearchForm")
	public String showViolationRecordSearchForm(Model model, @ModelAttribute ViolationRecordSearchForm form) {
		
		//セッション情報を初期化
		session.removeAttribute("searchInfo");
		
		List<ViolationAndPointFines> violationPointFines = service.getAllViolationPointFines();
		
		model.addAttribute("violationPointFines",violationPointFines);
		model.addAttribute("pageTitle","過去の違反履歴検索");
		
		return "violationRecordSearch/violationRecordSearchForm";
	}
	
	
	/**
	 * 取締歴検索結果画面から検索フォームに戻る時の処理
	 * @param model
	 * @param form : ViolationRecordSearchFormのフィールド値全て
	 * @return 検索条件をそのままにして、検索フォームへ戻る
	 */
	@PostMapping(value = "violationRecordSearchForm",params = "back")
	public String returnViolationRecordSearchForm(Model model,@ModelAttribute ViolationRecordSearchForm form) {
		
		//検索用の全違反リスト
		List<ViolationAndPointFines> violationPointFines = service.getAllViolationPointFines();
		
		model.addAttribute("violationPointFines",violationPointFines);
		model.addAttribute("pageTitle","過去の違反履歴検索");
		
		//セッションから検索条件情報を取得
		ViolationRecordSearchForm vForm = (ViolationRecordSearchForm)session.getAttribute("searchInfo");
		
		Calendar endDays = Calendar.getInstance();
		
		endDays.setTime(vForm.getEndDay());
		endDays.add(Calendar.DAY_OF_MONTH, -1);
		
		Date afterResultEndDay = endDays.getTime();
		
		form.setEndDay(afterResultEndDay);
		form.setStartDay(vForm.getStartDay());
		form.setViolationLocation(vForm.getViolationLocation());
		form.setViolation(vForm.getViolation());
		
		//セッション情報を初期化
		session.removeAttribute("searchInfo");
		
		return "violationRecordSearch/violationRecordSearchForm";
	}
	
	
	/**
	 * 入力チェックと検索結果画面表示の処理
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return 入力エラーが無ければ、検索結果画面へ遷移する。エラーがあればエラーメッセージと共に入力フォームへ戻る
	 */
	@PostMapping("/violationRecordSearchForm")
	public String searchViolationRecord(Model model, @ModelAttribute @Validated ViolationRecordSearchForm form, BindingResult bindingResult) {
		
		//セッションから検索条件の情報を取得
		ViolationRecordSearchForm reform = (ViolationRecordSearchForm)session.getAttribute("searchInfo");
		
		//検索条件があればその条件に従って検索結果を表示する
		if (reform != null) {
			form.setStartDay(reform.getStartDay());
			form.setEndDay(reform.getEndDay());
			form.setViolationLocation(reform.getViolationLocation());
			form.setViolation(reform.getViolation());
			
			log.info(form.toString());
			
			//検索結果の取締歴をリストに追加
			List<ViolationRecord> vRecord = service.getViolationRecord(form);
			
			model.addAttribute("vRecord",vRecord);
			model.addAttribute("pageTitle","過去の違反履歴検索結果");
			
			return "violationRecordSearch/violationRecordSearchResult";
		}
		
		//入力チェックの処理
		if (form.getStartDay() == null){
			bindingResult.rejectValue("startDay", null,"検索開始日を入力して下さい。" );
			model.addAttribute("startDay","検索開始日を入力して下さい。");
		}
		
		if (form.getEndDay() == null) {
			bindingResult.rejectValue("endDay", null,"検索終了日を入力してください。" );
			model.addAttribute("endDay","検索終了日を入力してください。");
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMsg","※未入力の項目があります。");
			
			log.info(bindingResult.toString());
			log.info(model.toString());
			
			return showViolationRecordSearchForm(model, form);
		}
		
		//入力チェック後、検索日時の範囲設定処理
		Calendar endDays = Calendar.getInstance();
		
		endDays.setTime(form.getEndDay());
		endDays.add(Calendar.DAY_OF_MONTH, 1);
		
		Date afterReturnEndDay = endDays.getTime();
		
		form.setEndDay(afterReturnEndDay);
		
		//セッションへ検索条件を登録
		session.setAttribute("searchInfo", form);
		
		//検索結果をリストに追加
		List<ViolationRecord> vRecord = service.getViolationRecord(form);
		
		//検索結果が無くリストが空っぽだった場合エラーメッセージを結果画面に表示させる
		if (vRecord.isEmpty()) {
		    model.addAttribute("errorMsg", "※指定された期間内に違反記録は見つかりませんでした。");
		}
		model.addAttribute("pageTitle","過去の違反歴検索結果");
		model.addAttribute("vRecord",vRecord);
		
		log.info(vRecord.toString());
		
		return "violationRecordSearch/violationRecordSearchResult";
	}
	
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @param vRecord
	 * @return
	 */
	@PostMapping(value = "/violationRecordSearch", params = "id")
	public String searchViolationRecordOne(@RequestParam("id")int id, Model model,@ModelAttribute ViolationRecord vRecord) {
		
		log.info("ID:{}",id);
		
		if (id != 0) {
			vRecord = service.getViolationRecordDetails(id);
			if (vRecord != null) {
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
				model.addAttribute("pageTitle","違反歴詳細");
				
				log.info(vRecord.toString());
				log.info(model.toString());
				log.info(vRecord.getSupplementaryColumn().toString());
				
				return "violationRecordSearch/registrationResults";
			}
		}
		return "topPage";
	}
	
	/**
	 *LocalDateTimeを和暦に変換する処理 
	 * @param date
	 * @param pattern
	 * @return String型の変数に返す
	 */
	public String parseLocalDateTimeToWareki(LocalDateTime date,String pattern) {
		
		   String result = null;
		   Locale locale = new Locale("ja","JP","JP");
		   
		   DateFormat warekiFormat = new SimpleDateFormat(pattern, locale);
		   
		   if (Objects.nonNull(date)) {
		       Date convertedDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
		       
		       result = warekiFormat.format(convertedDate);
		   }
		   return result;
		}
	
	/**
	 * Date型を和暦など文字列にに変換する処理
	 * @param date
	 * @param pattern
	 * @return String型の変数に返す
	 */
	public String parseDateToWareki(Date date,String pattern) {
		
		String result = null;
		Locale locale = new Locale("ja","JP","JP");
		
		DateFormat warekiFormat = new SimpleDateFormat(pattern ,locale);
		
		if (Objects.nonNull(date)) {
			result = warekiFormat.format(date);
		}
		return result;
	}
}

