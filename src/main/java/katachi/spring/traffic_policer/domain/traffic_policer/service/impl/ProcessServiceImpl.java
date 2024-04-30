package katachi.spring.traffic_policer.domain.traffic_policer.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import katachi.spring.traffic_policer.domain.traffic_policer.service.ProcessService;
import katachi.spring.traffic_policer.domain.traffic_policer.service.UserService;

/**
 * @author ShinonomeSyusui
 * @version 1.0.0
 */
@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	UserService service;

	@Autowired
	HttpSession session;

	/**
	 * ${@inheritDoc} 
	 */
	@Override
	public List<String> todoufukenList() {
		List<String> prefectures = List.of(
				"北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
				"茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
				"新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
				"静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
				"奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
				"徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
				"熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県");

		return prefectures;
	}

	/**
	 * ${@inheritDoc} 
	 */
	public String parseDateToWareki(Date date, String pattern) {
		String result = null;
		Locale locale = new Locale("ja", "JP", "JP");
		DateFormat warekiFormat = new SimpleDateFormat(pattern, locale);
		if (Objects.nonNull(date)) {
			result = warekiFormat.format(date);
		}
		return result;
	}
}
