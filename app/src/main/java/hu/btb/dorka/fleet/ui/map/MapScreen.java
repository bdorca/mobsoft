package hu.btb.dorka.fleet.ui.map;

import java.util.List;

import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.ui.Screen;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface MapScreen extends Screen{

    void refreshCoordinates(List<Car> list);
}
