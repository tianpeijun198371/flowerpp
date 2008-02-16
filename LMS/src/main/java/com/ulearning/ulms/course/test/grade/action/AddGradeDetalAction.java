/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-6
 * Time: 10:46:53
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddGradeDetalAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                return mapping.findForward(resultScreen);
        }
}
