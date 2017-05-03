package hu.btb.dorka.fleet.ui.login;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.btb.dorka.fleet.interactor.auth.AuthInteractor;
import hu.btb.dorka.fleet.interactor.auth.event.LoginEvent;
import hu.btb.dorka.fleet.ui.Presenter;

import static hu.btb.dorka.fleet.FleetApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class LoginPresenter extends Presenter<LoginScreen> {

    @Inject
    EventBus bus;

    @Inject
    AuthInteractor interactor;
    @Inject
    Executor executor;

    public LoginPresenter() {
    }

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen(){
        bus.unregister(this);
        super.detachScreen();
    }

    public void login(final String username, final String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.login(username, password);
            }
        });
    }

    public void onEventMainThread(LoginEvent event){
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
                screen.showLoginFailure();
            }
            Log.e("Networking", "Error reading command", event.getThrowable());
        } else {
            screen.navigateToMain();
        }

    }

}
