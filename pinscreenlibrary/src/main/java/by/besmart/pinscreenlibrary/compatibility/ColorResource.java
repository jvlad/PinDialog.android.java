package by.besmart.pinscreenlibrary.compatibility;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

/**
 * Created by zvlad on 10/30/15.
 */
public class ColorResource {
    public static int getColor(Context context, int id){
        int sdk = Build.VERSION.SDK_INT;
        if(sdk >= Build.VERSION_CODES.M) {
            return getColorAPIAbove22(context, id);
        }
        return getColorAPIUnder23(context, id);
    }

    @TargetApi(23)
    private static int getColorAPIAbove22(Context context, int id) {
        return context.getColor(id);
    }

    @SuppressWarnings("deprecation")
    private static int getColorAPIUnder23(Context context, int id) {
        return context.getResources().getColor(id);
    }
}
