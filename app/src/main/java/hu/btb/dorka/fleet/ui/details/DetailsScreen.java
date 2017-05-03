package hu.btb.dorka.fleet.ui.details;

import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.ui.Screen;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface DetailsScreen extends Screen {
    void setDetails(Car details);

    void commandResponse(int a);
}
