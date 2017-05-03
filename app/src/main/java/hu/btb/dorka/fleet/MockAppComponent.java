package hu.btb.dorka.fleet;

import javax.inject.Singleton;

import dagger.Component;
import hu.btb.dorka.fleet.interactor.InteractorModule;
import hu.btb.dorka.fleet.mock.MockNetworkModule;
import hu.btb.dorka.fleet.repository.RepositoryModule;
import hu.btb.dorka.fleet.ui.UIModule;

@Singleton
@Component(modules = {UIModule.class, MockNetworkModule.class, RepositoryModule.class, InteractorModule.class})
public interface MockAppComponent extends AppComponent {
}

