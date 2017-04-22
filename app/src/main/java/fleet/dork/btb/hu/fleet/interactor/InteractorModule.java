package fleet.dork.btb.hu.fleet.interactor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fleet.dork.btb.hu.fleet.interactor.car.CarInteractor;
import fleet.dork.btb.hu.fleet.interactor.map.MapInteractor;

@Module
public class InteractorModule {

	@Provides
	public CarInteractor provideCarInteractor() {
		return new CarInteractor();
	}

    @Provides
    public MapInteractor provideMapInteractor(){
        return new MapInteractor();
    }
}