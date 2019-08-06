package kr.pethub.job.crawler.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.MultiSqlSessionDaoSupport;
import kr.pethub.job.crawler.vo.SiteLink;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.job.crawler.vo.SiteLinkLog;

@Repository
public class CrawlingDao extends MultiSqlSessionDaoSupport{
	
	/**
	 * 사이트 Crawling 대상 URL 목록
	 * @return
	 */
	public List<SiteLink> selectSiteLinkList(String siteSrl) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("siteSrl",  siteSrl);
		
		return selectList("selectSiteLinkList", map);
	}
	
	/**
	 * 사이트 데이터 조회
	 * @param siteLinkData
	 * @return
	 */
	public List<SiteLinkData> selectSiteLinkDataList(SiteLinkData siteLinkData) {
		return selectList("selectSiteLinkDataList", siteLinkData);
	}
	
	/**
	 * 사이트 데이터 등록
	 * @param siteLinkData
	 * @return
	 */
	public int insertSiteLinkData(SiteLinkData siteLinkData) {
		return insert("insertSiteLinkData", siteLinkData);
	}
	
	/**
	 * 사이트 데이터 수정
	 * @param siteLinkData
	 * @return
	 */
	public int updateSiteLinkData(SiteLinkData siteLinkData) {
		return update("updateSiteLinkData", siteLinkData);
	}
	
	/**
	 * 사이트 데이터 삭제
	 * @param dataSrl
	 * @return
	 */
	public int deleteSiteLinkData(Integer dataSrl) {
		return delete("deleteSiteLinkData", dataSrl);
	}
	
	/**
	 * 사이트 데이터 에러 로그 등록
	 * @param siteLinkData
	 * @return
	 */
	public int insertSiteLinkErrorLog(Map<String,String> map) {
		return insert("insertSiteLinkErrorLog", map);
	}
	
	/**
	 * 사이트 리크수 업데이트
	 * @param siteLink
	 * @return
	 */
	public int updateSiteLinkCnt(SiteLink siteLink) {
		return update("updateSiteLinkCnt", siteLink);
	}
	
	/**
	 * 사이트 로그 등록
	 * @param siteLinkLog
	 * @return
	 */
	public int insertSiteLinkLog(SiteLinkLog siteLinkLog) {
		return insert("insertSiteLinkLog", siteLinkLog);
	}
	
}
