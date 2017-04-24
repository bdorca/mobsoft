package fleet.dork.btb.hu.fleet.network.api;


import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.network.model.Login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DefaultApi {
  
  /**
   * 
   * 
   * @param carId ID of car
   * @return Call<Car>
   */
  
  @GET("car")
  Call<Car> carGet(
    @Path("carId") Integer carId
  );

  
  /**
   * 
   * Gets `Car` objects.\nOptional query param of **size** determines\nsize of returned array
   * @param size Size of array
   * @return Call<List<Car>>
   */
  
  @GET("cars")
  Call<List<Car>> carsGet(
    @Query("size") Double size
  );

  
  /**
   * 
   * 
   * @param command 
   * @param id 
   * @return Call<Void>
   */
  
  @GET("command")
  Call<Void> commandGet(
    @Query("command") String command, @Query("id") Integer id
  );

  
  /**
   * 
   * 
   * @param latitude Latitude component of location.
   * @param longitude Longitude component of location.
   * @return Call<List<Car>>
   */
  
  @GET("coordinates")
  Call<List<Car>> coordinatesGet(
    @Query("latitude") Double latitude, @Query("longitude") Double longitude
  );

  
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
