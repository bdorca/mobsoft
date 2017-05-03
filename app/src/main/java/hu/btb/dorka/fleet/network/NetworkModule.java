package hu.btb.dorka.fleet.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.btb.dorka.fleet.network.api.AuthApi;
import hu.btb.dorka.fleet.network.api.CarApi;
import hu.btb.dorka.fleet.network.interceptors.AuthInterceptor;
import hu.btb.dorka.fleet.settings.Settings;
import hu.btb.dorka.fleet.util.GsonHelper;
import hu.btb.dorka.fleet.util.https.UnsafeClientFactory;
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