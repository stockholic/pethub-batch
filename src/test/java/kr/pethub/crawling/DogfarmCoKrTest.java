package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.DogfarmCoKr;

/**
 * 도그팜 http://www.dogfarm.co.kr/, KakaodogCoKr 과 비슷
 * @author shkr
 *
 */
public class DogfarmCoKrTest extends BaseTestCase{
//public class DogfarmCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	DogfarmCoKr obj = new DogfarmCoKr();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "http://www.dogfarm.co.kr/gnu/index.php";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.dogfarm.co.kr/gnu/sell.php?sub=view&id=4037");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		service.crawling("6");
	}
}
