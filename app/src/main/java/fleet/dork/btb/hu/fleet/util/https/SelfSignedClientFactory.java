package fleet.dork.btb.hu.fleet.util.https;

import android.content.Context;
import android.support.annotation.RawRes;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;

public class SelfSignedClientFactory {
	public static final String BKS = "BKS";
	public static final String TLS = "TLS";

	public static OkHttpClient.Builder getSelfSignedClient(Context context, @RawRes int keyStoreFile, String keystorePassword)
			throws CertificateException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
		return new OkHttpClient.Builder().sslSocketFactory(getPinnedCertSslSocketFactory(context, keyStoreFile, keystorePassword));
	}

	private static SSLSocketFactory getPinnedCertSslSocketFactory(Context context, @RawRes int keyStoreFile, String keystorePassword)
			throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, KeyManagementException {

		KeyStore trusted = KeyStore.getInstance(BKS);
		InputStream in = context.getResources().openRawResource(keyStoreFile);
		trusted.load(in, keystorePassword.toCharArray());
		SSLContext sslContext = SSLContext.getInstance(TLS);
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(trusted);
		sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

		return sslContext.getSocketFactory();
	}
}

