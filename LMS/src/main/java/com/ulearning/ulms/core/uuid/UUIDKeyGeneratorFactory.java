/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-10-30 11:45:51
 */

package com.ulearning.ulms.core.uuid;

import java.io.Serializable;

public class UUIDKeyGeneratorFactory
    implements Serializable
{

    public UUIDKeyGeneratorFactory()
    {
    }

    public  String getFactoryName()
    {
        return "UUIDKeyGeneratorFactory";
    }

    public  KeyGenerator getKeyGenerator()
        throws Exception
    {
        return new UUIDKeyGenerator();
    }
}