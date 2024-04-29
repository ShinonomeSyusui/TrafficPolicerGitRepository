package katachi.spring.trafficPolicer.domain.trafficPolicer.service;

import java.math.BigInteger;
import java.util.List;

import katachi.spring.trafficPolicer.domain.trafficPolicer.model.DriversSubInfo;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Job;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Licence;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.Officer;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.VehicleTypeName;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationAndPointFines;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationRecord;
import katachi.spring.trafficPolicer.domain.trafficPolicer.model.ViolationVehicle;
import katachi.spring.trafficPolicer.form.ViolationRecordSearchForm;

public interface UserService {
	
	/**
	 * ログインユーザーの検索
	 * @param controlNumber
	 * @return 登録されているユーザーの検索結果
	 */
	public Officer LoginOfficer(String controlNumber);
	
	/**
	 * 職業選択ドロップダウンリスト
	 * @return 全職業
	 */
	public List<Job> jobSelect();
	
	/**
	 * 違反車両のドロップダウンリスト
	 * @return 二次元配列での車両の種類
	 */
	public List<List<ViolationVehicle>> vehiclesType();
	
	/*違反項目全取得*/
	//public List<Violation> getViolationAll();
	
	/*反則金額全取得*/
	//public List<AmountOfViolation> getAmountOfViolation();
	
	/*新、反則金額全取得*/
	//public List<ViolationFines> getFinesAll();
	
	/**/
	/**
	 * 違反車両５タイプ全取得
	 * @return 取締対象車両の種類の名前
	 */
	public List<VehicleTypeName> getVehicleTypeName(); 
	
	/*違反項目1つだけ取得*/
	//public Violation getVioationOne(int id);
	
	/**/
	/**
	 * 職業1つだけ取得
	 * @param id DBに登録されているJobのId
	 * @return 引数で指定されたJobのId
	 */
	public Job getJobOne(int id);
	
	/**/
	/**
	 * 違反車両の種類１つだけ取得
	 * @param id 取締対象車両のId
	 * @return 引数で渡されたVehicleTypeのId
	 */
	public ViolationVehicle getVehicleType(int id);
	
	/*違反の反則金１つだけ取得*/
	//public ViolationFines getFinesOne(int id);
	
	/**/
	/**
	 * (新)違反項目と点数と反則金全部取得
	 * @return Listに、DBのTableに登録されている違反項目と点数と反則金の全てを返す
	 */
	public List<ViolationAndPointFines> getAllViolationPointFines();
	
	/**/
	/**
	 * (新)違反項目と点数と反則金1つだけ取得
	 * @param id DBに登録されている各項目のId
	 * @return 引数で渡されたIdと同じIdを持つ項目
	 */
	public ViolationAndPointFines getOneViolationPointFines(int id);
	
	/**/
	/**
	 * 免許証検索1件取得
	 * @param licenceNumber 入力フォームから受け取る12桁の数字
	 * @return 引数で受け取ったのと同じDBに登録されているlicenceNumber
	 */
	public Licence getOneLicence(BigInteger licenceNumber);
	
	/**/
	/**
	 * 免許証検索1件取得
	 * @param id licenceNumberに紐づけされているId
	 * @return 引数で渡されたIdと同じIdの項目
	 */
	public Licence getLicenceId(int id);
	
	/**
	 * 違反記録の登録
	 * @param violationRecord フォームから受け取った値全て
	 */
	public void violationEntry(ViolationRecord violationRecord);
	
	/**
	 * 違反記録のサブ情報登録
	 * @param driversSubInfo フォームから受け取った値すべて
	 */
	public void subInfoEntry(DriversSubInfo driversSubInfo);
	
	/**
	 * 違反記録の登録の結果
	 * @return 一番新しい取締履歴
	 */
	public ViolationRecord getNewRecord();
	
	/**/
	/**
	 * 違反履歴の検索
	 * @param violationRecordSearchForm フォームから受け取った値全て
	 * @return 引数で渡された値に該当するものを返す
	 */
	public List<ViolationRecord> getViolationRecord(ViolationRecordSearchForm violationRecordSearchForm);
	
	/**
	 * 違反履歴1件詳細取得
	 * @param id 違反記録のId
	 * @return 引数で渡されたIdに紐づいた取締情報のId
	 */
	public ViolationRecord getViolationRecordDetails(int id);
}
