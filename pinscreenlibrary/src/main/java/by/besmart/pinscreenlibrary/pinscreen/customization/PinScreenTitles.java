package by.besmart.pinscreenlibrary.pinscreen.customization;

import android.content.Context;

import by.besmart.pinscreenlibrary.R;

/**
 * Created by zvlad on 10/30/15.
 */
public class PinScreenTitles {
    private String initializingTitle;
    private String initializingSubtitle = "";

    private String confirmationTitle;
    private String confirmationSubtitle = "";

    private String requestTitle;
    private String requestSubtitle = "";

    public PinScreenTitles(Context context) {
        initializingTitle = context.getString(R.string.create_pin_title_default);
        confirmationTitle = context.getString(R.string.confirm_pin_title_default);
        requestTitle = context.getString(R.string.request_pin_title_default);
    }

    public String getInitializingTitle() {
        return initializingTitle;
    }

    public void setInitializingTitle(String initializingTitle) {
        this.initializingTitle = initializingTitle;
    }

    public String getConfirmationTitle() {
        return confirmationTitle;
    }

    public void setConfirmationTitle(String confirmationTitle) {
        this.confirmationTitle = confirmationTitle;
    }

    public String getInitializingSubtitle() {
        return initializingSubtitle;
    }

    public void setInitializingSubtitle(String initializingSubtitle) {
        this.initializingSubtitle = initializingSubtitle;
    }

    public String getConfirmationSubtitle() {
        return confirmationSubtitle;
    }

    public void setConfirmationSubtitle(String confirmationSubtitle) {
        this.confirmationSubtitle = confirmationSubtitle;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestSubtitle() {
        return requestSubtitle;
    }

    public void setRequestSubtitle(String requestSubtitle) {
        this.requestSubtitle = requestSubtitle;
    }
}
