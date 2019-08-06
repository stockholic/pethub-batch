package kr.pethub;

import java.util.HashMap;


import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.pethub.core.configuration.AnnotationConfiguration;
import kr.pethub.core.configuration.beans.jobHandler;


public class initBatch {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		
		Map<String, String> map = new HashMap<String, String>();

		if (args.length > 0) {
			
			map.put("job", args[0]);
			for (int i = 1; i < args.length; i++) {
				String[] params = args[i].split("=");
				map.put(params[0], params.length > 1 ? params[1] : "");
			}
			
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
			context.register(AnnotationConfiguration.class);
			context.refresh();

			jobHandler handler = context.getBean(jobHandler.class);
			handler.runJob(map);

		} else {
			help();
		}

		System.exit(0);

	}

	public static void help() {
		System.out.println("-----------------------------------------------");
		System.out.println("\t잡명을 입력하세요 !!");
		System.out.println("\tUage : java -jar 잡명 [파라미터 ...]");
		System.out.println("\tex) java -jar job startDt=2015 endDt=2016");
		System.out.println("-----------------------------------------------");
	}

}
