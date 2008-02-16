/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.action;

import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAO;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOFactory;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051121
 * Time: 135243
 */
public class UpdateExambatchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ExambatchForm tf = (ExambatchForm) form;
                ExambatchDAO dao = ExambatchDAOFactory.getDAO();
                dao.updateExambatch(tf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
