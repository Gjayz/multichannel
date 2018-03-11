package com.gjayz.opendemo.ui.activity;

import android.app.Application;
import android.content.Context;

import com.gjayz.opendemo.log.CrashHadler;
import com.gjayz.opendemo.utils.DeviceUtil;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

/**
 * Created by Tom on 2018/3/10.
 */

public class App extends Application {

    private ApplicationLike tinkerApplicationLike;
    private Context mAppContext;

    public static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mAppContext = getApplicationContext();
//        // 我们可以从这里获得Tinker加载过程的信息
//        tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
//
//        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
//        TinkerPatch.init(tinkerApplicationLike)
//                .reflectPatchLibrary()
//                .setPatchRollbackOnScreenOff(true)
//                .setPatchRestartOnSrceenOff(true)
//                .setFetchPatchIntervalByHours(3);
//
//        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
//        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();

        CrashHadler crashHadler = CrashHadler.getInstance(mAppContext);

        DeviceUtil deviceUtil = new DeviceUtil();
        deviceUtil.getDeviceInfos();
    }

    public static App getInstance(){
        return sInstance;
    }

    public Context getAppContext(){
        return mAppContext;
    }
}