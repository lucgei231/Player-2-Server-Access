package com.elefant.botAuthentication;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignatureVerifier {

    public static boolean verifySignature(String message, String signature) throws Exception {
        // Read the signature from file

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        // Create public key instance
        byte[] publicKeyBytes = Base64.getDecoder().decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl+5V+nnwelBIpfve3I0Fw4oLn+qLoLoOow/xmB+YPgRylH/amYSSITm9UuHxvodk43m+815ByN6DmdqBRB/HawsFeNWIPSH2PRCYykmEs6lfE/vKhnbedxvcCQfsVdGZhQ2rtYo1BdyvmFmjJJbXWV1Jtdednkg6zM0S8hROQJetpUXdfAteVNHVUbhJK4JJLfk0TXUrJg83+kxfIm3pezlacJ3Dfow3i2yY+YmPdz11P/XOAi8L/Ct0dAyRaNDoYf4A5MJ70DSdvoGsAHfa/Z4oY/WCvKowp6GSbunF8bQtnrnnWXz5B4hC3bJi/i5jrdiSawYIRK0621c8Q3EgTQIDAQAB");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Create signature instance
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(message.getBytes(StandardCharsets.UTF_8));

        // Verify the signature
        return sig.verify(signatureBytes);
    }

    public static String signString(String message) throws Exception {
        // Read and process the private key from file
        String privatePEM = new String(Files.readAllBytes(Paths.get("private_key.pem")));
        privatePEM = privatePEM
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        // Create private key instance
        byte[] privateKeyBytes = Base64.getDecoder().decode(privatePEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Create signature
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sig.sign());
    }


}