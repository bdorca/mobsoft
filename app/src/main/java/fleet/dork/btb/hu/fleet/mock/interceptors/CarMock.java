package fleet.dork.btb.hu.fleet.mock.interceptors;

import android.net.Uri;

import hu.aut.examples.szia.network.NetworkConfig;
import hu.aut.examples.szia.network.auth.AuthApi;
import hu.aut.examples.szia.repository.MemoryRepository;
import hu.aut.examples.szia.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static fleet.dork.btb.hu.fleet.mock.interceptors.MockHelper.makeResponse;
import static hu.aut.examples.szia.mock.interceptors.MockHelper.makeResponse;

public class CarMock {
	public static Response process(Request request) {
		Uri uri = Uri.parse(request.url().toString());

		String responseString;
		int responseCode;
		Headers headers = request.headers();

		if(!headers.get(AuthApi.AUTH_HEADER).equals(AuthApi.AUTH_PREFIX+MockConstants.REGISTERED_TOKEN_ID)){
			responseString = "UNAUTHENTICATED";
			responseCode = 423;
			headers = headers.newBuilder().removeAll(AuthApi.AUTH_HEADER).build();
			return makeResponse(request, headers, responseCode, responseString);
		}

		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "Flights") && request.method().equals("GET")) {
			MemoryRepository memoryRepository = new MemoryRepository();
			memoryRepository.open(null);
			responseString = GsonHelper.getGson().toJson(memoryRepository.getFavourites());
			responseCode = 200;
		} else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "Flights/nextArrivals") && request.method().equals("GET")) {
			MemoryRepository memoryRepository = new MemoryRepository();
			memoryRepository.open(null);
			responseString = GsonHelper.getGson().toJson(memoryRepository.getFavourites().subList(0, 2));
			responseCode = 200;
		} else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "Flights/nextDepartures") && request.method().equals("GET")) {
			MemoryRepository memoryRepository = new MemoryRepository();
			memoryRepository.open(null);
			responseString = GsonHelper.getGson().toJson(memoryRepository.getFavourites().subList(2, 4));
			responseCode = 200;
		} else {
			responseString = "ERROR";
			responseCode = 503;
		}

		return makeResponse(request, headers, responseCode, responseString);
	}
}
