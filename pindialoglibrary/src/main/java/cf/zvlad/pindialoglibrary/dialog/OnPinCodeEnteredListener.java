package cf.zvlad.pindialoglibrary.dialog;

import java.sql.Struct;

import cf.zvlad.pindialoglibrary.PinCode;

/**
 * Created by zvlad on 10/26/15.
 */
public interface OnPinCodeEnteredListener {
    void pinCodeEntered(DecimalPinDialog dialog, PinCode pin);
}
