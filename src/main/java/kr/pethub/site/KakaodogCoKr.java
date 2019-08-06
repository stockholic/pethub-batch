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
 * KaKao Dog  http://www.kakaodog.co.kr
 * @author shkr
 *
 */
public class KakaodogCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String domain = "http://www.kakaodog.co.kr";
		String selector = ".left tr";
		String patternLink ="(javascript:VipView\\()'([0-9]+)'(.*)";

		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr", selector);
		Collections.reverse(elements);
		
		int k = 1;
		for( Element ele :  elements) {
			logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
			
			SiteLinkData cli  = new SiteLinkData();
			
			if( ele.getElementsByTag("tr").hasAttr("onmouseover")  ) {
				//제목 추출
				String dataTitle = ele.getElementsByTag("td").get(5).text() + " " + ele.getElementsByTag("td").get(6).text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));
	
				//링크 추출
				String dataLink = ele.getElementsByTag("td").get(5).getElementsByTag("a").attr("href");
				dataLink = dataLink.replaceAll(patternLink, "$2");
				logger.debug( "LINK : {}" , domain + "/gnu/vip_view.php?id=" + dataLink );
				cli.setDataLink(domain + "/gnu/vip_view.php?id=" + dataLink);
	
				//이미지 추출
				String dataImg = ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src");
				dataImg = dataImg.contains("camera4.gif") ? "" : domain + "/gnu/" + dataImg; 
				logger.debug( "IMAGE : {}" , dataImg);
				cli.setDataImg(dataImg);	
				
				//아이디 추출
				logger.debug( "ID : {}" , dataLink );
				cli.setDataId( dataLink );
				
				//내용 접근 URL
				cli.setDataLink(cli.getDataLink());	
				
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

		String selector = "body > table > tbody > tr:nth-child(6) > td > table:nth-child(3) > tbody > tr:nth-child(2) > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , "euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
		
	}
	
	
}
