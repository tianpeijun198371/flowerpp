/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.action;

import com.ulearning.ulms.bbs.helper.MvnMemberHelper;
import com.ulearning.ulms.blog.helper.BlogHelper;
import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingClient;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingStub;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.form.JieFoClerkForm;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class UpdateUserAction extends Action
{
        protected static Log logger = LogFactory.getLog(UpdateUserAction.class);

        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UserForm uf = (UserForm) form;
                UserRoleForm urf = null;
                UserDAO dao = UserDAOFactory.getDAO();
                String lastloginDateStr = request.getParameter("lastloginDateStr");
                if ((lastloginDateStr != null) && (lastloginDateStr.trim().length() != 0))
                {
                        String[] tmp = StringUtil.splitString(lastloginDateStr, "-");
                        uf.setLastloginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0", "0", "0"));
                }
                OrganDAO daoo = OrganDAOFactory.getDAO();
                SecurityDAO daos = SecurityDAOFactory.getDAO();

                int userID = Integer.parseInt(request.getParameter("userID"));
                UserForm userForm = UserHelper.getUser(new Integer(userID).toString());

                //修改区域
                if((userForm != null)&&((uf.getRemark1() == null)||(uf.getRemark1().trim().length() == 0))){
                    uf.setRemark1(userForm.getRemark1());
                    uf.setRemark2(userForm.getRemark2());
                    uf.setRemark3(userForm.getRemark3());
                }
                //int  userID=uf.getUserID();

                int orgID = Integer.parseInt(request.getParameter("catalogID"));

                OrganForm oof = daoo.getOrgan(orgID);

                int user_type = Integer.parseInt(request.getParameter("user_type"));

                String[] roleID = request.getParameterValues("roleID");

                String[] orgRoleID = request.getParameterValues("orgRoleID");
                String aspID = request.getParameter("orgID");
                // update  jiefo user
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
                        if (tmp != null && !tmp.equals(""))
                        {
                                uf.setRemark6(tmp);
                        }


                }
                if (userForm.getRegisterDate() == null)
                {
                        uf.setRegisterDate(new Date(System.currentTimeMillis()));
                }
                else
                {
                        uf.setRegisterDate(userForm.getRegisterDate());
                }

                try
                {
                        //建议以后封装到中间层
                        //判断是否属于新理念项目
                        if (Config.isXLNProject() && ((uf.getPassword() != null && uf.getPassword().length() >= 1)))
                        {
                                UserForm uf_old = dao.getUser(String.valueOf(uf.getUserID()));
                                logger.info("同步新理念项目学窗用户!");
                                logger.info("uf.getPassword=" + uf.getPassword() + "<");
                                logger.info("uf_old.getPlainPassword()=" + uf_old.getPlainPassword() + "<");

                                boolean isNewUser=false;
                                if (uf_old.getExternalSystemUserID() == null || uf_old.getExternalSystemUserID().intValue() < 0)
                                {
                                        //以下先同步用户
                                        int result_int = -1;
                                        SetMeetingStub.UserInformation useInfo = SetMeetingClient.getUserInfoByAccount(userForm.getLoginName());
                                        System.out.println("useInfo = " + useInfo);
                                        if (useInfo == null)
                                        {
                                                result_int = SetMeetingClient.userRegister(uf.getLoginName(), uf.getName(), uf.getPlainPassword());
                                                logger.info("result_int=" + result_int + "<");
                                                if (result_int > 0)
                                                {
                                                        uf.setExternalSystemUserID(new Integer(result_int));
                                                }
                                                else
                                                {
                                                        logger.info("同步益学视频教学系统出错！");
                                                        throw new ULMSSysException("同步益学视频教学系统出错！", (String) null);
                                                }
                                                isNewUser=true;
                                        }
                                        else
                                        {
                                                uf.setExternalSystemUserID(new Integer(useInfo.getUserId()));
                                        }
                                }

                                if(!isNewUser)
                                {//加入没有新建,则修改密码
                                        boolean result = SetMeetingClient.changePwd(uf.getLoginName(), uf.getPassword(), uf_old.getPlainPassword());
                                        request.setAttribute("xuechuang_syn_rsult", String.valueOf(result));

                                        if (!result)
                                        {
                                                throw new ULMSAppException("同步益学视频教学系统出错", (String) null);
                                        }
                                }
                        }
                        else
                        {
                                logger.info("不同步新理念项目学窗用户密码!");
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        request.setAttribute("result","同步益学视频教学系统出错!可能此用户不能登录益学视频教学系统!请稍候再试！");
                        //throw new ULMSAppException("同步益学视频教学系统出错!", null, e);
                }

                try
                {


                        if (Config.getIsIntegrateJieFo())
                        {
                                int leibie = 0;
                                if (orgRoleID != null)
                                {
                                        for (int i = 0; i < orgRoleID.length; i++)
                                        {
                                                if (Integer.parseInt(orgRoleID[i]) == 10)
                                                {
                                                        leibie = 5;
                                                }
                                        }

                                }
                                else
                                {
                                        for (int i = 0; i < roleID.length; i++)
                                        {
                                                if (Integer.parseInt(roleID[i]) == 10)
                                                {
                                                        leibie = 5;
                                                }
                                        }
                                }

                                List organList = daoo.getUserAsp(orgID);
                                //LogUtil.debug("user", "[UserDAOOracle]====================the orgID is : " + orgID);
                                //LogUtil.debug("user", "[UserDAOOracle]====================the details.getCatalogID() is : " + details.getCatalogID());
                                OrganForm organForm = (OrganForm) organList.get(organList.size() - 1);
                                if (organForm.getIsAsp() == 1)
                                {
                                        int jiefoorgID = organForm.getOrgID();
                                        JieFoClerkForm jf = new JieFoClerkForm();

                                        jf.setUserID(userID);
                                        jf.setOrganID(jiefoorgID);
                                        jf.setClerk_name(uf.getLoginName());
                                        jf.setName(uf.getName());
                                        jf.setClerk_pwd(uf.getPassword());
                                        if (uf.getSex().equals("1"))
                                        {
                                                jf.setClerk_sex("男");
                                        }
                                        else
                                        {
                                                jf.setClerk_sex("女");
                                        }
                                        jf.setClerk_job(StringUtil.nullToStr(uf.getDesc1()));
                                        jf.setClerk_address(StringUtil.nullToStr(uf.getAddress()));
                                        jf.setClerk_telephone(StringUtil.nullToStr(uf.getPhone()));
                                        jf.setClerk_email(StringUtil.nullToStr(uf.getMail()));
                                        jf.setClerk_xl(StringUtil.nullToStr(uf.getEduLevel()));
                                        jf.setClerk_BM(oof.getOrgName());
                                        jf.setClerk_WorkType(StringUtil.nullToStr(uf.getDescription()));
                                        jf.setClerk_SFZNumber(StringUtil.nullToStr(uf.getCard()));
                                        jf.setClerk_post(StringUtil.nullToStr(uf.getPostalcode()));
                                        jf.setLeibie(leibie);
                                        dao.updateJieFoUser(jf);
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                int relationID = 0;
                if (user_type == SecurityConstants.USER_ORGAN_RELATION)
                {
                        relationID = orgID;
                }

                if ((userID != 0)&&((roleID != null)&&(roleID.length > 0)))
                {
                        // daoo.removeOrganUser("" + userID);
                        daos.removeUserRole(userID, relationID, user_type);
                }

                if (userID != 0)
                {
                        //daos.removeUserRole(userID, relationID, user_type);
                        if ((roleID != null)&&(roleID.length > 0))
                        {
                                for (int i = 0; i < roleID.length; i++)
                                {
                                        urf = new UserRoleForm();
                                        urf.setRoleID(Integer.parseInt(roleID[i]));
                                        urf.setUserID(userID);
                                        urf.setRelationID(relationID);
                                        urf.setType(user_type);
                                        daos.addUserRole(urf);
                                }
                                if ((orgRoleID != null) && (orgRoleID.length > 0))
                                {
                                        daos.removeUserRole(userID, orgID, SecurityConstants.USER_ORGAN_RELATION);
                                        for (int i = 0; i < orgRoleID.length; i++)
                                        {
                                                urf = new UserRoleForm();
                                                urf.setRoleID(Integer.parseInt(orgRoleID[i]));
                                                urf.setUserID(userID);
                                                urf.setRelationID(orgID);
                                                urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                                                daos.addUserRole(urf);
                                        }
                                }
                        }
                    //修改BBS对应成员密码
                    if(uf.getPassword()!=null)
                    {
                        System.out.println("==============ulms passwd ============== " + uf.getPassword());
                        MvnMemberHelper bbsHelper=new MvnMemberHelper();
                        bbsHelper.updateMvnPass(String.valueOf(uf.getUserID()),uf.getPassword());
                    }


                    dao.updateUser(uf);


                    BlogHelper.updateAccount(uf);
                        // daoo.addOrganUser(orgID,userID);
                }
                LogUtil.info("user", "[UpdateUserAction]===========resultScreen = " + resultScreen);
                request.setAttribute("orgID", aspID);
                return mapping.findForward(resultScreen);
        }

}
