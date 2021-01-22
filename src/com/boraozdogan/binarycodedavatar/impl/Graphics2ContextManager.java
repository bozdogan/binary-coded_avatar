package com.boraozdogan.binarycodedavatar.impl;

import java.awt.*;

public class Graphics2ContextManager
{
    private static Graphics2ContextManager object;
    private ImageContainer activeImageContainer;
    private Graphics2D activeImageGraphicsContext;

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

    public void setActiveImageContainer(ImageContainer newImageContainer)
    {
        activeImageContainer = newImageContainer;
    }

    public void createGraphicsContextForActiveImage()
    {
        activeImageGraphicsContext = activeImageContainer.getImageHandle().createGraphics();
    }

    public Graphics2D getActiveGraphicsContextHandle()
    {
        return activeImageGraphicsContext;
    }
}
