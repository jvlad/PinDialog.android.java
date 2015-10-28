package by.besmart.pinscreenlibrary.pinscreen;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import by.besmart.pinscreenlibrary.models.DecimalCharactersValues;
import by.besmart.pinscreenlibrary.models.PinCode;
import by.besmart.pinscreenlibrary.pinscreen.indicators.InputIndicatorsBar;
import by.besmart.pinscreenlibrary.R;

class DecimalPinDialog extends Dialog {
    private final DecimalCharactersValues decimalCharactersValues = new DecimalCharactersValues();
    private final int pinLength;
    private InputIndicatorsBar indicatorsBar;
    private ViewGroup inputIndicatorsContainer;
    private PinCode enteredCharacters;
    private OnPinEnteredListener onPinEnteredListener;
    private TextView titleView;
    private TextView subtitleView;

    private Handler invocationHandler;
    private Runnable invokePinEntered;
    public final int PIN_ENTERED_INVOCATION_DELAY = 150;

    public DecimalPinDialog(Context context, int pinLength) {
        super(context, R.style.pin_dialog);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.pin_dialog);
        this.pinLength = pinLength;
        enteredCharacters = new PinCode();
        titleView = (TextView) findViewById(R.id.operation_title);
        subtitleView = (TextView) findViewById(R.id.description);
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
        inputIndicatorsContainer = (ViewGroup) findViewById(R.id.input_indicators_container);
        indicatorsBar = new InputIndicatorsBar(context, inputIndicatorsContainer, this.pinLength);
        displayIndicatorBar();
        invokePinEntered = new Runnable() {
            @Override
            public void run() {
                onPinEnteredListener.pinCodeEntered(DecimalPinDialog.this, enteredCharacters.toString());
            }
        };
        invocationHandler = new Handler();
    }

    public void clearEnteredPin() {
        enteredCharacters.clearAll();
        indicatorsBar.clearAll();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        titleView.setText(title);
    }

    void setSubtitle(CharSequence subtitle) {
        subtitleView.setText(subtitle);
    }

    void setOnPinEnteredListener(OnPinEnteredListener onPinEnteredListener) {
        this.onPinEnteredListener = onPinEnteredListener;
    }

    private void pinEditViewOnClick(View v) {
        int i = v.getId();
        if (i == R.id.backspace) {
            enteredCharacters.clearLastEnteredCharacter();
            indicatorsBar.clearLastFilled();

        } else if (i == R.id.clear_all) {
            clearEnteredPin();
        }
    }

    private void displayIndicatorBar() {
        Iterator<View> viewsIterator = indicatorsBar.getViewsIterator();
        while (viewsIterator.hasNext()) {
            inputIndicatorsContainer.addView(viewsIterator.next());
        }
    }

    private void attachListenerToPinCharacters(View.OnClickListener listener) {
        ArrayList<View> pinCharacterViews = getPinCharacterViews();
        for (int i = 0; i < pinCharacterViews.size(); i++) {
            pinCharacterViews.get(i).setOnClickListener(listener);
        }
    }

    private void attachListenerToPinEditViews(View.OnClickListener listener) {
        ArrayList<View> pinEditViews = getPinEditViews();
        for (int i = 0; i < pinEditViews.size(); i++) {
            pinEditViews.get(i).setOnClickListener(listener);
        }
    }

    private void pinCharacterOnClick(View v) {
        int i = v.getId();
        if (i == R.id.pin_pad_digit_1) {
            pinPadCharacterClicked(decimalCharactersValues._1);

        } else if (i == R.id.pin_pad_digit_2) {
            pinPadCharacterClicked(decimalCharactersValues._2);

        } else if (i == R.id.pin_pad_digit_3) {
            pinPadCharacterClicked(decimalCharactersValues._3);

        } else if (i == R.id.pin_pad_digit_4) {
            pinPadCharacterClicked(decimalCharactersValues._4);

        } else if (i == R.id.pin_pad_digit_5) {
            pinPadCharacterClicked(decimalCharactersValues._5);

        } else if (i == R.id.pin_pad_digit_6) {
            pinPadCharacterClicked(decimalCharactersValues._6);

        } else if (i == R.id.pin_pad_digit_7) {
            pinPadCharacterClicked(decimalCharactersValues._7);

        } else if (i == R.id.pin_pad_digit_8) {
            pinPadCharacterClicked(decimalCharactersValues._8);

        } else if (i == R.id.pin_pad_digit_9) {
            pinPadCharacterClicked(decimalCharactersValues._9);

        } else if (i == R.id.pin_pad_digit_0) {
            pinPadCharacterClicked(decimalCharactersValues._0);
        }
    }

    private void pinPadCharacterClicked(char pinChar) {
        enteredCharacters.add(pinChar);
        indicatorsBar.fillNext();
        if (enteredCharacters.size() == pinLength && onPinEnteredListener != null) {
            invocationHandler.postDelayed(invokePinEntered, PIN_ENTERED_INVOCATION_DELAY);
        }
    }

    private ArrayList<View> getPinCharacterViews() {
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

    private ArrayList<View> getPinEditViews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(findViewById(R.id.backspace));
        views.add(findViewById(R.id.clear_all));
        return views;
    }
}
