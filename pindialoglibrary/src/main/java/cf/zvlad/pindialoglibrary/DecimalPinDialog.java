package cf.zvlad.pindialoglibrary;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import cf.zvlad.pidialoglibrary.R;

public class DecimalPinDialog extends Dialog {
    public final DecimalCharactersValues decimalCharactersValues = new DecimalCharactersValues();
    private final int pinLength;
    InputIndicatorsBar indicatorsBar;
    ViewGroup inputIndicatorsContainer;
    int enteredCharactersNumber;
    StringBuilder enteredSymbols;
    OnPinEnteredListener onPinEnteredListener;


    public DecimalPinDialog(Context context, int pinLength, OnPinEnteredListener onPinEnteredListener) {
        super(context, R.style.pin_dialog);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.pin_dialog);
        this.pinLength = pinLength;
        this.onPinEnteredListener = onPinEnteredListener;
        enteredSymbols = new StringBuilder();
        attachListenerToPinPadSymbols(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinSymbolOnClick(v);
            }
        });
        inputIndicatorsContainer = (ViewGroup) findViewById(R.id.input_indicators_container);
        indicatorsBar = new InputIndicatorsBar(context, inputIndicatorsContainer, this.pinLength);
        displayIndicatorBar();
        enteredCharactersNumber = 0;
    }

    private void displayIndicatorBar() {
        Iterator<View> viewsIterator = indicatorsBar.getViewsIterator();
        while (viewsIterator.hasNext()) {
            inputIndicatorsContainer.addView(viewsIterator.next());
        }
    }

    private void attachListenerToPinPadSymbols(View.OnClickListener listener) {
        ArrayList<Button> pinPadDigitButtons = getPinPadDigitButtons();
        for (int i = 0; i < pinPadDigitButtons.size(); i++) {
            pinPadDigitButtons.get(i).setOnClickListener(listener);
        }
    }

    private void pinSymbolOnClick(View v) {
        int i = v.getId();
        if (i == R.id.pin_pad_digit_1) {
            pinPadSymbolClicked(decimalCharactersValues._1);

        } else if (i == R.id.pin_pad_digit_2) {
            pinPadSymbolClicked(decimalCharactersValues._2);

        } else if (i == R.id.pin_pad_digit_3) {
            pinPadSymbolClicked(decimalCharactersValues._3);

        } else if (i == R.id.pin_pad_digit_4) {
            pinPadSymbolClicked(decimalCharactersValues._4);

        } else if (i == R.id.pin_pad_digit_5) {
            pinPadSymbolClicked(decimalCharactersValues._5);

        } else if (i == R.id.pin_pad_digit_6) {
            pinPadSymbolClicked(decimalCharactersValues._6);

        } else if (i == R.id.pin_pad_digit_7) {
            pinPadSymbolClicked(decimalCharactersValues._7);

        } else if (i == R.id.pin_pad_digit_8) {
            pinPadSymbolClicked(decimalCharactersValues._8);

        } else if (i == R.id.pin_pad_digit_9) {
            pinPadSymbolClicked(decimalCharactersValues._9);

        } else if (i == R.id.pin_pad_digit_0) {
            pinPadSymbolClicked(decimalCharactersValues._0);
        }
    }

    private void pinPadSymbolClicked(char pinChar) {
        enteredCharactersNumber++;
        enteredSymbols.append(pinChar);
        indicatorsBar.fillNext();
        if (enteredCharactersNumber == pinLength){
            onPinEnteredListener.onPinEntered(enteredSymbols.toString());
        }
    }

    private ArrayList<Button> getPinPadDigitButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.pin_pad_digit_1));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_2));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_3));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_4));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_5));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_6));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_7));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_8));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_9));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_0));
        return buttons;
    }
}
