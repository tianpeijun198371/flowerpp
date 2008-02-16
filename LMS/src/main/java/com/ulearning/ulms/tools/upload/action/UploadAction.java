/** * UploadAction.java. 
 * User: xiejh  Date: 2004-6-22 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.tools.upload.action;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestWrapper;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.FormatUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * This class takes the UploadForm and retrieves the text value
 * and file attributes and puts them in the request for the display.jsp
 * page to display them
 *
 * @author Mike Schachter
 * @version $Revision: 1.8 $ $Date: 2003/02/28 02:18:23 $
 */


public class UploadAction extends Action
{
        public synchronized void executeUpload(ActionMapping mapping,
                                               ActionForm form,
                                               HttpServletRequest request,
                                               HttpServletResponse response)
                throws Exception
        {
                if (form instanceof UploadForm)
                {
                        System.out.println("[UploadAction]---################### (request instanceof MultipartRequestWrapper) = " + (request instanceof MultipartRequestWrapper));
                        String encoding = request.getCharacterEncoding();
                        if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8")))
                        {
                                response.setContentType("text/html; charset=utf-8");
                        }

                        UploadForm theForm = (UploadForm) form;

                        //retrieve the file representation
                        FormFile file = theForm.getTheFile();
                        String size = (file.getFileSize() + "");

                        //retrieve the file name
                        //String fileName= file.getFileName();

                        //retrieve the content type
                        String contentType = file.getContentType();
                        String fileExtendName = theForm.getFileExtendName();
                        if (fileExtendName == null || String.valueOf(fileExtendName).equals("null"))
                        {
                                fileExtendName = "";
                        }

                        String newFileName = String.valueOf(theForm.getNewFileName());
                        //String newFileName = String.valueOf(theForm.getFileExtendName())
                        //判断是否由字母
                        if (!isValidName(String.valueOf(file)))
                        {
                                if (newFileName.equals("null") || newFileName.equals(""))
                                {
                                        newFileName = StringUtil.randomStr(12);
                                }
                        }
                        else
                        {
                                newFileName = String.valueOf(file);
                        }
                        if (newFileName.indexOf(".") < 0 && !fileExtendName.equals(""))
                        {
                                newFileName = newFileName + "." + fileExtendName;
                        }

                        //judge path isExist.if not then create it
                        String uploadPath = theForm.getFilePath();//request.getParameter("filePath");
                        uploadPath = FormatUtil.replaceString(uploadPath, "/", "\\");
                        List fileList = StringUtil.split(uploadPath + newFileName, "\\");
                        if (!size.equals("0"))
                        {
                                if (fileList != null && fileList.size() > 1)
                                {
                                        String f = Config.getUploadPhysicalPath();
                                        for (int k = 0; k < fileList.size() - 1; k++)
                                        {
                                                f = f + File.separator + (String) fileList.get(k);
                                                File fp = new File(f);
                                                if (!fp.exists())
                                                {
                                                        fp.mkdirs();
                                                }
                                        }
                                }

                                String newFilePath = Config.getUploadPhysicalPath()
                                        + theForm.getFilePath()
                                        + newFileName;

                                //retrieve the file size


                                String data = null;

                                try
                                {
                                        //retrieve the file data
                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        InputStream stream = file.getInputStream();
                                        //write the file to the file specified
                                        OutputStream bos = new FileOutputStream(newFilePath);
                                        int bytesRead = 0;
                                        byte[] buffer = new byte[8192];
                                        int con = 0;
                                        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
                                        {
                                                bos.write(buffer, 0, bytesRead);
                                                con++;
                                        }
                                        //LogUtil.info("yangds","[UploadAction]============buffersize="+con);
                                        bos.close();
                                        data = "The file has been written to \"" + theForm.getFilePath() + "\"";
                                        //close the stream
                                        stream.close();
                                }
                                catch (FileNotFoundException fnfe)
                                {
                                        LogUtil.debug("system", "[UploadAction]====================" + fnfe);
                                }
                                catch (IOException ioe)
                                {
                                        LogUtil.debug("system", "[UploadAction]====================" + ioe);
                                }
                        }
                        //place the data into the request for retrieval from display.jsp
                        //request.setAttribute("text", text);
                        //request.setAttribute("queryValue", queryValue);
                        request.setAttribute("fileExtendName", fileExtendName);
                        request.setAttribute("contentType", contentType);
                        request.setAttribute("size", size);
                        //request.setAttribute("data", data);
                        request.setAttribute("newFileName", newFileName);
                        request.setAttribute("newFilePath", theForm.getFilePath() + newFileName);
                        //destroy the temporary file created
                        file.destroy();

                        //return a forward to display.jsp
                        //return mapping.findForward("display");
                }
        }

        private static boolean isValidName(String name)
        {
                if (name == null)
                {
                        return false;
                }
                // 定义两个正则表达式
                String regex1 = "[a-zA-Z_0-9][a-zA-Z_0-9._% ]*[a-zA-Z0-9]";
                String regex2 = "[a-zA-Z]";
                Pattern p = null;
                Matcher m = null;
                if (name.length() > 1)
                {
                        // 串的长度大于1时用regex1去匹配
                        p = Pattern.compile(regex1);
                        m = p.matcher(name);
                        return m.matches();
                }
                else if (name.length() == 1)
                {
                        // 串的长度为1时用regex2去匹配
                        p = Pattern.compile(regex2);
                        m = p.matcher(name);
                        return m.matches();
                }
                return false;
        }
}
