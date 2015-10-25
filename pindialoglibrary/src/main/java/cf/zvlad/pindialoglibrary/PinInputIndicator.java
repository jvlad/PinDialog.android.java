package cf.zvlad.pindialoglibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cf.zvlad.pidialoglibrary.R;

/**
 * Created by zvlad on 10/25/15.
 */
public class PinInputIndicator {
    private boolean isFilled = false;
    private ViewGroup indicatorView;
    private View indicatorInnerFillingView;

    public PinInputIndicator(Context context, ViewGroup indicatorViewContainer) {
        indicatorView = createIndicatorView(context, indicatorViewContainer);
        indicatorInnerFillingView = indicatorView.findViewById(R.id.indicator_filling);
    }

    public void setFilled(boolean isFilled) {
        if (isFilled){
            indicatorInnerFillingView.setVisibility(View.VISIBLE);
        } else {
            indicatorInnerFillingView.setVisibility(View.GONE);
        }
        this.isFilled = isFilled;
    }

    public boolean isFilled() {
        return isFilled;
    }

    private ViewGroup createIndicatorView(Context context, ViewGroup indicatorViewContainer) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return (ViewGroup) inflater.inflate(R.layout.pin_input_indicator, indicatorViewContainer);
    }
}
