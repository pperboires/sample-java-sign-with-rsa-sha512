package org.example;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANJjhZQxpOUYm/ZU\n" +
                "J9BIHTSEg1mq8Y96t/ruNuRcGHrrGeveslWT4iYK4HLBakwSab2knKpdzAjWY6a+\n" +
                "P1dzlKJlEM86tEVaByw5MiUiZdwA3t//K2XSXEclZv4YO09TI0ODd7Q8cfATqGzz\n" +
                "pRstXlqR918L3hBkSR3n4TgEevVdAgMBAAECgYA7ZUli6xkIbIgrZNrQnGtSVk7W\n" +
                "08HapeSm/PWWRsHKgfERfA+QxW1FHDD9dMlTrKcZmQ15s31UyWHcwz0SrVi6dNhs\n" +
                "ND3TEnwASVc0six01f+aDIHkX+rQFOtxpdnzTwOAcqMZABvWASsq8/GXAhLLJxuJ\n" +
                "G+ZvDa85dRELzQST6QJBANsZpQGE/ba3p2NuKm/C2xXO+XKqTSZTuch5puIPJCTv\n" +
                "oWwYqWtFx2bgCfqhbXlP0uGjfL5nJ4NLEaf0KRRPD1MCQQD10kpXmLjl4FHCppcj\n" +
                "239LBmFp/0ydw/qzk6xexh+ZlpIKChCAPvmI0dlXULvZR326XQNS7B4z1y36iysG\n" +
                "akKPAkEAlWs5wn2F4VQCDacVvb4vVwIdz/sgPiEfM/7ytnwI9D+P4H45G/UsdZYY\n" +
                "eBoa5H5xnHu4GMmUgs9xLQ8v7K+2DQJAX4vSK4QH8gmjndXkjyJkssOTH4EExZmE\n" +
                "G0J90ASQCcGVl+NbkI1prj1qjd6qdQStoL9AaH2wK5QqoeRv37lzjwJAEeTPJrmL\n" +
                "9qdqac/eBa/Sh5r4KYubOrNms7gP1ImqTJmaN94X6m65NQRTaFU9EfMBkc4IBetQ\n" +
                "N/V5KNZTeMZTGA==\n" +
                "-----END PRIVATE KEY-----\n";

        String content = "teste";

        java.security.Signature signature = java.security.Signature.getInstance("SHA512withRSA");

        RSAPrivateKey rsa = getPrivateKey(privateKey);

        signature.initSign(rsa);
        signature.update(content.getBytes("UTF-8"));

        byte[] signed = signature.sign();

        String assinatura = Base64.getEncoder().encodeToString(signed);

        System.out.println("Assinatura: " + assinatura);
    }

    public static RSAPrivateKey getPrivateKey(String key) throws Exception {

        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("\n", "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}
