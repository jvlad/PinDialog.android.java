package cf.zvlad.pindialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cf.zvlad.pindialoglibrary.PinDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Hello!", Toast.LENGTH_LONG).show();
        Dialog d = new PinDialog(this, 2);
        d.show();
    }
}
