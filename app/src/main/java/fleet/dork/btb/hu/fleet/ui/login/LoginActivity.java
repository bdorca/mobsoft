package fleet.dork.btb.hu.fleet.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fleet.dork.btb.hu.fleet.R;
import fleet.dork.btb.hu.fleet.ui.carlist.CarListActivity;

import static fleet.dork.btb.hu.fleet.FleetApplication.injector;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginScreen {
    @Inject
    LoginPresenter loginPresenter;

    @Bind(R.id.username)
    AutoCompleteTextView usernameView;

    @Bind(R.id.password)
    EditText passwordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        injector.inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.email_sign_in_button)
    public void login() {
        loginPresenter.login(usernameView.getText().toString(), passwordView.getText().toString());
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
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void showLoginFailure() {
        showMessage("Can't log in!");
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

