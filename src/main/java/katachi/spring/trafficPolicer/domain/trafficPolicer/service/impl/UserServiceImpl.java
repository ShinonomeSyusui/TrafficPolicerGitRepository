package katachi.spring.trafficPolicer.domain.trafficPolicer.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.AmountOfViolation;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.DriversSubInfo;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Job;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Licence;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Officer;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.VehicleTypeName;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Violation;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationAndPointFines;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationFines;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationRecord;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationVehicle;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
import katachi.spring.trafficPolicer.form.ViolationRecordSearchForm;
import katachi.spring.trafficPolicer.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{ // NO_UCD (unused code)
	
	@Autowired
	public UserMapper mapper;

	@Override /*ログインユーザーチェック*/
	public Officer LoginOfficer(String controlNumber) {
		return mapper.LoginOfficer(controlNumber);
	}

	@Override /*職業選択ドロップダウンリスト*/
	public List<Job> jobSelect() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.jobSelect();
	}

	@Override /*違反車両のドロップダウンリスト*/
	public List<List<ViolationVehicle>> vehiclesType() {
		// TODO 自動生成されたメソッド・スタブ
		List<VehicleTypeName> vehicleSize = mapper.getVehicleTypeName();
		List<List<ViolationVehicle>> list = new ArrayList<>();
		
		for(VehicleTypeName size : vehicleSize) {
			list.add(mapper.vehiclesType(size.getId()));
		}
		return list;
//		return mapper.VehiclesType(vehicleTypeNameId);
	}

	/*違反項目全取得*/
	@Override
	public List<Violation> getViolationAll() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getViolationAll();
	}
	
	/*反則金額全取得*/
	@Override
	public List<AmountOfViolation> getAmountOfViolation() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getAmountOfViolation();
	}

	/*新、反則金額全取得*/
	@Override
	public List<ViolationFines> getFinesAll() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getFinesAll();
	}

	/*違反車両５タイプ全取得*/
	@Override
	public List<VehicleTypeName> getVehicleTypeName() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getVehicleTypeName();
	}

	/*違反項目1つだけ取得*/
	@Override
	public Violation getVioationOne(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getViolationOne(id);
	}

	/*職業1つだけ取得*/
	@Override
	public Job getJobOne(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getJobOne(id);
	}

	/*違反車両の種類１つだけ取得*/
	@Override
	public ViolationVehicle getVehicleType(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getVehicleType(id);
	}

	/*違反の反則金１つだけ取得*/
	@Override
	public ViolationFines getFinesOne(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getFinesOne(id);
	}

	/*(新)違反項目と点数と反則金全部取得*/
	@Override
	public List<ViolationAndPointFines> getAllViolationPointFines() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getAllViolationPointFines();
	}

	/*(新)違反項目と点数と反則金1つだけ取得*/
	@Override
	public ViolationAndPointFines getOneViolationPointFines(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getOneViolationPointFines(id);
	}

	/*免許証検索1件取得*/
	@Override
	public Licence getOneLicence(BigInteger licenceNumber) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getOneLicence(licenceNumber);
	}

	/*免許証１件取得ID*/
	@Override
	public Licence getLicenceId(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getLicenceId(id);
	}

	/*違反記録の登録*/
	@Override
	public void violationEntry(ViolationRecord violationRecord) {
		// TODO 自動生成されたメソッド・スタブ
		mapper.violationEntry(violationRecord);
	}

	/*違反記録の登録の結果*/
	@Override
	public ViolationRecord getNewRecord() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getNewRecord();
	}

	@Override
	public List<ViolationRecord> getViolationRecord(ViolationRecordSearchForm violationRecordSearchForm) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getViolationRecord(violationRecordSearchForm);
	}

	/*違反履歴1件詳細取得*/
	@Override
	public ViolationRecord getViolationRecordDetails(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getViolationRecordDetails(id);
	}

	/*違反記録のサブ情報登録*/
	@Override
	public void subInfoEntry(DriversSubInfo driversSubInfo) {
		// TODO 自動生成されたメソッド・スタブ
		mapper.subInfoEntry(driversSubInfo);
	}

	

}
