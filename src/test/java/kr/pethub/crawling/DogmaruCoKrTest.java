package kr.pethub.crawling;

/**
 * 도그마루 https://dogmaru.co.kr
 */
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.DogmaruCoKr;

public class DogmaruCoKrTest extends BaseTestCase{
//public class DogmaruCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	DogmaruCoKr obj = new DogmaruCoKr();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "https://dogmaru.co.kr/sdog";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("https://dogmaru.co.kr/sdog/?q=YToxOntzOjEyOiJrZXl3b3JkX3R5cGUiO3M6MzoiYWxsIjt9&bmode=view&idx=1659432&t=board");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 고양이_목록추출() throws IOException{
		
		String linkUrl = "https://dogmaru.co.kr/cat";
		
		obj.getCatList(linkUrl);
	}
	
	@Test
	public void 고양이_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("https://dogmaru.co.kr/cat/?q=YToxOntzOjEyOiJrZXl3b3JkX3R5cGUiO3M6MzoiYWxsIjt9&bmode=view&idx=1806822&t=board");
		
		obj.getCatContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		service.crawling("1");
	}
}
