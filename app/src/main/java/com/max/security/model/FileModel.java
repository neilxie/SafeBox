package com.max.security.model;

/**
 * Created by Administrator on 2016/8/31.
 */
public class FileModel {

    private String fileName;
    private String filePath;
    private String originPath;
    private int type;
    private String fileId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public FileType getFileType() {
        return FileType.mapValueToType(type);
    }

    public enum FileType {

        IMAGE(0x00),
        VIDEO(0x01),
        AUDIO(0x02),
        FILE(0x03);

        private int mValue;

        FileType(int value) {
            mValue = value;
        }

        public static FileType mapValueToType(final int value) {
            for(FileType type : FileType.values()) {
                if(value == type.getValue()) {
                    return type;
                }
            }

            return IMAGE;
        }

        public static FileType getDefault() {
            return IMAGE;
        }

        public int getValue() {
            return mValue;
        }

    }
}
