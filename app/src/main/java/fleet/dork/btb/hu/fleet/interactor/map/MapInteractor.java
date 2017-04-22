package fleet.dork.btb.hu.fleet.interactor.map;

import de.greenrobot.event.EventBus;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.interactor.map.event.CoordinateCarsEvent;
import fleet.dork.btb.hu.fleet.model.Coordinate;
import fleet.dork.btb.hu.fleet.repository.Repository;

import static android.R.attr.radius;

/**
 * Created by Dorka on 2017.04.22..
 */

public class MapInteractor {
    @Inject
    EventBus bus;

    @Inject
    Repository repo;

    public MapInteractor() {
    }


    public void getCarsAroundCoordinate(Coordinate coordinate, float radius ){
        CoordinateCarsEvent event=new CoordinateCarsEvent();
        try{
            repo.getCarsByCoord(coordinate, radius);
        }catch (Exception e){
            event.setThrowable(e);
            bus.post(event);
        }
    }

}
