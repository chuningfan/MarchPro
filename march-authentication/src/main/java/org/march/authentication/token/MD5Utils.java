package org.march.authentication.token;

import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MD5Utils {
	
	 private final static String DES = "DES";
     
     private final static String MD5 = "MD5";
     
     private final static String KEY="opeddsaead323353484591dadbc382a18340bf83414536";
  
     public static String md5Encrypt(String data) {
         String resultString = null;
         try {
             resultString = new String(data);
             MessageDigest md = MessageDigest.getInstance(MD5);
             resultString =byte2hexString(md.digest(resultString.getBytes()));
         } catch (Exception ex) {
         }
         return resultString;
     }
     

     private  static String byte2hexString(byte[] bytes) {
         StringBuffer bf = new StringBuffer(bytes.length * 2);
         for (int i = 0; i < bytes.length; i++) {
             if ((bytes[i] & 0xff) < 0x10) {
                 bf.append("T0");
             }
             bf.append(Long.toString(bytes[i] & 0xff, 16));
         }
         return bf.toString();
     }
     
     public static String desEncrypt(String data, String key) throws Exception {
         if (key==null) {
             key=KEY;
         }
         byte[] bt = encrypt(data.getBytes(), key.getBytes());
         String strs = new BASE64Encoder().encode(bt);
         return strs;
     }
  
     public static String desDecrypt(String data, String key) throws IOException,
             Exception {
         if (data == null){
             return null;
         }
         if (key==null) {
             key=KEY;
         }
         BASE64Decoder decoder = new BASE64Decoder();
         byte[] buf = decoder.decodeBuffer(data);
         byte[] bt = decrypt(buf,key.getBytes());
         return new String(bt);
     }
  
     private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
         DESKeySpec dks = new DESKeySpec(key);
         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
         SecretKey securekey = keyFactory.generateSecret(dks);
         Cipher cipher = Cipher.getInstance(DES);
         cipher.init(Cipher.ENCRYPT_MODE, securekey);
         return cipher.doFinal(data);
     }
      
      
     private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
         DESKeySpec dks = new DESKeySpec(key);
         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
         SecretKey securekey = keyFactory.generateSecret(dks);
         Cipher cipher = Cipher.getInstance(DES);
         cipher.init(Cipher.DECRYPT_MODE, securekey);
         return cipher.doFinal(data);
     }
     
}
