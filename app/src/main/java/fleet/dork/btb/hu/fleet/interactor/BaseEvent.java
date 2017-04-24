package fleet.dork.btb.hu.fleet.interactor;

/**
 * Created by Dorka on 2017.04.22..
 */

public class BaseEvent {

    Throwable throwable;


    public BaseEvent() {
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
