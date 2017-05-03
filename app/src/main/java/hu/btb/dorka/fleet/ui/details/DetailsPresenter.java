package hu.btb.dorka.fleet.ui.details;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.btb.dorka.fleet.interactor.car.CarInteractor;
import hu.btb.dorka.fleet.interactor.car.event.GetCarEvent;
import hu.btb.dorka.fleet.interactor.car.event.SendCommandEvent;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Command;
import hu.btb.dorka.fleet.ui.Presenter;

import static hu.btb.dorka.fleet.FleetApplication.injector;

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
        screen.setDetails(c);
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
