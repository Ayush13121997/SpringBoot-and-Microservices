package in.SpringLearning.H2config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
(basePackages = "in.SpringLearning.productdao",
entityManagerFactoryRef = "h2EntityManagerFactoryBean"
, transactionManagerRef = "h2platformTransactionManager" )
public class H2DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.h2")
    DataSourceProperties h2DataSourceProperties()
	{
		return new DataSourceProperties();
	}

    @Bean
    DataSource h2DataSource()
	{
		
		return h2DataSourceProperties().initializeDataSourceBuilder().build();
	}
    
    @Bean
    LocalContainerEntityManagerFactoryBean h2EntityManagerFactoryBean(
            @Qualifier("h2DataSource") DataSource dataSource) {
    	
    	HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setJpaPropertyMap(properties);
		em.setPackagesToScan("in.SpringLearning.productentity"); // Set your entity package
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		return em;
	}
	

	@Bean
	PlatformTransactionManager h2platformTransactionManager(@Qualifier("h2EntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean emfb)
	{
		return new JpaTransactionManager(emfb.getObject());
	}
    
}
