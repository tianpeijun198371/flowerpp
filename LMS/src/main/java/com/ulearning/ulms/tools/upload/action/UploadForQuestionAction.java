/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.tools.upload.action;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.upload.model.UploadForQuestionForm;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.FormatUtil;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.List;
import java.io.*;

/**
 * @author <a href="mailto:youmail@yourdomain.com">zhangy</a> Date: 2005-4-7
 */
public class UploadForQuestionAction extends Action
{
        public void executeUpload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
                throws Exception
        {

                MultipartParamUtils mp = new MultipartParamUtils(request, 1024 * 1014 * 10);
                if (form instanceof UploadForQuestionForm)
                {
                        //this line is here for when the input page is upload-utf8.jsp,
                        //it sets the correct character encoding for the response
                        String encoding = request.getCharacterEncoding();
                        if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8")))
                        {
                                response.setContentType("text/html; charset=utf-8");
                        }

                        UploadForQuestionForm theForm = (UploadForQuestionForm) form;

                        //retrieve the file representation
                        FormFile file = theForm.getTheFile();
                        FormFile fileTwo = theForm.getTheFileTwo();
                        String size = (file.getFileSize() + "");
                        String sizeTwo = (fileTwo.getFileSize() + "");

                        //retrieve the file name
                        //String fileName= file.getFileName();

                        //retrieve the content type
                        String contentType = file.getContentType();
                        String contentTypeTwo = fileTwo.getContentType();
                        String fileExtendName = theForm.getFileExtendName();
                        String fileExtendNameTwo = theForm.getFileExtendNameTwo();
                        if (fileExtendNameTwo == null || String.valueOf(fileExtendNameTwo).equals("null"))
                        {
                                fileExtendNameTwo = "";
                        }
                        if (fileExtendName == null || String.valueOf(fileExtendName).equals("null"))
                        {
                                fileExtendName = "";
                        }

                        String newFileNameTwo = String.valueOf(theForm.getNewFileNameTwo());
                        String newFileName = String.valueOf(theForm.getNewFileName());

                        if (newFileNameTwo.equals("null") || newFileNameTwo.equals(""))
                        {
                                newFileNameTwo = "";
                        }
                        if (newFileName.equals("null") || newFileName.equals(""))
                        {
                                newFileName = StringUtil.randomStr(12);
                        }
                        if (newFileNameTwo.indexOf(".") < 0 && !fileExtendNameTwo.equals(""))
                        {
                                newFileNameTwo = newFileNameTwo + "." + fileExtendNameTwo;
                        }
                        if (newFileName.indexOf(".") < 0 && !fileExtendName.equals(""))
                        {
                                newFileName = newFileName + "." + fileExtendName;
                        }

                        //judge path isExist.if not then create it
                        String uploadPathTwo = theForm.getFilePathTwo();
                        String uploadPath = theForm.getFilePath();//request.getParameter("filePath");
                        uploadPathTwo = FormatUtil.replaceString(uploadPathTwo, "/", "\\");
                        uploadPath = FormatUtil.replaceString(uploadPath, "/", "\\");
                        List fileListTwo = StringUtil.split(uploadPathTwo + newFileNameTwo, "\\");
                        List fileList = StringUtil.split(uploadPath + newFileName, "\\");
                        if (!sizeTwo.equals("0"))
                        {
                                if (fileListTwo != null && fileListTwo.size() > 1)
                                {
                                        String fTwo = Config.getUploadPhysicalPath();
                                        for (int k = 0; k < fileListTwo.size() - 1; k++)
                                        {
                                                fTwo = fTwo + "\\" + (String) fileListTwo.get(k);
                                                File fpTwo = new File(fTwo);
                                                if (!fpTwo.exists())
                                                {
                                                        fpTwo.mkdirs();
                                                }
                                        }
                                }
                        }
                        if (!size.equals("0"))
                        {
                                if (fileList != null && fileList.size() > 1)
                                {
                                        String f = Config.getUploadPhysicalPath();
                                        for (int k = 0; k < fileList.size() - 1; k++)
                                        {
                                                f = f + "\\" + (String) fileList.get(k);
                                                File fp = new File(f);
                                                if (!fp.exists())
                                                {
                                                        fp.mkdirs();
                                                }
                                        }
                                }

                                String newFilePathTwo = Config.getUploadPhysicalPath() + theForm.getFilePathTwo() + newFileNameTwo;
                                String newFilePath = Config.getUploadPhysicalPath()
                                        + theForm.getFilePath()
                                        + newFileName;

                                //retrieve the file size


                                String data = null;
                                String dataTwo = null;

                                try
                                {
                                        //retrieve the file data
                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        InputStream stream = file.getInputStream();
                                        InputStream streamTwo = fileTwo.getInputStream();
                                        //write the file to the file specified
                                        OutputStream bos = new FileOutputStream(newFilePath);
                                        OutputStream bosTwo = new FileOutputStream(newFilePathTwo);
                                        int bytesRead = 0;
                                        int bytesReadTwo = 0;
                                        byte[] buffer = new byte[8192];
                                        byte[] bufferTwo = new byte[8192];
                                        while ((bytesReadTwo = streamTwo.read(bufferTwo, 0, 8192)) != -1)
                                        {
                                                bosTwo.write(bufferTwo, 0, bytesReadTwo);
                                        }
                                        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
                                        {
                                                bos.write(buffer, 0, bytesRead);
                                        }
                                        bosTwo.close();
                                        bos.close();
                                        dataTwo = "The file has been written to \"" + theForm.getFilePath() + "\"";
                                        data = "The file has been written to \"" + theForm.getFilePath() + "\"";

                                        //close the stream
                                        streamTwo.close();
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
                        request.setAttribute("fileExtendNameTwo", fileExtendNameTwo);
                        request.setAttribute("contentTypeTwo", contentTypeTwo);
                        request.setAttribute("sizeTwo", sizeTwo);
                        request.setAttribute("newFileNameTwo", newFileNameTwo);
                        //place the data into the request for retrieval from display.jsp
                        //request.setAttribute("text", text);
                        //request.setAttribute("queryValue", queryValue);
                        request.setAttribute("fileExtendName", fileExtendName);
                        request.setAttribute("contentType", contentType);
                        request.setAttribute("size", size);
                        //request.setAttribute("data", data);
                        request.setAttribute("newFileName", newFileName);
                        request.setAttribute("mp", mp);
                        //destroy the temporary file created
                        fileTwo.destroy();
                        file.destroy();

                        //return a forward to display.jsp
                        //return mapping.findForward("display");
                }
        }
}
