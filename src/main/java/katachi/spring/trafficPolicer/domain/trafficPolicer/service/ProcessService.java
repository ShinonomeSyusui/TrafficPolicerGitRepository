package katachi.spring.trafficPolicer.domain.trafficPolicer.service;

import java.util.Date;
import java.util.List;

public interface ProcessService {
	
	/*都道府県リスト*/
	public List<String> todoufukenList();
	
	/*Date型を和暦など文字列にに変換する処理*/
	public String parseDateToWareki(Date date,String pattern);
}
