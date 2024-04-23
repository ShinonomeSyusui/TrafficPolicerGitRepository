package katachi.spring.trafficPolicer.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.DriversSubInfo;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Job;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Licence;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.VehicleTypeName;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationAndPointFines;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationVehicle;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ProcessService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ViolationDetailsWordService;
import katachi.spring.trafficPolicer.form.TrafficViolationNoticeCreationForm;
import lombok.extern.slf4j.Slf4j;

/**
 * TrafficViolationNoticeCreationFormへのアクセス及び内部処理のクラス
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Controller
@Slf4j
public class TrafficViolationNoticeCreation1Controller {
	@Autowired
	ViolationDetailsWordService violationDetailsWordService;
	
	@Autowired
	Licence licence;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserService service;
	
	@Autowired
	ProcessService pService;
	
	@Autowired
	MessageSource source;
	
	
	/**
	 * 交通違反告知書作成フォームを表示する
	 * @param form : TrafficViolationNoticeCreationFormのフィールド値全て
	 * @param model
	 * @return 交通違反告知書作成フォームへ
	 */
	@GetMapping("/trafficViolationNoticeCreation1")
	public String showTrafficViolationNoticeCreation1(TrafficViolationNoticeCreationForm form, Model model) {
		
		model.addAttribute("pageTitle",source.getMessage("violationCreation", null,Locale.JAPAN));
		model.addAttribute("jobItems",service.jobSelect());
		model.addAttribute("prefectures",pService.todoufukenList());
		
		vehicleSize(model);
		allList(model);
		allViolationDetailes(model);
		
		return "trafficViolationNoticeCreation/trafficViolationNoticeCreation1";
	}
	
	
	/**
	 * 入力内容確認画面でキャンセルボタンが押された時の処理
	 * @param model
	 * @param form : TrafficViolationNoticeCreationFormのフィールド値全て
	 * @return 交通違反告知書作成フォームへ
	 */
	@PostMapping(value = "/trafficViolationNoticeCreationReturn" ,params = "cancel")
	public String showReTrafficViolationNoticeCreation(Model model,@ModelAttribute TrafficViolationNoticeCreationForm form) {
		
		if (form.getViolation() != 0) {
			model.addAttribute("violationErrorMsg","違反項目が選択されていません");
		}
		return showTrafficViolationNoticeCreation1(form, model);
	}
	
	/**
	 *告知書フォームで "確認画面へ" のボタンを押したときの処理 
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return バリデーションエラーがあった場合、元のフォームへ。エラーなしは確認画面へ遷移。
	 */
	@PostMapping("/trafficViolationNoticeCreation1")
	public String showConfirmation(Model model,@ModelAttribute @Validated TrafficViolationNoticeCreationForm form,BindingResult bindingResult) {
		
		//自前の入力チェック処理
		if (form.getJobId() == 0) {
			bindingResult.rejectValue("jobId", null, null);
			model.addAttribute("jobErrorMsg","職業が選択されていません");
		}
		
		if (form.getViolationVehicleId() == null) {
			bindingResult.rejectValue("violationVehicleId", null, null);
			model.addAttribute("notVoilationVehicleId","反則車両が選択されていません");
		}
		
		if (form.getViolation() == 0) {
			bindingResult.rejectValue("violation", null, null);
			model.addAttribute("violationErrorMsg","違反項目が選択されていません");
		}
		
		if (bindingResult.hasErrors()) {
			if (form.getViolation() != 0) {
				model.addAttribute("violationErrorMsg"," ");
				model.addAttribute("errorMag","※未入力の項目があります");
				
				log.info(form.toString());
				log.info(bindingResult.toString());
				
				return showTrafficViolationNoticeCreation1(form,model);
			}
			
			model.addAttribute("errorMag","※未入力の項目があります");
			
			return showTrafficViolationNoticeCreation1(form,model);
		}
		
		//バリデーションチェックが通って入力内容確認画面へ行く処理
		String vehicleSelect = violationDetailsWordService.getVehicle(form.getVehicle());
		String genderSelect = violationDetailsWordService.getGender(form.getGender());
		String heavyTowingVehicle = form.getHeavyTowingVehicleSelect();
		ViolationAndPointFines violationAndPointFines = service.getOneViolationPointFines(form.getViolation());
		Job job = service.getJobOne(form.getJobId());
		Job subJob = service.getJobOne(form.getSubJobId());
		ViolationVehicle violationVehicle = service.getVehicleType(form.getViolationVehicleId());
		
		model.addAttribute("genderSelect",genderSelect);
		model.addAttribute("vehicleSelect",vehicleSelect);
		model.addAttribute("heavyTowingVehicle",heavyTowingVehicle);
		model.addAttribute("violationAndPointFines",violationAndPointFines);
		model.addAttribute("job",job);
		model.addAttribute("subJob", subJob);
		model.addAttribute("violationVehicle",violationVehicle);
		model.addAttribute("wareki",parseDateToWareki(form.getBirthday(),"GGGGyy年MM月dd日"));
		model.addAttribute("issueDate",parseDateToWareki(form.getIssueDate(),"GGGGyy年MM月dd日"));
		model.addAttribute("expiryDate",parseDateToWareki(form.getExpiryDate(), "GGGGyy年MM月dd日"));
		model.addAttribute("DateAndTimeOfViolation",parseLocalDateTimeToWareki(form.getDateAndTimeOfViolation(),"GGGGyy年MM月dd日HH時mm分頃"));
		model.addAttribute("pageTitle",source.getMessage("check.page", null,Locale.JAPAN));
		model.addAttribute("speedMsg",speedingMessage(form.getSpeed(),form.getResultOverSpeed(),form.getLegalSpeed(),form.getOverSpeed()));
		model.addAttribute("detailes",form.getDetailes());
		model.addAttribute("carelessness",form.getCarelessness());
		model.addAttribute("appearanceDate",parseDateToWareki(form.getAppearanceDate(), "GGGGyy年MM月dd日  午後３時まで"));
		
		log.info(model.toString());
		
		return "trafficViolationNoticeCreation/confirmation";
	}
	
	
	/**
	 *免許証検索結果から告知書作成ボタンを押した時の処理 
	 * @param id
	 * @param model
	 * @param form
	 * @return 交通違反告知書作成フォームへ
	 */
	@GetMapping(value = "trafficViolationNoticeCreation1",params = "id")
	public String getTrafficViolationNoticeCreation1(@RequestParam("id")Integer id, Model model,@ModelAttribute TrafficViolationNoticeCreationForm form) {
		
		DriversSubInfo driversSubInfo = new DriversSubInfo();
		
		if (id != null) {
			Licence licence = service.getLicenceId(id);
			
			ModelMapper modelMapper = new ModelMapper();
			
			modelMapper.addMappings(new PropertyMap<Licence, TrafficViolationNoticeCreationForm>() {
				
				//同じ値を格納する二つのフィールド名が違うので、同じに扱うようにする処理
				@Override
				protected void configure() {
					map().setViolatorName(source.getDriverName());
					map().setLicenceId(source.getId());
					skip().setSubJobId(0);      //必要のない引数を使わない処理
				}
			});
			
			modelMapper.map(licence, form);
		}
		
		form.setSubJobId(driversSubInfo.getSubJobId());
		
		return showTrafficViolationNoticeCreation1(form,model);
	}
	
	
	/**
	 *違反車両のサイズのドロップダウンリスト 
	 * @param model
	 * @return List型の配列変数に返す。
	 */
	private List<List<ViolationVehicle>> vehicleSize(Model model) {
		// TODO 自動生成されたメソッド・スタブ
		List<List<ViolationVehicle>> vehicleSize = service.vehiclesType();
		
		model.addAttribute("largeType",vehicleSize.get(0));
		model.addAttribute("standard",vehicleSize.get(1));
		model.addAttribute("motorcycle",vehicleSize.get(2));
		model.addAttribute("scooters",vehicleSize.get(3));
		
		return vehicleSize;
	}
	
	/**
	 * 全違反と反則金リスト
	 * @param model
	 */
	private void allList(Model model) {
		// TODO 自動生成されたメソッド・スタブ
		List<ViolationAndPointFines> violationPointFines = service.getAllViolationPointFines();
		List<VehicleTypeName> vehicleType = service.getVehicleTypeName();
		
		ArrayList<Integer> speed = new ArrayList<>(Arrays.asList(10, 15, 20, 25, 30, 35, 40, 50));
		
		model.addAttribute("violationPointFines", violationPointFines);
		model.addAttribute("vehicleType", vehicleType);
		model.addAttribute("speed", speed);
	}
	
	
	/**
	 *Date型を和暦など文字列にに変換する処理 
	 * @param date : 日付けのデータ
	 * @param pattern : 表示したい書式設定
	 * @return String型の変数に返す
	 */
	/**/
	private String parseDateToWareki(Date date,String pattern) {
		
		String result = null;
		Locale locale = new Locale("ja","JP","JP");
		
		DateFormat warekiFormat = new SimpleDateFormat(pattern ,locale);
		
		if (Objects.nonNull(date)) {
			result = warekiFormat.format(date);
		}
		return result;
	}
	
	/**
	 * LocalDateTime型を和暦に変換する処理
	 * @param date : 日付けデータ
	 * @param pattern : 表示したい書式設定
	 * @return String型の変数に返す
	 */
	private String parseLocalDateTimeToWareki(LocalDateTime date,String pattern) {
		
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
	 * 速度違反の詳細欄を表示するための処理
	 * @param speed : formの速度超過記述部部で"法定速度"、"指定速度"を選ぶラジオボタンの部分
	 * @param resultOverSpeed : formの速度超過記述部部で、実際に超過した速度
	 * @param legalSpeed : formの速度超過記述部部で、法定速度または指定速度
	 * @param overSpeed : formの速度超過記述部部で、実際に走行した速度
	 * @return String型の変数に返す
	 */
	public String speedingMessage(String speed,String resultOverSpeed,String legalSpeed,String overSpeed) {
		
		String speedMsg = null;
		
		if (speed != null) {
			if (speed.isBlank() && legalSpeed.isBlank() && overSpeed.isBlank()) {
				
				return speedMsg = null;
				
			}else if (speed != null && resultOverSpeed != null && legalSpeed != null && overSpeed != null) {
				speedMsg = speed + " " + " " + legalSpeed + "km/hの道路において、" + overSpeed + "㎞/hで走行、" + resultOverSpeed + "km/hの超過";
				
				return speedMsg ;
			}
		}
		return speedMsg = null;
	}
	
	
	/**
	 *全てのMapを纏めて、modelに積み込む処理を一つにしたもの 
	 * @param model
	 */
	private void allViolationDetailes(Model model) {
		
		Map<String,Integer> gender = violationDetailsWordService.getGenderMap();
		Map<String,Integer> carSelect = violationDetailsWordService.getCarSelect();
		Map<String,String> signalsMap = violationDetailsWordService.getSignalsMap();
		Map<String,String> oneStopViolation = violationDetailsWordService.getOneStopMap();
		Map<String,String> trafficBanViolation = violationDetailsWordService.getTrafficBanViolation();
		Map<String,String> levelCrossingNonStop = violationDetailsWordService.getLevelCrossingNonStop();
		Map<String,String> mobilePhoneUse = violationDetailsWordService.getMobilePhoneUse();
		Map<String,String> crossingPedestrianObstruction = violationDetailsWordService.getCrossingPedestrianObstruction();
		Map<String,String> carelessness = violationDetailsWordService.getCarelessness();
		
		model.addAttribute("gender",gender);
		model.addAttribute("carSelect",carSelect);
		model.addAttribute("signalsMap",signalsMap);
		model.addAttribute("oneStopViolation",oneStopViolation);
		model.addAttribute("trafficBanViolation",trafficBanViolation);
		model.addAttribute("levelCrossingNonStop",levelCrossingNonStop);
		model.addAttribute("mobilePhoneUse",mobilePhoneUse);
		model.addAttribute("crossingPedestrianObstruction",crossingPedestrianObstruction);
		model.addAttribute("carelessness",carelessness);
	}
}
