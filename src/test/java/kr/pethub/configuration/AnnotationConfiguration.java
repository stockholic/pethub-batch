package kr.pethub.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = {
													"kr.pethub.configuration"
													,"kr.pethub.job"
												}
												, excludeFilters = {
													@ComponentScan.Filter(Controller.class)
												}
							)
public class AnnotationConfiguration {
}  