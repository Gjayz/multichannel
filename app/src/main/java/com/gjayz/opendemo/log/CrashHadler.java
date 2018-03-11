package com.gjayz.opendemo.log;

import android.content.Context;

/**
 * Created by tom on 2018/3/11.
 */

public class CrashHadler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHadler";
    private static CrashHadler sInstance;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHadler(Context context) {
        mContext  = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashHadler getInstance(Context context){
        if (sInstance == null){
            synchronized (CrashHadler.class){
                if (sInstance == null){
                    sInstance = new CrashHadler(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        SaveErrorLogTask saveErrorLogTask = new SaveErrorLogTask(mContext, e);
        saveErrorLogTask.execute();
    }
}
