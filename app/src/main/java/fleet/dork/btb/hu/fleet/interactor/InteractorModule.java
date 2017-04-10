package fleet.dork.btb.hu.fleet.interactor;

import dagger.Module;
import dagger.Provides;
import fleet.dork.btb.hu.fleet.interactor.car.CarInteractor;

@Module
public class InteractorModule {


	@Provides
	public CarInteractor provideFavourites() {
		return new CarInteractor();
	}


}