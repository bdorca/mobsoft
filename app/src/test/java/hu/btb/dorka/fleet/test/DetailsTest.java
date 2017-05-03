package hu.btb.dorka.fleet.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import hu.btb.dorka.fleet.BuildConfig;
import hu.btb.dorka.fleet.mock.interceptors.MockConstants;
import hu.btb.dorka.fleet.model.Car;
import hu.btb.dorka.fleet.model.Command;
import hu.btb.dorka.fleet.repository.MemoryRepository;
import hu.btb.dorka.fleet.ui.details.DetailsPresenter;
import hu.btb.dorka.fleet.ui.details.DetailsScreen;
import hu.btb.dorka.fleet.ui.login.LoginPresenter;
import hu.btb.dorka.fleet.ui.login.LoginScreen;
import hu.btb.dorka.fleet.utils.RobolectricDaggerTestRunner;

import static hu.btb.dorka.fleet.TestHelper.setTestInjector;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailsTest {

    private Car test;
    private DetailsPresenter detailsPresenter;
    private LoginPresenter loginPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        detailsPresenter = new DetailsPresenter();
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachScreen(loginScreen);
        loginPresenter.login(MockConstants.REGISTERED_USERNAME, MockConstants.REGISTERED_PASSWORD);
        test = MemoryRepository.cars.get(0);
    }

    @Test
    public void testCar() {
        DetailsScreen detailsScreen = mock(DetailsScreen.class);
        detailsPresenter.attachScreen(detailsScreen);
        detailsPresenter.setCurrentCar(test);
        detailsPresenter.refreshData();
        ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass(Car.class);
        verify(detailsScreen,atMost(2)).setDetails(carCaptor.capture());
        Assert.assertTrue(MemoryRepository.cars.contains(carCaptor.getValue()));
    }

    @Test
    public void testCommand() {
        DetailsScreen detailsScreen = mock(DetailsScreen.class);
        detailsPresenter.attachScreen(detailsScreen);
        detailsPresenter.setCurrentCar(test);
        verify(detailsScreen).setDetails(test);

        ArgumentCaptor<Integer> commandCaptor = ArgumentCaptor.forClass(Integer.class);
        detailsPresenter.sendCommand(Command.START);
        verify(detailsScreen).commandResponse(commandCaptor.capture());
        Assert.assertTrue(commandCaptor.getValue()==200);
    }

    @After
    public void tearDown() {
        detailsPresenter.detachScreen();
        loginPresenter.detachScreen();
    }

}