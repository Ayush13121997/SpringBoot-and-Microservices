package in.SpringLearning.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	public static final Logger log = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		log.error("Unauthorized error: {}", authException.getMessage());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);//application/json
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
		
		final Map<String, Object> body = new HashMap<>();
		body.put("status", HttpServletResponse.SC_UNAUTHORIZED);//401
		body.put("error", "Unauthorized");//Unauthorized
		body.put("message", authException.getMessage());//Full message
		body.put("path", request.getServletPath());//path

		final ObjectMapper mapper = new ObjectMapper();
		
		try {
		mapper.writeValue(response.getOutputStream(), body);
		} catch (IOException e) {
			log.error("Error writing response: {}", e.getMessage());
		}
		
		
	}
	
	

}
