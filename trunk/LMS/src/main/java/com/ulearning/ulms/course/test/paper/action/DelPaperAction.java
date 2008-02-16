/**
 * DelPaperAction.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.course.test.paper.dao.PaperDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperDAOFactory;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelPaperAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] paperID = request.getParameterValues("paperID");

                PaperDAO dao = PaperDAOFactory.getDAO();

                PaperQuestionDAO dao1 = PaperQuestionDAOFactory.getDAO();

                for (int i = 0; i < paperID.length; i++)
                {
                        dao1.removePaperQuestion(Integer.parseInt(paperID[i]));
                        dao.removePaper(Integer.parseInt(paperID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
