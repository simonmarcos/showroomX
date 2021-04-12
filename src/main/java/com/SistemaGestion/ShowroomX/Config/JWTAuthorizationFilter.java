package com.SistemaGestion.ShowroomX.Config;

import com.SistemaGestion.ShowroomX.Repository.JWTService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader("Authorization");

        if (header == null || header.startsWith("Basic ")) {
            chain.doFilter(request, response);
            return;
        }

        Claims token = this.getToken(header);

        UsernamePasswordAuthenticationToken authentication = null;
        if (token != null) {
            String username = jwtService.getUsername(token);
            Collection<? extends GrantedAuthority> listRoles = jwtService.getRoles(token);

            authentication = new UsernamePasswordAuthenticationToken(username, null, listRoles);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private boolean validateToken(String header) {
        return jwtService.validate(header);
    }

    private Claims getToken(String header) {
        return jwtService.getClaims(header);
    }

}
