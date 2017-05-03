package hu.btb.dorka.fleet.mock;


import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.btb.dorka.fleet.network.NetworkModule;
import hu.btb.dorka.fleet.network.api.AuthApi;
import hu.btb.dorka.fleet.network.api.CarApi;
import hu.btb.dorka.fleet.settings.Settings;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {
	private NetworkModule networkModule = new NetworkModule();

	@Provides
	@Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder(final Settings settings) {
        return networkModule.provideOkHttpClientBuilder(settings);
    }


	@Provides
	@Singleton
	public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {

		builder.interceptors().add(2, new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				return MockHttpServer.call(request);
			}
		});

		return builder.build();
	}

	@Provides
	@Singleton
	public Retrofit provideRetrofit(OkHttpClient client) {
		return networkModule.provideRetrofit(client);
	}

	@Provides
	@Singleton
	public AuthApi provideLoginApi(Retrofit retrofit) {
		return networkModule.provideLoginApi(retrofit);
	}

	@Provides
	@Singleton
	public CarApi provideCarApi(Retrofit retrofit) {
		return networkModule.provideCarApi(retrofit);
	}


}
