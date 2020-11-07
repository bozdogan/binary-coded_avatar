package com.boraozdogan.binarycodedavatar.impl.conf;

import com.boraozdogan.binarycodedavatar.impl.Constants;
import com.boraozdogan.binarycodedavatar.interfaces.conf.Configuration;
import com.boraozdogan.binarycodedavatar.interfaces.conf.ConfigurationProvider;
import com.boraozdogan.binarycodedavatar.interfaces.conf.NonDefaultConfigurationProvider;

import java.nio.file.Paths;

public class SimpleConfigurationProvider implements ConfigurationProvider, NonDefaultConfigurationProvider
{
    @Override
    public Configuration provideConfiguration()
    {
        return this.provideConfiguration(Constants.PROVIDED_DEFAULT_CONFIGURATION_PATH);
    }

    @Override
    public Configuration provideConfiguration(String configurationPath)
    {
        SimpleConfiguration mySimpleConfiguration = new SimpleConfiguration();
        ConfigurationFileReader myConfigurationReader = new ConfigurationFileReader(ConfigurationFileType.RegularConfiguration);

        SimpleConfigurationFiller<SimpleConfiguration> myConfigurationFiller = new SimpleConfigurationFiller<SimpleConfiguration>();
        myConfigurationFiller.fill(mySimpleConfiguration, myConfigurationReader, configurationPath);

        return mySimpleConfiguration;
    }
}
