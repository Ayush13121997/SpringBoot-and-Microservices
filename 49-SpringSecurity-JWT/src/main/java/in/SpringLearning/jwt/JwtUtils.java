package in.SpringLearning.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	
	public static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${spring.app.jwt.expiration}")
	private int jwtExpirationMs;
	
	@Value("${spring.app.jwt.secret}")
	private String jwtSecret;
	
	//Generate JWT token from UserName
	
	/*This method generates a JWT for a user, including the username as the subject,
	 *  the current time as the issue time, and an expiration time based on jwtExpirationMs. 
	 *  The token is signed for security,
	*/
	public String generateJwtToken(UserDetails userDetails) {
		
		String username = userDetails.getUsername();
		
		return Jwts.builder()//method is used to create a new JWT token.This is the starting point for building the token, and you can add various claims (data) to it.
					.subject(username)// method sets the "subject" claim of the JWT.The subject is typically the username or unique identifier of the user.
					.issuedAt(new Date())//This indicates the exact time when the token was created.
					.expiration(new Date(new Date().getTime()+ jwtExpirationMs))//This indicates when the token will expire.
					.signWith(key())//The token is signed for security
					.compact();// The compact method finalizes the JWT creation process and returns the token as a String.This string is the actual JWT that can be sent to the client or stored
	}
	
	//Key for signing JWT
	public Key key() {
		
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));//This line decodes the Base64-encoded secret key (jwtSecret) and generates a cryptographic key using the HMAC-SHA algorithm.
	}
	
	
	// Get JWT from request header
	public String getJwtFromHeader(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		log.debug("Authorization token : {} " + bearerToken);
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			
			return bearerToken.substring(7);
		}
		
		return null;
	}
	
	//Getting UserName From Jwt Tokens
	public String getUserNameFromJwtToken(String token) {
		
		return Jwts.parser()//This line initializes a JWT parser using the Jwts class from the io.jsonwebtoken library.
				.verifyWith((SecretKey)key())//This line sets the key that will be used to verify the signature of the JWT. The key is obtained by calling the key() method, which returns a SecretKey object.
				.build()//This line builds the JWT parser with the specified verification key.
				.parseSignedClaims(token)//This line parses the provided JWT token (the token parameter) and extracts the signed claims from it.
				.getPayload()//	This line retrieves the payload (claims) of the parsed JWT.
				.getSubject();//This line extracts the "subject" claim from the JWT payload, which typically contains the username or unique identifier of the user.
	}
	

	//Validate JWT
	
	public boolean validateJwtToken(String authToken) {
		
		try {
			
			System.out.println(" Validation of Jwt ");
			
			Jwts.parser()
			.verifyWith((SecretKey)key())
			.build()
			.parseSignedClaims(authToken);
			
			return true;
			
		} catch (MalformedJwtException e) {
			log.error("Invalid Jwt Token: {} " + e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("Jwt token is expired : {}" + e.getMessage());
			
		}catch (UnsupportedJwtException e) {
			log.error("Jwt token is unsupported : {}" + e.getMessage());
		}catch (IllegalArgumentException e) {
			log.error("Jwt claims string is empty : {}" + e.getMessage());
		}
		
		return false ;
	}
	
	

}
