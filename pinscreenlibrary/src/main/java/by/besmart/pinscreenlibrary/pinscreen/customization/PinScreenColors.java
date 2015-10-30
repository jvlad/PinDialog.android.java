package by.besmart.pinscreenlibrary.pinscreen.customization;

import android.content.Context;
import android.graphics.Color;

import by.besmart.pinscreenlibrary.R;
import by.besmart.pinscreenlibrary.compatibility.ColorResource;

/**
 * Created by zvlad on 10/30/15.
 */
public class PinScreenColors {
    private int brandColor;
    private int strokesColor;
    private int backgroundColor;
    private int textColor;
    private int inactiveElementColor;

    public PinScreenColors(Context context) {
        brandColor = ColorResource.getColor(context, R.color.brand_default);
        strokesColor = ColorResource.getColor(context, R.color.strokes_default);
        backgroundColor = ColorResource.getColor(context, R.color.background_default);
        textColor = ColorResource.getColor(context, R.color.text_default);
        inactiveElementColor = ColorResource.getColor(context, R.color.inactive_element_default);
    }

    public int getBrandColor() {
        return brandColor;
    }

    public void setBrandColor(int brandColor) {
        this.brandColor = brandColor;
    }

    public int getStrokesColor() {
        return strokesColor;
    }

    public void setStrokesColor(int strokesColor) {
        this.strokesColor = strokesColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getInactiveElementColor() {
        return inactiveElementColor;
    }

    public void setInactiveElementColor(int inactiveElementColor) {
        this.inactiveElementColor = inactiveElementColor;
    }
}
