/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.action;

import com.ulearning.ulms.match.helper.MatchHelper;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.user.bean.UserHelper;

import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.blog.helper.BlogHelper;
import com.ulearning.ulms.finance.dao.UserAccountDAOImpl;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingClient;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingStub;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserAction extends Action
{
        protected static Log logger = LogFactory.getLog(AddUserAction.class);
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UserForm uf = (UserForm) form;
                String pwd = request.getParameter("password");
                String lastloginDateStr = request.getParameter("lastloginDateStr");
                if ((lastloginDateStr != null) && (lastloginDateStr.trim().length() != 0))
                {
                        String[] tmp = StringUtil.splitString(lastloginDateStr, "-");
                        uf.setLastloginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0", "0", "0"));
                }
                String[] roleID = request.getParameterValues("roleID");
                String[] orgRoleID = request.getParameterValues("orgRoleID");
                uf.setRoleID(roleID);
                uf.setOrgRoleID(orgRoleID);

                String province = request.getParameter("province");
                String area = request.getParameter("area");
                String city = request.getParameter("city");
                if((uf.getRemark1() == null) || (uf.getRemark1().equals("")))
                {
                   uf.setRemark1(province);
                   uf.setRemark2(area);
                   uf.setRemark3(city);
                }
                String loginName = uf.getLoginName();
                String name = uf.getName();
                String orgID= request.getParameter("orgID");
                int dupUserID = UserHelper.getUserID(loginName);
                if (dupUserID > 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "用户名已存在！");
                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadUtil.executeUpload(uf,
                                        request, response);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("course", "[CreatCourseAction] Exeception====================" + e);
                                throw e;
                        }
                        LogUtil.info("user", "[CreatCourseAction]===========1");
                        String tmp = StringUtils.trimToEmpty((String) request.getAttribute("newFilePath"));
                        System.out.println("tmp             file   path= " + tmp);
                        uf.setRemark6(tmp);
                }
                //To use user EJB insert a user to system
                /*UserHome home = EJBUtil.getUserHome();
                User user = home.create();
                uf.setPassword(pwd);
                int userID = user.addUser(uf);*/
                //Avoid to using EJB to add user
                uf.setPassword(pwd);
                

                //建议以后封装到中间层
                //判断是否属于新理念项目
                try
                {
                        if(Config.isXLNProject())
                        {
                                logger.info("同步新理念项目学窗用户!");
                                logger.info("uf.getLoginName()="+uf.getLoginName()+"<");
                                logger.info("uf.getName()="+uf.getName()+"<");
                                logger.info("uf.getPassword()="+uf.getPassword()+"<");
                                logger.info("uf.getPassword()1="+pwd+"<");
                                SetMeetingStub.UserInformation useInfo=SetMeetingClient.getUserInfoByAccount(uf.getLoginName());
                                logger.info("useInfo="+useInfo+"<");
                                if(useInfo!=null)
                                        logger.info("useInfo.getAccount()="+useInfo.getAccount()+"<");
                                if(useInfo!=null)
                                {
                                        throw new ULMSAppException("不能新建此用户，益学视频教学系统存在此用户");
                                }
                                
                                int result=SetMeetingClient.userRegister(uf.getLoginName(), uf.getName(), pwd);
                                
                                logger.info("result="+result+"<");
                                if(result>0)
                                {
                                        uf.setExternalSystemUserID(new Integer(result));
                                }
                                else
                                {
                                        throw new ULMSSysException("同步益学视频教学系统出错！",(String)null);
                                }
                        }
                }
                catch (ULMSAppException e)
                {
                        e.printStackTrace();
                        request.setAttribute("result","益学视频教学系统存在此用户!建议此用户登录平台后立即修改密码!");
                        //throw e;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        request.setAttribute("result","同步益学视频教学系统出错!可能此用户不能登录益学视频教学系统!请稍候再试！");
                        //throw new ULMSSysException("同步益学视频教学系统出错！",null,e);
                }
                int userID = UserHelper.addUser(uf);
                
                uf.setUserID(userID);
                BlogHelper.addAccount(uf);
                uf.setPassword(pwd);
                //使新来的用户按照每门课程的匹配规则，自动添加到课程中
                MatchHelper.addCoursUser(uf, uf.getCatalogID());
                UserAccountDAOImpl uadaoi = new UserAccountDAOImpl();
                uadaoi.instMainAccount(userID, name, loginName);
                // Forward to result page
                request.setAttribute("orgID",orgID);
                request.setAttribute("success",resultScreen);
                return mapping.findForward(resultScreen);
        }

}
