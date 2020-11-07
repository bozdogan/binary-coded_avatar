package com.boraozdogan.binarycodedavatar.interfaces;

import java.nio.file.Path;

public interface FileReader<T>
{
    public void setTarget(T target);
    public void load(Path filepath);
}
