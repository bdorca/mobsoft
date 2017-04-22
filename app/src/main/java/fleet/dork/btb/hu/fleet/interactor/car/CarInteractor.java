package fleet.dork.btb.hu.fleet.interactor.car;

import de.greenrobot.event.EventBus;

import java.util.List;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.interactor.car.event.GetCarEvent;
import fleet.dork.btb.hu.fleet.interactor.car.event.GetCarsEvent;
import fleet.dork.btb.hu.fleet.interactor.car.event.SendCommandEvent;
import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Command;
import fleet.dork.btb.hu.fleet.repository.Repository;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class CarInteractor {

    @Inject
    EventBus bus;

    @Inject
    Repository repo;

    public CarInteractor() {
        injector.inject(this);
    }

    public void getCars(){
        GetCarsEvent event=new GetCarsEvent();
        try {
            List<Car> cars= repo.getAllCars();
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

    public void sendCommand(Command command, int id){
        SendCommandEvent event=new SendCommandEvent();
        try{
            event.setMessage("");
            event.setResponse(0);
            bus.post(event);
        }catch(Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
