package in.SpringLearning.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.debug("AuthTokenFilter called for URI: {}", request.getRequestURI());

		try {

			String jwtFromHeader = jwtUtils.getJwtFromHeader(request);// Extract JWT from the request header

			log.debug("AuthTokenFilter - JWT from Header: {}", jwtFromHeader);

			// If JWT is present and valid, set the authentication in the context
			if (jwtFromHeader != null && jwtUtils.validateJwtToken(jwtFromHeader)) {

				String userNameFromJwtToken = jwtUtils.getUserNameFromJwtToken(jwtFromHeader);// Extract username from
																								// JWT

				UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromJwtToken);// Load user
																										// details using
																										// the extracted
																										// username

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userNameFromJwtToken, null, userDetails.getAuthorities());// Create an authentication token

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));// Set
																											// additional
																											// details
																											// for the
																											// authentication
																											// token

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);// Set the authentication in
																							// the security context

				log.debug("Roles from JWT: {}", userDetails.getAuthorities());
			}

		} catch (Exception e) {

			log.error("Cannot set user authentication: {}", e.getMessage());

		}

		filterChain.doFilter(request, response);// Continue the filter chain

	}
}
