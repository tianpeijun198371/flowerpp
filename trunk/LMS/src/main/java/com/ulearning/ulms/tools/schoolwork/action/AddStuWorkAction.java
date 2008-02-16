/**
 * AddStuWorkAction.java.
 * User: Administrator  Date: 2005-4-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.tools.schoolwork.bean.StuWorkHelper;
import com.ulearning.ulms.tools.schoolwork.form.StuWorkForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddStuWorkAction extends DispatchAction
{

        public AddStuWorkAction()
        {

        }

        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse)
                throws Exception
        {
                StuWorkForm stuWorkForm = (StuWorkForm) actionForm;

                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadAction uploadAction = new UploadAction();
                                UploadForm uploadForm = stuWorkForm.getUploadForm();
                                uploadAction.executeUpload(actionMapping, uploadForm,
                                        httpServletRequest, httpServletResponse);

                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();

                        }
                        LogUtil.info("course", "222222222222222");

                        if (!String.valueOf(httpServletRequest.getAttribute("size")).equals("0"))
                        {
                                stuWorkForm.setLink((String) httpServletRequest.getAttribute("newFilePath"));
                        }
                        else if (ValidateUtil.isEmpty((String) (httpServletRequest.getAttribute("fileName"))))
                        {
                                stuWorkForm.setLink("");
                        }
                        else
                        {
                                stuWorkForm.setLink("");
                        }
                }

                StuWorkHelper.insertStuWork(stuWorkForm);

                //update user's point,add by yud
                //EvaluateManageDAO evaluateManageDAO = new EvaluateManageDAOImpl();
                //List l = evaluateManageDAO.getPointConversion();
                //int initialPoint = ((ERecordPointConversionModel) l.get(3)).getPoint();//数据库中已经初始化的值

                //List list = evaluateManageDAO.isThisUserInERecord(StringUtil.parseInt(stuWorkForm.getUserId())); //判断是否已经有该用户的经验值数据

//                if (StringUtil.parseInt(stuWorkForm.getUserId()) > 0)
//                {
//                        ERecordForm eRecordForm = new ERecordForm();
//                        eRecordForm.setUserID(StringUtil.parseInt(stuWorkForm.getUserId()));
//                        if (list.size() != 0)
//                        {
//                                int oldPoint = ((ERecordModel) list.get(0)).getPoint();  //用户已有经验值
//                                eRecordForm.setPoint(oldPoint + initialPoint);
//                        }
//                        else
//                        {
//                                eRecordForm.setPoint(initialPoint);
//                        }
//                        EvaluateManageHelper.setPointERecord(eRecordForm);
//                }
                //end

                MultipartParamUtils mp = new MultipartParamUtils(httpServletRequest, 0x9e7000);
                httpServletRequest.setAttribute("mp", mp);
                ActionForward inforward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?documentIDs=" + stuWorkForm.getSwId());
                return new ActionForward(bf.toString(), true);
        }

}


