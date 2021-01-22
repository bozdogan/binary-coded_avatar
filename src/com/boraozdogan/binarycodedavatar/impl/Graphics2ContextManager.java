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
        this.activeImageContainer = newImageContainer;
    }

    public void createGraphicsContextForActiveImage()
    {
        this.activeImageGraphicsContext = this.activeImageContainer.getImageHandle().createGraphics();
    }

    public Graphics2D getActiveGraphicsContextHandle()
    {
        return this.activeImageGraphicsContext;
    }
}
