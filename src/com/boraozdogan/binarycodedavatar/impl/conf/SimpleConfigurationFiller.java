package com.boraozdogan.binarycodedavatar.impl.conf;

import com.boraozdogan.binarycodedavatar.interfaces.conf.Configuration;

import java.nio.file.Paths;

public class SimpleConfigurationFiller<T extends AbstractConfiguration>
{
    public SimpleConfigurationFiller()
    {
    }

    public void fill(T configuration, ConfigurationFileReader reader, String configurationPath)
    {
        reader.setTarget(configuration);
        reader.load(Paths.get(configurationPath));
        reader.setTarget(null);
    }
}
