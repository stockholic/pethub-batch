package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.KakaodogCoKr;

/**
 * KaKao Dog  http://www.kakaodog.co.kr DogfarmCoKr 과 비슷
 * @author shkr
 *
 */
public class KakaodogCoKrTest extends BaseTestCase{
//public class PuppycafeCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	KakaodogCoKr obj = new KakaodogCoKr();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "http://www.kakaodog.co.kr/gnu/index.php";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.kakaodog.co.kr/gnu/vip_view.php?id=9298");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		service.crawling("10");
	}
	
	
	/* 
	http://www.catjoa.com/main/main.php
	*/
}
