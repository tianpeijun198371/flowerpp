/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: fengch
 * Date: 2008-1-22 9:34:49 
 */
package com.ulearning.ulms.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletRequest;

// Referenced classes of package com.jivesoftware.util:
//            MemoryDataSource

public class MultipartParamUtil
{

        protected static Log logger = LogFactory.getLog(MultipartParamUtil.class);
        private static MimetypesFileTypeMap typeMap = new MimetypesFileTypeMap();
        private Map parameters;

        public MultipartParamUtil(ServletRequest request, int maxRequestSize)
                throws IOException
        {
                parameters = new HashMap();
                String contentType = request.getContentType();
                if (contentType != null && contentType.startsWith("multipart/form-data"))
                {
                        try
                        {
                                Multipart multipart = new MimeMultipart(new MemoryDataSource(request.getInputStream(), contentType, maxRequestSize));
                                for (int i = 0; i < multipart.getCount(); i++)
                                {
                                        Part part = multipart.getBodyPart(i);
                                        Enumeration e = part.getAllHeaders();
                                        do
                                        {
                                                if (!e.hasMoreElements())
                                                {
                                                        break;
                                                }
                                                String paramName = ((Header) e.nextElement()).getValue();
                                                if (paramName.indexOf("filename=\"") > -1)
                                                {
                                                        int nStart = paramName.indexOf("name=\"") + "name=\"".length();
                                                        int nEnd = paramName.indexOf("\"", nStart);
                                                        int fnStart = paramName.indexOf("filename=\"") + "filename=\"".length();
                                                        int fnEnd = paramName.indexOf("\"", fnStart);
                                                        String name = paramName.substring(nStart, nEnd);
                                                        String filename = paramName.substring(fnStart, fnEnd);
                                                        parameters.put(name, filename);
                                                        parameters.put(name + "Data", part.getInputStream());
                                                        parameters.put(name + "Size", new Integer(part.getSize()));
                                                }
                                                else
                                                {
                                                        int nStart = paramName.indexOf("name=\"");
                                                        nStart += "name=\"".length();
                                                        int nEnd = paramName.lastIndexOf("\"");
                                                        if (nStart > -1 && nEnd > -1)
                                                        {
                                                                paramName = paramName.substring(nStart, nEnd);
                                                                if (paramName != null)
                                                                {
                                                                        Object value = part.getContent();
                                                                        if (value != null)
                                                                        {
                                                                                parameters.put(paramName, value.toString().trim());
                                                                        }
                                                                }
                                                        }
                                                }
                                        } while (true);
                                }

                        }
                        catch (MessagingException e)
                        {
                                logger.error(e);
                        }
                }
                String name;
                for (Enumeration e = request.getParameterNames(); e.hasMoreElements(); parameters.put(name, request.getParameter(name)))
                {
                        name = (String) e.nextElement();
                }

        }

        public Iterator getParameterNames()
        {
                return parameters.keySet().iterator();
        }

        public String getParameter(String name, boolean emptyStringOK)
        {
                if (name == null)
                {
                        return null;
                }
                String value = null;
                if (parameters.containsKey(name))
                {
                        value = parameters.get(name).toString();
                        if ("".equals(value) && !emptyStringOK)
                        {
                                value = null;
                        }
                }
                return value;
        }

        public String getParameter(String name)
        {
                return getParameter(name, false);
        }

        public int getIntParameter(String name, int defaultValue)
        {
                int value = defaultValue;
                String param = getParameter(name);
                if (param != null)
                {
                        try
                        {
                                value = Integer.parseInt(param);
                        }
                        catch (NumberFormatException ignored)
                        {
                        }
                }
                return value;
        }

        public long getLongParameter(String name, long defaultValue)
        {
                long value = defaultValue;
                String param = getParameter(name);
                if (param != null)
                {
                        try
                        {
                                value = Long.parseLong(param);
                        }
                        catch (NumberFormatException ignored)
                        {
                        }
                }
                return value;
        }

        public boolean getBooleanParameter(String name)
        {
                String param = getParameter(name);
                return param != null && ("true".equals(param) || "on".equals(param));
        }

        public InputStream getParameterData(String name)
        {
                if (name == null)
                {
                        return null;
                }
                Object obj = parameters.get(name + "Data");
                if (obj != null)
                {
                        return (InputStream) obj;
                }
                else
                {
                        return null;
                }
        }

        public int getParameterSize(String name)
        {
                if (name == null)
                {
                        return 0;
                }
                Object obj = parameters.get(name + "Size");
                if (obj != null)
                {
                        return ((Integer) obj).intValue();
                }
                else
                {
                        return 0;
                }
        }

        public String getParameterContentType(String name)
        {
                if (name == null)
                {
                        return null;
                }
                String fileName = (String) parameters.get(name);
                if (fileName == null)
                {
                        return null;
                }
                String contentType = typeMap.getContentType(fileName);
                if (contentType == null)
                {
                        return "application/octet-stream";
                }
                else
                {
                        return contentType;
                }
        }

}
