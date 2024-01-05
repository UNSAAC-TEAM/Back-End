package org.unsaac.es_bim.iam.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final static String SECRET_KEY="34kj88h79AJ83jn59JN2038iJNy317H3MPQSMXC7Ansad9n1";
    public String getToken(User user){
        return getToken(new HashMap<>(),user);
    }
    private String getToken(Map<String,Object> extraclaims,User user){
        return Jwts
                .builder()
                .claims(extraclaims)
                .claim("userId",user.getId())
                .claim("role",user.getRole().name())
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey())
                .compact();
    }
    private SecretKey getKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getUserNameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token, UserDetails user){
        final String username=getUserNameFromToken(token);
        return (username.equals(user.getUsername())&&!isTokenExpired(token));
    }
    private Claims getAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
