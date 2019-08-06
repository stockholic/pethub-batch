package kr.pethub.crawling;

import java.io.IOException;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.AnimalOrKr;

/**
 * 유기견보호센터 http://www.animal.or.kr
 * @author shkr
 *
 */
public class AnimalOrKrTest extends BaseTestCase{
//public class AnimalOrKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	AnimalOrKr obj = new AnimalOrKr();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "http://www.animal.or.kr/bbs/board.php?bo_table=commu_08";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.animal.or.kr/bbs/board.php?bo_table=commu_08&wr_id=42090");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		service.crawling("7");
	}
	
	
}
