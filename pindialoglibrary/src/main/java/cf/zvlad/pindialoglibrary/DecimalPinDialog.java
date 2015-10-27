package cf.zvlad.pindialoglibrary;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import cf.zvlad.pidialoglibrary.R;

public class DecimalPinDialog extends Dialog {
    private final DecimalCharactersValues decimalCharactersValues = new DecimalCharactersValues();
    private final int pinLength;
    InputIndicatorsBar indicatorsBar;
    ViewGroup inputIndicatorsContainer;
    PinCode enteredCharacters;
    OnPinEnteredListener onPinEnteredListener;

    public DecimalPinDialog(Context context, int pinLength, OnPinEnteredListener onPinEnteredListener) {
        super(context, R.style.pin_dialog);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.pin_dialog);
        this.pinLength = pinLength;

        this.onPinEnteredListener = onPinEnteredListener;
        enteredCharacters = new PinCode();
        attachListenerToPinCharacters(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinCharacterOnClick(v);
            }
        });
        attachListenerToPinEditActions(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editActionsOnClick(v);
            }
        });
        inputIndicatorsContainer = (ViewGroup) findViewById(R.id.input_indicators_container);
        indicatorsBar = new InputIndicatorsBar(context, inputIndicatorsContainer, this.pinLength);
        displayIndicatorBar();
    }

    private void editActionsOnClick(View v) {
        int i = v.getId();
        if (i == R.id.backspace) {
            enteredCharacters.clearLastEnteredCharacter();
            indicatorsBar.clearLastFilled();

        } else if (i == R.id.clear_all) {
            enteredCharacters.clearAll();
            indicatorsBar.clearAll();
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

    private void attachListenerToPinEditActions(View.OnClickListener listener) {
        ArrayList<View> editActionViews = getEditActionViews();
        for (int i = 0; i < editActionViews.size(); i++) {
            editActionViews.get(i).setOnClickListener(listener);
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
        if (enteredCharacters.size() == pinLength) {
            onPinEnteredListener.onPinEntered(enteredCharacters.toString());
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

    private ArrayList<View> getEditActionViews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(findViewById(R.id.backspace));
        views.add(findViewById(R.id.clear_all));
        return views;
    }
}
