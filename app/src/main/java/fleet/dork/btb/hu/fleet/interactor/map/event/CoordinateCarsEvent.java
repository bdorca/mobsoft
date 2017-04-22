package fleet.dork.btb.hu.fleet.interactor.map.event;

import java.util.List;

import fleet.dork.btb.hu.fleet.interactor.BaseEvent;
import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Coordinate;

/**
 * Created by Dorka on 2017.04.22..
 */
public class CoordinateCarsEvent extends BaseEvent{
    private List<Car> cars;
    private Coordinate coordinate;
    private float radius;

    public CoordinateCarsEvent() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
