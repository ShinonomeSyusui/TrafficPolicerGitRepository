package katachi.spring.traffic_policer.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.traffic_policer.domain.traffic_policer.model.DriversSubInfo;
import katachi.spring.traffic_policer.domain.traffic_policer.model.Job;
import katachi.spring.traffic_policer.domain.traffic_policer.model.Licence;
import katachi.spring.traffic_policer.domain.traffic_policer.model.Officer;
import katachi.spring.traffic_policer.domain.traffic_policer.model.VehicleTypeName;
import katachi.spring.traffic_policer.domain.traffic_policer.model.ViolationAndPointFines;
import katachi.spring.traffic_policer.domain.traffic_policer.model.ViolationRecord;
import katachi.spring.traffic_policer.domain.traffic_policer.model.ViolationVehicle;
import katachi.spring.traffic_policer.form.ViolationRecordSearchForm;

@Mapper
public interface UserMapper {
	
	/*ログイン（セキュリティ）*/
	public Officer LoginOfficer(String controlNumber);
	
	/*職業選択ドロップダウンリスト*/
	public List<Job> jobSelect();
	
	/*違反車両のドロップダウンリスト*/
	public List<ViolationVehicle> vehiclesType(int vehicleTypeNameId);
	
	/*違反車両のサイズ取得*/
	public List<VehicleTypeName> getVehicleTypeName();
	
	/*職業1つだけ取得*/
	public Job getJobOne(int id);
	
	/*違反車両の種類１つだけ取得*/
	public ViolationVehicle getVehicleType(int id);
	
	/*(新)違反項目と点数と反則金全部取得*/
	public List<ViolationAndPointFines> getAllViolationPointFines();
	
	/*(新)違反項目と点数と反則金1つだけ取得*/
	public ViolationAndPointFines getOneViolationPointFines(int id);
	
	/*免許証検索1件取得*/
	public Licence getOneLicence(BigInteger licenceNumber);
	
	/*免許証検索1件取得*/
	public Licence getLicenceId(int id);
	
	/*違反記録の登録*/
	public void entryViolation(ViolationRecord violationRecord);
	
	/*違反記録サブ情報の登録*/
	public void entrySubInfo(DriversSubInfo driversSubInfo);
	
	/*違反記録の登録の結果*/
	public ViolationRecord getNewRecord();
	
	/*違反記録の検索*/
	public List<ViolationRecord> getViolationRecord(ViolationRecordSearchForm violationRecordSearchForm);
	
	/*違反履歴1件詳細取得*/
	public ViolationRecord getViolationRecordDetails(int id);
	
}
