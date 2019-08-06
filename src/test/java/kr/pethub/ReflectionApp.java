package kr.pethub;

import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReflectionApp {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void 메소드_출력() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		try {
			
			Class<?> clasz = Class.forName("kr.pethub.job.crawler.vo.CrawlingInfo");
			
			logger.debug("class : {} : ", clasz.getName());
			
			Method[] methods = clasz.getMethods();
			for( Method method : methods ) {
				logger.debug(method.getName());
			}
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	@Test
	public void 메소트_실행() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		Object obj = null;
		try {
			
			Class<?> clasz = Class.forName("kr.pethub.job.crawler.vo.CrawlingInfo");
			
			if(obj == null) obj = clasz.newInstance();
			
			Method setId = clasz.getMethod("setId", String.class);
			setId.invoke(obj, "1234");
			
			Method getId = clasz.getMethod("getId");
			logger.debug("getId : {}", getId.invoke(obj));
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	

	
	
}
