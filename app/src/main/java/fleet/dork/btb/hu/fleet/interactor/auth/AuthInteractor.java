package fleet.dork.btb.hu.fleet.interactor.auth;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import fleet.dork.btb.hu.fleet.exception.UnauthenticatedException;
import fleet.dork.btb.hu.fleet.interactor.auth.event.LoginEvent;
import fleet.dork.btb.hu.fleet.interactor.auth.event.LogoutEvent;
import fleet.dork.btb.hu.fleet.network.api.AuthApi;
import fleet.dork.btb.hu.fleet.network.model.Credential;
import fleet.dork.btb.hu.fleet.network.model.Token;
import fleet.dork.btb.hu.fleet.settings.Settings;
import retrofit2.Call;
import retrofit2.Response;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * Created by Dorka on 2017.04.22..
 */

public class AuthInteractor {

    @Inject
    EventBus bus;

    @Inject
    AuthApi api;

    @Inject
    Settings settings;

    public AuthInteractor() {
        injector.inject(this);
    }

    public void login(String username, String password){
        LoginEvent event=new LoginEvent();
        Call<Token> call= api.loginPost(new Credential(username,password));
        try {
            Response<Token> response=call.execute();
            if(response.code()!=200){
                throw  new UnauthenticatedException();
            }
            Token token=response.body();
            event.setCode(response.code());
            saveSession(token);
            bus.post(event);
        } catch (Exception e) {
            e.printStackTrace();
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void logout() {
        LogoutEvent event = new LogoutEvent();
        Call call = api.logout(settings.getSessionToken().getId());
        Response response;
        try {
            response = call.execute();
            event.setCode(response.code());
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }

    }


    private void saveSession(Token token) throws Exception {

        if (token == null || token.getId() == null || token.getId().equals(""))
            throw new Exception("No session token!");
        else {
            settings.saveSessionToken(token);
        }
    }

}
