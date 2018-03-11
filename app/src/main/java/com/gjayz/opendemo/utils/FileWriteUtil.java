package com.gjayz.opendemo.utils;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by tom on 2018/3/11.
 */

public class FileWriteUtil {

    public static void writeFile(String fileName, String content){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            bos.write(content.getBytes());
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
