package it.aruba.sp.config;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import it.aruba.sp.service.JwtService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	
	private static final int PREFIX_SIZE_JWT = 7;
	private static final String BEARER = "Bearer ";
	private final UserDetailsService userDetailsService;
	private final JwtService jwtService;

	@Override
	protected void doFilterInternal(
			@Nonnull HttpServletRequest request, 
			@Nonnull HttpServletResponse response, 
			@Nonnull FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String jwt;
		if (Objects.isNull(authHeader) || 
				!authHeader.startsWith(BEARER)) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(PREFIX_SIZE_JWT);
		String username = jwtService.extractUsername(jwt);
		
		if (!Objects.isNull(username) && 
				Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername (username);
			
			if (jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails, 
						null, 
						userDetails.getAuthorities());
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
						);
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
