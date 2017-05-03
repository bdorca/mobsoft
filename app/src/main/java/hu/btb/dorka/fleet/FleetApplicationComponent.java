package hu.btb.dorka.fleet;

import javax.inject.Singleton;

import dagger.Component;
import hu.btb.dorka.fleet.interactor.InteractorModule;
import hu.btb.dorka.fleet.interactor.auth.AuthInteractor;
import hu.btb.dorka.fleet.interactor.car.CarInteractor;
import hu.btb.dorka.fleet.interactor.map.MapInteractor;
import hu.btb.dorka.fleet.network.NetworkModule;
import hu.btb.dorka.fleet.repository.RepositoryModule;
import hu.btb.dorka.fleet.ui.UIModule;
import hu.btb.dorka.fleet.ui.carlist.CarListActivity;
import hu.btb.dorka.fleet.ui.carlist.CarListPresenter;
import hu.btb.dorka.fleet.ui.details.DetailsActivity;
import hu.btb.dorka.fleet.ui.details.DetailsPresenter;
import hu.btb.dorka.fleet.ui.login.LoginActivity;
import hu.btb.dorka.fleet.ui.login.LoginPresenter;
import hu.btb.dorka.fleet.ui.map.MapActivity;
import hu.btb.dorka.fleet.ui.map.MapPresenter;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class, NetworkModule.class})
public interface FleetApplicationComponent {
    void inject(CarListActivity carListActivity);
    void inject(LoginActivity loginActivity);
    void inject(DetailsActivity detailsActivity);
    void inject(MapActivity mapActivity);
    void inject(FleetApplication application);

    void inject(CarListPresenter carListPresenter);

    void inject(DetailsPresenter detailsPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(MapPresenter mapPresenter);

    void inject(CarInteractor interactor);
    void inject(MapInteractor interactor);
    void inject(AuthInteractor interactor);
}