package com.boraozdogan.binarycodedavatar.interfaces.conf;

public interface Configuration
{
    public <T> T getValue(String key, Class<T> type);
}
