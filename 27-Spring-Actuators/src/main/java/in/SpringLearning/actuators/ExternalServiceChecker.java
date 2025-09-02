package in.SpringLearning.actuators;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceChecker {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "https://github.com/"; // Replace with your actual health-check endpoint

    public boolean isServiceUp() {
        try {
            // Expect the endpoint to return 2xx when service is healthy
            restTemplate.getForEntity(url, String.class);
            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }
}

