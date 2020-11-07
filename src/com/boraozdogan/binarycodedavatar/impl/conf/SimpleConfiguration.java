package com.boraozdogan.binarycodedavatar.impl.conf;

import com.boraozdogan.binarycodedavatar.interfaces.conf.Configuration;

import java.util.Hashtable;
import java.util.Map;

public class SimpleConfiguration extends AbstractConfiguration
{
    public SimpleConfiguration()
    {
        this.configurationContainer = new Hashtable<String, Object>();
    }

    public Object getValue(String key)
    {
        return this.configurationContainer.get(key);
    }

    @Override
    public <T> T getValue(String key, Class<T> type)
    {
        return type.cast(this.getValue(key));
    }
}
