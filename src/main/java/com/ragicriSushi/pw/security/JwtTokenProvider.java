package com.ragicriSushi.pw.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtTokenProvider {

//    public String createToken(String username, String role)
//    {
//        Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
//        String access_token= JWT.create()
//                .withSubject(username)
//                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
//               // .withIssuer()
//                .withClaim("role",role)
//                .sign(algorithm);
//        return access_token;
//    }
}
