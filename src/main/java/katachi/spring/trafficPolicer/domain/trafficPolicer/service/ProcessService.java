package katachi.spring.trafficPolicer.domain.trafficPolicer.service;

import java.util.Date;
import java.util.List;

public interface ProcessService {
	
	/**
	 * 都道府県リスト
	 * @return 都道府県
	 */
	public List<String> todoufukenList();
	
	/**
	 * Date型を和暦など文字列にに変換する処理
	 * @param date 日付け
	 * @param pattern 変換後の書式
	 * @return 変換された書式の日付け
	 */
	public String parseDateToWareki(Date date,String pattern);
}
