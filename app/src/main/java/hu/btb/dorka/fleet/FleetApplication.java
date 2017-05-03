package hu.btb.dorka.fleet;

import android.app.Application;

import javax.inject.Inject;

import hu.btb.dorka.fleet.repository.Repository;
import hu.btb.dorka.fleet.ui.UIModule;

public class FleetApplication extends Application {

	public static FleetApplicationComponent injector;

	@Inject
	Repository repository;

    public void setInjector(FleetApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

	@Override
	public void onCreate() {
		super.onCreate();

		injector =
				DaggerFleetApplicationComponent.builder().
						uIModule(
								new UIModule(this)
						).build();
		repository.open(getApplicationContext());
	}
}