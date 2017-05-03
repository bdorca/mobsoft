package hu.btb.dorka.fleet.interactor.car.event;

import java.util.List;

import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.util.NetworkEvent;

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
