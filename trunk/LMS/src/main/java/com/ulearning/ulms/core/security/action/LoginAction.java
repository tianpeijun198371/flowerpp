/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Mar 25, 2004
 * Time: 1:42:47 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.admin.webconfig.webcustom.bean.WebCustomHelper;
import com.ulearning.ulms.core.context.SessionContainer;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.form.LoginForm;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAO;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAOFactory;
import com.ulearning.ulms.evaluate.form.EAccessForm;
import com.ulearning.ulms.evaluate.form.ERecordForm;
import com.ulearning.ulms.evaluate.helper.EvaluateManageHelper;
import com.ulearning.ulms.evaluate.model.ERecordModel;
import com.ulearning.ulms.evaluate.model.ERecordPointConversionModel;
import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.tools.access.model.Access;
import com.ulearning.ulms.tools.access.webimpls.AccessWebImpl;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginAction extends Action
{
        public static HashMap userMap = null;

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                // If user pressed 'Cancel' button,
                // return to home page
                String certificateID = request.getParameter("certificateID");

                String visutType = request.getParameter("visutType");
                String visitID = request.getParameter("visitID");
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

                if (!isActive(name))
                {
                        //判断是否是家长
                        if((name!=null)&&(name.startsWith("jz_"))){
                                String xueshLoginName = name.substring(3,name.length());
                                int xueshUserID = SecurityHelper.checkSecurity(xueshLoginName, passwd);
                                if(xueshUserID > 0){
                                        UserForm xueshForm = UserHelper.getUser(new Integer(xueshUserID).toString());
                                        addJiaZhang(xueshLoginName,passwd,xueshForm.getCatalogID());
                                }else{
                                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, " 用户名错误,请核实后再登陆!");
                                        return mapping.findForward("register_login");
                                }
                        }else{
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, " 用户名错误,请核实后再登陆!");
                                return mapping.findForward("register_login");
                        }
                }
                int userID = SecurityHelper.checkSecurity(name, passwd);

                if (userID > 0)
                {
                        resultScreen = "success";

                        STicket ticket = SecurityHelper.getTicket(userID);
                        /*//Cache STicket for every body,Get stick from context
                        HashMap stickets = null;
                        STicket ticket = null;
                        String strUserID = Integer.toString(userID);
                        ServletContext scontext = getServlet().getServletContext();
                        Object ticketMap = scontext.getAttribute("ulms_STICKET_CACHE");
                        if(ticketMap == null)
                        {
                             stickets = new HashMap();
                        }else
                        {                                                   u
                             stickets = (HashMap)ticketMap;
                        }
                        Object ticketObj = stickets.get(strUserID);
                        if(ticketObj == null)
                        {
                            ticket = SecurityHelper.getTicket(userID);
                            stickets.put(strUserID,ticket);
                            scontext.setAttribute("ulms_STICKET_CACHE",stickets);
                            System.out.println("[LoginAction]============ ticketObj == null");
                        }else
                        {
                            ticket = (STicket)ticketObj;
                            System.out.println("[LoginAction]============ get ticketObj");
                        }*/

                        //Cache End
                        //Judge whether the sum of user is Exceed the max user num
                        request.getSession()
                                .setAttribute(LMSConstants.USERID_KEY,
                                        new Integer(userID).toString());

                        if (isExceedMaxNum())
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                        "系统已经到达最大用户数！");

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
                                        request.getSession()
                                                .setAttribute(name, new SessionContainer());
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

                        //取消界面自定义样式
                        //HashMap myConfig = WebCustomHelper.getMyConfig(userID, ticket.getOrgID(), ticket.getAspID());
                        //request.getSession().setAttribute("myConfig", myConfig);
                        //add login info
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

                        //-----------------------------------Add UserLoginEvent to Lmslog--------------------------------

                        //SysConfigHelper helper = new SysConfigHelper();
                        //SysConfigForm sysConfigForm = helper.getSysConfig("0");
                        /*
                        System.out.println("*****************************************");
                        if (sysConfigForm.getIsLogLogin().equals("1"))
                        {
                                System.out.println("sysConfigForm:lmslog will add log");
                        }
                        else
                        {
                                System.out.println("sysConfigForm:lmslog will not  add log");
                        }*/
                        if (getSysConfigForm().getIsLogLogin().equals("1"))
                        {
                                // System.out.println("lmslog ,userid=" + ticket.getUserID());
                                LmslogForm lmslogForm = new LmslogForm();
                                lmslogForm.setLogTypeID(LmslogConstants.LOGTYPE_USER);
                                lmslogForm.setUserID(ticket.getUserID());
                                lmslogForm.setOrgID(ticket.getOrgID());
                                lmslogForm.setUserIP(request.getRemoteAddr());
                                lmslogForm.setOperationTypeID(LmslogConstants.OPERATION_USER_LOGIN);
                                lmslogForm.setOperationTable("LMSDB");
                                lmslogForm.setOperationObjectID(ticket.getUserID());
                                lmslogForm.setDescription("用户登录成功时将被记录到日志.");

                                LmslogDAO dao = LmslogDAOFactory.getDAO();
                                dao.insert(lmslogForm);
                        }

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
                }
                else
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, " 密码错误,请核实后再登陆!");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                // Forward to result page
                if ((certificateID == null) ||
                        (certificateID.equalsIgnoreCase("null")) ||
                        (certificateID.trim().length() == 0))
                {
                        //判断session是否以前存在登录地址，若存在直接跳转到此页面
                        HttpSession session = request.getSession();
                        String previousUrl = (String) session.getAttribute(LMSConstants.PREVIOUS_LOGIN_URL);
                        if (previousUrl != null)
                        {
                                session.removeAttribute(LMSConstants.PREVIOUS_LOGIN_URL);
                                response.sendRedirect(request.getContextPath() + previousUrl);
                                return null;
                        }
                        ActionForward forward = mapping.findForward(resultScreen);
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        //首页
                        if (visutType != null && !visutType.equals(""))
                        {
                                if (visutType.equals(String.valueOf(LMSConstants.VISIT_USER)))
                                {
                                        forward = mapping.findForward("portal");
                                        bf = new StringBuffer(forward.getPath());

                                }
                                else if (visutType.equals(String.valueOf(LMSConstants.VISIT_TEACHER)))
                                {
                                        forward = mapping.findForward("teacher");
                                        bf = new StringBuffer(forward.getPath());
                                        bf.append("?userID=" + visitID);
                                }
                                else if (visutType.equals(String.valueOf(LMSConstants.VISIT_SCHOOL)))
                                {
                                        forward = mapping.findForward("school");
                                        bf = new StringBuffer(forward.getPath());
                                        bf.append("?aspID=" + visitID);
                                }
                                else if (visutType.equals(String.valueOf(LMSConstants.VISIT_CLASS)))
                                {
                                        forward = mapping.findForward("class");
                                        bf = new StringBuffer(forward.getPath());
                                        bf.append("?courseID=" + visitID);
                                }
                                else if (visutType.equals(String.valueOf(LMSConstants.VISIT_STUDENT)))
                                {
                                        forward = mapping.findForward("student");
                                        bf = new StringBuffer(forward.getPath());
                                        bf.append("?userID=" + visitID);
                                }
                                else if (visutType.equals(String.valueOf(LMSConstants.VISIT_APPLY)))
                                {
                                        forward = mapping.findForward("apply");
                                        bf = new StringBuffer(forward.getPath());
                                }
                        }
                        return new ActionForward(bf.toString());


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

        //判断用户是否被激活
        public boolean isActive(String loginName)
        {
                boolean isActive = false;

                int temp = SecurityHelper.checkActive(loginName);

                if (temp > 0)
                {
                        isActive = true;
                }

                //SecurityHelper
                return isActive;
        }

        public void addJiaZhang(String loginName,String pwd,int orgID)
        {
                UserForm details = new UserForm();
                String jz_name = "jz_" + loginName;
                details.setLoginName(jz_name);
                details.setAvailable("1");
                details.setPassword(pwd);
                details.setName(details.getRemark8());
                UserDAO userDAO = UserDAOFactory.getDAO();
                OrganDAO orgDao = OrganDAOFactory.getDAO();
                int userID = userDAO.addUser(details);
                if (userID != 0)
                {
                        UserRoleForm urf = new UserRoleForm();
                        OrgUserForm ouf = new OrgUserForm();
                        ouf.setOrgID(details.getCatalogID());
                        ouf.setUserID(userID);
                        orgDao.addOrganUser(ouf);
                        //UserRoleForm urf = new UserRoleForm();
                        urf.setUserID(userID);
                        urf.setRoleID(SecurityConstants.ROLE_STUPARENT);
                        urf.setRelationID(orgID);
                        urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                        //urf.setRelationID(SecurityConstants.DEFAULT_RELATIONID_PLATFORM);
                        //urf.setType(SecurityConstants.USER_CERTIFICATE_RELATION);
                        SecurityDAO secDao = SecurityDAOFactory.getDAO();
                        secDao.addUserRole(urf);
                }
        }


}
