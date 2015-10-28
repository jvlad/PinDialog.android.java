package by.besmart.pinscreenlibrary.models;

import java.util.ArrayList;
import java.util.List;

public class PinCode {
    private List<Character> enteredCharacters;
    private StringBuilder pin;

    public PinCode() {
        enteredCharacters = new ArrayList<>();
        pin = new StringBuilder();
    }

    public void add(Character character){
        enteredCharacters.add(character);
    }

    public int size(){
        return enteredCharacters.size();
    }

    public void clearAll() {
        if (enteredCharacters.isEmpty()){
            return;
        }
        enteredCharacters.clear();
    }

    public void clearLastEnteredCharacter() {
        if (enteredCharacters.size() == 1){
            enteredCharacters.clear();
        } else if (enteredCharacters.size() > 1) {
            int lastCharacterIndex = enteredCharacters.size() - 1;
            enteredCharacters.remove(lastCharacterIndex);
        }
    }

    @Override
    public String toString() {
        resetPin();
        for (int i = 0; i < enteredCharacters.size(); i++) {
            pin.append(enteredCharacters.get(i));
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

    private void resetPin() {
        pin.delete(0, pin.length());
    }
}
