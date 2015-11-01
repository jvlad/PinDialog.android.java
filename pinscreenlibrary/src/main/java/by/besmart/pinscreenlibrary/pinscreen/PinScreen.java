package by.besmart.pinscreenlibrary.pinscreen;

import android.content.Context;

import by.besmart.pinscreenlibrary.listeners.OnPinConfirmationFailsListener;
import by.besmart.pinscreenlibrary.listeners.OnPinCreatedListener;
import by.besmart.pinscreenlibrary.listeners.OnPinReceivedListener;

public class PinScreen {
    private String tempPin;

    private DecimalPinDialog pinView;
    private OnPinEnteredListener onPinEnteredListener;
    private OnPinEnteredListener onInitialPinEnteredListener;
    private OnPinEnteredListener onConfirmingPinEnteredListener;

    private OnPinReceivedListener onPinReceivedListener;
    private OnPinCreatedListener onPinCreatedListener;
    private OnPinConfirmationFailsListener onPinConfirmationFailsListener;

    private PinScreenTitles titles;

    public PinScreen(Context context, int pinLength) {
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
                compareWithTempPin(pin);
            }
        };
        pinView = new DecimalPinDialog(context, pinLength);
        titles = new PinScreenTitles(context);
    }

    public void startPinCreation() {
        pinView.reset();
        pinView.setOnPinEnteredListener(onInitialPinEnteredListener);
        pinView.setTitle(titles.getInitializingTitle());
        pinView.setSubtitle(titles.getInitializingSubtitle());
        pinView.show();
    }

    private void confirm(DecimalPinDialog pinDialog, String pin) {
        tempPin = pin;
        pinDialog.reset();
        pinDialog.setOnPinEnteredListener(onConfirmingPinEnteredListener);
        pinDialog.setTitle(titles.getConfirmationTitle());
        pinDialog.setSubtitle(titles.getConfirmationSubtitle());
    }

    private void compareWithTempPin(String pin) {
        if (pin.equals(tempPin)) {
            onPinCreatedListener.pinCodeCreated(this, pin);
        } else {
            onPinConfirmationFailsListener.pinConfirmationFailed(this);
        }
        tempPin = null;
    }

    public void startPinRequest() {
        pinView.reset();
        pinView.setOnPinEnteredListener(onPinEnteredListener);
        pinView.setTitle(titles.getRequestTitle());
        pinView.setSubtitle(titles.getRequestSubtitle());
        pinView.show();
    }

    public void cancel(){
        tempPin = null;
        pinView.reset();
        pinView.cancel();
    }

    public InputIndicatorsBar getIndicatorsBar() {
        return pinView.getIndicatorsBar();
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

    public PinScreenTitles getTitles() {
        return titles;
    }
}
