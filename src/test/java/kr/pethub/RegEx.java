package kr.pethub;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegEx {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void 숫자만_체크() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String pattern ="^[0-9]*$";
		
		String str1 = "1234";
		String str2 = "12sagd34";
		
		isValid(pattern, str1);
		isValid(pattern, str2);
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	@Test
	public void URL_패턴1() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String str = "onClick=window.open('/abcd/xyz.php?aa=11&bb+22&cc=','','width=200;height=500')".trim();
		
		// (?!) 뒤에 대소문자 안가림
		String pattern ="(onClick=window\\.open\\()'([^\\s']+)'(.*)";
		
		isRegexElement(pattern, str);
		
		String prefix = "http://www.pethub.kr";
		String surfix = str.replaceAll(pattern, "$2");
		
		logger.debug("Link : {} ",  prefix + surfix);
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	

	@Test
	public void URL_패턴2() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String str = "<a href='/abcd/def.php?aa=11&bb=22' target='_blank'>link</a>".trim();
		
		// (.*) 모든문자
		String pattern ="(<a href=)'([^\\s']+)'(.*)";

		isRegexElement(pattern, str);
		
		String prefix = "http://www.pethub.kr";
		String surfix = str.replaceAll(pattern, "$2");
		
		logger.debug("Link : {}  ",  prefix + surfix);
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	@Test
	public void URL_패턴3() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String str = "javascript:view(\"bbs\",\"2\")".trim();
		
		String pattern ="(javascript:view\\()\"([a-z]+)\",\"([0-9]+)\"(.*)";

		isRegexElement(pattern, str);
		
		String prefix = "http://www.pethub.kr";
		String surfix = str.replaceAll(pattern, "/xyz.php?board=$2&no=$3");
		
		logger.debug("Link : {}  ",  prefix + surfix);
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	@Test
	public void URL_패턴4() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String str = "https://m.stock.naver.com/item/index.nhn?code=140410".trim();
		
		String patternId ="(.*)(code=)([0-9]+)";
		//isRegexElement(patternId, surfix);
		String id = str.replaceAll(patternId, "$3");
		logger.debug("id : {}  ",  id);
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	@Test
	public void 이미지추출() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String str = "<img src=\"/images/board/xxx.php?no=1234\" class=\"top_img\">".trim();
		
		String pattern ="(<img src=)\"([^\\s\"]+)\"(.*)";

		isRegexElement(pattern, str);
		
		String prefix = "http://www.pethub.kr";
		String surfix = str.replaceAll(pattern, "$2");
		
		logger.debug("Link : {}  ",  prefix + surfix);
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	
	@Test
	public void 태그제거() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String str = "<a href='/abcd/def.php?aa=11&bb=22' target='_blank'>하하하</a> <호호호호><img src=\"/images/board/xxx.php?no=1234\" class=\\\"top_img\">".trim();
		
		String pattern ="<[\\/a-zA-Z]+[^>]+>";
		isValid(pattern, str);
		
		logger.debug("Result : {}  ", str.replaceAll(pattern, ""));

		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	
	public boolean isValid(String pattern, String str) {
		
		boolean isValid = false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		isValid = m.find();
		
		logger.debug("isMatch : {} ", isValid);
		
		
		return isValid;
		
	}
	
	public boolean isRegexElement(String pattern, String str) {
		
		boolean isValid = false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);

		if(m.matches()) {
			logger.debug("Element found");
			for(int i = 0; i < m.groupCount(); i++) {
				logger.debug(i + " : {}", m.group(i));	
			}
			isValid = true;
		}else {
			logger.debug("Element not found");
		}
		
		return isValid;
		
	}
	
}
