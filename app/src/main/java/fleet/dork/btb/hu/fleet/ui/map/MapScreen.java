package fleet.dork.btb.hu.fleet.ui.map;

import java.util.List;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface MapScreen {

    void refreshCoordinate(int id,float lat, float lon);
    void refreshCoordinates(List<Integer> list);
}
