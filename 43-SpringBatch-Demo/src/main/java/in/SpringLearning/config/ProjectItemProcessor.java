package in.SpringLearning.config;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;

import in.SpringLearning.entity.Projects;

public class ProjectItemProcessor implements ItemProcessor<Projects, Projects> {

	@Override
	public Projects process(Projects item) throws Exception {
		
		if(Optional.ofNullable(item) != null) {
			
			return new Projects(
					item.getProjectId(),
					item.getProjectName().toLowerCase(),
					item.getBudget());
		}
		
		return null;
	}

}
