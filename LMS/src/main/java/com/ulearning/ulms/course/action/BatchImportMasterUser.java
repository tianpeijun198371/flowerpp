/** * BatchImportMasterUser.java.
 * User: chenxj
 * Date: 2004-11-18
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.EJBUtil;
import com.ulearning.ulms.course.util.BatchImportMasterUserUtil;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.BatchInputUploadForm;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.user.ejb.User;
import com.ulearning.ulms.user.ejb.UserHome;
import com.ulearning.ulms.user.exceptions.UserSystemException;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.util.UserDbInput;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BatchImportMasterUser extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                BatchInputUploadForm biuf = (BatchInputUploadForm) form;

                System.out.println(
                        "biuf.getType() ======================================= " +
                                biuf.getType());

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        //upload file to system
                        try
                        {
                                UploadForm uploadForm = biuf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
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
                                BatchImportMasterUserUtil batchImportMasterUserUtil = new BatchImportMasterUserUtil();
                                List userFormList = null;
                                UserForm uf = null;

                                //get absolutely path of file want to input
                                file_path = biuf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");

                                file_path = rootPath + file_path + fileName;

                                //check file of want to input
                                String returnString = "";

                                try
                                {
                                        returnString = batchImportMasterUserUtil.checkExcel(file_path,
                                                Integer.parseInt(biuf.getType()), request);
                                }
                                catch (UserSystemException e)
                                {
                                        e.printStackTrace();
                                }

                                if (returnString.equals(""))
                                {
                                        //UserHome home = EJBUtil.getUserHome();
                                        //User user = home.create();
                                        //insert user info from Execel file
                                        try
                                        {
                                                batchImportMasterUserUtil.insertIntoDb(file_path,
                                                        Integer.parseInt(biuf.getType()), request);
                                        }
                                        catch (Exception e)
                                        {
                                                e.printStackTrace();
                                        }

                                        resultScreen = "success";
                                }
                                else
                                {
                                        request.setAttribute("CheckInfo", returnString);
                                        resultScreen = "batch.Input.check.error";
                                }
                        }
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);
                request.setAttribute("orgID", biuf.getOrgID());

                return mapping.findForward(resultScreen);
        }
}
