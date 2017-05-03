package hu.btb.dorka.fleet.util.analytics;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import hu.btb.dorka.fleet.R;


public class GoogleAnalyticsAdapter implements AnalyticsAdapter {

    public static final String SEPARATOR = "|";
    public static final String CATEGORY = "Events";

    private Tracker mTracker;

    public GoogleAnalyticsAdapter() {
    }

    @Override
    public void init(Context context) {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
            mTracker = analytics.newTracker(R.xml.global_tracker);
            analytics.setLocalDispatchPeriod(10); // In seconds, default 1800
            analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            analytics.enableAutoActivityReports((Application) context);
            mTracker.enableAutoActivityTracking(true);
            mTracker.enableExceptionReporting(true);
        }
    }

    @Override
    public void event(String event, String... values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : values) {
            stringBuilder.append(value);
            stringBuilder.append(SEPARATOR);
        }

        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(CATEGORY)
                .setAction(event)
                .setLabel(stringBuilder.toString())
                .build());
    }
}
