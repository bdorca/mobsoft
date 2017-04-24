package fleet.dork.btb.hu.fleet.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fleet.dork.btb.hu.fleet.network.api.CarApi;
import fleet.dork.btb.hu.fleet.network.api.LoginApi;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import fleet.dork.btb.hu.fleet.utils.GsonHelper;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public CarApi provideCarApi(Retrofit retrofit) {
        return retrofit.create(CarApi.class);
    }
    @Provides
    @Singleton
    public LoginApi provideLoginApi(Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }


}