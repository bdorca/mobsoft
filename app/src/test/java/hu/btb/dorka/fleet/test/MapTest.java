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
import hu.btb.dorka.fleet.model.Coordinate;
import hu.btb.dorka.fleet.ui.login.LoginPresenter;
import hu.btb.dorka.fleet.ui.login.LoginScreen;
import hu.btb.dorka.fleet.ui.map.MapPresenter;
import hu.btb.dorka.fleet.ui.map.MapScreen;
import hu.btb.dorka.fleet.utils.RobolectricDaggerTestRunner;

import static hu.btb.dorka.fleet.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MapTest {

	private MapPresenter mapPresenter;
	private LoginPresenter loginPresenter;

	@Before
	public void setup() throws Exception {
		setTestInjector();
		mapPresenter = new MapPresenter();
		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter = new LoginPresenter();
		loginPresenter.attachScreen(loginScreen);
		loginPresenter.login(MockConstants.REGISTERED_USERNAME, MockConstants.REGISTERED_PASSWORD);
	}

	@Test
	public void testMap() {
		MapScreen mapScreen = mock(MapScreen.class);
		mapPresenter.attachScreen(mapScreen);
		mapPresenter.refreshMap(new Coordinate(0,0),0);

		ArgumentCaptor<List> mapCaptor = ArgumentCaptor.forClass(List.class);
		verify(mapScreen).refreshCoordinates(mapCaptor.capture());
		assertTrue(mapCaptor.getValue().size() > 0);

	}


	@After
	public void tearDown() {
		mapPresenter.detachScreen();
		loginPresenter.detachScreen();
	}

}