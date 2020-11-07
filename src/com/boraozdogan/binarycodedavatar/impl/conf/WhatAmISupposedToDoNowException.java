package com.boraozdogan.binarycodedavatar.impl.conf;

public class WhatAmISupposedToDoNowException extends UnsupportedOperationException
{
    public WhatAmISupposedToDoNowException()
    {
        super("FREAKING OUT!!1!one");
    }

    public WhatAmISupposedToDoNowException(String message)
    {
        super(message + " WHAT AM I SUPPOSED TO DO NOW?!? I'M FREAKING OUT!!1!one");
    }
}
