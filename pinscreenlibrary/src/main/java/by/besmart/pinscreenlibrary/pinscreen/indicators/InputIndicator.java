package by.besmart.pinscreenlibrary.pinscreen.indicators;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import by.besmart.pinscreenlibrary.R;

class InputIndicator {
    private ToggleButton indicatorView;

    InputIndicator(Context context, ViewGroup indicatorViewContainer) {
        indicatorView = createIndicatorView(context, indicatorViewContainer);
    }

    void setFilled(boolean isFilled) {
        if (isFilled) {
            indicatorView.setChecked(true);
        } else {
            indicatorView.setChecked(false);
        }
    }

    ToggleButton getView() {
        return indicatorView;
    }

    private ToggleButton createIndicatorView(Context context, ViewGroup indicatorViewContainer) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return (ToggleButton) inflater.inflate(R.layout.pin_input_indicator,
                                                      indicatorViewContainer, false);
    }
}
