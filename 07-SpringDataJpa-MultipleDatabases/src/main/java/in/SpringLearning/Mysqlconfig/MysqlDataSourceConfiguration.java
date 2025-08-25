package in.SpringLearning.Mysqlconfig;

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
@EnableJpaRepositories(
        basePackages = "in.SpringLearning.ordrdao",
        entityManagerFactoryRef = "mysqlEntityManagerFactoryBean",
        transactionManagerRef = "mysqlplatformTransactionManager"
)
public class MysqlDataSourceConfiguration {
	
	@ConfigurationProperties("spring.datasource.mysql")
	@Bean
	 DataSourceProperties mysqlDataSourceProperties()
	{
		return new DataSourceProperties();
	}
	
	
	@Bean
	 DataSource mysqlDataSource()
	{
		
		
		return mysqlDataSourceProperties().initializeDataSourceBuilder().build() ;
	}
	
	@Bean
    LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactoryBean(
            @Qualifier("mysqlDataSource") DataSource dataSource) {
        
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
//        em.setJpaPropertyMap(properties);
        em.setPackagesToScan("in.SpringLearning.ordrentity"); // Set your entity package
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
        return em;
    }
	
	
	@Bean
	PlatformTransactionManager mysqlplatformTransactionManager(@Qualifier("mysqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean emfb)
	{
		return new JpaTransactionManager(emfb.getObject());
	}

}
