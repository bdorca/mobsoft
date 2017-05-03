package fleet.dork.btb.hu.fleet.mock;


import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.aut.examples.szia.network.NetworkModule;
import hu.aut.examples.szia.network.auth.AuthApi;
import hu.aut.examples.szia.network.flights.FlightApi;
import hu.aut.examples.szia.network.flights.NewsApi;
import hu.aut.examples.szia.settings.Settings;
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

		builder.interceptors().add(3, new Interceptor() {
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
	public AuthApi provideAuthApi(Retrofit retrofit) {
		return networkModule.provideAuthApi(retrofit);
	}

	@Provides
	@Singleton
	public FlightApi provideFlightApi(Retrofit retrofit) {
		return networkModule.provideFlightApi(retrofit);
	}

	@Provides
	@Singleton
	public NewsApi provideNews(Retrofit retrofit) {
		return networkModule.provideNewsApi(retrofit);
	}

}
