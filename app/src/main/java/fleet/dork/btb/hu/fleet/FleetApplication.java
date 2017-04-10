package fleet.dork.btb.hu.fleet;

import android.app.Application;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.repository.Repository;
import fleet.dork.btb.hu.fleet.ui.UIModule;

public class FleetApplication extends Application {

	public static FleetApplicationComponent injector;

	@Inject
	Repository repository;

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