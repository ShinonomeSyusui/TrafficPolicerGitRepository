package katachi.spring.trafficPolicer.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.DriversSubInfo;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Licence;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationRecord;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ProcessService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.ViolationDetailsWordService;
import katachi.spring.trafficPolicer.form.TrafficViolationNoticeCreationForm;

/**
 * 交通違反告知書入力確認画面へのアクセスと内部処理のクラス
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Controller
public class ConfirmationController {
	@Autowired
	TrafficViolationNoticeCreation1Controller creation1Controller;
	
	@Autowired
	ViolationDetailsWordService violationDetailsWordService;
	
	@Autowired
	ViolationRecordSearchFormController controller;

	@Autowired
	UserService service;

	@Autowired
	ProcessService pService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	Licence licence;
	
	
	/**
	 * 確認画面で発行ボタンを押したときの処理
	 * @param model
	 * @param form
	 * @param vRecord
	 * @param driversSubInfo
	 * @return 登録結果画面へ遷移する
	 */
	@PostMapping(value = "confirmation",params = "confirm")
	public String insertViolationRecord(Model model,@ModelAttribute TrafficViolationNoticeCreationForm form,ViolationRecord vRecord, DriversSubInfo driversSubInfo) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.addMappings(new PropertyMap<TrafficViolationNoticeCreationForm, ViolationRecord>() {
			
			@Override
			protected void configure() {
				map().setViolationAndPointFinesId(source.getViolation());
			}
		});
		
		vRecord = modelMapper.map(form,ViolationRecord.class);
		
		service.violationEntry(vRecord);
		
		driversSubInfo = modelMapper.map(form, DriversSubInfo.class);
		
		driversSubInfo.setViolationRecordId(vRecord.getId());
		
		System.out.println(driversSubInfo);
		
		service.subInfoEntry(driversSubInfo);
		
		getNewRecordOne(model, vRecord);
		
		return "trafficViolationNoticeCreation/hasRegistered";
	}
	
	/**
	 *Date型を和暦など文字列にに変換する処理 
	 * @param date : 日付けデータ
	 * @param pattern : 表示したい書式設定
	 * @return String型の変数へ返す
	 */
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
	 * 違反データを登録後、登録した最新データを表示するための処理
	 * @param model
	 * @param vRecord : ViolationRecordのフィールド値全て
	 * @return homePageへリダイレクトする
	 */
	private String getNewRecordOne(Model model,@ModelAttribute ViolationRecord vRecord) {
		
		vRecord = service.getNewRecord();
		
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
			model.addAttribute("warekiViolationTimeDetailes",controller.parseLocalDateTimeToWareki(vRecord.getDateAndTimeOfViolation(), "GGGGyy年MM月dd日HH時mm分頃"));
			model.addAttribute("appearanceDate",parseDateToWareki(vRecord.getAppearanceDate(), "GGGGyy年MM月dd日  午後３時まで"));
			model.addAttribute("speedMsg",creation1Controller.speedingMessage(vRecord.getSpeed(), vRecord.getResultOverSpeed(), vRecord.getLegalSpeed(),vRecord.getOverSpeed()));
			model.addAttribute("pageTitle","登録結果");
		}
		return "/redirect:homePage";
	}

}
