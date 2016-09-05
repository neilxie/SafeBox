package com.max.security.model;

/**
 * Created by Administrator on 2016/9/5.
 */
public enum FileModelType {

    IMAGE(1),
    VIDEO(2),
    AUDIO(3),
    FILE(4);


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
