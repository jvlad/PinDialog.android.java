package by.besmart.pinscreenlibrary.pinscreen;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import by.besmart.pinscreenlibrary.R;
import by.besmart.pinscreenlibrary.pinscreen.customization.PinScreenColors;

class DecimalPinDialog extends Dialog {
    public final int PIN_ENTERED_INVOCATION_DELAY_MILLIS = 150;

    private final int pinLength;
    private InputIndicatorsBar indicatorsBar;

    private OnPinEnteredListener onPinEnteredListener;
    private TextView titleView;
    private TextView subtitleView;

    private ArrayList<View> pinEditViews;
    private List<Character> enteredCharacters;

    private StringBuilder pinBuilder;
    private Handler invocationHandler;
    private Runnable invokePinEntered;

    public DecimalPinDialog(Context context, int pinLength) {
        super(context, R.style.pin_screen);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.pin_dialog);
        this.pinLength = pinLength;
        enteredCharacters = new ArrayList<>();
        pinBuilder = new StringBuilder();
        titleView = (TextView) findViewById(R.id.title);
        subtitleView = (TextView) findViewById(R.id.subtitle);
        pinEditViews = findPinEditViews();
        attachListenerToPinCharacters(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinCharacterOnClick(v);
            }
        });
        attachListenerToPinEditViews(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinEditViewOnClick(v);
            }
        });
        ViewGroup inputIndicatorsContainer = (ViewGroup) findViewById(R.id.input_indicators_container);
        invokePinEntered = new Runnable() {
            @Override
            public void run() {
                onPinEnteredListener.pinCodeEntered(DecimalPinDialog.this, buildPin());
            }
        };
        invocationHandler = new Handler();
        indicatorsBar = new InputIndicatorsBar(context, inputIndicatorsContainer, this.pinLength);
    }

    public void clearEnteredPin() {
        enteredCharacters.clear();
        indicatorsBar.clearAll();
        setPinEditViewsEnabled(false);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        titleView.setText(title);
    }

    public InputIndicatorsBar getIndicatorsBar() {
        return indicatorsBar;
    }

    private String buildPin() {
        resetPin();
        for (int i = 0; i < enteredCharacters.size(); i++) {
            pinBuilder.append(enteredCharacters.get(i));
        }
        return pinBuilder.toString();
    }

    void setSubtitle(CharSequence subtitle) {
        subtitleView.setText(subtitle);
    }

    void setOnPinEnteredListener(OnPinEnteredListener onPinEnteredListener) {
        this.onPinEnteredListener = onPinEnteredListener;
    }

    private void setPinEditViewsEnabled(boolean isEnabled) {
        for (int i = 0; i < pinEditViews.size(); i++) {
            pinEditViews.get(i).setEnabled(isEnabled);
        }
    }

    private void pinEditViewOnClick(View v) {
        int i = v.getId();
        if (i == R.id.backspace) {
            clearLastEnteredCharacter();
            indicatorsBar.clearLastFilled();
        } else if (i == R.id.clear_all) {
            clearEnteredPin();
        }
    }

    private void clearLastEnteredCharacter() {
        if (enteredCharacters.size() == 1) {
            enteredCharacters.clear();
            setPinEditViewsEnabled(false);
        } else if (enteredCharacters.size() > 1) {
            enteredCharacters.remove(enteredCharacters.size() - 1);
        }
    }

    private void attachListenerToPinCharacters(View.OnClickListener listener) {
        ArrayList<View> pinCharacterViews = findPinCharacterViews();
        for (int i = 0; i < pinCharacterViews.size(); i++) {
            pinCharacterViews.get(i).setOnClickListener(listener);
        }
    }

    private void attachListenerToPinEditViews(View.OnClickListener listener) {
        for (int i = 0; i < pinEditViews.size(); i++) {
            pinEditViews.get(i).setOnClickListener(listener);
        }
    }

    private void pinCharacterOnClick(View v) {
        TextView view = (TextView) v;
        char character = view.getText().charAt(0);
        pinPadCharacterSelected(character);
    }

    private void pinPadCharacterSelected(char pinChar) {
        if (enteredCharacters.size() == 0) {
            setPinEditViewsEnabled(true);
        }
        enteredCharacters.add(pinChar);
        indicatorsBar.fillNext();
        if (enteredCharacters.size() == pinLength && onPinEnteredListener != null) {
            invocationHandler.postDelayed(invokePinEntered, PIN_ENTERED_INVOCATION_DELAY_MILLIS);
        }
    }

    private ArrayList<View> findPinCharacterViews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(findViewById(R.id.pin_pad_digit_1));
        views.add(findViewById(R.id.pin_pad_digit_2));
        views.add(findViewById(R.id.pin_pad_digit_3));
        views.add(findViewById(R.id.pin_pad_digit_4));
        views.add(findViewById(R.id.pin_pad_digit_5));
        views.add(findViewById(R.id.pin_pad_digit_6));
        views.add(findViewById(R.id.pin_pad_digit_7));
        views.add(findViewById(R.id.pin_pad_digit_8));
        views.add(findViewById(R.id.pin_pad_digit_9));
        views.add(findViewById(R.id.pin_pad_digit_0));
        return views;
    }

    private ArrayList<View> findPinEditViews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(findViewById(R.id.backspace));
        views.add(findViewById(R.id.clear_all));
        return views;
    }

    private void resetPin() {
        pinBuilder.delete(0, pinBuilder.length());
    }
}
