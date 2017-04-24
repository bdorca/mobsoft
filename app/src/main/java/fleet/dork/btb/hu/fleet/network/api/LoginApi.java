package fleet.dork.btb.hu.fleet.network.api;

import fleet.dork.btb.hu.fleet.network.model.Login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
  
  /**
   * 
   * 
   * @param body 
   * @return Call<Void>
   */
  
  @POST("login")
  Call<Void> loginPost(
    @Body Login body
  );

  
}
