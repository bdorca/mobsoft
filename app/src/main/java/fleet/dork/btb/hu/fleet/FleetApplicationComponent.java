package fleet.dork.btb.hu.fleet;

import javax.inject.Singleton;

import dagger.Component;
import fleet.dork.btb.hu.fleet.interactor.InteractorModule;
import fleet.dork.btb.hu.fleet.interactor.car.CarInteractor;
import fleet.dork.btb.hu.fleet.repository.RepositoryModule;
import fleet.dork.btb.hu.fleet.ui.UIModule;
import fleet.dork.btb.hu.fleet.ui.carlist.CarListActivity;
import fleet.dork.btb.hu.fleet.ui.details.DetailsActivity;
import fleet.dork.btb.hu.fleet.ui.login.LoginActivity;
import fleet.dork.btb.hu.fleet.ui.map.MapActivity;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class})
public interface FleetApplicationComponent {
    void inject(CarListActivity carListActivity);
    void inject(LoginActivity loginActivity);
    void inject(DetailsActivity detailsActivity);
    void inject(MapActivity mapActivity);
    void inject(FleetApplication application);
    void inject(CarInteractor interactor);
}