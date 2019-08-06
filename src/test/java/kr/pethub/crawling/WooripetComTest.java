package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.WooripetCom;

/**
 * 우리펫 http://www.우리펫.com
 * @author shkr
 *
 */
public class WooripetComTest extends BaseTestCase{
//public class PuppycafeCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	WooripetCom obj = new WooripetCom();
	
	@Test
	public void 강아지_분양_목록추출() throws IOException{
		
		String linkUrl = "http://www.xn--oy2b11v46j.com/shop/shop/listtype.php?type=4";
		
		obj.getDogList(linkUrl);
	}
	
	
	@Test
	public void 강아지_프리미엄_목록추출() throws IOException{
		
		String linkUrl = "http://www.xn--oy2b11v46j.com/shop/shop/listtype.php?type=3";
		
		obj.getDogList(linkUrl);
	}
	
	
	@Test
	public void 강아지_할인_목록추출() throws IOException{
		
		String linkUrl = "http://www.xn--oy2b11v46j.com/shop/shop/listtype.php?type=5";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.xn--oy2b11v46j.com/shop/shop/item.php?it_id=1556070372");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	
	@Test
	public void 데이터저장(){
		service.crawling("5");
	}
}
