package in.SpringLearning.report;

import org.springframework.stereotype.Controller;

@Controller
public class PdfReport implements IReport{

	@Override
	public void generateReport() {
		
		System.out.println("Excel Report Generated ....");
		
	}
	

}
