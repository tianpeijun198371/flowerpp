/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-11-3 15:09:52 
 */

package com.ulearning.ulms.tools.study.info.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.organ.exceptions.OrganSysException;
import com.ulearning.ulms.tools.study.info.bean.StudyInfoHelper;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAO;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOFactory;
import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoSysException;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.tools.study.info.util.StudyInfoDbImport;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Date;

public class BatchImportStudyRecordAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {       System.out.println("=================================here4");
                String resultScreen = "success";
               // BatchInputUploadForm biuf = (BatchInputUploadForm) form;
                int orgID = StringUtil.parseInt((String) request.getSession().getAttribute(LMSConstants.USER_ORGID_KEY));
                int userID = Integer.parseInt((String) request.getSession().getAttribute(LMSConstants.USERID_KEY));
                StudyInfoForm stf=(StudyInfoForm)form; 
                 if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                //UploadForm uploadForm = biuf.getUploadForm();
                                //executeUpload(mapping, uploadForm, request, response);
                                UploadUtil.executeUpload(stf,
                                        request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("user",
                                        "[AddUserInBatchAction] Exeception====================" +
                                                e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                String fileName = "";
                                String file_path = "";
                                String rootPath = Config.getUploadPhysicalPath();
                                //StudyInfoHelper studyInfoHelper = new StudyInfoHelper();
                                List studyInfoList = null;

                                //get absolutely path of file that want to input
                                file_path = stf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");
                                file_path = rootPath.trim() + file_path.trim() + fileName.trim();
                                System.out.println("file_path = " + file_path);
                                System.out.println("fileName = " + fileName);

                                //check file want to input
                                String returnString = "";
                                StudyInfoDbImport studyInfoDbImport = new StudyInfoDbImport();
                                try
                                {
                                returnString = studyInfoDbImport.checkExcel(file_path.trim());
                                }
                                catch (StudyInfoSysException e)
                                {
                                        e.printStackTrace();
                                }

                                //insert into db
                                if (returnString.equals(""))
                                {
                                        StudyInfoDAO dao = StudyInfoDAOFactory.getDAO();
                                        try
                                        {
                                                studyInfoList = studyInfoDbImport.insertIntoDb(file_path);
                                                for (int i = 0; i < studyInfoList.size(); i++)
                                                {
                                                        StudyInfoForm studyInfoForm = (StudyInfoForm) studyInfoList.get(i);
                                                        studyInfoForm.setOrgID(orgID);
                                                         studyInfoForm.setUserID(userID);
                                                        studyInfoForm.setCreateDate(new Date());
                                                         dao.addStudyInfo(studyInfoForm);
                                                         LogUtil.debug("StudyInfoForm",
                                                                "[BatchImportOrganAction]===========add a organ");
                                                }
                                        }
                                        catch (OrganSysException e)
                                        {
                                                e.printStackTrace();
                                        }

                                        resultScreen = "success";
                                }
                                else //forward to check error  page
                                {
                                        request.setAttribute("CheckInfo", returnString);
                                        resultScreen = "batch.Input.check.error";
                                }
                        }
                }
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);
                return mapping.findForward(resultScreen);
             }
}
