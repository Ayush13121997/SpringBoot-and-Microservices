package in.SpringLearning.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import in.SpringLearning.report.IReport;

@Component
public class ReportService {

	private IReport report;

	@Autowired
	public ReportService(IReport report) {
		this.report = report;
	}

	public void createReport() {

		report.generateReport();
	}

}
