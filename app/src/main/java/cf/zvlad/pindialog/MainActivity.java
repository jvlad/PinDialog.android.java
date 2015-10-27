package cf.zvlad.pindialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cf.zvlad.pindialoglibrary.OnPinWorkflowEndListener;
import cf.zvlad.pindialoglibrary.PinWorkflow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PinWorkflow pinTest = new PinWorkflow(this, 5, new OnPinWorkflowEndListener() {
            @Override
            public void pinWorkflowEnded(boolean success, String pinCode) {
                finishPinWorkflow(success, pinCode);
            }
        });
        pinTest.startPinCreation();
    }

    private void finishPinWorkflow(boolean success, String pinCode) {
        if (success){
            Toast.makeText(this, "Good job: " + pinCode, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sorry", Toast.LENGTH_SHORT).show();
        }
    }


}
