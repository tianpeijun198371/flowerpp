/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: fengch
 * Date: 2008-1-22 9:36:26 
 */
package com.ulearning.ulms.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import javax.activation.DataSource;

class MemoryDataSource
    implements DataSource
{
    protected static Log logger = LogFactory.getLog(MemoryDataSource.class);
    private String contentType;
    private ByteArrayOutputStream buf;

    public MemoryDataSource(InputStream in, String contentType, int maxRequestSize)
    {
        this.contentType = contentType;
        buf = new ByteArrayOutputStream();
        try
        {
            int size = 0;
            int data = -1;
            do
            {
                if((data = in.read()) <= -1)
                    break;
                buf.write(data);
            } while(++size <= maxRequestSize);
        }
        catch(Exception e)
        {
            logger.error(e);
        }
    }

    public String getContentType()
    {
        return contentType;
    }

    public String getName()
    {
        return "";
    }

    public InputStream getInputStream()
        throws IOException
    {
        return new ByteArrayInputStream(buf.toByteArray());
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        throw new UnsupportedOperationException();
    }
}