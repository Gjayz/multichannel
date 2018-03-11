package com.gjayz.opendemo.utils;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by Tom on 2018/3/11.
 */

public class DeviceUtil {

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
}
