package by.besmart.pinscreenlibrary.models;

import java.util.ArrayList;
import java.util.List;

public class PinCode {
    private List<Character> pinCharacters;
    private StringBuilder pin;

    public PinCode() {
        pinCharacters = new ArrayList<>();
        pin = new StringBuilder();
    }

    public void add(Character character){
        pinCharacters.add(character);
    }

    public int size(){
        return pinCharacters.size();
    }

    public void clearAll() {
        pinCharacters.clear();
    }

    @Override
    public String toString() {
        resetPin();
        for (int i = 0; i < pinCharacters.size(); i++) {
            pin.append(pinCharacters.get(i));
        }
        return pin.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PinCode)){
            return false;
        }
        String pinCharacters = o.toString();
        return pinCharacters.equals(this.toString());
    }

    public void clear() {
        pinCharacters.clear();
    }

    public void remove(int location) {
        pinCharacters.remove(location);
    }

    private void resetPin() {
        pin.delete(0, pin.length());
    }
}
