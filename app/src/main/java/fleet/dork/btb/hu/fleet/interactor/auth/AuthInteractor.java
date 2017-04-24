package fleet.dork.btb.hu.fleet.interactor.auth;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.exception.UnauthenticatedException;
import fleet.dork.btb.hu.fleet.interactor.auth.event.LoginEvent;
import fleet.dork.btb.hu.fleet.network.api.LoginApi;
import fleet.dork.btb.hu.fleet.network.model.Login;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dorka on 2017.04.22..
 */

public class AuthInteractor {

    @Inject
    EventBus bus;

    @Inject
    LoginApi api;

    public AuthInteractor() {
    }

    public void login(String username, String password){
        LoginEvent event=new LoginEvent();
        Call<Void> call= api.loginPost(new Login(username,password));
        try {
            Response<Void> response=call.execute();
            if(response.code()!=200){
                throw  new UnauthenticatedException();
            }
            event.setResponse(response.code());
            bus.post(event);
        } catch (Exception e) {
            e.printStackTrace();
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
