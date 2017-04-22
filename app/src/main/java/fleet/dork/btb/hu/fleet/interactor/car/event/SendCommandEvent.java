package fleet.dork.btb.hu.fleet.interactor.car.event;

import fleet.dork.btb.hu.fleet.interactor.BaseEvent;
import fleet.dork.btb.hu.fleet.model.Command;

/**
 * Created by Dorka on 2017.04.22..
 */
public class SendCommandEvent extends BaseEvent{
    private String message;
    private int response;

    public SendCommandEvent() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
