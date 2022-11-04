package ir.eyrsa.app.ayinname.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    static SharedPreferences sharedPreferences;

    public static final String EMAIL="EMAIL";


    public static SharedPreferences getSharedPreferences()
    {
        if(sharedPreferences == null)
        {
            sharedPreferences = Application.getContext().getSharedPreferences(Config.TAG, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

}
