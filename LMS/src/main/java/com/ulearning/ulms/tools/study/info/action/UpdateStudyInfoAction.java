/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-24 9:23:53 
 */

package com.ulearning.ulms.tools.study.info.action;

import com.ulearning.ulms.tools.study.info.bean.StudyInfoHelper;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAO;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOFactory;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateStudyInfoAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int type = Integer.parseInt(request.getParameter("type"));
                if ( type == 2){
                        resultScreen = "recordsuccess";
                }
                StudyInfoForm StudyInfo = (StudyInfoForm) form;

                StudyInfoDAO dao = StudyInfoDAOFactory.getDAO();
                StudyInfoForm oldForm = dao.getStudyInfo(StudyInfo.getStudyInfoID());
                StudyInfo.setCreateDate(oldForm.getCreateDate());
                dao.updateStudyInfo(StudyInfo);
                LogUtil.info("admin",
                "[UpdateStudyInfoAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
