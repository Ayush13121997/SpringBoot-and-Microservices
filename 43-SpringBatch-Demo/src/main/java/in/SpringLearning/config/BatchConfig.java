package in.SpringLearning.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import in.SpringLearning.entity.Projects;

@Configuration
public class BatchConfig {
	
	@Autowired
	DataSource datasource;
	
	@Bean
	JdbcCursorItemReader<Projects> reader(){
		
		return new JdbcCursorItemReaderBuilder<Projects>()
				.name("ProjectsItemReader")
				.dataSource(datasource)
				.sql("SELECT project_id,project_name,budget FROM projects")
				.rowMapper(new BeanPropertyRowMapper<>(Projects.class))
				.build();
		
	}
	
	@Bean
	ProjectItemProcessor processer() {
		
		return new ProjectItemProcessor();
	}
	
	@Bean
	FlatFileItemWriter<Projects> writer(){
		
		return new FlatFileItemWriterBuilder<Projects>()
				.name("ProjectsItemWriter")
				.resource(new FileSystemResource("src/main/resources/output-source.csv"))
				.delimited()
				.delimiter(",")
				.sourceType(Projects.class)
				.names("projectId","projectName","budget")
				.build();
			
	}
	
	@Bean
	Step step1(JobRepository jobRepository ,JdbcCursorItemReader<Projects> reader, ProjectItemProcessor processer, FlatFileItemWriter<Projects> writer, PlatformTransactionManager manager) {
		
		return new StepBuilder("step1", jobRepository)
				.<Projects,Projects>chunk(10,manager)
				.reader(reader)
				.processor(processer)
				.writer(writer)
				.build();
		
	}
	
	@Qualifier("importProjectsJob")
	@Bean
	Job importProjectJobs(JobRepository repository , Step step1) {
		
		return new JobBuilder("importProjectsJob", repository)
				.start(step1)
				.build();
	}

}
