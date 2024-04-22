package katachi.spring.trafficPolicer.domain.trafficPolicer.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * messages.propertiesで定義されている文字列を、反則告知書のフォーム画面（HTML）で,
 * 表示する選択項目をMapに格納するクラス
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Service
public class ViolationDetailsWordService {

	@Autowired
	public MessageSource mSource;

	/**
	 * 性別のMap
	 * @return
	 */
	public Map<String, Integer> getGenderMap() {
		
		Map<String, Integer> gender = new LinkedHashMap<>();
		
		String male = mSource.getMessage("male", null, Locale.JAPAN);
		String female = mSource.getMessage("female", null, Locale.JAPAN);
		
		gender.put(male, 1);
		gender.put(female, 2);
		
		return gender;
	}
	
	/**
	 * 受け取った引数から、genderのKeyを返すメソッド
	 * @param gender
	 * @return
	 */
	public String getGender(Integer gender) {
		
		Map<String,Integer> map = getGenderMap();
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == gender) {
				
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 *自家用or事業用のラジオボタンのMap 
	 * @return
	 */
	public Map<String, Integer> getCarSelect() {
		
		Map<String, Integer> carSelect = new LinkedHashMap<>();
		
		String publicUse = mSource.getMessage("privateUse", null, Locale.JAPAN);
		String forBusiness = mSource.getMessage("forBusiness", null, Locale.JAPAN);
		
		carSelect.put(publicUse, 1);
		carSelect.put(forBusiness, 2);
		
		return carSelect;
	}
	
	public String getVehicle(Integer vehicle) {
		
		Map<String,Integer> map = getCarSelect();
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() == vehicle) {
				
				return entry.getKey();
			}
		}
		
		return null;
	}

	/*信号無視の項目のMap*/
	public Map<String, String> getSignalsMap() {
		
		Map<String, String> signalsMap = new LinkedHashMap<>();
		
		String red = mSource.getMessage("signalRed", null, Locale.JAPAN);
		String yellow = mSource.getMessage("signalYellow", null, Locale.JAPAN);
		
		signalsMap.put(red, red);
		signalsMap.put(yellow, yellow);
		
		return signalsMap;
	}

	/*指定場所一時不停止の項目のMap*/
	public Map<String, String> getOneStopMap() {
		
		Map<String, String> oneStopViolation = new LinkedHashMap<>();
		
		String notOneStop = mSource.getMessage("notOneStop", null, Locale.JAPAN);
		String trafficObstruction = mSource.getMessage("trafficObstruction", null, Locale.JAPAN);
		
		oneStopViolation.put(notOneStop, notOneStop);
		oneStopViolation.put(trafficObstruction, trafficObstruction);
		
		return oneStopViolation;

	}

	/*通行禁止場所違反の項目のMap*/
	public Map<String, String> getTrafficBanViolation() {
		
		Map<String, String> trafficBanViolation = new LinkedHashMap<>();
		
		String oneWayViolation = mSource.getMessage("oneWayViolation", null, Locale.JAPAN);
		String PassingThroughProhibitedAreas = mSource.getMessage("PassingThroughProhibitedAreas", null, Locale.JAPAN);
		String violationTurnRight = mSource.getMessage("violationTurnRight", null, Locale.JAPAN);
		String violationTurnLeft = mSource.getMessage("violationTurnLeft", null, Locale.JAPAN);
		String violationStraight = mSource.getMessage("violationStraight", null, Locale.JAPAN);
		
		trafficBanViolation.put(oneWayViolation, oneWayViolation);
		trafficBanViolation.put(PassingThroughProhibitedAreas, PassingThroughProhibitedAreas);
		trafficBanViolation.put(violationTurnRight, violationTurnRight);
		trafficBanViolation.put(violationTurnLeft, violationTurnLeft);
		trafficBanViolation.put(violationStraight, violationStraight);
		
		return trafficBanViolation;
	}

	/*踏切一時不停止違反の項目のMap*/
	public Map<String, String> getLevelCrossingNonStop() {
		
		Map<String, String> levelCrossingNonStop = new LinkedHashMap<String, String>();
		
		String TemporaryStoppageAtLevelCrossing = mSource.getMessage("TemporaryStoppageAtLevelCrossing", null,
				Locale.JAPAN);
		String SafetyNotConfirmed = mSource.getMessage("SafetyNotConfirmed", null, Locale.JAPAN);
		
		levelCrossingNonStop.put(TemporaryStoppageAtLevelCrossing, TemporaryStoppageAtLevelCrossing);
		levelCrossingNonStop.put(SafetyNotConfirmed, SafetyNotConfirmed);
		
		return levelCrossingNonStop;
	}

	/*携帯電話使用等(保持)の項目のMap*/
	public Map<String, String> getMobilePhoneUse() {
		
		Map<String, String> mobilePhoneUse = new LinkedHashMap<String, String>();
		
		String useWirelessCommunicationDevice = mSource.getMessage("useWirelessCommunicationDevice", null,
				Locale.JAPAN);
		String holdTheDevice = mSource.getMessage("holdTheDevice", null, Locale.JAPAN);
		
		mobilePhoneUse.put(useWirelessCommunicationDevice, useWirelessCommunicationDevice);
		mobilePhoneUse.put(holdTheDevice, holdTheDevice);
		
		return mobilePhoneUse;
	}

	/*横断歩行者等妨害等の項目のMap*/
	public Map<String, String> getCrossingPedestrianObstruction() {
		
		Map<String, String> crossingPedestrianObstruction = new LinkedHashMap<String, String>();
		
		String crossingPedestrianObstructionNonStop = mSource.getMessage("crossingPedestrianObstructionNonStop", null,
				Locale.JAPAN);
		String crossingPedestrianObstructionObstructionOfTraffic = mSource
				.getMessage("crossingPedestrianObstructionObstructionOfTraffic", null, Locale.JAPAN);
		
		crossingPedestrianObstruction.put(crossingPedestrianObstructionNonStop, crossingPedestrianObstructionNonStop);
		crossingPedestrianObstruction.put(crossingPedestrianObstructionObstructionOfTraffic,
				crossingPedestrianObstructionObstructionOfTraffic);
		
		return crossingPedestrianObstruction;
	}

	/*不注意による確認義務の項目のMap*/
	public Map<String, String> getCarelessness() {
		
		Map<String, String> carelessness = new LinkedHashMap<>();
		
		String signal = mSource.getMessage("signal", null, Locale.JAPAN);
		String LegallyProhibitedPlaces = mSource.getMessage("legallyProhibitedPlaces", null, Locale.JAPAN);
		String LicenseCarrying = mSource.getMessage("licenseCarrying", null, Locale.JAPAN);
		String speed = mSource.getMessage("speed", null, Locale.JAPAN);
		String RailroadCrossing = mSource.getMessage("railroadCrossing", null, Locale.JAPAN);
		String roadSign = mSource.getMessage("roadSign", null, Locale.JAPAN);
		String SameTrafficZone = mSource.getMessage("sameTrafficZone", null, Locale.JAPAN);
		
		carelessness.put(signal, signal);
		carelessness.put(LegallyProhibitedPlaces, LegallyProhibitedPlaces);
		carelessness.put(LicenseCarrying, LicenseCarrying);
		carelessness.put(speed, speed);
		carelessness.put(RailroadCrossing, RailroadCrossing);
		carelessness.put(roadSign, roadSign);
		carelessness.put(SameTrafficZone, SameTrafficZone);
		
		return carelessness;
	}
}
