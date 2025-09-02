package in.SpringLearning.actuators;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MsgMetrics {
	
	public MsgMetrics(MeterRegistry meterRegistry) {
        meterRegistry.counter("Msg.create", "type","online")
				.increment();
    }


}
