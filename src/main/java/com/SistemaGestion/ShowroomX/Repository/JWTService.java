package com.SistemaGestion.ShowroomX.Repository;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public interface JWTService {

    public String create(Authentication authentication);

    public boolean validate(String header);

    public Claims getClaims(String header);

    public String getUsername(Claims token);

    public Collection<? extends GrantedAuthority> getRoles(Claims token) throws IOException;
}
