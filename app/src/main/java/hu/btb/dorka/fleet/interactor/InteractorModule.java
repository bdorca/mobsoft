package hu.btb.dorka.fleet.interactor;

import dagger.Module;
import dagger.Provides;
import hu.btb.dorka.fleet.interactor.auth.AuthInteractor;
import hu.btb.dorka.fleet.interactor.car.CarInteractor;
import hu.btb.dorka.fleet.interactor.map.MapInteractor;

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

    @Provides
    public AuthInteractor provideAuthInteractor(){ return new AuthInteractor();}
}