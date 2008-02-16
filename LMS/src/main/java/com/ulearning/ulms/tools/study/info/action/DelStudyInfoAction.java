/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-24 9:23:06 
 */

package com.ulearning.ulms.tools.study.info.action;

import com.ulearning.ulms.tools.study.info.bean.StudyInfoHelper;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAO;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOFactory;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAO;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOFactory;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.tools.study.info.bean.StudyInfoHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelStudyInfoAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int type = Integer.parseInt(request.getParameter("type"));
                if(type == 2){
                     resultScreen = "recordsuccess";
        }
                String[] StudyInfoID = request.getParameterValues("studyInfoID");

                System.out.println("StudyInfoID[i] = " + StudyInfoID);
                System.out.println("StudyInfoID[i] = " + StudyInfoID.length);

                for (int i = 0; i < StudyInfoID.length; i++)
                {
                        StudyInfoDAO dao = StudyInfoDAOFactory.getDAO();
                        System.out.println("StudyInfoID[i] = " + StudyInfoID[i]);
                        StudyInfoForm del = StudyInfoHelper.getStudyInfo(Integer.parseInt(StudyInfoID[i]));
                        int studyInfoID = del.getStudyInfoID();

                        if (studyInfoID > 0)
                        {
                                dao.removeStudyInfo(String.valueOf(studyInfoID));
                        }
                        dao.removeStudyInfo(StudyInfoID[i]);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}