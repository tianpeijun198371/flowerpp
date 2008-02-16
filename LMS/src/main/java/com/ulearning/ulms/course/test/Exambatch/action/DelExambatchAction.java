/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.action;

import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAO;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * 删除考场数据的action
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class DelExambatchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] exambatchID = request.getParameterValues("exambatchID");
                ExambatchDAO dao = ExambatchDAOFactory.getDAO();

                for (int i = 0; i < exambatchID.length; i++)
                {
                        dao.deleteExambatch(Integer.parseInt(exambatchID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
