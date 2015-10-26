package cf.zvlad.pindialoglibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zvlad on 10/26/15.
 */
public class PinCode {
    List<Character> enteredCharacters;

    public PinCode(InputIndicatorsBar indicatorsBar) {
        enteredCharacters = new ArrayList<>();
    }

    public void add(Character character){
        enteredCharacters.add(character);
    }

    public int size(){
        return enteredCharacters.size();
    }

    public void clearAllEnteredCharacters() {
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
}
