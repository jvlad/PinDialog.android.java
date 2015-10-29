package by.besmart.pinscreenlibrary.listeners;

import by.besmart.pinscreenlibrary.pinscreen.PinScreen;

public interface OnPinReceivedListener {
    void pinCodeReceived(PinScreen pinScreen, String pinCode);
}
