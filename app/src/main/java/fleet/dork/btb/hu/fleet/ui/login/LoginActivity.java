package fleet.dork.btb.hu.fleet.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import fleet.dork.btb.hu.fleet.R;
import fleet.dork.btb.hu.fleet.ui.carlist.CarListActivity;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

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
        injector.inject(this);
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

    @Override
    public void navigateToMain() {
        Intent i=new Intent(this, CarListActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showLoginFailure() {

    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

