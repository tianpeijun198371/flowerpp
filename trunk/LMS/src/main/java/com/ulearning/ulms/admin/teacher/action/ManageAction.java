package com.ulearning.ulms.admin.teacher.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 15:56:49
 * To change this template use File | Settings | File Templates.
 */
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManageAction extends DispatchAction
{
        //����Ӧ�̹����ѯ
        public ActionForward offer(ActionMapping actionMapping,
                                   ActionForm actionForm, HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse); //To change body of overridden methods use File | Settings | File Templates.
        }

        //�����ʦ���ܹ����ѯ
        public ActionForward tech(ActionMapping actionMapping,
                                  ActionForm actionForm, HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse); //To change body of overridden methods use File | Settings | File Templates.
        }

        //�������շѹ����ѯ
        public ActionForward fee(ActionMapping actionMapping,
                                 ActionForm actionForm, HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse); //To change body of overridden methods use File | Settings | File Templates.
        }
}
