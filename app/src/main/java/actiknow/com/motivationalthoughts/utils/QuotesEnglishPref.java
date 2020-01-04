package actiknow.com.motivationalthoughts.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class QuotesEnglishPref {
    public static String FIREBASE_ID = "firebase_id";
    public static String LANGUAGE = "language";
    public static String LANGUAGE_ENGLISH = "language_english";
    public static String LANGUAGE_HINDI = "language_hindi";
    public static String VALUE = "value";
    
    private static QuotesEnglishPref quotesEnglishPref;
    private String QUOTES_ENGLISH = "Quotes_English";
    
    public static QuotesEnglishPref getInstance () {
        if (quotesEnglishPref == null)
            quotesEnglishPref = new QuotesEnglishPref();
        return quotesEnglishPref;
    }
    
    private SharedPreferences getPref (Context context) {
        return context.getSharedPreferences (QUOTES_ENGLISH, Context.MODE_PRIVATE);
    }
    
    public String getStringPref (Context context, String key) {
        return getPref (context).getString (key, "");
    }
    
    public int getIntPref (Context context, String key) {
        return getPref (context).getInt (key, 0);
    }
    
    public boolean getBooleanPref (Context context, String key) {
        return getPref (context).getBoolean (key, false);
    }
    
    public void putBooleanPref (Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getPref (context).edit ();
        editor.putBoolean (key, value);
        editor.apply ();
    }
    
    public void putStringPref (Context context, String key, String value) {
        SharedPreferences.Editor editor = getPref (context).edit ();
        editor.putString (key, value);
        editor.apply ();
    }
    
    public void putIntPref (Context context, String key, int value) {
        SharedPreferences.Editor editor = getPref (context).edit ();
        editor.putInt (key, value);
        editor.apply ();
    }
    
    
}
