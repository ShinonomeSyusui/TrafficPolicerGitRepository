package katachi.spring.traffic_policer.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.traffic_policer.domain.traffic_policer.model.DriversSubInfo;
import katachi.spring.traffic_policer.domain.traffic_policer.model.Licence;
import katachi.spring.traffic_policer.domain.traffic_policer.model.ViolationRecord;
import katachi.spring.traffic_policer.domain.traffic_policer.service.ProcessService;
import katachi.spring.traffic_policer.domain.traffic_policer.service.UserService;
import katachi.spring.traffic_policer.domain.traffic_policer.service.ViolationDetailsWordService;
import katachi.spring.traffic_policer.form.TrafficViolationNoticeCreationForm;
import lombok.extern.slf4j.Slf4j;

/**
 * 交通違反告知書入力確認画面へのアクセスと内部処理のクラス
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Controller
@Slf4j
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
	
	@Autowired
	MessageSource source;
	
	
	/**
	 * 確認画面で発行ボタンを押したときの処理
	 * @param model
	 * @param form
	 * @param vRecord
	 * @param driversSubInfo
	 * @return 登録結果画面へ遷移する
	 */
	@PostMapping(value = "confirmation",params = "confirm")
	public String insertViolationRecord(Model model,@ModelAttribute TrafficViolationNoticeCreationForm form,
			ViolationRecord vRecord, DriversSubInfo driversSubInfo) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		//単純なMapper以外の条件追加
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.addMappings(new PropertyMap<TrafficViolationNoticeCreationForm, ViolationRecord>() { 
			
			@Override
			protected void configure() {
				map().setViolationAndPointFinesId(source.getViolation());
			}
		});
		
		//違反記録の登録 
		vRecord = modelMapper.map(form,ViolationRecord.class); 
		service.entryViolation(vRecord); 
		
		//違反者のその他の情報の登録
		driversSubInfo = modelMapper.map(form, DriversSubInfo.class);
		driversSubInfo.setViolationRecordId(vRecord.getId()); 
		log.debug(driversSubInfo.toString());
		service.entrySubInfo(driversSubInfo);
		 
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
	 * 違反データを登録後、登録した最新データをmodelに格納する
	 * @param model
	 * @param vRecord : ViolationRecordのフィールド値全て
	 */
	private void getNewRecordOne(Model model,@ModelAttribute ViolationRecord vRecord) {
		
		//一番新しい取締記録の取得
		vRecord = service.getNewRecord();
		
		//違反情報があれば、モデルに格納
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
			model.addAttribute("pageTitle",source.getMessage("registration.result", null,Locale.JAPAN));
		}
	}
}
