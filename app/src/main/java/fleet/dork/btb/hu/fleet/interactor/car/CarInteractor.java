package fleet.dork.btb.hu.fleet.interactor.car;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.exception.UnexpectedErrorException;
import fleet.dork.btb.hu.fleet.interactor.car.event.GetCarEvent;
import fleet.dork.btb.hu.fleet.interactor.car.event.GetCarsEvent;
import fleet.dork.btb.hu.fleet.interactor.car.event.SendCommandEvent;
import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Command;
import fleet.dork.btb.hu.fleet.network.api.CarApi;
import fleet.dork.btb.hu.fleet.repository.Repository;
import retrofit2.Call;
import retrofit2.Response;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class CarInteractor {

    @Inject
    EventBus bus;

    @Inject
    Repository repo;

    @Inject
    CarApi api;

    public CarInteractor() {
        injector.inject(this);
    }

    public void getCars(int size){
        GetCarsEvent event=new GetCarsEvent();
        Call<List<Car>> call=api.carsGet(size);
        try {
            Response<List<Car>> response=call.execute();
            if(!response.isSuccess()){
                throw new UnexpectedErrorException();
            }
            List<Car> cars =response.body();
            repo.updateCars(cars);
            //List<Car> cars= repo.getAllCars();
            event.setCars(cars);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
            e.printStackTrace();
        }
    }

    public void getCar(int id){
        GetCarEvent event=new GetCarEvent();
        try{
            Car c=repo.getCar(id);
            event.setCar(c);
            bus.post(event);
        }catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void refreshCar(int id){
        GetCarEvent event=new GetCarEvent();
        Call<Car> call=api.carsCarIdGet(id);
        try{
            Response<Car> response=call.execute();
            if(!response.isSuccess()){
                throw new UnexpectedErrorException();
            }
            Car c=response.body();
            repo.saveCar(c);
            event.setCar(c);
            bus.post(event);
        }catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void sendCommand(Command command, int id){
        SendCommandEvent event=new SendCommandEvent();
        Call<Void> call=api.carsCarIdCommandGet(command.toString(),id);
        try{
            Response<Void> response=call.execute();
            event.setResponse(response.code());
            bus.post(event);
        }catch(Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
