package toandx.render.demoBackend.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import toandx.render.demoBackend.entity.User;

import java.util.Date;

// Step 4. Create JwtTokenProvider to generate JWT, decode, get info from JWT, validate JWT
@Component
public class JwtTokenProvider {

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 10*1000; //10 second

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    // Tạo ra jwt từ thông tin user
    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Claims getClaimFromToken(String token) { // Decode JWT
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    public String getUsernameFromToken(String token) {
        final Claims claims = getClaimFromToken(token);
        return claims.getSubject();
    }

    public Date getExpirationFromToken(String token) {
        final Claims claims = getClaimFromToken(token);
        return claims.getExpiration();
    }

    public boolean isTokenExpired(String token)
    {
        final Date expiration = getExpirationFromToken(token);
        System.out.println(String.format("Now %ld - Expire %ld", System.currentTimeMillis(),expiration.getTime()));
        return expiration.before(new Date());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        } catch (MalformedJwtException ex) {
            System.out.println(ex.toString());
            return false;
        } catch (ExpiredJwtException ex) {
            System.out.println(ex.toString());
            return false;
        } catch (UnsupportedJwtException ex) {
            System.out.println(ex.toString());
            return false;
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }
}

