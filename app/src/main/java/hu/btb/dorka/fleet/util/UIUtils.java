package hu.btb.dorka.fleet.util;

import android.widget.ImageView;

import hu.btb.dorka.fleet.model.Car;

import static android.R.drawable.presence_away;
import static android.R.drawable.presence_offline;
import static android.R.drawable.presence_online;

/**
 * Created by dorka on 2017.04.26..
 */

public class UIUtils {

    public static void setStatusImageView(ImageView statusImageView, Car.StatusEnum status){
        switch (status) {
            case STOPPED:
                statusImageView.setImageResource(presence_away);
                break;
            case RUNNING:
                statusImageView.setImageResource(presence_online);
                break;
            case TURNED_OFF:
                statusImageView.setImageResource(presence_offline);
                break;
        }

    }
}
