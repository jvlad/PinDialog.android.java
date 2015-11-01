package by.besmart.pinscreen;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import by.besmart.pinscreenlibrary.listeners.OnPinConfirmationFailsListener;
import by.besmart.pinscreenlibrary.listeners.OnPinCreatedListener;
import by.besmart.pinscreenlibrary.listeners.OnPinReceivedListener;
import by.besmart.pinscreenlibrary.pinscreen.PinScreen;
import by.besmart.pinscreenlibrary.pinscreen.customization.PinScreenColors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int pinLength = 6;
        final PinScreen pinCreation = new PinScreen(this, pinLength);
        pinCreation.setOnPinConfirmationFailsListener(new OnPinConfirmationFailsListener() {
            @Override
            public void pinConfirmationFailed(PinScreen pinScreen) {
                //do smth here
                //e.g.
                Toast.makeText(MainActivity.this, "Sorry, Pins don't match", Toast.LENGTH_SHORT).show();

                //e.g.
                pinScreen.startPinCreation();
//                pinScreen.cancel();
            }
        });
        pinCreation.setOnPinCreatedListener(new OnPinCreatedListener() {
            @Override
            public void pinCodeCreated(PinScreen pinScreen, String pinCode) {
                //do smth here
                //e.g.
                Toast.makeText(MainActivity.this, "Good job: " + pinCode, Toast.LENGTH_SHORT).show();
            }
        });
        pinCreation.startPinCreation();

//        pinLength = 3;
//        final PinScreen pinRequest = new PinScreen(this, pinLength);
//        pinRequest.setOnPinReceivedListener(new OnPinReceivedListener() {
//            @Override
//            public void pinCodeReceived(PinScreen pinScreen, String pinCode) {
//                //do smth here
//                //e.g.
//                Toast.makeText(MainActivity.this, "Entered pin is: " + pinCode, Toast.LENGTH_SHORT).show();
//            }
//        });
//        pinRequest.startPinRequest();
    }
}
