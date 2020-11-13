package com.syntax.wordprocessor.common.utilities;

import com.syntax.wordprocessor.common.constants.Constants;

/**
 * Directory entry.
 */
public class DirectoryEntry {

    public enum TypeEnum {
        DIR,
        FILE
    }

    public String mName = Constants.EMPTY_STRING;
    public TypeEnum mType = TypeEnum.FILE;
    public long mSize = 0;

    public DirectoryEntry() {
        mName = Constants.EMPTY_STRING;
        mType = TypeEnum.FILE;
        mSize = 0;
    }

    public DirectoryEntry(String name, TypeEnum type, long size) {
        mName = name;
        mType = type;
        mSize = size;
    }
}
