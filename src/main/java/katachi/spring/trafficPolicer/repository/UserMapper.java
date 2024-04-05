package katachi.spring.trafficPolicer.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
import katachi.spring.trafficPolicer.form.ViolationRecordSearchForm;

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
	
	/*違反項目全取得*/
	public List<Violation> getViolationAll();
	
	/*反則金額全取得*/
	public List<AmountOfViolation> getAmountOfViolation();
	
	/*新、反則金全取得*/
	public List<ViolationFines> getFinesAll();
	
	/*違反項目1つだけ取得*/
	public Violation getViolationOne(int id);
	
	/*職業1つだけ取得*/
	public Job getJobOne(int id);
	
	/*違反車両の種類１つだけ取得*/
	public ViolationVehicle getVehicleType(int id);
	
	/*違反の反則金１つだけ取得*/
	public ViolationFines getFinesOne(int id);
	
	/*(新)違反項目と点数と反則金全部取得*/
	public List<ViolationAndPointFines> getAllViolationPointFines();
	
	/*(新)違反項目と点数と反則金1つだけ取得*/
	public ViolationAndPointFines getOneViolationPointFines(int id);
	
	/*免許証検索1件取得*/
	public Licence getOneLicence(BigInteger licenceNumber);
	
	/*免許証検索1件取得*/
	public Licence getLicenceId(int id);
	
	/*違反記録の登録*/
	public void violationEntry(ViolationRecord violationRecord);
	
	/*違反記録サブ情報の登録*/
	public void subInfoEntry(DriversSubInfo driversSubInfo);
	
	/*違反記録の登録の結果*/
	public ViolationRecord getNewRecord();
	
	/*違反記録の検索*/
	public List<ViolationRecord> getViolationRecord(ViolationRecordSearchForm violationRecordSearchForm);
	
	/*違反履歴1件詳細取得*/
	public ViolationRecord getViolationRecordDetails(int id);
	
}
