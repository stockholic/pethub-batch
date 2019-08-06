package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.DogsijangCoKr;

/**
 * 도그시장 http://www.dogsijang.co.kr
 * @author shkr
 *
 */
public class DogsijangCoKrTest extends BaseTestCase{
//public class DogsijangCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	DogsijangCoKr obj = new DogsijangCoKr();
	
	@Test
	public void 강아지_목록추출() throws IOException {
		
		String linkUrl = "http://www.dogsijang.co.kr/board_dog/list.php?tb=board_sale";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.dogsijang.co.kr/board_dog/view.php?tb=board_sale&pagenum=1&search_live=&search_dog=&no=352472");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		//siteSrl 일련번호
		service.crawling("8");
	}
}
