package cf.zvlad.pindialoglibrary;

/**
 * Created by zvlad on 10/27/15.
 */
public interface OnPinWorkflowEndListener {
    void pinWorkflowEnded(boolean success, String pinCode);
}
