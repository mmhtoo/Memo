package com.mmhtoo.note.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mmhtoo.note.service.ITokenService;
import com.mmhtoo.note.util.KeyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService implements ITokenService {

    private @Value("${jwt.issuer}") String ISSUER;
    // in days
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
    public boolean isValid(String token) {
        return false;
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
        calendar.add(Calendar.DAY_OF_MONTH,TOKEN_AGE);
        return calendar.getTime();
    }

    @Override
    public JWTVerifier getVerifier()
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return JWT.require(getAlgorithm())
                .build();
    }

}
