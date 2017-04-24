package fleet.dork.btb.hu.fleet.ui.carlist;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.interactor.car.CarInteractor;
import fleet.dork.btb.hu.fleet.interactor.car.event.GetCarsEvent;
import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.ui.Presenter;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class CarListPresenter extends Presenter<CarListScreen> {

    @Inject
    CarInteractor interactor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public CarListPresenter() {
    }

    @Override
    public void attachScreen(CarListScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }


    public void refreshList(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.getCars(20);
            }
        });
    }

    public void onEventMainThread(GetCarsEvent event){
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading cars", event.getThrowable());
        } else {
            if (screen != null) {
                for(Car c : event.getCars()){
                    screen.showMessage(c.getLicence());
                }
            }
        }
    }
}