package fleet.dork.btb.hu.fleet.ui.map;

import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.ui.Screen;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface MapScreen extends Screen{

    void refreshCoordinates(List<Car> list);
}
