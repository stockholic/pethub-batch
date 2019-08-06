package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.ZooseyoCom;

/**
 * 주세요닷컴 http://www.zooseyo.com 
 * @author shkr
 *
 */
public class ZooseyoComTest extends BaseTestCase{
//public class ZooseyoComTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	ZooseyoCom obj = new ZooseyoCom();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "http://www.zooseyo.com/sale/sale_list.php?animal=dog";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.zooseyo.com/sale/sale_view.php?type=f&oid_no=bbag1562389717647&no=277599&page=1&kind=&area=");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	
	@Test
	public void 고양이_목록추출() throws IOException{
		
		String linkUrl = "http://www.zooseyo.com/sale/sale_list.php?animal=cat";
		
		obj.getCatList(linkUrl);
	}

	@Test
	public void 고양이_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.zooseyo.com/sale/sale_view.php?type=f&oid_no=bbag1562571054429&no=277836&page=1&kind=&area=");
		
		obj.getCatContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		
		//siteSrl 일련번호
		service.crawling("3");
	}
}
