package hu.btb.dorka.fleet.util.analytics;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import io.fabric.sdk.android.Fabric;

public class FabricAdapter implements AnalyticsAdapter {

    public static final String EQUALS = "=";

    @Override
    public void init(Context context) {
        Fabric.with(new Fabric.Builder(context).debuggable(true).kits(new Crashlytics(), new Answers()).build());
    }

    @Override
    public void event(String event, String... values) {
        CustomEvent customEvent = new CustomEvent(event);
        for (String value : values) {
            String[] split = value.split(EQUALS);
            if (split.length == 2) {
                customEvent.putCustomAttribute(split[0], split[1]);
            } else {
                customEvent.putCustomAttribute(value, value);
            }
        }
        if (Answers.getInstance() != null) {
            Answers.getInstance().logCustom(customEvent);
        }
    }
}
