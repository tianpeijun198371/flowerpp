/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-24 9:22:14 
 */

package com.ulearning.ulms.tools.study.info.action;

import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAO;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOFactory;
import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoDAOSysException;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AddStudyInfoAction extends UploadAction

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

                LogUtil.info("system", "[AddStudyInfoAction]===========Begin");

                StudyInfoForm pf = (StudyInfoForm) form;
                System.out.println("[AddStudyInfoAction]===============getXieZuoPingYu = "+ pf.getXieZuoPingYu());
                System.out.println("[AddStudyInfoAction]===============getName = "+ pf.getName());
                try
                {
                        StudyInfoDAO dao = StudyInfoDAOFactory.getDAO();
                        pf.setCreateDate(new Date());
                        dao.addStudyInfo(pf);
                        System.out.println("============================createDate");
                }
                catch (StudyInfoDAOSysException e)
                {
                        e.printStackTrace();
                }

                LogUtil.info("admin",
                        "[AddStudyInfoAction]===========resultScreen = " + resultScreen);
                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
