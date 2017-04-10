package fleet.dork.btb.hu.fleet.interactor.car;

import com.google.common.eventbus.EventBus;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.repository.Repository;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class CarInteractor {

    @Inject
    EventBus bus;

    @Inject
    Repository repo;

    public CarInteractor() {
        injector.inject(this);
    }

    public void getCars(){

    }
}
