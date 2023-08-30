package com.mmhtoo.note.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;

public interface ITokenService {

    /*
     * for generating token with payload
     * @params{ payload : Map<String,String> }
     * @return : String
     * description : Will generate token with payload from argument
     */
    String generate(Map<String,String> payload) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    /*
     * for checking token is expired or not
     * @params{ token : String }
     * @return : boolean
     * description : Will return true if token has not expired , otherwise false
     */
    boolean hasExpired(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    /*
     * for checking token is currently valid or not
     * @params{ token : String }
     * @return : boolean
     * description : Will return true if token is valid, otherwise false
     */
    boolean isValid(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    /*
     * for getting algorithm for signing token
     * @return : Algorithm
     */
    Algorithm getAlgorithm() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    /*
     * will return expire date from current date
     */
    Date getExpiredDate();

    /*
     * will return verifier
     */
    JWTVerifier getVerifier() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    /*
     * for getting payload from token
     */
    Map<String, Claim> getPayloadFromToken(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;

}
