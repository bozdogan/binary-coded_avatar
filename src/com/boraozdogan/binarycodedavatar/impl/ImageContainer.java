package com.boraozdogan.binarycodedavatar.impl;

import java.awt.image.BufferedImage;
import java.util.*;

public class ImageContainer 
{
    private Map<String, Object> metadata;
    private BufferedImage i;

    public ImageContainer()
    {
        metadata = new HashMap<>();
    }

    public void setMetadata(String key, Object value)
    {
        metadata.put(key, value);
    }

    public void createNew()
    {
        // TODO(bora): Extraction of metadata should be done a separate class.
        // It varies!!
        Integer valueofWdt = Integer.valueOf((String) metadata.get("imgwdt"));
        Integer valueofHgt = Integer.valueOf((String) metadata.get("imghgt"));
        Integer valueofTyp = Integer.valueOf((String) metadata.get("imgtyp"));
        i = new BufferedImage(valueofWdt, valueofHgt, valueofTyp);
    }

    public BufferedImage getImageHandle()
    {
        return i;
    }
}