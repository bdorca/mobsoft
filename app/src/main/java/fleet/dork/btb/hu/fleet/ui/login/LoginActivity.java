package fleet.dork.btb.hu.fleet.ui.login;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginScreen {
    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }
}

