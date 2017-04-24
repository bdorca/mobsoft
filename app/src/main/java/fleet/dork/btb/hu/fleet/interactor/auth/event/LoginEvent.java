package fleet.dork.btb.hu.fleet.interactor.auth.event;

import fleet.dork.btb.hu.fleet.interactor.BaseEvent;

/**
 * Created by Dorka on 2017.04.22..
 */
public class LoginEvent extends BaseEvent{
    private int response;

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public LoginEvent() {

    }
}
