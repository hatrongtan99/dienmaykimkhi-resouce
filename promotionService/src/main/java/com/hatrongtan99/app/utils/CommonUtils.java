package com.hatrongtan99.app.utils;

import java.security.SecureRandom;
import java.util.Random;

public class CommonUtils {

    private static final Integer DEFAULT_CODE_LENGTH = 8;
    public static String generateCouponCode(Integer codeLength) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        if (codeLength == null || codeLength <DEFAULT_CODE_LENGTH){
            codeLength = DEFAULT_CODE_LENGTH;
        }
        Random random = new SecureRandom();
        StringBuilder couponCodeBuffer = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            couponCodeBuffer.append(chars[random.nextInt(chars.length)]);
        }
        return couponCodeBuffer.toString();
    }
}
