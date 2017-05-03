package hu.btb.dorka.fleet;

import android.app.Application;

import javax.inject.Inject;

import hu.btb.dorka.fleet.mock.MockNetworkModule;
import hu.btb.dorka.fleet.network.NetworkModule;
import hu.btb.dorka.fleet.repository.Repository;
import hu.btb.dorka.fleet.ui.UIModule;
import hu.btb.dorka.fleet.util.Store;

public class FleetApplication extends Application {

	public static AppComponent injector;

	@Inject
	Repository repository;

    public void setInjector(AppComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

	@Override
	public void onCreate() {



        //MockDebug + MockRelease
        //noinspection PointlessBooleanExpression
        if (BuildConfig.MOCK) {
            injector = DaggerMockAppComponent.builder().uIModule(new UIModule(this)).mockNetworkModule(new MockNetworkModule()).build();
        } else {
            injector = DaggerAppComponent.builder().uIModule(new UIModule(this)).networkModule(new NetworkModule()).build();
        }

        injector.inject(this);

        Store.init(this);
        repository.open(getApplicationContext());
	}
}