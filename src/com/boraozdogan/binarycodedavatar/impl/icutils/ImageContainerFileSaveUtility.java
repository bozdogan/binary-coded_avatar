package com.boraozdogan.binarycodedavatar.impl.icutils;

import com.boraozdogan.binarycodedavatar.impl.ImageContainer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static com.boraozdogan.binarycodedavatar.impl.Constants.IDENTIFIER_STRING_FOR_PNG_PICTURE_FORMAT;

public class ImageContainerFileSaveUtility
{
    private ImageContainer imageContainer;
    private File objectForFileHandling;
    private String pictureFormatIdentifierString;

    public ImageContainerFileSaveUtility(ImageContainer newImageContainer, File newObjectForFileHandling)
    {
        imageContainer = newImageContainer;
        objectForFileHandling = newObjectForFileHandling;
    }

    public void setPictureFormatIdentifier(String newPictureFormatIdentifierString)
    {
        pictureFormatIdentifierString = newPictureFormatIdentifierString;
    }

    public void executeFileSavingProcess() throws IOException
    {
        ImageIO.write(imageContainer.getImageHandle(), pictureFormatIdentifierString, objectForFileHandling);
    }
}
