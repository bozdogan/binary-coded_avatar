package com.boraozdogan.binarycodedavatar.impl.conf;

import com.boraozdogan.binarycodedavatar.interfaces.conf.Configuration;
import com.boraozdogan.binarycodedavatar.interfaces.conf.ConfigurationProvider;
import com.boraozdogan.binarycodedavatar.interfaces.conf.NonDefaultConfigurationProvider;

public class ConfigurationProviderSingleton
{
    private static ConfigurationProviderSingleton configurationProviderSingletonInstance;

    private final ConfigurationProvider myConfigurationProvider;
    private Configuration providedConfiguration;

    private ConfigurationProviderSingleton()
    {
        this.myConfigurationProvider = new SimpleConfigurationProvider();
    }

    public static ConfigurationProviderSingleton getInstance()
    {
        if(ConfigurationProviderSingleton.configurationProviderSingletonInstance == null)
        {
            ConfigurationProviderSingleton.configurationProviderSingletonInstance = new ConfigurationProviderSingleton();
            return ConfigurationProviderSingleton.configurationProviderSingletonInstance;
        }
        else
        {
            return ConfigurationProviderSingleton.configurationProviderSingletonInstance;
        }
    }

    public void initializeSingleton(String configurationFilePath)
    {
        this.providedConfiguration = ((NonDefaultConfigurationProvider) this.myConfigurationProvider).provideConfiguration(configurationFilePath);
    }

    public Configuration fetchConfiguration()
    {
        if(providedConfiguration == null)
            throw new NotInitializedWhatAmISupposedToDoNowException();

        return providedConfiguration;
    }
}
