package fleet.dork.btb.hu.fleet.ui.login;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.interactor.auth.AuthInteractor;
import fleet.dork.btb.hu.fleet.interactor.auth.event.LoginEvent;
import fleet.dork.btb.hu.fleet.ui.Presenter;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

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
