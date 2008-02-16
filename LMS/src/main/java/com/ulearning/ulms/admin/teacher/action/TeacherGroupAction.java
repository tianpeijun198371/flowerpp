package com.ulearning.ulms.admin.teacher.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 13:02:20
 * To change this template use File | Settings | File Templates.
 */
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TeacherGroupAction extends DispatchAction
{
        public ActionForward add(ActionMapping actionMapping,
                                 ActionForm actionForm, HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse); //To change body of overridden methods use File | Settings | File Templates.
        }

        public ActionForward del(ActionMapping actionMapping,
                                 ActionForm actionForm, HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse); //To change body of overridden methods use File | Settings | File Templates.
        }

        public ActionForward modif(ActionMapping actionMapping,
                                   ActionForm actionForm, HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse); //To change body of overridden methods use File | Settings | File Templates.
        }

        public ActionForward view(ActionMapping actionMapping,
                                  ActionForm actionForm, HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse); //To change body of overridden methods use File | Settings | File Templates.
        }
}
