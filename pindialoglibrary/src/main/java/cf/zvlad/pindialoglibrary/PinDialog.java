package cf.zvlad.pindialoglibrary;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import cf.zvlad.pidialoglibrary.R;

public class PinDialog extends Dialog {
    //todo move pinPad behavior to separate class 'DecimalPinPad'
    public final char PIN_PAD_DIGIT_VALUE_1 = '1';
    public final char PIN_PAD_DIGIT_VALUE_2 = '2';
    public final char PIN_PAD_DIGIT_VALUE_3 = '3';
    public final char PIN_PAD_DIGIT_VALUE_4 = '4';
    public final char PIN_PAD_DIGIT_VALUE_5 = '5';
    public final char PIN_PAD_DIGIT_VALUE_6 = '6';
    public final char PIN_PAD_DIGIT_VALUE_7 = '7';
    public final char PIN_PAD_DIGIT_VALUE_8 = '8';
    public final char PIN_PAD_DIGIT_VALUE_9 = '9';
    public final char PIN_PAD_DIGIT_VALUE_0 = '0';


    public PinDialog(Context context, int pinLength) {
        super(context, R.style.pin_dialog);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.pindialog);
        attachListenerToPinPadDigits(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinPadDigitOnClick(v);
            }
        });
    }

    private void attachListenerToPinPadDigits(View.OnClickListener listener) {
        ArrayList<Button> pinPadDigitButtons = getPinPadDigitButtons();
        for (int i = 0; i < pinPadDigitButtons.size(); i++) {
            pinPadDigitButtons.get(i).setOnClickListener(listener);
        }
    }

    private void pinPadDigitOnClick(View v) {
        int i = v.getId();
        if (i == R.id.pin_pad_digit_1) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_1);

        } else if (i == R.id.pin_pad_digit_2) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_2);

        } else if (i == R.id.pin_pad_digit_3) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_3);

        } else if (i == R.id.pin_pad_digit_4) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_4);

        } else if (i == R.id.pin_pad_digit_5) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_5);

        } else if (i == R.id.pin_pad_digit_6) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_6);

        } else if (i == R.id.pin_pad_digit_7) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_7);

        } else if (i == R.id.pin_pad_digit_9) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_8);

        } else if (i == R.id.pin_pad_digit_9) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_9);

        } else if (i == R.id.pin_pad_digit_0) {
            pinPadSymbolClicked(PIN_PAD_DIGIT_VALUE_0);
        }
    }

    private void pinPadSymbolClicked(char pinChar) {
        showShortToastWithText(pinChar + " is pressed");
    }

    //todo remove. This is a test method.
    private void showShortToastWithText(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

//    private void doValidation() {
//        count++;
//        if (count == 1) {
//            smallcircle_1.setBackgroundResource(R.drawable.small_circle_filled);
//        } else if (count == 2) {
//            smallcircle_2.setBackgroundResource(R.drawable.small_circle_filled);
//        } else if (count == 3) {
//            smallcircle_3.setBackgroundResource(R.drawable.small_circle_filled);
//        } else if (count == 4) {
//            smallcircle_4.setBackgroundResource(R.drawable.small_circle_filled);
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    Log.d("pin", mBuilder.toString() + ":" + pin);
//                    if (mBuilder.toString().equals(pin)) {
//                        Toast.makeText(context, OnPinMatchMessage,
//                                              Toast.LENGTH_SHORT).show();
//                        PinDialog.this.dismiss();
//                    } else {
//                        Toast.makeText(context, onPinMismatchedMessage,
//                                              Toast.LENGTH_SHORT).show();
//                    }
//                    count = 0;
//                    mBuilder = new StringBuilder();
//                    smallcircle_1
//                            .setBackgroundResource(R.drawable.small_circle);
//                    smallcircle_2
//                            .setBackgroundResource(R.drawable.small_circle);
//                    smallcircle_3
//                            .setBackgroundResource(R.drawable.small_circle);
//                    smallcircle_4
//                            .setBackgroundResource(R.drawable.small_circle);
//                }
//
//            }, 250);
//        }
//    }

    private ArrayList<Button> getPinPadDigitButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.pin_pad_digit_1));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_2));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_3));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_4));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_5));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_6));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_7));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_9));
        buttons.add((Button) findViewById(R.id.pin_pad_digit_0));
        return buttons;
    }
}
