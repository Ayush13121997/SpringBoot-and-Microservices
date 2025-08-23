package in.SpringLearning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import in.SpringLearning.Service.ReportService;
import in.SpringLearning.config.AppConfig;

@Configuration
public class MainApp {

	public static void main(String[] args) {

		ApplicationContext ctxt = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ReportService reportService = ctxt.getBean(ReportService.class);
		
		reportService.createReport();
		
		ConfigurableApplicationContext c = (ConfigurableApplicationContext)ctxt ;
		
		c.close();
		
	}
}
