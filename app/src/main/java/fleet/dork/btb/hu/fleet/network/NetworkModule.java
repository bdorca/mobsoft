package fleet.dork.btb.hu.fleet.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fleet.dork.btb.hu.fleet.network.api.AuthApi;
import fleet.dork.btb.hu.fleet.network.api.CarApi;
import fleet.dork.btb.hu.fleet.network.interceptors.AuthInterceptor;
import fleet.dork.btb.hu.fleet.settings.Settings;
import fleet.dork.btb.hu.fleet.util.GsonHelper;
import fleet.dork.btb.hu.fleet.util.https.UnsafeClientFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    @Singleton


    public OkHttpClient.Builder provideOkHttpClientBuilder(final Settings settings) {
        OkHttpClient.Builder clientBuilder = null;
        try {
            clientBuilder = UnsafeClientFactory.getUnsafeClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clientBuilder == null) {
            throw new RuntimeException("HttpClient cannot be initialized!");
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        clientBuilder.interceptors().add(0, logging);
        clientBuilder.interceptors().add(1, new AuthInterceptor(settings));
        return clientBuilder;
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
    public AuthApi provideLoginApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }


}