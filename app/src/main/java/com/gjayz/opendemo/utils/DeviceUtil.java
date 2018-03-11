package com.gjayz.opendemo.utils;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2018/3/11.
 */

public class DeviceUtil {

    private static final String TAG = "DeviceUtil";

    /**
     * 返回屏幕的宽度
     * @param activity
     * @return
     */
    public static Point getScreenSize(Activity activity){
        Point point = new Point();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        point.x = displayMetrics.widthPixels;
        point.y = displayMetrics.heightPixels;
        return point;
    }

    /**
     * 获取屏幕的宽度
     * @param activity
     * @return 屏幕的长度和宽度的数组
     */
    public static Point getScreenSizePoint(Activity activity){
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 获取屏幕的正式的宽高
     * @param activity
     * @return
     */
    public static Point getScreenRealSize(Activity activity){
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        return point;
    }

    private Map<String, String> mDeviceInfosMap = new HashMap<>();

    public Map<String, String> getDeviceInfos(){
        Log.i(TAG, "getDeviceInfos");
        try {
            Class buildClass = Class.forName("android.os.Build");
            Field[] fields = buildClass.getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(null);
                mDeviceInfosMap.put(name, value.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mDeviceInfosMap;
    }
}