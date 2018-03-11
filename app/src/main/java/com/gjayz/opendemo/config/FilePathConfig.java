package com.gjayz.opendemo.config;

import android.os.Environment;

import com.gjayz.opendemo.BuildConfig;

import java.io.File;

/**
 * Created by tom on 2018/3/11.
 */

public class FilePathConfig {

    /**
     * 文件保存目录
     */
    public static String FILE_CACHE_PATH = Environment.getExternalStorageDirectory() + File.separator + BuildConfig.APPLICATION_ID;

    /**
     * log 文件保存的目录
     */
    public static String ERROR_LOG_PATH = FILE_CACHE_PATH + File.separator + "error_log";
}
