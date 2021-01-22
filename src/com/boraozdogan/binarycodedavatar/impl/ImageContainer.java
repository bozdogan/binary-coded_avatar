package com.boraozdogan.binarycodedavatar.impl;

import com.boraozdogan.binarycodedavatar.impl.util.ImageContainerMetadataResultSet;
import com.boraozdogan.binarycodedavatar.impl.util.MetadataExtractionHelper;

import java.awt.image.BufferedImage;
import java.util.*;

public class ImageContainer 
{
    private Map<String, Object> metadata;
    private BufferedImage i;

    public ImageContainer()
    {
        this.metadata = new HashMap<>();
    }

    public void setMetadata(String key, Object value)
    {
        this.metadata.put(key, value);
    }

    public void createNew()
    {
        MetadataExtractionHelper mdeh = new MetadataExtractionHelper();
        ImageContainerMetadataResultSet mdrs = mdeh.extractMetadataOf(this);
        Integer valueofWdt = mdrs.getWtd();
        Integer valueofHgt = mdrs.getHgt();
        Integer valueofTyp = mdrs.getTyp();
        this.i = new BufferedImage(valueofWdt, valueofHgt, valueofTyp);
    }

    public BufferedImage getImageHandle()
    {
        return this.i;
    }
}