package kr.pethub.job.crawler.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.core.utils.JsoupUtil;
import kr.pethub.job.crawler.dao.CrawlingDao;
import kr.pethub.job.crawler.vo.SiteLink;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.job.crawler.vo.SiteLinkLog;

@Service
public class CrawlingService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CrawlingDao crawlingDao;
	
	/**
	 * Reflction 을 이용하여 Class 호출 실행
	 */
	public void crawling() {
		crawling(""); 
	}
	
	/**
	 * Reflction 을 이용하여 Class 호출 실행
	 * @param siteSrl
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public void crawling(String siteSrl) {
		
		//사이트 Crawling 대상 URL
		List<SiteLink> linkList = crawlingDao.selectSiteLinkList(siteSrl);
		 String patternUrl = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$";
		
		for( SiteLink lst : linkList ) {

			logger.info("----------------------------- " + lst.getLinkNm() +" :  "+ lst.getLinkUrl() );
			
			int linkCnt = 0;
			SiteLinkLog siteLinkLog = new SiteLinkLog();
			
			try {
				
				//Class 생성
				Class<?> clasz = Class.forName(lst.getLinkCls());
				Object obj = clasz.newInstance();
				
				//Method 호출
				Method getList = clasz.getMethod(lst.getLinkMtdLst(), String.class);
				List<SiteLinkData> list =  (List<SiteLinkData>)getList.invoke(obj, lst.getLinkUrl());
				
				//추출한 데이터 저장
				for(int i = 0; i < list.size(); i++ ) {
					
					//키값(ID)이 올바른것 만 저장, 숫자 이여야 함 
					if(  JsoupUtil.isRegex("^[0-9]+$", list.get(i).getDataId()) ) { 
					
						SiteLinkData siteLinkData = new SiteLinkData();
						siteLinkData.setSiteSrl(lst.getSiteSrl());
						siteLinkData.setLinkSrl(lst.getLinkSrl());
						siteLinkData.setDataId(list.get(i).getDataId());
						siteLinkData.setDataTitle(list.get(i).getDataTitle());
						siteLinkData.setDataLink(list.get(i).getDataLink());
						siteLinkData.setDataImg(list.get(i).getDataImg());
						
						siteLinkData.setDataLink((list.get(i).getDataLink()));
						
						//update 후 없으면 insert
						if(crawlingDao.updateSiteLinkData(siteLinkData) == 0){
							
							//내용 추출
							if( StringUtils.isNotEmpty( lst.getLinkMtdCts() )  && JsoupUtil.isRegex(patternUrl, siteLinkData.getDataLink()) ) {
								Method getContent = clasz.getMethod(lst.getLinkMtdCts(), SiteLinkData.class);
								getContent.invoke(obj, siteLinkData);
							}
							
							crawlingDao.insertSiteLinkData(siteLinkData);
						}
						
						linkCnt++;
					}
					
				}
				
				//결과 로그저장(성공)
				siteLinkLog.setSiteSrl(lst.getSiteSrl());
				siteLinkLog.setLinkSrl(lst.getLinkSrl());
				siteLinkLog.setLogCd("200");
				siteLinkLog.setLinkCnt(linkCnt);
				crawlingDao.insertSiteLinkLog(siteLinkLog);
				
			}catch(Exception e) {
				//결과 로그저장(에러)
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String exceptionAsString = sw.toString();
				
				siteLinkLog.setSiteSrl(lst.getSiteSrl());
				siteLinkLog.setLinkSrl(lst.getLinkSrl());
				siteLinkLog.setLogCd("500");
				siteLinkLog.setLogMsg(exceptionAsString);
				siteLinkLog.setLinkCnt(linkCnt);
				crawlingDao.insertSiteLinkLog(siteLinkLog);
				
				e.printStackTrace();
			}finally {
				
				//최종 리스트 수, 실행일 업데이트
				SiteLink siteLink = new SiteLink();
				siteLink.setLinkCnt(linkCnt);
				siteLink.setLinkSrl(lst.getLinkSrl());
				crawlingDao.updateSiteLinkCnt(siteLink);
			}
			
			
		}
		
	}
	
}
