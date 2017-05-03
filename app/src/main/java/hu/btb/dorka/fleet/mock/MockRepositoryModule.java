package hu.btb.dorka.fleet.mock;

import hu.btb.dorka.fleet.repository.MemoryRepository;
import hu.btb.dorka.fleet.repository.Repository;
import hu.btb.dorka.fleet.repository.RepositoryModule;

/**
 * Created by dorka on 2017.05.03..
 */
public class MockRepositoryModule extends RepositoryModule {
    @Override
    public Repository provideRepository() {
        return new MemoryRepository();
    }
}
