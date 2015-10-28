package by.besmart.pinscreenlibrary.pinscreen;

import android.content.Context;

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

    public PinScreen(Context context, int pinLength) {
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
        pinDialog.setTitle("Enter new please…");
        pinDialog.show();
    }

    public void startPinRequest() {
        pinDialog.setOnPinEnteredListener(new OnPinEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, String pin) {
                onPinReceivedListener.pinCodeReceived(pin);
            }
        });
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
        pinDialog.setTitle("Confirm please…");
    }
}
