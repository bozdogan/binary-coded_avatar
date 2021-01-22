package com.boraozdogan.binarycodedavatar.impl;

import java.awt.*;

public class Graphics2ContextManager
{
    private static Graphics2ContextManager object;
    private ImageContainer currentlyActiveImageContainer;
    private Graphics2D currentlyActiveImageGraphicsContext;

    private Graphics2ContextManager()
    {
    }

    public static Graphics2ContextManager getInstance()
    {
        if(object == null)
        {
            object = new Graphics2ContextManager();
        }

        return object;
    }

    public void setCurrentlyActiveImageContainer(ImageContainer newImageContainer)
    {
        this.currentlyActiveImageContainer = newImageContainer;
    }

    public void createGraphicsContextForActiveImage()
    {
        this.currentlyActiveImageGraphicsContext = this.currentlyActiveImageContainer.getImageHandle().createGraphics();
    }

    public Graphics2D getCurrentlyActiveGraphicsContextHandle()
    {
        return this.currentlyActiveImageGraphicsContext;
    }
}
