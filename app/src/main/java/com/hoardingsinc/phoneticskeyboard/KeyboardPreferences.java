package com.hoardingsinc.phoneticskeyboard;

import android.content.Context;
import android.content.SharedPreferences;

public class KeyboardPreferences {
    private static final String PREFS_NAME = "Preferences";
    private static final String PREFIX_LAYOUT = "layout";

    public static final int LAYOUT_NORMAL = 0;
    public static final int LAYOUT_EXTENDED = 1;
    public static final int LAYOUT_COMPACT = 2;

    private Context mContext;

    public KeyboardPreferences(Context context) {
        mContext = context;
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(PREFS_NAME, 0);
    }

    public int getLayout() {
        return getSharedPreferences().getInt(PREFIX_LAYOUT, LAYOUT_NORMAL);
    }

    public void saveLayout(int layout) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(PREFIX_LAYOUT, layout);
        editor.apply();
    }

}
