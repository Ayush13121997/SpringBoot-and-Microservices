package in.SpringLearning.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import in.SpringLearning.model.Customer;
import in.SpringLearning.util.KafkaConstants;

//This configuration sets up Kafka producer beans so the Spring Boot app can easily send messages with string keys 
//and JSON-serialized Customer objects as values.
//
//producerFactory() creates producers configured with bootstrap server and serializers.
//
//kafkaTemplate() provides a ready-to-use template for producing Kafka messages with those settings.



@Configuration
public class KafkaProduceConfig {

    @Bean
    ProducerFactory<String, Customer> producerFactory() {
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.HOST);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Customer>(configProps);
	}

	/**
	 * This method is used to create KafkaTemplate bean obj
	 * @return
	 */
	@Bean(name = "kafkaTemplate")
	KafkaTemplate<String, Customer> kafkaTemplate() {
		
		return new KafkaTemplate<>(producerFactory());
	}


}
