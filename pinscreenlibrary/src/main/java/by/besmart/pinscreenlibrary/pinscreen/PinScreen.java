package by.besmart.pinscreenlibrary.pinscreen;

import android.content.Context;

import by.besmart.pinscreenlibrary.R;
import by.besmart.pinscreenlibrary.listeners.OnPinConfirmationFailsListener;
import by.besmart.pinscreenlibrary.listeners.OnPinCreatedListener;
import by.besmart.pinscreenlibrary.listeners.OnPinReceivedListener;

public class PinScreen {
    private String tempPin;
    private OnPinReceivedListener onPinReceivedListener;
    private OnPinConfirmationFailsListener onPinConfirmationFailsListener;
    private OnPinCreatedListener onPinCreatedListener;
    private DecimalPinDialog pinDialog;
    private OnPinEnteredListener onInitialPinEntered;
    private OnPinEnteredListener onConfirmingPinEntering;
    private OnPinEnteredListener onPinEnteredListener;

    public PinScreen(Context context, int pinLength) {
        onPinEnteredListener = new OnPinEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, String pin) {
                onPinReceivedListener.pinCodeReceived(pin);
            }
        };
        onInitialPinEntered = new OnPinEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, String pin) {
                confirm(dialog, pin);
            }
        };
        onConfirmingPinEntering = new OnPinEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, String pin) {
                compareWithTempPin(dialog, pin);
            }
        };
        pinDialog = new DecimalPinDialog(context, pinLength);
    }

    public void startPinCreation() {
        pinDialog.clearEnteredPin();
        pinDialog.setOnPinEnteredListener(onInitialPinEntered);
        pinDialog.setTitle(R.string.create_pin_title);
        pinDialog.show();
    }

    public void startPinRequest() {
        pinDialog.clearEnteredPin();
        pinDialog.setOnPinEnteredListener(onPinEnteredListener);
        pinDialog.setTitle(R.string.enter_pin_title);
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
            onPinCreatedListener.pinCodeCreated(pin);
        } else {
            onPinConfirmationFailsListener.pinConfirmationFailed();
        }
        tempPin = null;
    }

    private void confirm(DecimalPinDialog pinDialog, String pin) {
        tempPin = pin;
        pinDialog.clearEnteredPin();
        pinDialog.setOnPinEnteredListener(onConfirmingPinEntering);
        pinDialog.setTitle(R.string.confirm_pin_title);
    }
}
