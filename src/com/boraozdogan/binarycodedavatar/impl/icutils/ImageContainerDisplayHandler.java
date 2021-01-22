package com.boraozdogan.binarycodedavatar.impl.icutils;

import com.boraozdogan.binarycodedavatar.impl.ImageContainer;
import com.boraozdogan.binarycodedavatar.interfaces.PreparedBitmapDisplayProcedure;

public class ImageContainerDisplayHandler
{
    private ImageContainer imageContainer;

    public ImageContainerDisplayHandler(ImageContainer newImageContainer)
    {
        this.imageContainer = newImageContainer;
    }

    public void executeDisplayProcedure(PreparedBitmapDisplayProcedure pbdp)
    {
        pbdp.runBitmapDisplayProcedure(this.imageContainer.getImageHandle());
    }
}
