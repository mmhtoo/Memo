package com.mmhtoo.note.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mmhtoo.note.service.ITokenService;
import com.mmhtoo.note.util.KeyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService implements ITokenService {

    private @Value("${jwt.issuer}") String ISSUER;
    // in month
    private @Value("${jwt.age}") int TOKEN_AGE;

    @Override
    public String generate(Map<String, String> payload)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(getExpiredDate())
                .withPayload(payload)
                .sign(getAlgorithm());
    }

    @Override
    public boolean hasExpired(String token)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
       JWTVerifier verifier = getVerifier();
       DecodedJWT decodedJWT = verifier.verify(token);
       return decodedJWT.getExpiresAt().before(new Date());
    }

    @Override
    public boolean isValid(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        DecodedJWT decodedJWT = this.getVerifier().verify(token);
        String issuer = decodedJWT.getIssuer();
        return !this.hasExpired(token) && issuer.equals(ISSUER);
    }

    @Override
    public Algorithm getAlgorithm()
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Algorithm.RSA256(
                KeyUtil.getRSAPublicKey() ,
                KeyUtil.getRSAPrivateKey()
        );
    }

    @Override
    public Date getExpiredDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,TOKEN_AGE);
        return calendar.getTime();
    }

    @Override
    public JWTVerifier getVerifier()
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return JWT.require(getAlgorithm())
                .build();
    }

    @Override
    public Map<String, Claim> getPayloadFromToken(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return this.getVerifier()
                .verify(token)
                .getClaims();
    }

    @Override
    public Map<String, Claim> getPayloadFromRequest(HttpServletRequest request) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if( bearerToken == null ) return null;

        return this.getPayloadFromToken(
                bearerToken.substring(7)
        );
    }

}
