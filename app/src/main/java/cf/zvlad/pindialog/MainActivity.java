package cf.zvlad.pindialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cf.zvlad.pindialoglibrary.OnPinEnteredListener;
import cf.zvlad.pindialoglibrary.DecimalPinDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnPinEnteredListener onPinEnteredListener = new OnPinEnteredListener() {
            @Override
            public void onPinEntered(String pin) {showShortToastWithText(pin);}
        };
        Dialog d = new DecimalPinDialog(this, 3, onPinEnteredListener);
        d.show();
    }

    private void showShortToastWithText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
