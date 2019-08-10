package kr.pethub.crawling;

import java.io.IOException;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.ZooseyoOrKr;

/**
 * 종합유기견보호센터  http://www.zooseyo.or.kr https://mobile.zooseyo.or.kr:444
 * @author shkr
 *
 */
public class ZooseyoOrKrTest extends BaseTestCase{
//public class ZooseyoOrKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	ZooseyoOrKr obj = new ZooseyoOrKr();
	
	@Test
	public void 강아지무료_목록추출() throws IOException{
		
		String linkUrl = "https://mobile.zooseyo.or.kr:444/index.php?page=parcel_out_list";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지무료_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("https://mobile.zooseyo.or.kr:444/?page=parcel_out_view&no=281976&bpage=1&area=&animal=&srchDeadDate=");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		
		//siteSrl 일련번호
		service.crawling("11");
	}
}
