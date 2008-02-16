/**
 * UploadUtil.java.
 * User: fengch Date: 2005-4-28 16:35:25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import com.ulearning.ulms.content.dao.ContentManageDAO;
import com.ulearning.ulms.content.dao.ContentManageDAOFactory;
import com.ulearning.ulms.core.exceptions.MaxLengthExceededAppException;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.lang.RandomStringUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestWrapper;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UploadUtil
{
        private static ContentManageDAO contentManageDAO;

        static
        {
                try
                {
                        contentManageDAO = ContentManageDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * ???????而???.
         */
        public static final long MAX_ALLOWED_FILE_SIZE = StringUtil.parseLong(Config.get(
                "upload-max-allowed-file-size"));

        /**
         * ????而??>
         * ????????????
         * ?????
         * 1 ??????orm㊣???“??而??>
         * <html:file property="theFile" />
         * <html:file property="theFile1" />
         * <html:file property="theFile2" />
         * ......
         * 2 ??????orm㊣???“??而???﹞??(??﹞???:Config.getUploadPhysicalPath()+filePath): <br>
         * <INPUT TYPE="hidden" NAME="filePath" value="course\">
         * 3 ???????truct Action??“??ctionForm㊣????om.eduedu.elms.tools.upload.model.UploadForm.
         * <p/>
         * ???㊣?????equest???????:  <br>
         * 1 而?? <br>
         * request.getAttribute("size"))
         * request.getAttribute("size1"));
         * ......
         * 2 而?? <br>
         * request.getAttribute("newFilePath"))
         * request.getAttribute("newFilePath1"));
         * ......
         *
         * @param form
         * @param request
         * @param response
         * @throws Exception
         */
        public static void executeUpload(ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                if (form instanceof UploadForm)
                {
                        System.out.println(
                                "[UploadUtil]---################### (request instanceof MultipartRequestWrapper) = " +
                                        (request instanceof MultipartRequestWrapper));

                        //this line is here for when the input page is upload-utf8.jsp,
                        //it sets the correct character encoding for the response
                        String encoding = request.getCharacterEncoding();

                        if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8")))
                        {
                                response.setContentType("text/html; charset=utf-8");
                        }

                        UploadForm theForm = (UploadForm) form;

                        //judge path isExist.if not then create it
                        String uploadPath = theForm.getFilePath();
                        uploadPath = StringUtil.replaceString(uploadPath, "\\",
                                File.separator);
                        uploadPath = StringUtil.replaceString(uploadPath, "/",
                                File.separator);
                        //uploadPath = StringUtil.replaceString(uploadPath, "/\\", File.separator);

                        upload(request, theForm.getTheFile(), uploadPath, "");
                        upload(request, theForm.getTheFile1(), uploadPath, "1");
                        upload(request, theForm.getTheFile2(), uploadPath, "2");
                        upload(request, theForm.getTheFile3(), uploadPath, "3");
                        upload(request, theForm.getTheFile4(), uploadPath, "4");
                        upload(request, theForm.getTheFile5(), uploadPath, "5");
                        upload(request, theForm.getTheFile6(), uploadPath, "6");
                        upload(request, theForm.getTheFile7(), uploadPath, "7");
                        upload(request, theForm.getTheFile8(), uploadPath, "8");
                        upload(request, theForm.getTheFile9(), uploadPath, "9");
                }
        }

        /**
         * ??而??       *
         *
         * @param request
         * @param file
         * @param uploadPath
         * @param number
         * @throws Exception
         */
        private static void upload(HttpServletRequest request, FormFile file,
                                   String uploadPath, String number) throws Exception
        {
                int size = 0;

                if (file != null)
                {
                        size = file.getFileSize();
                }


                if ((file == null) || (size == 0))
                {
                        request.setAttribute("size" + number, "0");
                        return;
                }

                //retrieve the content type
                String contentType = file.getContentType();

                String fileName = String.valueOf(file.getFileName());

                String newFileName = UploadUtil.getAvailableFileName(fileName);
                String newFilePath = null;

                newFilePath = Config.getUploadPhysicalPath() + uploadPath +
                        newFileName;
                newFilePath = UploadUtil.getAvailableAbsoluteFilePath(newFilePath);
                newFilePath = StringUtil.replaceString(newFilePath, "//", "/");


                if (size > MAX_ALLOWED_FILE_SIZE)
                {
                        MaxLengthExceededAppException maxLengthExceededAppException = new MaxLengthExceededAppException(
                                "d:" + FormatUtil.formatSize(MAX_ALLOWED_FILE_SIZE));
                        maxLengthExceededAppException.setErrorKey(
                                "error.upload.maxlength_exceeded");
                        throw maxLengthExceededAppException;
                }

                if (size != 0)
                {
                        String dir = "";
                        dir = Config.getUploadPhysicalPath() + uploadPath;
                        IOUtil.mkDir(dir);

                        //retrieve the file size
                        InputStream stream = null;
                        OutputStream bos = null;

                        try
                        {
                                //retrieve the file data
                                stream = file.getInputStream();
                                System.out.println("stream.available() ===== " +
                                        stream.available());
                                //write the file to the file specified
                                bos = new FileOutputStream(newFilePath);

                                int bytesRead = 0;
                                byte[] buffer = new byte[8192];
                                int i = 0;

                                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
                                {
                                        bos.write(buffer, 0, bytesRead);
                                }

                                String data = "The file has been written to \"" + newFilePath;

                                //close the stream
                        }
                        catch (FileNotFoundException fnfe)
                        {
                                LogUtil.error("system",
                                        "[UploadUtil]upload===================" + fnfe);
                                fnfe.printStackTrace();
                        }
                        catch (IOException ioe)
                        {
                                LogUtil.error("system",
                                        "[UploadUtil]upload===================" + ioe);
                                ioe.printStackTrace();
                        }
                        catch (Exception ex)
                        {
                                LogUtil.error("system",
                                        "[UploadUtil]upload===================" + ex);
                                ex.printStackTrace();
                        }
                        finally
                        {
                                try
                                {
                                        bos.close();
                                }
                                catch (Exception ex)
                                {
                                }

                                try
                                {
                                        stream.close();
                                }
                                catch (Exception ex)
                                {
                                }
                        }
                }

                System.out.println("[UploadUtil]upload----size=" + size);

                newFileName = IOUtil.getFileName(newFilePath);

                request.setAttribute("contentType" + number, contentType);
                request.setAttribute("size" + number, String.valueOf(size));
                request.setAttribute("newFileName" + number, newFileName);
                request.setAttribute("newFilePath" + number, (uploadPath + newFileName));

                //destroy the temporary file created
                file.destroy();
        }

        /**
         * ﹞?????????????????
         * ??????",?????褁???????dda.txt""
         *
         * @param fileName
         * @return
         */
        public static String getAvailableFileName(String fileName)
        {
                StringBuffer newAbsoluteFilePath_buffer = new StringBuffer();
                System.out.println("[UploadUtil]getNewFileName fileName=" + fileName);

                if (fileName == null)
                {
                        return "unknow";
                }

                for (int i = 0; i < fileName.length(); i++)
                {
                        char c = fileName.charAt(i);

                        //System.out.println("getNewFileName c=" + c);
                        if (((int) c) > 125)
                        {
                                if (i != 0)
                                {
                                        newAbsoluteFilePath_buffer.append("_");
                                }

                                newAbsoluteFilePath_buffer.append(RandomStringUtils.randomAlphabetic(
                                        4));
                                newAbsoluteFilePath_buffer.append(IOUtil.getFileExtName(
                                        fileName));

                                break;
                        }
                        else
                        {
                                newAbsoluteFilePath_buffer.append(c);
                        }
                }

                System.out.println(
                        "[UploadUtil]getNewFileName newAbsoluteFilePath_buffer=" +
                                newAbsoluteFilePath_buffer);

                return newAbsoluteFilePath_buffer.toString();
        }

        /**
         * ﹞??????????褁?????
         * ????\a.txt",?????褁???????d:\a_1.txt"
         *
         * @return
         */
        public static String getAvailableAbsoluteFilePath(String absoluteFilePath)
        {
                String newAbsoluteFilePath = absoluteFilePath;
                System.out.println(
                        "[UploadUtil]getAvailableAbsoluteFilePath--------absoluteFilePath = " +
                                absoluteFilePath);

                String fileExt = "";
                String fileName = null;
                String filePath = null;

                if (IOUtil.isExistFile(newAbsoluteFilePath))
                {
                        int dotBeginIndex = absoluteFilePath.lastIndexOf('.');
                        int separatorBeginIndex = absoluteFilePath.lastIndexOf(File.separator);

                        if (dotBeginIndex != -1)
                        {
                                fileExt = absoluteFilePath.substring(dotBeginIndex);
                                fileName = absoluteFilePath.substring(separatorBeginIndex + 1,
                                        dotBeginIndex);
                        }
                        else
                        {
                                fileName = absoluteFilePath.substring(separatorBeginIndex + 1);
                        }

                        filePath = absoluteFilePath.substring(0, separatorBeginIndex + 1);

                        int i = 0;

                        do
                        {
                                i++;
                                newAbsoluteFilePath = filePath + fileName + "_" + i + fileExt;
                        } while (IOUtil.isExistFile(newAbsoluteFilePath));
                }


                return newAbsoluteFilePath;
        }
}
