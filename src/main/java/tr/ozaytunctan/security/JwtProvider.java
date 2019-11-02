package tr.ozaytunctan.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	private static String SECRET = "O.we.@!Z.tnctA.jwt.Y1254!";

	private static Long TOKEN_EXPIRE_TIME = 15 * 60 * 1000L;

	public String generateToken(String subject, Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET.getBytes()).compact();
	}

	public String generateToken(UserDetails userPrincible) {
		return Jwts.builder().setClaims(new HashMap<>()).setSubject(userPrincible.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET.getBytes()).compact();
	}

	public Date extractExpired(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	private <R> R extractClaims(String token, Function<Claims, R> claimResolver) {
		return claimResolver.apply(extractClaims(token));
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token) {
		return extractExpired(token).after(new Date());
	}

	public boolean tokenValidate(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return (userDetails.getUsername().equals(username) && isTokenExpired(token));
	}
}
