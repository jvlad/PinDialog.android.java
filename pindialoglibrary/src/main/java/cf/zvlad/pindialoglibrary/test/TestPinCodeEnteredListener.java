package cf.zvlad.pindialoglibrary.test;

import android.content.Context;
import android.widget.Toast;

import cf.zvlad.pindialoglibrary.PinCode;
import cf.zvlad.pindialoglibrary.dialog.DecimalPinDialog;
import cf.zvlad.pindialoglibrary.dialog.OnPinCodeEnteredListener;

/**
 * Created by zvlad on 10/27/15.
 */
public class TestPinCodeEnteredListener implements OnPinCodeEnteredListener {
    private final Context context;

    public TestPinCodeEnteredListener(Context context) {
        this.context = context;
    }

    @Override
    public void pinCodeEntered(DecimalPinDialog dialog, PinCode pin) {
        Toast.makeText(context, pin.toString(), Toast.LENGTH_SHORT).show();
    }
}
