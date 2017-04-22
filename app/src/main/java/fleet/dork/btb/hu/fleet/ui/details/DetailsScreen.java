package fleet.dork.btb.hu.fleet.ui.details;

import java.util.List;

import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.ui.Screen;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface DetailsScreen extends Screen {
    void setDetails(Car details);

    void commandResponse(int a, String message);
}
