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
 * 유기견보호센터 http://www.animal.or.kr
 * @author shkr
 *
 */
public class AnimalOrKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String domain = "http://www.animal.or.kr";
		String selector = "#fboardlist table .mw_basic_list_subject_m";
		String patternId ="(.*)(wr_id=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		for( Element ele :  elements) {
			logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
			
			SiteLinkData cli  = new SiteLinkData();
			
			//제목 추출
			String dataTitle = ele.getElementsByClass("mw_basic_list_subject_desc").text();
			logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
			cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));

			//링크 추출
			String dataLink = ele.select("a").attr("href").replace("..", ""); 
			logger.debug( "LINK : {}" , domain + dataLink );
			cli.setDataLink(domain + dataLink);

			//이미지 추출
			String dataImg = ele.getElementsByTag("img").attr("src").replace("..", ""); ;
			logger.debug( "IMAGE : {}" , domain + dataImg );
			cli.setDataImg(domain + dataImg);	
			
			//아이디 추출
			String dataId = dataLink.replaceAll(patternId, "$3");
			logger.debug( "ID : {}" , dataId );
			cli.setDataId( dataId );
			
			//내용 접근 URL
			cli.setDataLink(cli.getDataLink());	
			
			list.add(cli);
			
		}
		


		return list;
	}
	
	/**
	 * 강아지 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public void getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "#view_content";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
		
	}
	
	
}
