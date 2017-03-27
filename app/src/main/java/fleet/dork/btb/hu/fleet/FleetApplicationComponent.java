package fleet.dork.btb.hu.fleet;

import javax.inject.Singleton;

import dagger.Component;
import fleet.dork.btb.hu.fleet.ui.UIModule;
import fleet.dork.btb.hu.fleet.ui.main.MainActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface FleetApplicationComponent {
    void inject(MainActivity mainActivity);

}