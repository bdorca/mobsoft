package fleet.dork.btb.hu.fleet.ui.login;

import fleet.dork.btb.hu.fleet.ui.Screen;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface LoginScreen extends Screen {
    void navigateToMain();
    void showLoginFailure();
}
