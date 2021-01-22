package com.boraozdogan.binarycodedavatar.impl.util;

public class ImageContainerMetadataResultSet
{
    private Integer wtd;
    private Integer hgt;
    private Integer typ;

    public ImageContainerMetadataResultSet(Integer newWtd, Integer newHgt)
    {
        this.wtd = newWtd;
        this.hgt = newHgt;
    }

    public void updatePictureFormatIdentifier(Integer newTyp)
    {
        this.typ = newTyp;
    }

    public Integer getWtd()
    {
        return wtd;
    }

    public Integer getHgt()
    {
        return hgt;
    }

    public Integer getTyp()
    {
        return typ;
    }
}
