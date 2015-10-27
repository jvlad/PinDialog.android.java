package cf.zvlad.pindialoglibrary;

import android.app.Dialog;
import android.content.Context;

import cf.zvlad.pindialoglibrary.dialog.DecimalPinDialog;
import cf.zvlad.pindialoglibrary.dialog.OnPinCodeEnteredListener;

/**
 * Created by zvlad on 10/27/15.
 */
public class PinWorkflow {
    private Context context;
    private int pinLength;
    PinCode tempPin;
    OnPinWorkflowEndListener onPinWorkflowEndListener;
    OnPinCodeEnteredListener initialEntering;
    OnPinCodeEnteredListener confirmationEntering;

    public PinWorkflow(Context context, int pinLength, OnPinWorkflowEndListener onPinWorkflowEndListener) {
        this.context = context;
        this.pinLength = pinLength;
        this.onPinWorkflowEndListener = onPinWorkflowEndListener;
        initialEntering = new OnPinCodeEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, PinCode pin) {
                confirm(pin);
                dialog.cancel();
            }
        };
        confirmationEntering = new OnPinCodeEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, PinCode pin) {
                check(pin);
                dialog.cancel();
            }
        };
        tempPin = new PinCode();
    }

    public void startPinCreation() {
        DecimalPinDialog pinDialog = new DecimalPinDialog(context, pinLength);
        pinDialog.setOnPinCodeEnteredListener(initialEntering);
        pinDialog.setTitle("Enter new please…");
        pinDialog.show();

    }

    public void startPinRequest() {
        DecimalPinDialog pinDialog = new DecimalPinDialog(context, pinLength);
        pinDialog.setOnPinCodeEnteredListener(new OnPinCodeEnteredListener() {
            @Override
            public void pinCodeEntered(DecimalPinDialog dialog, PinCode pin) {
                onPinWorkflowEndListener.pinWorkflowEnded(true, pin.toString());
            }
        });
        pinDialog.show();
    }

    private void check(PinCode pin) {
        if (pin.equals(tempPin)) {
            onPinWorkflowEndListener.pinWorkflowEnded(true, pin.toString());
        } else {
            onPinWorkflowEndListener.pinWorkflowEnded(false, null);
        }
        tempPin = null;
    }

    private void confirm(PinCode pin) {
        tempPin = pin;
        DecimalPinDialog pinDialog = new DecimalPinDialog(context, pinLength);
        pinDialog.setOnPinCodeEnteredListener(confirmationEntering);
        pinDialog.setTitle("Confirm please…");
        pinDialog.show();
    }
}
