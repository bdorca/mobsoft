package hu.btb.dorka.fleet;

import javax.inject.Singleton;

import dagger.Component;
import hu.btb.dorka.fleet.interactor.InteractorModule;
import hu.btb.dorka.fleet.mock.MockNetworkModule;
import hu.btb.dorka.fleet.repository.TestRepositoryModule;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends AppComponent {
}
