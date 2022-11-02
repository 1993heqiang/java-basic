package org.rookie.utils;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class HttpsClient {
	public static void main(String[] args) throws Exception {
		// Path to the key store that holds the SSL certificate (typically a jks file).
		String certPath = "";
		// Password used to access the key in the key store.
		String password = "";
		SSLContext sslContext = generateSSLContext(certPath, password);
		String str = "https://xxxxx.example.com:443";
		URL url = new URL(str);
		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
		urlConnection.setHostnameVerifier((s, sslSession) -> true);
		urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());
		int code = urlConnection.getResponseCode();
		System.out.println(code);
	}

	private static SSLContext generateSSLContext(String certPath, String password) {
		try (InputStream inputStream = HttpsClient.class.getResourceAsStream(certPath)) {
			char[] pwdChars = password.toCharArray();
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(inputStream, pwdChars);

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, pwdChars);

			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), SecureRandom.getInstanceStrong());
			return sslContext;
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
}
