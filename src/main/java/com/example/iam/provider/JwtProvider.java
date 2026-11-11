package com.example.iam.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.example.iam.model.Rol;
import com.example.iam.model.Usuario;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private Key getSigningKey() {
        // JWT_SECRET_KEY must be a Base64-encoded value of at least 256 bits
        // Generate with: openssl rand -base64 48
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    // Generación del token empaquetando los roles en el Claim "roles"
    public String generateToken(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        String username = user.getUsername();
        List<String> roles = user.getRoles()
                .stream()
                .map(Rol::getNombre)
                .collect(Collectors.toList());
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        return getClaims(token).get("roles", List.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}