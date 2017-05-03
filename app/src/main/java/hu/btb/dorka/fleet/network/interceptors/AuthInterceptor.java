package hu.btb.dorka.fleet.network.interceptors;

import android.util.Log;

import java.io.IOException;

import hu.btb.dorka.fleet.network.api.AuthApi;
import hu.btb.dorka.fleet.network.model.Token;
import hu.btb.dorka.fleet.settings.Settings;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

	public final String TAG = Interceptor.class.getSimpleName();
	private Settings settings;

	public AuthInterceptor(Settings settings) {
		this.settings = settings;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Token sessionToken = settings.getSessionToken();
		if (sessionToken != null && sessionToken.getId() != null && !sessionToken.getId().equals("")) {
			Log.i(TAG, "Set session token: " + AuthApi.AUTH_HEADER + ": " + sessionToken.getId());
			request = request.newBuilder().addHeader(AuthApi.AUTH_HEADER, AuthApi.AUTH_PREFIX + sessionToken.getId()).build();
		}
		return chain.proceed(request);
	}

}
