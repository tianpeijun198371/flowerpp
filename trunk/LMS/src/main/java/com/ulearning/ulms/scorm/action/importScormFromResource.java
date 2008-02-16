/**
 * ImportCourseAction.java.
 * User: fengch  Date: 2004-7-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.action;

import com.ulearning.ulms.content.dao.ContentManageDAO;
import com.ulearning.ulms.content.dao.ContentManageDAOFactory;
import com.ulearning.ulms.content.model.ContentModel;
import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.scorm.model.ScormModel;
import com.ulearning.ulms.scorm.util.ScormConstants;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.apache.struts.webapp.upload.UploadAction;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class importScormFromResource extends UploadAction
{
        protected static Log logger = LogFactory.getLog(ImportCourseAction.class);

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String sessionID = request.getSession().getId();
                String absoultPath = "";
                logger.info(
                        "##############request.getParameter(\"absoultPhysicsPath\") = " +
                                request.getParameter("absoultPhysicsPath"));

                if (request.getParameter("absoultPhysicsPath") == null)
                {
                        //request id
                        int contentID = NumberUtils.toInt(request.getParameter("contentID"));

                        //dao.get cm
                        ContentManageDAO contentManageDAO = ContentManageDAOFactory.getDAO();
                        ContentModel cm = contentManageDAO.getContent(contentID);

                        //absoultPath
                        String location = cm.getLocation();

                        if ((cm.getContentSize() != -1) && (location != null) &&
                                (location.length() > 1))
                        {
                                String path = location.substring(1);
                                absoultPath = Config.getUploadPhysicalPath() + path;
                                absoultPath = absoultPath.replace('/', File.separatorChar);
                                absoultPath = absoultPath.replace('\\', File.separatorChar);
                        }
                }
                else
                {
                        absoultPath = StringUtils.trimToEmpty(request.getParameter(
                                "absoultPhysicsPath"));
                }

                logger.info("absoultPath ================= " + absoultPath);

                //target
                String targetDir = ScormConstants.ImportRoot + "temp" + File.separator +
                        sessionID + File.separator;
                logger.info(
                        "targetDir =============================================== " +
                                targetDir);

                java.io.File targetFile = new java.io.File(targetDir);

                File sourceFile = new File(absoultPath);

                if (!sourceFile.exists())
                {
                        throw new ULMSAppException("目标文件(" + absoultPath + ")不存在");
                }

                String fileName = FilenameUtils.getName(absoultPath);
                FileUtils.copyFileToDirectory(sourceFile, targetFile);

                ScormModel sm = (ScormModel) form;
                request.setAttribute("ScormModel", sm);
                request.setAttribute("uploadDir", targetDir);
                request.setAttribute("uploadFile", targetDir + fileName);

                return mapping.findForward("success");
        }
}
