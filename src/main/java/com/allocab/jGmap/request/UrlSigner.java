package com.allocab.jGmap.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import net.iharder.Base64;

public class UrlSigner {
	  // This variable stores the binary key, which is computed from the string (Base64) key
	  private static byte[] key;

	  public UrlSigner(String keyString) throws IOException {
	    // Convert the key from 'web safe' base 64 to binary
	    keyString = keyString.replace('-', '+');
	    keyString = keyString.replace('_', '/');
	    //System.out.println("Key: " + keyString);
	    key = Base64.decode(keyString);
	  }

	  public String signRequest(String path, String query) throws NoSuchAlgorithmException,
	    InvalidKeyException, UnsupportedEncodingException, URISyntaxException {

	    // Retrieve the proper URL components to sign
	    String resource = path + '?' + query;

	    // Get an HMAC-SHA1 signing key from the raw key bytes
	    SecretKeySpec sha1Key = new SecretKeySpec(key, "HmacSHA1");

	    // Get an HMAC-SHA1 Mac instance and initialize it with the HMAC-SHA1 key
	    Mac mac = Mac.getInstance("HmacSHA1");
	    mac.init(sha1Key);

	    // compute the binary signature for the request
	    byte[] sigBytes = mac.doFinal(resource.getBytes());

	    // base 64 encode the binary signature
	    String signature = Base64.encodeBytes(sigBytes);

	    // convert the signature to 'web safe' base 64
	    signature = signature.replace('+', '-');
	    signature = signature.replace('/', '_');

	    return resource + "&signature=" + signature;
	  }	
	
}
