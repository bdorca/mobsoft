package fleet.dork.btb.hu.fleet.interactor.map;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.interactor.map.event.CoordinateCarsEvent;
import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Coordinate;
import fleet.dork.btb.hu.fleet.network.api.CarApi;
import fleet.dork.btb.hu.fleet.repository.Repository;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

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
