package com.max.security.utils;

import android.os.Environment;

import com.max.security.greendao.FileModel;

import java.io.File;

/**
 * Created by Administrator on 2016/9/6.
 */
public class FileUtils {

    private static final String LOCAL_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".SafeBox";
    private static final String IMAGE_FILE_PATH = LOCAL_PATH + File.separator + "images";
    private static final String VIDEO_FILE_PATH = LOCAL_PATH + File.separator + "videos";
    private static final String AUDIO_FILE_PATH = LOCAL_PATH + File.separator + "audios";
    private static final String PRIVATE_FILE_PATH = LOCAL_PATH + File.separator + "files";
    private static final String DECRYPT_DIR = "decrypt";

    public static String getDecryptFilePath(FileModel fileModel) {
        String path = fileModel.getFilePath() + File.separator + DECRYPT_DIR + File.separator + fileModel.getFileName();
        return path;
    }
}
