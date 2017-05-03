package hu.btb.dorka.fleet.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import hu.btb.dorka.fleet.BuildConfig;
import hu.btb.dorka.fleet.mock.interceptors.MockConstants;
import hu.btb.dorka.fleet.settings.Settings;
import hu.btb.dorka.fleet.ui.login.LoginPresenter;
import hu.btb.dorka.fleet.ui.login.LoginScreen;
import hu.btb.dorka.fleet.utils.RobolectricDaggerTestRunner;

import static hu.btb.dorka.fleet.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {

	private LoginPresenter loginPresenter;
	private Settings settings;

	@Before
	public void setup() throws Exception {
		setTestInjector();
		settings = new Settings();
		settings.deleteSessionToken();
		loginPresenter = new LoginPresenter();
	}

	@Test
	public void testLogin() {
		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter.attachScreen(loginScreen);
		loginPresenter.login(MockConstants.REGISTERED_USERNAME, MockConstants.REGISTERED_PASSWORD);
		verify(loginScreen).navigateToMain();
		Assert.assertEquals(settings.getSessionToken().getId(), MockConstants.REGISTERED_TOKEN_ID);
	}


	@After
	public void tearDown(){
		loginPresenter.detachScreen();
	}

}