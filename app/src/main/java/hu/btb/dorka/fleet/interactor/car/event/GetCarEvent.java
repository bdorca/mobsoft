package hu.btb.dorka.fleet.interactor.car.event;

import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.util.NetworkEvent;

/**
 * Created by Dorka on 2017.04.22..
 */
public class GetCarEvent extends NetworkEvent {

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public GetCarEvent() {

    }
}
