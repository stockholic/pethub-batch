package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.TheilovedogCom;

/**
 * I love dog http://www.theilovedog.com
 * @author shkr
 *
 */
public class TheilovedogComTest extends BaseTestCase{
//public class TheilovedogComTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	TheilovedogCom obj = new TheilovedogCom();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "http://www.theilovedog.com/dog/list.php?category=1";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.theilovedog.com/dog/view.php?category=1&id=626");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		service.crawling("2");
	}
}
