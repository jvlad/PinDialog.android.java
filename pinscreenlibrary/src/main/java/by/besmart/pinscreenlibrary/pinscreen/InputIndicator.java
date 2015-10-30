package by.besmart.pinscreenlibrary.pinscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import by.besmart.pinscreenlibrary.R;

class InputIndicator {
    private final Context context;
    private final ViewGroup indicatorViewContainer;
    private ToggleButton indicatorView;

    InputIndicator(Context context, ViewGroup indicatorViewContainer) {
        this.context = context;
        this.indicatorViewContainer = indicatorViewContainer;
        resetView();
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

    void resetView() {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        indicatorView = (ToggleButton) inflater.inflate(R.layout.pin_input_indicator,
                                                               indicatorViewContainer, false);
    }
}
