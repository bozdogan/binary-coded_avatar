package com.boraozdogan.binarycodedavatar.impl.conf;

public class ConfigurationFileTypeNotSupportedException extends UnsupportedOperationException
{
    public ConfigurationFileTypeNotSupportedException()
    {
        this("Configuration file type is not supported.");
    }

    public ConfigurationFileTypeNotSupportedException(String message)
    {
        super(message);
    }
}
