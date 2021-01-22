package com.boraozdogan.binarycodedavatar.impl.util;

import com.boraozdogan.binarycodedavatar.impl.ImageContainer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MetadataExtractionHelper
{
    private Map<String, Object> localMetadata;

    public MetadataExtractionHelper()
    {
        this.localMetadata = new HashMap<>();
    }

    public void setMetadata(String key, Object value)
    {
        this.localMetadata.put(key, value);
    }

    public ImageContainerMetadataResultSet extractMetadataOf(ImageContainer c)
    {
        ImageContainerMetadataResultSet icmdrs = null;
        try
        {
            MetadataHandler mh = new MetadataHandler(ImageContainer.class.getDeclaredField("metadata"));
            Map<String, Object> md = mh.getMetadataFromInstance(c);
            icmdrs = new ImageContainerMetadataResultSet(Integer.valueOf((String) md.get("imgwdt")),Integer.valueOf((String) md.get("imghgt")));
            icmdrs.updatePictureFormatIdentifier(Integer.valueOf(Integer.parseInt((String) md.get("imgtyp"))));
        }
        catch(NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        return icmdrs;
    }

    private class MetadataHandler
    {
        Field metadataField;

        public MetadataHandler(Field newMetadataField)
        {
            newMetadataField.setAccessible(true);
            this.metadataField = newMetadataField;
        }

        public Map<String, Object> getMetadataFromInstance(Object o)
        {
            Map<String, Object> m = null;
            try
            {
                return (Map<String, Object>) metadataField.get(o);
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
            return m;
        }
    }
}
