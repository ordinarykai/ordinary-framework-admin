package io.github.ordinarykai.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

/**
 * @author kai
 * @date 2022/3/12 13:39
 */
public abstract class MyStringUtil {

    /**
     * 两次Md5加密
     *
     * @param plaintext 要加密的字符串
     * @return 加密后的字符串
     */
    public static String twiceMd5Encode(String plaintext) {
        return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(plaintext.getBytes(StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8));
    }

}