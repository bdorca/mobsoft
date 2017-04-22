package fleet.dork.btb.hu.fleet.ui.map;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.interactor.auth.AuthInteractor;
import fleet.dork.btb.hu.fleet.interactor.map.MapInteractor;
import fleet.dork.btb.hu.fleet.interactor.map.event.CoordinateCarsEvent;
import fleet.dork.btb.hu.fleet.model.Coordinate;
import fleet.dork.btb.hu.fleet.ui.Presenter;

import static android.R.attr.radius;
import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class MapPresenter extends Presenter<MapScreen> {


    @Inject
    EventBus bus;

    @Inject
    MapInteractor interactor;
    @Inject
    Executor executor;


    public MapPresenter() {
    }

    @Override
    public void attachScreen(MapScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }

    public void refreshMap(final Coordinate coordinate, final float radius){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.getCarsAroundCoordinate(coordinate, radius);
            }
        });
    }

    public void onEventMainThread(CoordinateCarsEvent event){
        if(event.getThrowable()!=null){
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading command", event.getThrowable());
        }else{
            screen.refreshCoordinates(event.getCars());
        }
    }
}
