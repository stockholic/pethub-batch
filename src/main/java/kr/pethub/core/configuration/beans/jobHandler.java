package kr.pethub.core.configuration.beans;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLink;

@Component
public class jobHandler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CrawlingService crawlingService;
	
	/**
	 * job 분기처리
	 * @param params
	 */
	public void runJob(Map<String,String> params) {
		
		logger.info("Job name : {} : ",  params.get("job" ));
		
		String job  = params.get("job").toString();
		
		
		//---------------------------------- 크롤로 작업
		if("crawling".equals(job)){
			
			logger.info("batchItv : {} : ",  params.get("batchItv" ));
			logger.info("siteSrl : {} : ",  params.get("siteSrl" ));
			

			if(params.get("siteSrl") != null || params.get("batchItv") != null) {
				SiteLink siteLink = new SiteLink();
				siteLink.setSiteSrl(params.get("siteSrl" ));
				siteLink.setBatchItv(params.get("batchItv" ));
				
				crawlingService.crawling(siteLink);
			}else {
				logger.info("batchItv or siteSrl not found");
			}
			
		}else {
			logger.info("Job is not found");
		}
		
		
	}
	
	
	
}
