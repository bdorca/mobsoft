package hu.btb.dorka.fleet;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import hu.btb.dorka.fleet.ui.UIModule;
import hu.btb.dorka.fleet.ui.carlist.CarListPresenter;
import hu.btb.dorka.fleet.ui.details.DetailsPresenter;
import hu.btb.dorka.fleet.ui.login.LoginPresenter;
import hu.btb.dorka.fleet.ui.map.MapPresenter;
import hu.btb.dorka.fleet.utils.UiExecutor;

@Module
public class TestModule {

	private final UIModule UIModule;

	public TestModule(Context context) {

		this.UIModule = new UIModule(context);
	}

	@Provides
	public Context provideContext() {
		return UIModule.provideContext();
	}

	@Provides
	public LoginPresenter provideLoginPresenter() {
		return UIModule.provideLoginPresenter();
	}

	@Provides
	public CarListPresenter provideMainPresenter() {
		return UIModule.provideMainPresenter();
	}

	@Provides
	public DetailsPresenter provideNewsPresenter() {
		return UIModule.provideDetailsPresenter();
	}

	@Provides
	public MapPresenter provideFlightsPresenter() {
		return UIModule.provideMapPresenter();
	}

	@Provides
	@Singleton
	public EventBus provideEventBus() {
		return EventBus.getDefault();
	}

	@Provides
	@Singleton
	public Executor provideExecutor() {
		return new UiExecutor();
	}


}
