package com.gjayz.opendemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gjayz.opendemo.config.Config;
import com.gjayz.opendemo.ui.activity.App;

import java.util.Set;

/**
 * Created by Tom on 2018/3/11.
 */

public class SharePreferenceUtil {

    private static SharedPreferences.Editor sEditor;
    private static SharedPreferences sSharedPreferences;

    static {
        sSharedPreferences = App.getInstance().getApplicationContext()
                .getSharedPreferences(Config.SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
    }

    public static void put(String key, Object value){
        if (sEditor == null){
            if (value instanceof Integer){
                sEditor.putInt(key, (Integer) value);
            }else if (value instanceof Boolean){
                sEditor.putBoolean(key, (Boolean) value);
            }else if (value instanceof String){
                sEditor.putString(key, (String) value);
            }else if (value instanceof Float){
                sEditor.putFloat(key, (Float) value);
            }else if (value instanceof Long){
                sEditor.putLong(key, (Long) value);
            }else if (value instanceof Set){
                sEditor.putStringSet(key, (Set<String>) value);
            }else{
                throw new RuntimeException("unsupport type exception, not supoort type " + value.getClass());
            }
            sEditor.commit();
        }
    }

    public static String getString(String key, String defValue){
        return sSharedPreferences.getString(key, defValue);
    }

    public static int getInt(String key, int defValue){
        return sSharedPreferences.getInt(key, defValue);
    }

    public static boolean getString(String key, boolean defValue){
        return sSharedPreferences.getBoolean(key, defValue);
    }

    public static float getFloat(String key, float defValue){
        return sSharedPreferences.getFloat(key, defValue);
    }

    public static long getLong(String key, long defValue){
        return sSharedPreferences.getLong(key, defValue);
    }

    public static Set<String> getSet(String key, Set<String> defValue){
        return sSharedPreferences.getStringSet(key, defValue);
    }
}