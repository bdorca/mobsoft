package hu.btb.dorka.fleet.util.analytics;

import android.content.Context;

public interface AnalyticsAdapter {
    void init(Context context);

    void event(String event, String... values);
}
