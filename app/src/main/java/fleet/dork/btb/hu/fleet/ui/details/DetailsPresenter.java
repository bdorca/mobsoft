package fleet.dork.btb.hu.fleet.ui.details;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.interactor.car.CarInteractor;
import fleet.dork.btb.hu.fleet.interactor.car.event.GetCarEvent;
import fleet.dork.btb.hu.fleet.interactor.car.event.SendCommandEvent;
import fleet.dork.btb.hu.fleet.model.Car;
import fleet.dork.btb.hu.fleet.model.Command;
import fleet.dork.btb.hu.fleet.ui.Presenter;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class DetailsPresenter extends Presenter<DetailsScreen> {

    @Inject
    CarInteractor interactor;

    @Inject
    EventBus bus;

    @Inject
    Executor executor;

    private Car currentCar;

    @Override
    public void attachScreen(DetailsScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }

    public void refreshData(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.getCar(currentCar.getCarId());
            }
        });

    }

    public void setCurrentCar(Car c){
        currentCar=c;
    }

    public void sendCommand(final Command c){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.sendCommand(c,currentCar.getCarId());
            }
        });
    }

    public void onEventMainThread(GetCarEvent event){
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading car", event.getThrowable());
        } else {
            setCurrentCar(event.getCar());
            screen.setDetails(currentCar);
        }
    }

    public void onEventMainThread(SendCommandEvent event){
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading command", event.getThrowable());
        } else {
            screen.commandResponse(event.getResponse());
        }

    }
}
