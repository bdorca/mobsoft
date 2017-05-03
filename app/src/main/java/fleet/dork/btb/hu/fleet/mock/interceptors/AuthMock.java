package fleet.dork.btb.hu.fleet.mock.interceptors;

import android.net.Uri;

import java.util.Date;

import fleet.dork.btb.hu.fleet.network.NetworkConfig;
import fleet.dork.btb.hu.fleet.network.api.AuthApi;
import fleet.dork.btb.hu.fleet.network.model.Credential;
import fleet.dork.btb.hu.fleet.network.model.Token;
import fleet.dork.btb.hu.fleet.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static fleet.dork.btb.hu.fleet.mock.interceptors.MockHelper.bodyToString;
import static fleet.dork.btb.hu.fleet.mock.interceptors.MockHelper.makeResponse;

public class AuthMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString = "";
        int responseCode = 0;
        Headers headers = request.headers();
        headers = headers.newBuilder().removeAll(AuthApi.AUTH_HEADER).build();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "auth/login")) {
            Credential credential = GsonHelper.getGson().fromJson(bodyToString(request), Credential.class);

            if (credential.getUsername().equals(MockConstants.REGISTERED_USERNAME) && credential.getPassword().equals((MockConstants.REGISTERED_PASSWORD))) {
                responseString = GsonHelper.getGson().toJson(new Token(MockConstants.REGISTERED_TOKEN_ID, 123123123L, new Date(), null));
                responseCode = 200;
            } else {
                responseString = "Bad username of password!";
                responseCode = 401;
            }
        }

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "auth/logout")) {
            if (uri.getQueryParameter("accessToken").equals(MockConstants.REGISTERED_TOKEN_ID)) {
                responseString = "OK";
                responseCode = 200;
            } else {
                responseString = "Unknown accessToken!";
                responseCode = 401;
            }

        }

        return makeResponse(request, headers, responseCode, responseString);
    }

}
