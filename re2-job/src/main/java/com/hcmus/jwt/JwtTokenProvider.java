package com.hcmus.jwt;

import com.hcmus.manager.IProductConnector;
import com.hcmus.manager.impl.ProductConnector;
import com.hcmus.model.Product;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenProvider {


    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "HongQuan";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    private final String JWT_USERNAME = "LeHongQuan";

    // Tạo ra jwt từ thông tin user
    public String generateToken() {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(JWT_USERNAME)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin UserName từ jwt
    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) throws Exception {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (Exception exception) {
            throw exception;
        }
    }
}
