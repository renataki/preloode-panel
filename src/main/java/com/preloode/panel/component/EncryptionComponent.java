package com.preloode.panel.component;

import com.preloode.panel.model.encryption.RsaResponse;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


@Component
public class EncryptionComponent {


    private static final Logger logger = LoggerFactory.getLogger(EncryptionComponent.class);

    private PrivateKey rsaPrivateKey;

    private PublicKey rsaPublicKey;


    public String convertBytesToHex(byte[] byteString) {

        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0; i < byteString.length; i++) {

            String hex = Integer.toHexString(0xff & byteString[i]);

            if(hex.length() == 1) {

                stringBuffer.append('0');

            }

            stringBuffer.append(hex);

        }

        logger.info("Byte to hex converted");

        return stringBuffer.toString();

    }


    public RsaResponse generateRsaKeyPair(int keySize) {

        RsaResponse result = new RsaResponse() {
            {
                setResponse("Failed to generate RSA key pair");
                setResult(false);
            }
        };

        try {

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(keySize);
            KeyPair pair = keyGen.generateKeyPair();
            result.setPrivateKey(Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded()));
            result.setPublicKey(Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));

            result.setResponse("RSA key pair generated");
            result.setResult(true);

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info(result.getResponse());

        return result;

    }


    public String hmacSha256Hash(String secretKey, String string) {

        String result = "";

        try {

            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);

            result = new String(Hex.encodeHex(mac.doFinal(string.getBytes())));

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public String hmacSha512Hash(String secretKey, String string) {

        String result = "";

        try {

            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
            mac.init(secretKeySpec);

            result = new String(Hex.encodeHex(mac.doFinal(string.getBytes())));

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public void initializeRsaPrivateKey(String key) {

        try {

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            rsaPrivateKey = keyFactory.generatePrivate(keySpec);

            logger.info("RSA private key initialized");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

    }


    public void initializeRsaPublicKey(String key) {

        try {

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            rsaPublicKey = keyFactory.generatePublic(keySpec);

            logger.info("RSA public key initialized");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

    }


    public String rsaDecrypt(String privateKey, String string) {

        String result = "";

        initializeRsaPrivateKey(privateKey);

        try {

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

            byte[] stringByte = Base64.getDecoder().decode(string.getBytes());

            result = new String(cipher.doFinal(stringByte));

            logger.info("RSA string decrypted");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public String rsaEncrypt(String publicKey, String string) {

        String result = "";

        initializeRsaPublicKey(publicKey);

        try {

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

            byte[] stringByte = cipher.doFinal(string.getBytes());

            result = Base64.getEncoder().encodeToString(stringByte);

            logger.info("RSA string encrypted");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public byte[] sha256Byte(String message) {

        byte[] result = null;

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            result = messageDigest.digest(message.getBytes(Charset.forName("UTF-8")));

            logger.info("SHA 256 byte hashed");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public String sha256Hash(String string) {

        String result = "";

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encodedByte = messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
            result = convertBytesToHex(encodedByte);

            logger.info("SHA 256 string hashed");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public String sha512Hash(String string) {

        String result = "";

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] encodedByte = messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
            result = convertBytesToHex(encodedByte);

            logger.info("SHA 512 string hashed");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


}
