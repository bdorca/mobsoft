package hu.btb.dorka.fleet.network.api;


import java.util.List;

import hu.btb.dorka.fleet.model.Car;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CarApi {
  
  /**
   * 
   * Gets `Car` objects.\nOptional query param of **size** determines\nsize of returned array
   * @param size Size of array
   * @return Call\<List\<Car>>
   */
  
  @GET("cars")
  Call<List<Car>> carsGet(
    @Query("size") int size
  );


  
  /**
   * 
   * 
   * @param carId ID of car
   * @return Call<Car>
   */
  
  @GET("cars/{carId}")
  Call<Car> carsCarIdGet(
    @Path("carId") int carId
  );


  /**
   *
   *
   * @param command command
   * @param carId carId
   * @return Call<Void> call
   */

  @GET("cars/command/{carId}")
  Call<Void> carsCarIdCommandGet(
          @Path("carId") int carId,@Query("command") String command
  );


}
