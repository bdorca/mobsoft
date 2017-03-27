package fleet.dork.btb.hu.fleet;

import android.app.Application;

import fleet.dork.btb.hu.fleet.ui.UIModule;

public class FleetApplication extends Application {

	public static FleetApplicationComponent injector;

	@Override
	public void onCreate() {
		super.onCreate();

		injector =
				DaggerFleetApplicationComponent.builder().
						uIModule(
								new UIModule(this)
						).build();
	}
}