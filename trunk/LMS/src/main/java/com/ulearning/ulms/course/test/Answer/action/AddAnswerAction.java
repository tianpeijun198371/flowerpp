/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Answer.action;

import com.ulearning.ulms.course.test.Answer.dao.AnswerDAO;
import com.ulearning.ulms.course.test.Answer.dao.AnswerDAOFactory;
import com.ulearning.ulms.course.test.Answer.form.AnswerForm;

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
public class AddAnswerAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                AnswerForm tf = (AnswerForm) form;
                AnswerDAO dao = AnswerDAOFactory.getDAO();
                dao.insertAnswer(tf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
