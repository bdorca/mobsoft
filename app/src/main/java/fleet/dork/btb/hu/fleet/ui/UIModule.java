package fleet.dork.btb.hu.fleet.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fleet.dork.btb.hu.fleet.ui.details.DetailsPresenter;
import fleet.dork.btb.hu.fleet.ui.login.LoginPresenter;
import fleet.dork.btb.hu.fleet.ui.carlist.CarListPresenter;
import fleet.dork.btb.hu.fleet.ui.map.MapPresenter;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public CarListPresenter provideMainPresenter() {
        return new CarListPresenter();
    }


    @Provides
    @Singleton
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    @Singleton
    public DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    @Singleton
    public MapPresenter provideMapPresenter(){
        return new MapPresenter();
    }

}