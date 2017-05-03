package fleet.dork.btb.hu.fleet.network.api;

import fleet.dork.btb.hu.fleet.network.model.Credential;
import fleet.dork.btb.hu.fleet.network.model.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApi {

    String AUTH_HEADER = "X-Access-Token";
    String AUTH_PREFIX = "";

  @POST("auth/login")
  Call<Token> loginPost(
    @Body Credential body
  );

    @POST("auth/logout")
    Call<ResponseBody> logout(@Query("accessToken") String accessToken);

}
