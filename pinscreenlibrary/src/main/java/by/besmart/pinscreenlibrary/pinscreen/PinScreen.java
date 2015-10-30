package by.besmart.pinscreenlibrary.pinscreen;

import android.content.Context;

import by.besmart.pinscreenlibrary.listeners.OnPinConfirmationFailsListener;
import by.besmart.pinscreenlibrary.listeners.OnPinCreatedListener;
import by.besmart.pinscreenlibrary.listeners.OnPinReceivedListener;
import by.besmart.pinscreenlibrary.pinscreen.customization.PinScreenColors;
import by.besmart.pinscreenlibrary.pinscreen.customization.PinScreenTitles;

public class PinScreen {
    private String tempPin;

    private DecimalPinDialog pinDialog;
    private InputIndicatorsBar indicatorsBar;
    private OnPinEnteredListener onPinEnteredListener;
    private OnPinEnteredListener onInitialPinEnteredListener;
    private OnPinEnteredListener onConfirmingPinEnteredListener;

    private OnPinReceivedListener onPinReceivedListener;
    private OnPinCreatedListener onPinCreatedListener;
    private OnPinConfirmationFailsListener onPinConfirmationFailsListener;

    public PinScreenTitles titles;

    //todo implement coloring logic
    public PinScreenColors colors;

    public PinScreen(Context context, int pinLength, PinScreenColors colors) {
        onPinEnteredListener = new OnPinEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, String pin) {
                onPinReceivedListener.pinCodeReceived(PinScreen.this, pin);
            }
        };
        onInitialPinEnteredListener = new OnPinEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, String pin) {
                confirm(dialog, pin);
            }
        };
        onConfirmingPinEnteredListener = new OnPinEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, String pin) {
                compareWithTempPin(dialog, pin);
            }
        };
        pinDialog = new DecimalPinDialog(context, pinLength);
        indicatorsBar = pinDialog.indicatorsBar;
        titles = new PinScreenTitles(context);
        this.colors = colors;
    }

    public PinScreen(Context context, int pinLength) {
        this(context, pinLength, new PinScreenColors(context));
    }

    public InputIndicatorsBar getIndicatorsBar() {
        return indicatorsBar;
    }

    public void cancel(){
        tempPin = null;
        pinDialog.cancel();
    }

    public void startPinCreation() {
        pinDialog.clearEnteredPin();
        pinDialog.setOnPinEnteredListener(onInitialPinEnteredListener);
        pinDialog.setTitle(titles.getInitializingTitle());
        pinDialog.setSubtitle(titles.getInitializingSubtitle());
        pinDialog.show();
    }

    public void startPinRequest() {
        pinDialog.clearEnteredPin();
        pinDialog.setOnPinEnteredListener(onPinEnteredListener);
        pinDialog.setTitle(titles.getRequestTitle());
        pinDialog.setSubtitle(titles.getRequestSubtitle());
        pinDialog.show();
    }

    public void setOnPinReceivedListener(OnPinReceivedListener onPinReceivedListener) {
        this.onPinReceivedListener = onPinReceivedListener;
    }

    public void setOnPinConfirmationFailsListener(OnPinConfirmationFailsListener onPinConfirmationFailsListener) {
        this.onPinConfirmationFailsListener = onPinConfirmationFailsListener;
    }

    public void setOnPinCreatedListener(OnPinCreatedListener onPinCreatedListener) {
        this.onPinCreatedListener = onPinCreatedListener;
    }

    private void compareWithTempPin(DecimalPinDialog pinDialog, String pin) {
        if (pin.equals(tempPin)) {
            onPinCreatedListener.pinCodeCreated(this, pin);
        } else {
            onPinConfirmationFailsListener.pinConfirmationFailed(this);
        }
        tempPin = null;
    }

    private void confirm(DecimalPinDialog pinDialog, String pin) {
        tempPin = pin;
        pinDialog.clearEnteredPin();
        pinDialog.setOnPinEnteredListener(onConfirmingPinEnteredListener);
        pinDialog.setTitle(titles.getConfirmationTitle());
        pinDialog.setSubtitle(titles.getConfirmationSubtitle());
    }
}
