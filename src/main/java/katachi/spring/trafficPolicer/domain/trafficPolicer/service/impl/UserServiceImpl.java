package katachi.spring.trafficPolicer.domain.trafficPolicer.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.DriversSubInfo;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Job;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Licence;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Officer;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.VehicleTypeName;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationAndPointFines;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationRecord;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationVehicle;
import katachi.spring.trafficPolicer.domain.trafficPolicer.service.UserService;
import katachi.spring.trafficPolicer.form.ViolationRecordSearchForm;
import katachi.spring.trafficPolicer.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{ // NO_UCD (unused code)
	
	@Autowired
	public UserMapper mapper;

	/**
	 * ${@inheritDoc}
	 */
	@Override 
	public Officer LoginOfficer(String controlNumber) {
		return mapper.LoginOfficer(controlNumber);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public List<Job> jobSelect() {
		return mapper.jobSelect();
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override 
	public List<List<ViolationVehicle>> vehiclesType() { //二次元配列でメソッドを定義
		List<VehicleTypeName> vehicleSize = mapper.getVehicleTypeName();//車両の大きさの種類を格納する配列
		List<List<ViolationVehicle>> list = new ArrayList<>();//取締対象車両の細かい種類を格納する配列
		
		//DBから取り出した車両の種類を"変数List"に格納していく
		for(VehicleTypeName size : vehicleSize) {
			list.add(mapper.vehiclesType(size.getId()));
		}
		return list;
//		return mapper.VehiclesType(vehicleTypeNameId);
	}

	/*違反項目全取得*/
	/*@Override
	public List<Violation> getViolationAll() {
		return mapper.getViolationAll();
	}*/
	
	/*反則金額全取得*/
	/*@Override
	public List<AmountOfViolation> getAmountOfViolation() {
		return mapper.getAmountOfViolation();
	}*/

	/*新、反則金額全取得*/
	/*@Override
	public List<ViolationFines> getFinesAll() {
		return mapper.getFinesAll();
	}*/

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public List<VehicleTypeName> getVehicleTypeName() {
		return mapper.getVehicleTypeName();
	}

	/*違反項目1つだけ取得*/
	/*@Override
	public Violation getVioationOne(int id) {
		return mapper.getViolationOne(id);
	}*/

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public Job getJobOne(int id) {
		return mapper.getJobOne(id);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public ViolationVehicle getVehicleType(int id) {
		return mapper.getVehicleType(id);
	}

	/*違反の反則金１つだけ取得*/
	/*@Override
	public ViolationFines getFinesOne(int id) {
		return mapper.getFinesOne(id);
	}*/

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public List<ViolationAndPointFines> getAllViolationPointFines() {
		return mapper.getAllViolationPointFines();
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public ViolationAndPointFines getOneViolationPointFines(int id) {
		return mapper.getOneViolationPointFines(id);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public Licence getOneLicence(BigInteger licenceNumber) {
		return mapper.getOneLicence(licenceNumber);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public Licence getLicenceId(int id) {
		return mapper.getLicenceId(id);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public void violationEntry(ViolationRecord violationRecord) {
		mapper.violationEntry(violationRecord);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public ViolationRecord getNewRecord() {
		return mapper.getNewRecord();
	}
	
	/**
	 * ${@inheritDoc}
	 */
	@Override
	public List<ViolationRecord> getViolationRecord(ViolationRecordSearchForm violationRecordSearchForm) {
		return mapper.getViolationRecord(violationRecordSearchForm);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public ViolationRecord getViolationRecordDetails(int id) {
		return mapper.getViolationRecordDetails(id);
	}

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public void subInfoEntry(DriversSubInfo driversSubInfo) {
		mapper.subInfoEntry(driversSubInfo);
	}

	

}
