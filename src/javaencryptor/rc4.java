/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaencryptor;

import java.security.*;
import javax.crypto.*;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author umang18oct
 */
class rc4 {

   private static final String algorithm = "RC4";
   public static String encrypt(String toEncrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // create an instance of cipher
      Cipher cipher = Cipher.getInstance(algorithm);
  
      // initialize the cipher with the key
      cipher.init(Cipher.ENCRYPT_MODE, sk);
  
      // enctypt!
      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
      String encodedFinalData = DatatypeConverter.printBase64Binary(encrypted);
  
      return encodedFinalData;
   }
  
   public static String decrypt(String toDecrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      byte[] todecrypt = toDecrypt.getBytes();
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // do the decryption with that key
      Cipher cipher = Cipher.getInstance(algorithm);
      cipher.init(Cipher.DECRYPT_MODE, sk);
      byte[] decrypted = cipher.doFinal(todecrypt);
      String decodedFinalData = DatatypeConverter.printBase64Binary(decrypted);
      return decodedFinalData;
   }
}

