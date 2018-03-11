package com.gjayz.opendemo.log;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.gjayz.opendemo.BuildConfig;
import com.gjayz.opendemo.config.FilePathConfig;
import com.gjayz.opendemo.utils.FileWriteUtil;
import com.tencent.tinker.android.dex.util.FileUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tom on 2018/3/11.
 */

public class SaveErrorLogTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;
    private Throwable mException;

    private SimpleDateFormat mDataFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);

    public SaveErrorLogTask(Context context, Throwable ex){
        this.mContext = context;
        this.mException = ex;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        mException.printStackTrace(printWriter);
        Throwable cause = mException.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        String time = mDataFormat.format(new Date());
        String fileName = time + ".txt";
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append("\nsdkVersion：" + Build.VERSION.SDK);
        stringBuffer.append("\nmanufacturer：" + Build.MANUFACTURER);
        stringBuffer.append("\nmodel：" + Build.MODEL);
        stringBuffer.append("\nversion" +  BuildConfig.VERSION_NAME);
        stringBuffer.append("\nerrorStr：" + result);
        stringBuffer.append("\ntime：" + time);

        FileWriteUtil.writeFile(FilePathConfig.ERROR_LOG_PATH + File.separator + fileName, stringBuffer.toString());
        return null;
    }
}
