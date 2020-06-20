 package com.ln;

 import java.security.Key;
 import javax.crypto.Cipher;
 import javax.crypto.KeyGenerator;
 import javax.crypto.SecretKey;
 import javax.crypto.SecretKeyFactory;
 import javax.crypto.spec.DESKeySpec;

 public class DESCoder
 {
   public static final String KEY_ALGORITHM = "DES";
   public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

   public static byte[] initkey()
     throws Exception
   {
     KeyGenerator kg = KeyGenerator.getInstance("DES");

     kg.init(56);

     SecretKey secretKey = kg.generateKey();

     return secretKey.getEncoded();
   }

   public static Key toKey(byte[] key)
     throws Exception
   {
     DESKeySpec dks = new DESKeySpec(key);

     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

     SecretKey secretKey = keyFactory.generateSecret(dks);
     return secretKey;
   }

   public static byte[] encrypt(byte[] data, byte[] key)
     throws Exception
   {
     Key k = toKey(key);

     Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

     cipher.init(1, k);

     return cipher.doFinal(data);
   }

   public static byte[] decrypt(byte[] data, byte[] key)
     throws Exception
   {
     Key k = toKey(key);

     Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

     cipher.init(2, k);

     return cipher.doFinal(data);
   }
 }
