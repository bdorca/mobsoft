package fleet.dork.btb.hu.fleet.interactor.car.event;

import fleet.dork.btb.hu.fleet.interactor.BaseEvent;
import fleet.dork.btb.hu.fleet.model.Car;

/**
 * Created by Dorka on 2017.04.22..
 */
public class GetCarEvent extends BaseEvent{

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
