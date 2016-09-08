package com.max.security.model;

/**
 * Created by Administrator on 2016/9/5.
 */
public enum FileModelType {

    IMAGE(0),
    VIDEO(1),
    AUDIO(2),
    FILE(3);


    private int mValue;

    FileModelType(int value) {
        mValue = value;
    }

    public static FileModelType mapValueToType(int value) {
        for(FileModelType type : FileModelType.values()) {
            if(type.getValue() == value) {
                return type;
            }
        }

        return IMAGE;
    }

    public int getValue() {
        return mValue;
    }
}
