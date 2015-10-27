package cf.zvlad.pindialoglibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zvlad on 10/26/15.
 */
public class InputIndicatorsBar {
    private final Context context;
    private final ViewGroup indicatorsBarContainer;
    private final ArrayList<InputIndicator> indicators;
    private ArrayList<View> indicatorsBarView;
    private InputIndicator lastFilled = null;

    public InputIndicatorsBar(Context context, ViewGroup indicatorsBarContainer, int quantity) {
        this.context = context;
        this.indicatorsBarContainer = indicatorsBarContainer;
        indicators = createIndicators(quantity);
        indicatorsBarView = createIndicatorsBarView();
    }

    public Iterator<View> getViewsIterator() {
        return indicatorsBarView.iterator();
    }

    public void fillNext() {
        if (lastFilled == null) {
            fillFirst();
        } else if (lastFilled != getLastIndicator()) {
            fillAfterLastFilled();
        }
    }

    public void clearLastFilled(){
        if (lastFilled == null){
            return;
        }
        lastFilled.setFilled(false);
        moveLastFilledToPrevious();
    }

    private void moveLastFilledToPrevious() {
        int lastFilledIndex = indicators.indexOf(lastFilled);
        if (lastFilledIndex == 0){
            lastFilled = null;
        } else {
            lastFilled = indicators.get(lastFilledIndex - 1);
        }
    }

    public void clearAll(){
        if (lastFilled == null){
            return;
        }
        for (int i = 0; i < indicators.size(); i++) {
            InputIndicator indicator = indicators.get(i);
            indicator.setFilled(false);
        }
        lastFilled = null;
    }

    private void fillFirst() {
        InputIndicator indicator = getFirstIndicator();
        indicator.setFilled(true);
        lastFilled = indicator;
    }

    private void fillAfterLastFilled() {
        int nextIndex = indicators.indexOf(lastFilled) + 1;
        InputIndicator next = indicators.get(nextIndex);
        next.setFilled(true);
        lastFilled = next;
    }

    private ArrayList<InputIndicator> createIndicators(int quantity) {
        ArrayList<InputIndicator> indicators = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            indicators.add(new InputIndicator(context, indicatorsBarContainer));
        }
        return indicators;
    }

    private ArrayList<View> createIndicatorsBarView() {
        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < indicators.size(); i++) {
            InputIndicator indicator = indicators.get(i);
            views.add(indicator.getIndicatorView());
        }
        return views;
    }

    private InputIndicator getFirstIndicator() {
        return indicators.get(0);
    }

    private InputIndicator getLastIndicator() {
        int lastIndex = indicators.size() - 1;
        return indicators.get(lastIndex);
    }
}
