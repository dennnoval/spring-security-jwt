package dennnoval.springsecurityjwt;

import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoTests {

  @Test
  public void secretKeyGenerator() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      SecretKey secretKey = keyGen.generateKey();
      String stringKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
      System.out.println(stringKey);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void secretKeyGenerator2() {
    try {
      byte[] secureRandomKeyBytes = new byte[512 / 8];
      SecureRandom secureRandom = new SecureRandom();
      secureRandom.nextBytes(secureRandomKeyBytes);
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256, secureRandom);
      SecretKey secretKey = keyGen.generateKey();
      String stringKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
      System.out.println(stringKey);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void secretKeySpecGenerator() {
    try {
      byte[] secureRandomKeyBytes = new byte[512 / 8];
      SecureRandom secureRandom = new SecureRandom();
      secureRandom.nextBytes(secureRandomKeyBytes);
      SecretKeySpec secretKeySpec = new SecretKeySpec(secureRandomKeyBytes, "AES");
      String stringKey = Base64.getEncoder().encodeToString(secretKeySpec.getEncoded());
      System.out.println(stringKey);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
