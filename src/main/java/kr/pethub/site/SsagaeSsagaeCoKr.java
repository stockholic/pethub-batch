package kr.pethub.site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.pethub.core.utils.JsoupUtil;
import kr.pethub.job.crawler.vo.SiteLinkData;

/**
 * 싸개싸개  http://www.ssagae-ssagae.co.kr
 * @author shkr
 *
 */

public class SsagaeSsagaeCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 무료  목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String selector = "#fboardlist > div.list-webzine .list-item";
		String domain = "http://www.ssagae-ssagae.co.kr";
		String patternId ="(.*)(wr_id=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		Collections.reverse(elements);
		
//		System.out.println(">>>>>>>" +  elements);
		
//		for(  Element ele :  elements ) {
//			System.out.println(ele.select(".media-heading").first().getElementsByTag("a").text());
//			System.out.println(ele.select(".media-heading").first().getElementsByTag("a").attr("href"));
//			System.out.println(ele.select(".img-item").first().getElementsByTag("a").attr("href"));
//			System.out.println("----------------------------------------------------------------------");
//		}
		
		
		int k = 1;
		for( Element ele :  elements) {
				

			logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
			
			SiteLinkData cli  = new SiteLinkData();
			
			//제목 추출
			String dataTitle = ele.select(".media-heading").first().getElementsByTag("a").text();
			logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
			cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle)) ;
			
			//링크 추출
			String dataLink =  ele.select(".media-heading").first().getElementsByTag("a").attr("href");
			logger.debug( "LINK : {}" , dataLink );
			cli.setDataLink(dataLink);
			
			//이미지 추출
			String dataImg =  ele.select(".img-item").first().getElementsByTag("a").attr("href");
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
		

		return list;
	}
	
	/**
	 * 강아지 무료 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = ".view-content";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
	
	
}
