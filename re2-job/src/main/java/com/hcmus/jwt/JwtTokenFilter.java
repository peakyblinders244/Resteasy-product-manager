package com.hcmus.jwt;

import io.jsonwebtoken.Jwts;
import org.apache.http.auth.UsernamePasswordCredentials;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtTokenFilter implements ContainerRequestFilter {
    private final String JWT_SECRET = "HongQuan";
    private final String JWT_USERNAME = "LeHongQuan";
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    public void filter(ContainerRequestContext requestContext) throws IOException {

        JwtTokenProvider tmp = new JwtTokenProvider();
        String test = tmp.generateToken();
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        String token = authorizationHeader.substring("Bearer ".length()).trim();

        try {
            if(token != null && jwtTokenProvider.validateToken(token)){
                String userName = jwtTokenProvider.getUserNameFromJwt(token);

                //Kiểm tra trong database có tồn tại username ko... làm demo nên cho là auto có
//                if(userName == JWT_USERNAME){
//
//                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
