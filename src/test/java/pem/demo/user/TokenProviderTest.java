package pem.demo.user;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenProviderTest {
    @Test
    public void makeTokenTest() throws Exception{
        TokenProvider tokenProvider = new TokenProvider();
        String s = tokenProvider.makeJwt("guest");
        System.out.println("s = " + s);

        Claims claims = tokenProvider.parseJwtToken("Bearer " + s);
        System.out.println("claims = " + claims.get("user"));
        System.out.println("claims = " + claims.getIssuer());

    }

}