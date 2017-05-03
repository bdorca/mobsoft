package fleet.dork.btb.hu.fleet.mock.interceptors;

import android.net.Uri;

import java.util.Date;

import hu.aut.examples.szia.model.Credential;
import hu.aut.examples.szia.model.Token;
import hu.aut.examples.szia.model.User;
import hu.aut.examples.szia.network.NetworkConfig;
import hu.aut.examples.szia.network.auth.AuthApi;
import hu.aut.examples.szia.util.GsonHelper;
import hu.aut.examples.szia.util.MD5Hash;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.aut.examples.szia.mock.interceptors.MockHelper.bodyToString;
import static hu.aut.examples.szia.mock.interceptors.MockHelper.makeResponse;

public class AuthMock {
	public static Response process(Request request) {
		Uri uri = Uri.parse(request.url().toString());

		String responseString = "";
		int responseCode = 0;
		Headers headers = request.headers();
		headers = headers.newBuilder().removeAll(AuthApi.AUTH_HEADER).build();

		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "SziaUsers/login")) {
			Credential credential = GsonHelper.getGson().fromJson(bodyToString(request), Credential.class);

			if (credential.getUsername().equals(MockConstants.REGISTERED_USERNAME) && credential.getPassword().equals(MD5Hash.make(MockConstants.REGISTERED_PASSWORD))) {
				responseString = GsonHelper.getGson().toJson(new Token(MockConstants.REGISTERED_TOKEN_ID, 123123123L, new Date(), null));
				responseCode = 200;
			} else {
				responseString = "Bad username of password!";
				responseCode = 401;
			}
		}

		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "SziaUsers/logout")) {
			if (uri.getQueryParameter("accessToken").equals(MockConstants.REGISTERED_TOKEN_ID)) {
				responseString = "OK";
				responseCode = 200;
			} else {
				responseString = "Unknown accessToken!";
				responseCode = 401;
			}

		}

		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "SziaUsers")) {

			User user = GsonHelper.getGson().fromJson(bodyToString(request), User.class);
			if (user.getUsername().equals(MockConstants.UNREGISTERED_USERNAME) && user.getPassword().equals(MD5Hash.make(MockConstants.UNREGISTERED_PASSWORD))) {
				responseString = GsonHelper.getGson().toJson(user);
				responseCode = 200;
			} else {
				responseString = "Error, user already registered";
				responseCode = 410;
			}
		}

		return makeResponse(request, headers, responseCode, responseString);
	}

}
