package hu.btb.dorka.fleet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import hu.btb.dorka.fleet.BuildConfig;
import hu.btb.dorka.fleet.mock.interceptors.MockConstants;
import hu.btb.dorka.fleet.repository.MemoryRepository;
import hu.btb.dorka.fleet.ui.carlist.CarListPresenter;
import hu.btb.dorka.fleet.ui.carlist.CarListScreen;
import hu.btb.dorka.fleet.ui.login.LoginPresenter;
import hu.btb.dorka.fleet.ui.login.LoginScreen;
import hu.btb.dorka.fleet.utils.RobolectricDaggerTestRunner;

import static hu.btb.dorka.fleet.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CarsTest {

	private CarListPresenter carListPresenter;
	private LoginPresenter loginPresenter;

	@Before
	public void setup() throws Exception {
		setTestInjector();
		carListPresenter = new CarListPresenter();
		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter = new LoginPresenter();
		loginPresenter.attachScreen(loginScreen);
		loginPresenter.login(MockConstants.REGISTERED_USERNAME, MockConstants.REGISTERED_PASSWORD);
	}

	@Test
	public void testFlights() {
		CarListScreen favouritesScreen = mock(CarListScreen.class);
		carListPresenter.attachScreen(favouritesScreen);
		carListPresenter.refreshList();

		ArgumentCaptor<List> carsCaptor = ArgumentCaptor.forClass(List.class);
		verify(favouritesScreen).refreshCars(carsCaptor.capture());
		assertTrue(carsCaptor.getValue().size()== MemoryRepository.cars.size());
	}


	@After
	public void tearDown() {
		carListPresenter.detachScreen();
		loginPresenter.detachScreen();
	}

}