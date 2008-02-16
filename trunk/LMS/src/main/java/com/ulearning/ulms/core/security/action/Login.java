package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.core.context.SessionContainer;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.form.LoginForm;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAO;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAOFactory;
import com.ulearning.ulms.evaluate.form.EAccessForm;
import com.ulearning.ulms.evaluate.form.ERecordForm;
import com.ulearning.ulms.evaluate.model.ERecordModel;
import com.ulearning.ulms.evaluate.model.ERecordPointConversionModel;
import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.tools.access.model.Access;
import com.ulearning.ulms.tools.access.webimpls.AccessWebImpl;
import com.ulearning.ulms.util.LMSConstants;

/**
 * Created by IntelliJ IDEA.
 * User: liaoxx
 * Date: 2007-3-29
 * Time: 11:00:31
 * To change this template use File | Settings | File Templates.
 */
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends Action
{
        public static HashMap userMap = null;

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                // If user pressed 'Cancel' button,
                // return to home page
                String certificateID = "";
                certificateID = request.getParameter("certificateID");

                if (isCancelled(request))
                {
                        return mapping.findForward("login");
                }

                String resultScreen = "success";
                LoginForm lf = (LoginForm) form;
                String name = lf.getName();
                String passwd = lf.getPasswd();
                request.getSession().setAttribute("elms_login_name", name);
                request.getSession().setAttribute("elms_login_passwd", passwd);

                int userID = 1;
                resultScreen = "success";

                STicket ticket = SecurityHelper.getTicket(userID);
                request.getSession()
                        .setAttribute(LMSConstants.USERID_KEY,
                                new Integer(userID).toString());

                if (isExceedMaxNum())
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "系统已经到达最大用户数！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }
                else
                {
                        if (isMultiLogin(name))
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                        "一个账号不能在两个地方同时登录！");

                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                        }
                        else
                        {
                                request.getSession().setAttribute(name, new SessionContainer());
                        }
                }

                request.getSession().setAttribute(LMSConstants.TICKET_KEY, ticket);
                request.getSession().setAttribute(LMSConstants.LOGINNAME_KEY, name);
                request.getSession()
                        .setAttribute(LMSConstants.USER_ORGID_KEY,
                                new Integer(ticket.getOrgID()).toString());
                request.getSession()
                        .setAttribute(LMSConstants.USER_ASPID_KEY,
                                new Integer(ticket.getAspID()).toString());
                request.getSession()
                        .setAttribute(LMSConstants.LOGINNAME_KEY_NAME,
                                ticket.getUf().getName());

                HashMap properties = new HashMap();
                properties.put("sysStartTime",
                        String.valueOf(System.currentTimeMillis()));
                properties.put("ip", request.getRemoteAddr());
                request.getSession().setAttribute("Access", properties);

                Access access = new Access();
                access.setUserID(userID);
                access.setModuleID(1); //login
                access.setCourseID(0);
                access.setCertificateID(0);
                access.setProjectID(0);
                access.setOrgID(ticket.getOrgID());
                access.setIp(request.getRemoteAddr());
                access.setCreateDateTime(new Date());
                access.setUserTime(0);

                AccessWebImpl accessWebImpl = new AccessWebImpl();
                accessWebImpl.insert(access);

                //ping gu , add by yud
                EAccessForm pgAccessform = new EAccessForm();
                pgAccessform.setAccessType("登陆");
                pgAccessform.setUserID(userID);
                pgAccessform.setModuleID(ticket.getOrgID());
                pgAccessform.setType("0");
                pgAccessform.setParentID(0);
                pgAccessform.setIP(request.getRemoteAddr());
                com.ulearning.ulms.evaluate.helper.EvaluateManageHelper.addAccess(pgAccessform);

                //update user's point
                EvaluateManageDAO dao = EvaluateManageDAOFactory.getDAO();
                List l = dao.getPointConversion();
                int initialPoint = ((ERecordPointConversionModel) l.get(0)).getPoint(); //数据库中已经初始化的值
                List list = dao.isThisUserInERecord(userID); //判断是否已经有该用户的经验值数据
                ERecordForm eRecordForm = new ERecordForm();
                eRecordForm.setUserID(userID);

                if (list.size() != 0)
                {
                        int oldPoint = ((ERecordModel) list.get(0)).getPoint(); //用户已有经验值
                        eRecordForm.setPoint(oldPoint + initialPoint);
                        eRecordForm.seteRecordID(((ERecordModel) list.get(0)).geteRecordID());
                        dao.updateERecord(eRecordForm.geteRecordModel());
                }
                else
                {
                        eRecordForm.setPoint(initialPoint);
                        dao.addERecord(eRecordForm.geteRecordModel());
                }

                //com.ulearning.ulms.evaluate.helper.EvaluateManageHelper.setPointERecord(eRecordForm);

                // Forward to result page
                if ((certificateID == null) ||
                        (certificateID.equalsIgnoreCase("null")) ||
                        (certificateID.trim().length() == 0))
                {
                        return mapping.findForward(resultScreen);
                }
                else
                { //如果从首页的项目介绍里登陆，则返回项目介绍页面

                        ActionForward forward = mapping.findForward("certIntro");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?fromMystudy=1&certificateID=" + certificateID +
                                "&isNeedApply=1");

                        return new ActionForward(bf.toString());
                }
        }

        static SysConfigForm getSysConfigForm()
        {
                String configOrgID = "0";
                SysConfigHelper helper = new SysConfigHelper();

                return helper.getSysConfig(configOrgID);
        }

        /**
         * Judge whether the sum of user is Exceed the max user num
         *
         * @return true is exceeding, otherwise false
         */
        public boolean isExceedMaxNum()
        {
                //"0" means needn't verify
                //System.out.println("isLimit============"+isLimit);
                SysConfigForm scf = getSysConfigForm();
                String isLimit = scf.getIsLimitMaxOnLineUsers();

                if ((isLimit == null) || (isLimit.trim().length() == 0) ||
                        (isLimit.equalsIgnoreCase("0")))
                {
                        return false;
                }

                ServletContext sc = getServlet().getServletContext();
                int userSum = SessionContainer.getCount();
                sc.setAttribute(LMSConstants.LOGINUSER_NUM_KEY, new Integer(userSum));

                //System.out.println("userSum=============="+userSum);
                //System.out.println("isLimit=================="+isLimit);
                if ((userSum >= Integer.parseInt(isLimit)))
                {
                        return true;
                }

                return false;
        }

        /**
         * Judge whether more than two users is using the same acount
         *
         * @param loginName
         * @return true is multi-login, otherwise false
         */
        public boolean isMultiLogin(String loginName)
        {
                SysConfigForm scf = getSysConfigForm();
                String isAllowMultiLogin = scf.getIsAllowMultiLogin();

                //"0" means the multi-login is  disable
                if ((isAllowMultiLogin == null) ||
                        (isAllowMultiLogin.trim().length() == 0) ||
                        (isAllowMultiLogin.equalsIgnoreCase("0")))
                {
                        boolean existUser = SessionContainer.existUser(loginName);

                        if (existUser)
                        {
                                return true;
                        }
                }

                return false;
        }
}
