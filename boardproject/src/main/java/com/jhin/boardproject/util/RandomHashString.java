package com.jhin.boardproject.util;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

public class RandomHashString {
    public static String getRandomString(int length)
    {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int rIndex = random.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    stringBuffer.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    stringBuffer.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    stringBuffer.append((random.nextInt(10)));
                    break;
            }
        }
        return stringBuffer.toString();
    }

    public static String getRandomHashString()
    {
        return BCrypt.hashpw(getRandomString(50),BCrypt.gensalt());
    }
}
