package katachi.spring.trafficPolicer.domain.trafficPolicer.model;

import lombok.Data;
/**
 * LicenceTypeのモデルクラス、DBの項目とフィールドが対応している。
 * @author ShinonomeSyusui
 * @version 1.0.0
 **/
@Data
public class LicenceType {

	private int id;
	
	private int licenceId;
	
	private int largeSize; //大型
	
	private int mediumSize; //中型
	
	private int semiMediumSize; //準中型
	
	private int usually; //普通
	
	private int largeSpecial; //大型特殊（大特）
	
	private int largeMotorcycle; //大自二
	
	private int ordinaryMotorcycle; //普自二
	
	private int smallSpecial; //小型特殊（小特）
	
	private int scooters; //原付
	
	private int largeType2; //大型2種（大二）
	
	private int mediumSizeType2; //中型二種（中二）
	
	private int ordinaryType2; //普通二種（普二）
	
	private int largeSpecialType2; //大型特殊二種（大特二）
	
	private int towingAndType2; //牽引、牽引二種（引・引二）
	
	
	/**
	 * フィールド値に"1"が入っていれば、その免許の種類をすべて返すメソッド
	 * @return
	 */
	public String getLicenceTypeJp() {
		
		String licenceType = "";
		
		if(largeSize == 1) licenceType += "・大型  ";
		if(mediumSize == 1) licenceType += "・中型  ";
		if(semiMediumSize == 1) licenceType += "・準中型  ";
		if(usually == 1) licenceType += "・普通  ";
		if(largeSpecial == 1) licenceType += "・大特  ";
		if(largeMotorcycle == 1) licenceType += "・大自二  ";
		if(ordinaryMotorcycle == 1) licenceType += "・普自二  ";
		if(smallSpecial == 1) licenceType += "・小特  ";
		if(scooters == 1) licenceType += "・原付  ";
		if(largeType2 == 1) licenceType += "・大二  ";
		if(mediumSizeType2 == 1) licenceType += "・中二  ";
		if(ordinaryType2 == 1) licenceType += "・普二  ";
		if(largeSpecialType2 == 1) licenceType += "・大特二  ";
		if(towingAndType2 == 1) licenceType += "・引・引二  ";
		
		return licenceType;
	}
}
