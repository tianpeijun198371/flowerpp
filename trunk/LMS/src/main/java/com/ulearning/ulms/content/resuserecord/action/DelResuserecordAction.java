/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.action;

import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAO;
import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAOFactory;

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
 * Date: 20060317
 * Time: 103906
 */
public class DelResuserecordAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] resuseID = request.getParameterValues("resuseID");
                ResuserecordDAO dao = ResuserecordDAOFactory.getDAO();

                for (int i = 0; i < resuseID.length; i++)
                {
                        dao.deleteResuserecord(Integer.parseInt(resuseID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
