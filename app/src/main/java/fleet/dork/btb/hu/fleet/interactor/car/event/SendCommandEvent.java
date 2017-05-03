package fleet.dork.btb.hu.fleet.interactor.car.event;


import fleet.dork.btb.hu.fleet.util.NetworkEvent;

/**
 * Created by Dorka on 2017.04.22..
 */
public class SendCommandEvent extends NetworkEvent {
    private int response;

    public SendCommandEvent() {
    }


    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
