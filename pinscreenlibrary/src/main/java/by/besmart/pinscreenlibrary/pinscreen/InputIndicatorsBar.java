package by.besmart.pinscreenlibrary.pinscreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class InputIndicatorsBar {
    private int spaceIncreasingPercentRatio = 100;
    private int maxIndicatorsWithoutExtraSpacing = 4;
    private int extraSpacingPeriod = 3; // number of indicators extra space will recurring after
    private boolean isExtraSpacingEnabled = true;
    private final Context context;
    private final ViewGroup indicatorsBarContainer;
    private final ArrayList<InputIndicator> indicators;
    private InputIndicator lastFilled = null;

    InputIndicatorsBar(Context context, ViewGroup indicatorsBarContainer, int quantity) {
        this.context = context;
        this.indicatorsBarContainer = indicatorsBarContainer;
        indicators = createIndicators(quantity);
        refreshViews();
    }

    public void setSpaceIncreasingPercentRatio(int spaceIncreasingPercentRatio) {
        this.spaceIncreasingPercentRatio = spaceIncreasingPercentRatio;
        refreshViews();
    }

    public void setMaxIndicatorsWithoutExtraSpacing(int maxIndicatorsWithoutExtraSpacing) {
        this.maxIndicatorsWithoutExtraSpacing = maxIndicatorsWithoutExtraSpacing;
        refreshViews();
    }

    public void setExtraSpacingPeriod(int extraSpacingPeriod) {
        this.extraSpacingPeriod = extraSpacingPeriod;
        refreshViews();
    }

    public void setIsExtraSpacingEnabled(boolean isExtraSpacingEnabled) {
        this.isExtraSpacingEnabled = isExtraSpacingEnabled;
        refreshViews();
    }

    void refreshViews() {
        indicatorsBarContainer.removeAllViews();
        ArrayList<View> indicatorsViews = createIndicatorsBarView();
        for (int i = 0; i < indicatorsViews.size(); i++) {
            indicatorsBarContainer.addView(indicatorsViews.get(i));
        }
    }

    void fillNext() {
        if (lastFilled == null) {
            fillFirst();
        } else if (lastFilled != getLastIndicator()) {
            fillAfterLastFilled();
        }
    }

    void clearLastFilled() {
        if (lastFilled == null) {
            return;
        }
        lastFilled.setFilled(false);
        setLastFilledToPrevious();
    }

    void clearAll() {
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
            indicator.resetView();
            View indicatorView = indicator.getView();
            if (i % extraSpacingPeriod == 0
                        && i != 0) {
                increaseLeftMargin(indicatorView);
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
            indicator.resetView();
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

    void increaseLeftMargin(View view) {
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        double leftMarginPrecise = marginParams.leftMargin;
        double increasingSize = leftMarginPrecise * (spaceIncreasingPercentRatio / 100.0);
        leftMarginPrecise += increasingSize;
        marginParams.leftMargin = ((int) leftMarginPrecise) + 1;
        view.setLayoutParams(marginParams);
    }
}
