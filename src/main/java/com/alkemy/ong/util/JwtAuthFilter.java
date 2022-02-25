package com.alkemy.ong.util;

import static com.alkemy.ong.util.Constants.SECRET_KEY;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SPACE = "";
    private final String AUTHORITIES = "authorities";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String authenticationHeader = request.getHeader("Authorization");
            if (existJwtToken(authenticationHeader)) {
                Claims claims = validateToken(request);
                if (claims.get(AUTHORITIES) != null) {
                    setUpAuth(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException
                | UnsupportedJwtException
                | MalformedJwtException e) {

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, SPACE);
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    private void setUpAuth(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get(AUTHORITIES);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean existJwtToken(String authenticationHeader) {
        return (authenticationHeader != null && authenticationHeader.startsWith(PREFIX));
    }

}
