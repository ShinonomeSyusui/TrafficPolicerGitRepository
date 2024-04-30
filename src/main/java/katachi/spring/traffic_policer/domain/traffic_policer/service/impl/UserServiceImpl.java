package katachi.spring.traffic_policer.domain.traffic_policer.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.traffic_policer.domain.traffic_policer.model.DriversSubInfo;
import katachi.spring.traffic_policer.domain.traffic_policer.model.Job;
import katachi.spring.traffic_policer.domain.traffic_policer.model.Licence;
import katachi.spring.traffic_policer.domain.traffic_policer.model.Officer;
import katachi.spring.traffic_policer.domain.traffic_policer.model.VehicleTypeName;
import katachi.spring.traffic_policer.domain.traffic_policer.model.ViolationAndPointFines;
import katachi.spring.traffic_policer.domain.traffic_policer.model.ViolationRecord;
import katachi.spring.traffic_policer.domain.traffic_policer.model.ViolationVehicle;
import katachi.spring.traffic_policer.domain.traffic_policer.service.UserService;
import katachi.spring.traffic_policer.form.ViolationRecordSearchForm;
import katachi.spring.traffic_policer.repository.UserMapper;

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

	/**
	 * ${@inheritDoc}
	 */
	@Override
	public List<VehicleTypeName> getVehicleTypeName() {
		return mapper.getVehicleTypeName();
	}

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
	public void entryViolation(ViolationRecord violationRecord) {
		mapper.entryViolation(violationRecord);
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
	public void entrySubInfo(DriversSubInfo driversSubInfo) {
		mapper.entrySubInfo(driversSubInfo);
	}

	

}
