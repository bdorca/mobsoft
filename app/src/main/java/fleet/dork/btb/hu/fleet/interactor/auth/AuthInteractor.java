package fleet.dork.btb.hu.fleet.interactor.auth;

import de.greenrobot.event.EventBus;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.interactor.auth.event.LoginEvent;

/**
 * Created by Dorka on 2017.04.22..
 */

public class AuthInteractor {

    @Inject
    EventBus bus;

    public AuthInteractor() {
    }

    public void login(String username, String password){
        LoginEvent event=new LoginEvent();
        try {
            event.setResponse(0);
            event.setMessage("");
            bus.post(event);
        } catch (Exception e) {
            e.printStackTrace();
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
