package fleet.dork.btb.hu.fleet.interactor.car.event;

import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.util.NetworkEvent;

/**
 * Created by Dorka on 2017.04.22..
 */
public class GetCarsEvent extends NetworkEvent {

    private List<Car> cars;

    public GetCarsEvent() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
