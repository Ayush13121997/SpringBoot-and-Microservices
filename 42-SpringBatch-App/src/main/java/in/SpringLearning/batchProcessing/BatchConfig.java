package in.SpringLearning.batchProcessing;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import in.SpringLearning.dto.Person;

@Configuration
public class BatchConfig {

    //ItemReader
    @Bean
    FlatFileItemReader<Person> reader(){
		
		return new FlatFileItemReaderBuilder<Person>()
				.name("PersonItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names("firstName" , "lastName")
				.targetType(Person.class)
				.build();
		
		
	}

    //ItemProcessor
    @Bean
    PersonItemProcessor processor() {
		
		return new PersonItemProcessor();
	}

    //ItemWriter
    @Bean
    JdbcBatchItemWriter<Person> writer(DataSource datasource){
		
		return new JdbcBatchItemWriterBuilder<Person>()
				.sql("INSERT INTO person(firstName,lastName) VALUES (:firstName , :lastName)")
				.dataSource(datasource)
				.beanMapped()
				.build();
	}
    
	//steps
    @Bean
    Step step1(JobRepository jobRepository ,FlatFileItemReader<Person> reader , PersonItemProcessor processor , JdbcBatchItemWriter<Person> writer , PlatformTransactionManager manager) {
    	
    	return new StepBuilder("step1", jobRepository)
    			.<Person,Person>chunk(10, manager)
    			.reader(reader)
    			.processor(processor)
    			.writer(writer)
    			.build();
    	
    }
	
	//job
	@Bean
    Job importPersonJob(JobRepository jobRepository , Step step1) {
    	
    	return new JobBuilder("importPersonJob", jobRepository)
    			.start(step1)
    			.build();
    }

}
