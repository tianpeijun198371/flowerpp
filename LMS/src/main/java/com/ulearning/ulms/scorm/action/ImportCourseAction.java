/**
 * ImportCourseAction.java.
 * User: fengch  Date: 2004-7-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.scorm.model.ScormModel;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.webapp.upload.UploadAction;
import org.apache.struts.webapp.upload.UploadForm;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImportCourseAction extends UploadAction
{
        protected static Log logger = LogFactory.getLog(ImportCourseAction.class);

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                executeUpload(mapping, form, request, response);

                //this shouldn't happen in this example
                return mapping.findForward("success");
        }

        private void executeUpload(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                if (form instanceof UploadForm)
                {
                        //this line is here for when the input page is upload-utf8.jsp,
                        //it sets the correct character encoding for the response
                        String encoding = request.getCharacterEncoding();

                        if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8")))
                        {
                                response.setContentType("text/html; charset=utf-8");
                        }

                        UploadForm theForm = (UploadForm) form;

                        //retrieve the text data
                        String text = theForm.getTheText();

                        //retrieve the query string value
                        String queryValue = theForm.getQueryParam();

                        //retrieve the file representation
                        FormFile file = theForm.getTheFile();

                        //retrieve the file name
                        String fileName = file.getFileName();

                        //retrieve the content type
                        String contentType = file.getContentType();

                        //retrieve the file size
                        String size = (file.getFileSize() + " bytes");

                        String data = null;

                        logger.info("[ImportCourseAction]executeUpload--text0=" + text);
                        logger.info("[ImportCourseAction]executeUpload--queryValue0=" +
                                queryValue);
                        logger.info("[ImportCourseAction]executeUpload--fileName0=" +
                                fileName);
                        logger.info("[ImportCourseAction]executeUpload--contentType0=" +
                                contentType);
                        logger.info("[ImportCourseAction]executeUpload--size0=" + size);
                        logger.info(
                                "[ImportCourseAction]executeUpload--theForm.getFilePath()0=" +
                                        theForm.getFilePath());

                        String sessionID = request.getSession().getId();

                        String uploadDir = ScormConstants.ImportRoot + "temp" +
                                File.separator + sessionID + File.separator;
                        java.io.File theUploadDir = new java.io.File(uploadDir);

                        //  The course directory should not exist yet
                        if (!theUploadDir.isDirectory())
                        {
                                theUploadDir.mkdirs();
                        }

                        try
                        {
                                //retrieve the file data
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                InputStream stream = file.getInputStream();

                                //write the file to the file specified
                                OutputStream bos = new FileOutputStream(uploadDir + fileName);
                                int bytesRead = 0;
                                byte[] buffer = new byte[8192];

                                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
                                {
                                        bos.write(buffer, 0, bytesRead);
                                }

                                bos.close();
                                data = "The file has been written to \"" + uploadDir + "\"";

                                //close the stream
                                stream.close();
                        }
                        catch (FileNotFoundException fnfe)
                        {
                                fnfe.printStackTrace();

                                return;
                        }
                        catch (IOException ioe)
                        {
                                ioe.printStackTrace();

                                return;
                        }

                        ScormModel sm = (ScormModel) form;
                        //place the data into the request for retrieval from display.jsp
                        request.setAttribute("ScormModel", sm);

                        request.setAttribute("text", text);
                        request.setAttribute("uploadDir", uploadDir);
                        request.setAttribute("uploadFile", uploadDir + fileName);
                        request.setAttribute("fileName", fileName);
                        request.setAttribute("contentType", contentType);
                        request.setAttribute("size", size);
                        request.setAttribute("data", data);

                        logger.info("[ImportCourseAction]executeUpload--sm.getName=" +
                                sm.getName());
                        logger.info("[ImportCourseAction]executeUpload--sm.getRelationID=" +
                                sm.getRelationID());

                        logger.info("[ImportCourseAction]executeUpload--text=" + text);
                        logger.info("[ImportCourseAction]executeUpload--queryValue=" +
                                queryValue);
                        logger.info("[ImportCourseAction]executeUpload--fileName=" +
                                fileName);
                        logger.info("[ImportCourseAction]executeUpload--uploadFile=" +
                                (uploadDir + fileName));
                        logger.info("[ImportCourseAction]executeUpload--contentType=" +
                                contentType);
                        logger.info("[ImportCourseAction]executeUpload--size=" + size);
                        logger.info("[ImportCourseAction]executeUpload--data=" + data);
                        logger.info(
                                "[ImportCourseAction]executeUpload--theForm.getFilePath()=" +
                                        theForm.getFilePath());

                        //destroy the temporary file created
                        file.destroy();
                }
        }
}
