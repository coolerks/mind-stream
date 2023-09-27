package top.integer.blog.utils;

import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * jwt 工具类
 * @author moyok
 */
@Slf4j
public class JwtUtils {
    private static String SALT;
    private static JWTVerifier VERIFIER;

    public static String createToken(long userId) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000 * 24))
                .withClaim("id", userId)
                .sign(Algorithm.HMAC512(SALT));
    }

    static {
        String path = System.getProperty("user.dir");
        File file = new File(path + "/SALT");
        if (!file.exists()) {
            String salt = UUID.randomUUID().toString();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                bufferedWriter.write(salt);
                bufferedWriter.flush();
            } catch (IOException e) {
                log.error("创建salt失败....");
                System.exit(0);
            }
        };
        try (FileInputStream data = new FileInputStream(file)) {
            SALT = SecureUtil.md5(data);
            Algorithm algorithm = Algorithm.HMAC512(SALT);
            VERIFIER = JWT.require(algorithm).build();
        } catch (IOException e) {
            log.error("加密工具错误....");
            System.exit(0);
        }
    }

    public static void verify(String token) {
        VERIFIER.verify(token);
    }

    public static long getUserId(String token) {
        return VERIFIER.verify(token).getClaim("id").asLong();
    }


}
