package fleet.dork.btb.hu.fleet;

import javax.inject.Singleton;

import dagger.Component;
import fleet.dork.btb.hu.fleet.ui.UIModule;
import fleet.dork.btb.hu.fleet.ui.details.DetailsActivity;
import fleet.dork.btb.hu.fleet.ui.login.LoginActivity;
import fleet.dork.btb.hu.fleet.ui.main.MainActivity;
import fleet.dork.btb.hu.fleet.ui.map.MapActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface FleetApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(DetailsActivity detailsActivity);
    void inject(MapActivity mapActivity);
}