package kr.pethub.site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.pethub.core.utils.JsoupUtil;
import kr.pethub.job.crawler.vo.SiteLinkData;

/**
 * 주세요닷컴 http://www.zooseyo.com
 * @author shkr
 *
 */

public class ZooseyoCom {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String selector = "body > table:nth-child(6) > tbody > tr > td:nth-child(2) > table:nth-child(9) > tbody > tr > td > table";
		String domain = "http://www.zooseyo.com";
		String patternId ="(.*)(no=)([0-9]+)(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		for( Element ele :  elements) {
			
			if( ele.getElementsByTag("tr").hasAttr("onclick")  ) {
				
				logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(4).text()  + " " + ele.getElementsByTag("td").get(5).text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
				
				//링크 추출
				String dataLink = domain + ele.getElementsByTag("td").get(6).getElementsByTag("a").attr("href").replace("..", "");
				logger.debug( "LINK : {}" , dataLink );
				cli.setDataLink(dataLink);
				
				//이미지 추출
				String dataImg = domain + ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src");
				logger.debug( "IMAGE : {}" , dataImg );
				cli.setDataImg(dataImg);	
				
				//아이디 추출
				String dataId = dataLink.replaceAll(patternId, "$3");
				logger.debug( "ID : {}" , dataId );
				cli.setDataId( dataId );
				
				//내용 접근 URL
				cli.setDataLink(dataLink);	
				
				list.add(cli);
				
				
			}
			
		}
		

		return list;
	}
	
	/**
	 * 강아지 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "body > table:nth-child(2) > tbody > tr > td > table:nth-child(24) > tbody > tr > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	/**
	 * 고양이 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getCatList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String selector = "body > table:nth-child(6) > tbody > tr > td:nth-child(2) > table:nth-child(9) > tbody > tr > td > table";
		String domain = "http://www.zooseyo.com";
		String patternId ="(.*)(no=)([0-9]+)(.*)";
		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		for( Element ele :  elements) {
			
			if( ele.getElementsByTag("tr").hasAttr("onclick")  ) {
				
				logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle = ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(4).text()  + " " + ele.getElementsByTag("td").get(5).text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
				
				//링크 추출
				String dataLink = domain + ele.getElementsByTag("td").get(6).getElementsByTag("a").attr("href").replace("..", "");
				logger.debug( "LINK : {}" , dataLink );
				cli.setDataLink(dataLink);
				
				//이미지 추출
				String dataImg = domain + ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src");
				logger.debug( "IMAGE : {}" , dataImg );
				cli.setDataImg(dataImg);	
				
				//아이디 추출
				String dataId = dataLink.replaceAll(patternId, "$3");
				logger.debug( "ID : {}" , dataId );
				cli.setDataId( dataId );
				
				//내용 접근 URL
				cli.setDataLink(dataLink);	
				
				list.add(cli);
				
				
			}
			
		}
		

		return list;
	}
	
	/**
	 * 고양이 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getCatContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "body > table:nth-child(2) > tbody > tr > td > table:nth-child(24) > tbody > tr > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
}
