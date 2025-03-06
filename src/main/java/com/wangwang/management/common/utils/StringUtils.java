package com.wangwang.management.common.utils;

import com.wangwang.management.common.exception.BusinessException;
import com.wangwang.management.enums.ResponseCodeAndMsgEnum;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * 脱敏手机号
     */
    public static String desensitizePhoneNumber(String phone) {
        if (phone == null || phone.length() < 4) {
            return phone; // 手机号长度不够，直接返回原字符串
        }
        // 将前三位和最后四位之间的部分替换为*
        char[] charArray = phone.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int s = 1;
        int length = charArray.length;
        for (char c : charArray) {
            if (s <= 3) {
                stringBuilder.append(c);
            } else if (s > length - 4 && length > 8) {
                stringBuilder.append(c);
            } else {
                stringBuilder.append("*");
            }
            s++;
        }

        return stringBuilder.toString();
    }


    /**
     * 生成对称加密Key
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // 可以选择128, 192, 256位
        return keyGenerator.generateKey();
    }

    /**
     * 对称加密数据
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) {
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new BusinessException(ResponseCodeAndMsgEnum.DATA_ENCRYPTION_AND_DECRYPTION, e);
        }

    }

    /**
     * 对称解密数据
     *
     * @param encryptedData
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String key) {
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new BusinessException(ResponseCodeAndMsgEnum.DATA_ENCRYPTION_AND_DECRYPTION, e);
        }
    }


    public static void main(String[] args) {

        String key = "Q8arRKLlcFnEsR8rT7QRzpfwpwuMFT8vho/DcXtD7Wo=";

        String phone = "13044441111";
        String phone1 = "13044441111=";
        String phone2 = "13044441111+";
        String phone3 = "130444411_11+";
        String phone4 = "王按时大多数130444411_11+";

        String[] aa = {phone, phone1, phone2, phone3, phone4};
        try {
            for (String s : aa) {

                String encrypt = encrypt(s, key);


                String decrypt = decrypt(encrypt, key);

                System.out.println(s + ":  " + encrypt + "   " + decrypt);

            }


////            SecretKey secretKey = generateKey();
//////
//////
//////            System.out.println(secretKey.getFormat());
//////
////            System.out.println(secretKey.getAlgorithm());
//////
////            System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
//
//
//
//
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//
////
////            Cipher cipher = Cipher.getInstance("AES");
//////            cipher.init(Cipher.DECRYPT_MODE, key);
////            byte[] decodedBytes = ;
////            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
//
//            String s = Base64.getEncoder().encodeToString(cipher.doFinal(phone.getBytes(StandardCharsets.UTF_8)));
//            System.out.println(phone + " ： " + s + "     == " + new String(cipher.doFinal(Base64.getDecoder().decode(s))));
//
//            String s1 = Base64.getEncoder().encodeToString(cipher.doFinal(phone1.getBytes(StandardCharsets.UTF_8)));
//
//            System.out.println(phone1 + " ： " +s1 + "     == " + new String(cipher.doFinal(Base64.getDecoder().decode(s1))));
//
//            String s2 = Base64.getEncoder().encodeToString(cipher.doFinal(phone2.getBytes(StandardCharsets.UTF_8)));
//
//            System.out.println(phone2 + " ： " +s2 + "     == " + new String(cipher.doFinal(Base64.getDecoder().decode(s2))));
//            String s3 = Base64.getEncoder().encodeToString(cipher.doFinal(phone3.getBytes(StandardCharsets.UTF_8)));
//
//            System.out.println(phone3 + " ： " +s3 + "     == " + new String(cipher.doFinal(Base64.getDecoder().decode(s3))));
//            String s4 = Base64.getEncoder().encodeToString(cipher.doFinal(phone4.getBytes(StandardCharsets.UTF_8)));
//
//            System.out.println(phone4 + " ： " +s4 + "     == " + new String(cipher.doFinal(Base64.getDecoder().decode(s4)),StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }


//        System.out.println(desensitizePhoneNumber("123"));
//
//        System.out.println(desensitizePhoneNumber("1234"));
//        System.out.println(desensitizePhoneNumber("12345"));
//        System.out.println(desensitizePhoneNumber("123456"));
//        System.out.println(desensitizePhoneNumber("1234567"));
//        System.out.println(desensitizePhoneNumber("12345678"));
//        System.out.println(desensitizePhoneNumber("123456789"));
//
//        System.out.println(desensitizePhoneNumber("123456789a"));
//        System.out.println(desensitizePhoneNumber("123456789ab"));
//        System.out.println(desensitizePhoneNumber("123456789abc"));
    }
}
