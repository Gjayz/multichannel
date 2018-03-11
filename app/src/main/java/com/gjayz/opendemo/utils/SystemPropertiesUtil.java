package com.gjayz.opendemo.utils;

import java.lang.reflect.Method;

/**
 * Created by Tom on 2018/3/11.
 */

public class SystemPropertiesUtil {

    private static Class mClazz;
    private static Method mGetMethod;
    private static Method mGetMethodWithDef;
    private static Method mGetIntMethod;
    private static Method mGetLongMethod;
    private static Method mGetBooleanMethod;

    static{
        try {
            mClazz = Class.forName("android.os.SystemProperties");
            mGetMethod = mClazz.getDeclaredMethod("get", String.class);
            mGetMethodWithDef = mClazz.getDeclaredMethod("get", String.class, String.class);
            mGetIntMethod = mClazz.getDeclaredMethod("getInt", String.class, Integer.class);
            mGetLongMethod = mClazz.getDeclaredMethod("getLong", String.class, Long.class);
            mGetBooleanMethod = mClazz.getDeclaredMethod("getBoolean", String.class, Boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        if (mGetMethod != null){
            try {
                return (String) mGetMethod.invoke(null, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "unknown";
    }

    public static String get(String key, String defValue){
        if (mGetMethod != null){
            try {
                return (String) mGetMethodWithDef.invoke(null, key, defValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }

    public static int getInt(String key, int defValue){
        if (mGetMethod != null){
            try {
                return (int) mGetIntMethod.invoke(null, key, defValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }

    public static long getLong(String key, long defValue){
        if (mGetMethod != null){
            try {
                return (int) mGetLongMethod.invoke(null, key, defValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }

    public static boolean getBoolean(String key, boolean defValue){
        if (mGetMethod != null){
            try {
                return (boolean) mGetLongMethod.invoke(null, key, defValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }
}