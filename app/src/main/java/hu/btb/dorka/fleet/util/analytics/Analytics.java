package hu.btb.dorka.fleet.util.analytics;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Analytics {
    private static List<AnalyticsAdapter> adapters;

    static {
        adapters = new ArrayList<>();
    }

    public static void registerAdapter(AnalyticsAdapter analyticsAdapter) {
        adapters.add(analyticsAdapter);
    }

    public static void unregisterAdapters() {
        adapters.clear();
    }

    public static void init(Context context) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.init(context);
        }
    }

    public static void event(String event, String... values) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.event(event, values);
        }
    }


}
