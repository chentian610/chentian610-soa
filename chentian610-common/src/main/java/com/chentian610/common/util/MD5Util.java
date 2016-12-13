package com.chentian610.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: zhangyu
 * Date: 11-1-13
 * Time: 上午11:36
 */
public class MD5Util {

    public static String toMd5(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
       /* if (args.length != 1) {
            System.err.println("Usage:java DigitalSignature2Example ");
            System.exit(1);
        }

        byte[] plainText = args[0].getBytes("UTF8");
        //形成RSA公钥对
        //System.out.println("\nStart generating RSA key");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        //System.out.println("Finish generating RSA key");
        //使用私?签名
        Signature sig = Signature.getInstance("SHA1WithRSA");
        sig.initSign(key.getPrivate());
        sig.update(plainText);
        byte[] signature = sig.sign();
       // System.out.println(sig.getProvider().getInfo());
       // System.out.println("\nSignature:");
       // System.out.println(new String(signature, "UTF8"));

        //使用公?验证
       // System.out.println("\nStart signature verification");
        sig.initVerify(key.getPublic());
        sig.update(plainText);
        try {
            if (sig.verify(signature)) {
              //  System.out.println("Signature verified");
            } //else System.out.println("Signature failed");
        } catch (SignatureException e) {
           // System.out.println("Signature failed");
        }
        */
        System.out.println(toMd5("111111"));
    }
}
