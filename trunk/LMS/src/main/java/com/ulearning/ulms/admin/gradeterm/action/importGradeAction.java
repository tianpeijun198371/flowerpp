/**
 * importGradeAction.java.
 * User: huangsb Date: 2006-3-23 18:27:24
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.action;

import com.ulearning.ulms.admin.gradeterm.form.GradeTermForm;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.user.util.DBIput;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class importGradeAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                LogUtil.info("huangsb", "ImportQuestionAction===========start");

                String resultScreen = "success";
                GradeTermForm bf = (GradeTermForm) form;

                LogUtil.info("huangsb",
                        "ImportGradeAction===========courseID=" + bf.getGradetID());

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        //upload file to system
                        try
                        {
                                UploadForm uf = bf.getUploadForm();
                                executeUpload(mapping, uf, request, response);
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

                                file_path = bf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");

                                file_path = rootPath + file_path + fileName;
                                LogUtil.info("huangsb",
                                        "ImportQuestionAction===========file_path=" + file_path);

                                //check file of want to input
                                String returnString = "";
                                DBIput iqu = new DBIput();
                                returnString = ""; //iqu.checkExcel(file_path);

                                if (returnString.equals(""))
                                {
                                        try
                                        {
                                                iqu.insertIntoDb(file_path);
                                        }
                                        catch (Exception e)
                                        {
                                                e.printStackTrace();
                                        }

                                        resultScreen = "success";
                                }
                                else
                                {
                                        //request.setAttribute("CheckInfo",returnString);
                                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                                "导入错误，请重新导入！");

                                        return mapping.findForward(LMSConstants.ERROR_FORWARD);

                                        //resultScreen = "batch.Input.check.error";
                                }
                        }
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
