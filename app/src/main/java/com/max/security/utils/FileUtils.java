package com.max.security.utils;

import android.os.Environment;

import com.max.security.greendao.FileModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Administrator on 2016/9/6.
 */
public class FileUtils {

    private static final String PASSKEY = "afasdf";
    private static final String DESKEY = "asfsdfsdf";

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

    /**
     * encrypt or decrypt file
     * @param sourcePath
     * source file full path
     * @param destPath
     * dest file full path
     * @param mode
     * Cipher.ENCRYPT_MODE encrypt file,
     * Cipher.DECRYPT_MODE decrypt file
     */
    public static void encryptOrdecrypt(String sourcePath, String destPath, int mode) {
        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;

        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(DESKEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(PASSKEY.getBytes());
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(mode, secretKey, iv, sr);

            File destFile = new File(destPath);
            File parent = destFile.getParentFile();
            if(!parent.exists()) {
                parent.mkdirs();
            }

            is = new FileInputStream(sourcePath);
            out = new FileOutputStream(destFile);
            cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(cis != null) {
                try {
                    cis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
