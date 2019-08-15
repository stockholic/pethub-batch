package kr.pethub.crawling;

import java.io.IOException;



import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.SsagaeSsagaeCoKr;

/**
 * 싸개싸개  http://www.ssagae-ssagae.co.kr
 * @author shkr
 *
 */
//public class SsagaeSsagaeCoKrTest extends BaseTestCase{
public class SsagaeSsagaeCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	SsagaeSsagaeCoKr obj = new SsagaeSsagaeCoKr();
	
	@Test
	public void 강아지무료_목록추출() throws IOException{
		
		
		String linkUrl = "http://www.ssagae-ssagae.co.kr/bbs/board.php?bo_table=b0101";
		obj.getDogList(linkUrl);
		
		String linkUrl2 = "http://www.ssagae-ssagae.co.kr/bbs/board.php?bo_table=b0102";
		obj.getDogList(linkUrl2);
		
	}
	
	@Test
	public void 강아지무료_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.ssagae-ssagae.co.kr/bbs/board.php?bo_table=b0101&wr_id=580");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		
		//siteSrl 일련번호
		service.crawling("11");
	}
}
