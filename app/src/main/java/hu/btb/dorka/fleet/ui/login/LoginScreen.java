package hu.btb.dorka.fleet.ui.login;

import hu.btb.dorka.fleet.ui.Screen;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface LoginScreen extends Screen {
    void navigateToMain();
    void showLoginFailure();
}
