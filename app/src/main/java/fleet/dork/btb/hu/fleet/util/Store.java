package fleet.dork.btb.hu.fleet.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class Store {
	private static SharedPreferences preferences;
	private static Gson gson;

	public static void init(Context context) {
		Store.preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
		Store.gson = new Gson();
	}

	@SuppressLint("CommitPrefEdits")
	public static <T> void put(String key, T element) {
		if (preferences == null || gson == null) {
			Log.e("Store", "Store must be initialized before use!");
			throw new RuntimeException("Store must be initialized before use!");
		}

		preferences.edit().putString(key, gson.toJson(element, element.getClass())).commit();
	}

	public static <T> T get(String key, Type type) {
		if (preferences == null || gson == null) {
			Log.e("Store", "Store must be initialized before use!");
			throw new RuntimeException("Store must be initialized before use!");
		}

		String content = preferences.getString(key, "");
		return gson.fromJson(content, type);
	}

	@SuppressLint("CommitPrefEdits")
	public static void remove(String key) {
		if (preferences == null || gson == null) {
			Log.e("Store", "Store must be initialized before use!");
			throw new RuntimeException("Store must be initialized before use!");
		}

		preferences.edit().remove(key).commit();
	}

}

