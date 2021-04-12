package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Config.SimpleGrantedAuthorityMixin;
import com.SistemaGestion.ShowroomX.Repository.JWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Service
public class JWTServiceImpl implements JWTService {


    @Override
    public String create(Authentication authResult) {
        Collection<? extends GrantedAuthority> listRoles = authResult.getAuthorities();
        Claims claims = Jwts.claims();

        try {
            claims.put("authorities", new ObjectMapper().writeValueAsString(listRoles));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(authResult.getName())
                .setIssuer("simon-marcos")
                .signWith(SignatureAlgorithm.HS512, "simon-marcos")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();

        return token;
    }

    @Override
    public boolean validate(String header) {
        boolean isValid = false;
        if (header != null && header.split("\\.").length == 3) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public Claims getClaims(String header) {
        Claims token = null;

        //If token is different True
        if (!validate(header)) return null;

        token = Jwts.parser()
                .setSigningKey("simon-marcos")
                .parseClaimsJws(header.replace("Bearer ", "").trim()).getBody();
        return token;
    }

    @Override
    public String getUsername(Claims token) {
        return token.getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(Claims token) throws IOException {
        String username = token.getSubject();
        Object roles = token.get("authorities");

        Collection<? extends GrantedAuthority> listRoles = Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class).readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
        return listRoles;
    }
}
