package hu.btb.dorka.fleet.interactor.map;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.btb.dorka.fleet.interactor.map.event.CoordinateCarsEvent;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Coordinate;
import hu.btb.dorka.fleet.network.api.CarApi;
import hu.btb.dorka.fleet.repository.Repository;

import static hu.btb.dorka.fleet.FleetApplication.injector;

/**
 * Created by Dorka on 2017.04.22..
 */

public class MapInteractor {
    @Inject
    EventBus bus;

    @Inject
    Repository repo;

    @Inject
    CarApi api;

    public MapInteractor() {
        injector.inject(this);

    }


    public void getCarsAroundCoordinate(Coordinate coordinate, float radius ){
        CoordinateCarsEvent event=new CoordinateCarsEvent();
        try{
            List<Car> cars=repo.getCarsByCoord(coordinate, radius);
            event.setCars(cars);
            bus.post(event);
        }catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
