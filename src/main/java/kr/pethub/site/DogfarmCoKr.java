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
 * 착한애견분양 http://chdog.co.kr
 * @author shkr
 *
 */
public class DogfarmCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String domain = "http://www.dogfarm.co.kr";
		String selector = ".left tr";
		String patternId ="(.*)(id=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl, "euc-kr",selector);
		Collections.reverse(elements);
		
		/*
		for( Element ele :  elements) {
			if( ele.getElementsByTag("tr").hasAttr("onmouseover")  ) {
				System.out.println(ele.html());
				System.out.println("----------------------------------------------------------------");
			}
		}
		*/
		
		int k = 1;
		for( Element ele :  elements) {

			if( ele.getElementsByTag("tr").hasAttr("onmouseover")  ) {
			
				logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
				
				SiteLinkData cli  = new SiteLinkData();
				
				//제목 추출
				String dataTitle =  ele.getElementsByTag("td").get(2).text() + " " + ele.getElementsByTag("td").get(3).text() + " " + ele.getElementsByTag("td").get(4).text() + " " + ele.getElementsByTag("td").get(5).text() + " " + ele.getElementsByTag("td").get(6).text();
				logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
				cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));
	
				//링크 추출
				String dataLink = domain + "/gnu/" + ele.getElementsByTag("td").get(2).getElementsByTag("a").attr("href"); 
				logger.debug( "LINK : {}",  dataLink );
				cli.setDataLink( dataLink);
	
				//이미지 추출
				String dataImg = ele.getElementsByTag("td").get(1).getElementsByTag("img").attr("src"); 
				dataImg = dataImg.contains("camera5.gif") ? "" : domain + "/gnu/" + dataImg; 
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

		String selector = "body > table:nth-child(3) > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(8) > td";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() ,"euc-kr", selector );
		
		String dataContent = JsoupUtil.specialCharacterRemove(contents.text());		
		siteLinkData.setDataContent(dataContent);
		logger.debug( "CONTENTS : {}" , dataContent );
	}
	
	
}
