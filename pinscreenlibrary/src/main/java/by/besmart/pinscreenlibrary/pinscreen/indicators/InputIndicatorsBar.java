package by.besmart.pinscreenlibrary.pinscreen.indicators;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

public class InputIndicatorsBar {
    public static int spaceIncreasingPercentRatio = 50;
    public static int maxIndicatorsWithoutExtraSpacing = -3;
    public static int extraSpacingPeriod = 3; // extra space will recurring after specified number of indicators
    public boolean isExtraSpacingEnabled = true;
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

    public void clearLastFilled() {
        if (lastFilled == null) {
            return;
        }
        lastFilled.setFilled(false);
        setLastFilledToPrevious();
    }

    public void clearAll() {
        if (lastFilled == null) {
            return;
        }
        for (int i = 0; i < indicators.size(); i++) {
            InputIndicator indicator = indicators.get(i);
            indicator.setFilled(false);
        }
        lastFilled = null;
    }

    private void setLastFilledToPrevious() {
        int lastFilledIndex = indicators.indexOf(lastFilled);
        if (lastFilledIndex == 0) {
            lastFilled = null;
        } else {
            lastFilled = indicators.get(lastFilledIndex - 1);
        }
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
        if (isExtraSpacingEnabled
                    && indicators.size() > maxIndicatorsWithoutExtraSpacing) {
            return createExtraSpacedIndicatorsBar();
        }

        return createEqualSpacedIndicatorsBar();
    }

    private ArrayList<View> createExtraSpacedIndicatorsBar() {
        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < indicators.size(); i++) {
            InputIndicator indicator = indicators.get(i);
            View indicatorView;
            if (i % extraSpacingPeriod == 0
                        && i !=0){
                indicatorView = getIndicatorViewWithIncreasedLeftMargin(indicator);
            } else {
                indicatorView = indicator.getView();
            }
            views.add(indicatorView);
        }
        return views;
    }

    @NonNull
    private ArrayList<View> createEqualSpacedIndicatorsBar() {
        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < indicators.size(); i++) {
            InputIndicator indicator = indicators.get(i);
            views.add(indicator.getView());
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

    private View getIndicatorViewWithIncreasedLeftMargin(InputIndicator indicator) {
        View view = indicator.getView();
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        double leftMarginPrecise= marginParams.leftMargin;
        double increasingSize = leftMarginPrecise * (spaceIncreasingPercentRatio / 100.0);
        leftMarginPrecise += increasingSize;
        marginParams.leftMargin = ((int) leftMarginPrecise) + 1;
        view.setLayoutParams(marginParams);
        return view;
    }
}
