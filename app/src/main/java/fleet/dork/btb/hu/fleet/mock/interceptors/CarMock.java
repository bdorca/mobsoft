package fleet.dork.btb.hu.fleet.mock.interceptors;

import android.net.Uri;

import fleet.dork.btb.hu.fleet.network.NetworkConfig;
import fleet.dork.btb.hu.fleet.network.api.AuthApi;
import fleet.dork.btb.hu.fleet.repository.MemoryRepository;
import fleet.dork.btb.hu.fleet.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static fleet.dork.btb.hu.fleet.mock.interceptors.MockHelper.makeResponse;

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

		if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "cars") && request.method().equals("GET")) {
			MemoryRepository memoryRepository = new MemoryRepository();
			memoryRepository.open(null);
			responseString = GsonHelper.getGson().toJson(memoryRepository.getAllCars());
			responseCode = 200;
		} else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "cars/command") && request.method().equals("GET")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            if(memoryRepository.getCar(Integer.parseInt(uri.getPath().substring(uri.getPath().lastIndexOf("/"))))!=null){
                responseCode = 200;
            }else {
                responseCode = 400;
            }
            responseString="";
		} else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "cars") && request.method().equals("GET")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            responseString = GsonHelper.getGson().toJson(memoryRepository.getCar(Integer.parseInt(uri.getPath().substring(uri.getPath().lastIndexOf("/")))));
            responseCode = 200;
		} else {
			responseString = "ERROR";
			responseCode = 503;
		}

		return makeResponse(request, headers, responseCode, responseString);
	}
}
