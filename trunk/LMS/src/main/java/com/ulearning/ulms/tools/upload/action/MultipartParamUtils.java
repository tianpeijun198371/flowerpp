/** * MultipartParamUtil.java.
 * User: xiejh  Date: 2004-7-1 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.tools.upload.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletRequest;
import java.io.*;
import javax.activation.DataSource;

/**
 * This class retrieves web page parameters from a multipart/form-data encoded
 * HTML form and stores them as key/values pairs. It also stores any parameters
 * found in the ServletRequest object (non-multipart/form-data parameters).
 *
 * @see
 */
public class MultipartParamUtils
{
        private Map parameters;

        public MultipartParamUtils(ServletRequest request, int maxRequestSize)
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
                                        for (Enumeration enum_ = part.getAllHeaders(); enum_.hasMoreElements();)
                                        {
                                                String paramName = ((Header) enum_.nextElement()).getValue();
//                    //---------------------------------------------------
//                    //编码转换，解决中文文件名问题
//                    paramName = StringUtils.IsoToGBK(paramName);
//                    //---------------------------------------------------
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
                                                        parameters.put(name + "ContentType", part.getContentType());
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
                                        }

                                }

                        }
                        catch (MessagingException e)
                        {
                                //Log.error(e);
                                e.printStackTrace();
                        }
                }
                String name;
                for (Enumeration enum_ = request.getParameterNames();
                     enum_.hasMoreElements();
                     parameters.put(name, request.getParameter(name)))
                {
                        name = (String) enum_.nextElement();
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
                Object obj = parameters.get(name + "ContentType");
                if (obj != null)
                {
                        return (String) obj;
                }
                else
                {
                        return "text/plain";
                }
        }
}

class MemoryDataSource implements DataSource
{
        private String contentType;
        private ByteArrayOutputStream buf;

        public MemoryDataSource(InputStream in, String contentType, int maxRequestSize)
        {
                this.contentType = contentType;
                buf = new ByteArrayOutputStream();
                try
                {
                        int data = -1;
                        int size = 0;
                        while ((data = in.read()) > -1)
                        {
                                buf.write(data);
                                if (++size > maxRequestSize)
                                {
                                        break;
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        //Log.error(e);
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