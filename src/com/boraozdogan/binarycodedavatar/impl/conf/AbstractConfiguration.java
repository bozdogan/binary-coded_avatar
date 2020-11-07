package com.boraozdogan.binarycodedavatar.impl.conf;

import com.boraozdogan.binarycodedavatar.interfaces.conf.Configuration;

import java.util.Map;

public abstract class AbstractConfiguration implements Configuration
{
    Map<String, Object> configurationContainer;
}
