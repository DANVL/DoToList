package org.doto.config.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.doto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class TokenProvider {

    private final UserDetailsService userDetailsService;
    private final String SECRET = "SECRET";

    @Autowired
    public TokenProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String provideToken(User user) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setClaims(
                        new HashMap<String, Object>() {{
                            put("id", user.getId());
                            put("email", user.getEmail());
                            put("role", user.getRole().name());
                        }})
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public Authentication getAuth(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return String.valueOf(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().get("email"));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            System.err.println("Exception: " + ex);
            return false;
        }
    }


}
