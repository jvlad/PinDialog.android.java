package cf.zvlad.pindialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cf.zvlad.pindialoglibrary.listeners.OnPinConfirmationFailsListener;
import cf.zvlad.pindialoglibrary.listeners.OnPinCreatedListener;
import cf.zvlad.pindialoglibrary.pinscreen.PinScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PinScreen pinTest = new PinScreen(this, 5);
        pinTest.setOnPinConfirmationFailsListener(new OnPinConfirmationFailsListener() {
            @Override
            public void pinConfirmationFailed() {
                Toast.makeText(MainActivity.this, "pinConfirmationFailed", Toast.LENGTH_SHORT).show();
                pinTest.startPinCreation();
            }
        });
        pinTest.setOnPinCreatedListener(new OnPinCreatedListener() {
            @Override
            public void pinCodeCreated(String pinCode) {
                Toast.makeText(MainActivity.this, "Good job: " + pinCode, Toast.LENGTH_SHORT).show();
            }
        });
        pinTest.startPinCreation();
    }

}
