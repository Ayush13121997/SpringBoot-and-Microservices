package in.SpringLearning.report;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

@Controller
@Primary
public class ExcelReport implements IReport {

	@Override
	public void generateReport() {
		
		System.out.println("Excel Report Generated ....");
		
	}
	
	

}
