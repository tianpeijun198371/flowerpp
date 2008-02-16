/**
 * RegisterAction.java. 
 * User: huangsb  Date: 2004-8-16 
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. 
 */
package com.ulearning.ulms.user.action;

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
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.finance.helper.UserAccountHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingClient;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingStub;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.form.JieFoClerkForm;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegisterAction extends Action
{
        protected static Log logger = LogFactory.getLog(RegisterAction.class);
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UserDAO userDAO = UserDAOFactory.getDAO();
                OrganDAO orgDao = OrganDAOFactory.getDAO();
                SecurityDAO secDao = SecurityDAOFactory.getDAO();
                UserForm uf = (UserForm) form;
                String pwd = request.getParameter("password");
                //��֤��
                String random = request.getParameter("rand");
                String randomSession = StringUtil.nullToStr((String) request.getSession().getAttribute("random"));
                //System.out.println("============random========================="+random);
                //System.out.println("============randomSession========================="+randomSession);
                int orgID = uf.getCatalogID();
                int roleID = SecurityConstants.ROLE_COURSR_STUDENT;
                if(request.getParameter("roleID")!=null&&!request.getParameter("roleID").equals(""))
                {
                    roleID = Integer.parseInt(request.getParameter("roleID"));
                }
                String certificateID = request.getParameter("certificateID");
                System.out.println("==============================certificateID ===============" + certificateID);
                OrganForm oof = orgDao.getOrgan(orgID);
                String lastloginDateStr = request.getParameter("lastloginDateStr");
                if ((lastloginDateStr != null) && (lastloginDateStr.trim().length() != 0))
                {
                        String[] tmp = StringUtil.splitString(lastloginDateStr, "-");
                        uf.setLastloginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0", "0", "0"));
                }
                String loginName = uf.getLoginName();
                String name = uf.getName();
                int dupUserID = UserHelper.getUserID(loginName);

                if (random.equals("") || !random.equals(randomSession))
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "��֤�벻��ȷ��");
                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                if (dupUserID > 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "�û����Ѵ��ڣ�");
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
                                throw e;
                        }
                        String tmp = StringUtils.trimToEmpty((String) request.getAttribute("newFilePath"));
                        uf.setRemark6(tmp);
                }
                /*
                int userID2 = userDAO.getIsExistEmail(uf.getMail());
                if(userID2>0)
                {
                     UserForm uf2 = UserHelper.getUser(new Integer(userID2).toString());
                        if(uf2.getAvailable().equals("1"))
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "E-mail�Ѿ�ע��������飡");
                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                        }else if(uf2.getAvailable().equals("0"))
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "E-mail�Ѿ�ע���������ʱ��Ҫע�ᣡ");
                                return mapping.findForward(LMSConstants.ERROR_FORWARD);                                
                        }
                }
                int isSameIdentity = UserHelper.isExistIdentity(uf.getCard());
                if (isSameIdentity > 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "��ע������֤���Ѿ�ע���,��ȷ��������ȷ��");
                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }
                */

                try
                {
//�����Ժ��װ���м��
                        //�ж��Ƿ�������������Ŀ
                        if(Config.isXLNProject())
                        {
                                logger.info("ͬ����������Ŀѧ���û�!");
                                logger.info("uf.getLoginName()="+uf.getLoginName()+"<");
                                logger.info("uf.getName()="+uf.getName()+"<");
                                logger.info("uf.getPassword()1="+pwd+"<");
                                SetMeetingStub.UserInformation useInfo=SetMeetingClient.getUserInfoByAccount(uf.getLoginName());
                                logger.info("useInfo="+useInfo+"<");
                                if(useInfo!=null)
                                        logger.info("useInfo.getAccount()="+useInfo.getAccount()+"<");
                                if(useInfo!=null)
                                {
                                        throw new ULMSAppException("�����½����û�����ѧ��Ƶ��ѧϵͳ���ڴ��û�");
                                }

                                int result=SetMeetingClient.userRegister(uf.getLoginName(), uf.getName(), pwd);
                                logger.info("result="+result+"<");
                                if(result>0)
                                {
                                        uf.setExternalSystemUserID(new Integer(result));
                                }
                                else
                                {
                                        throw new ULMSSysException("ͬ����ѧ��Ƶ��ѧϵͳ����",(String)null);
                                }
                                request.setAttribute("xuechuang_syn_rsult",String.valueOf(result));
                        }
                }
                catch (ULMSAppException e)
                {
                        request.setAttribute("result","��ѧ��Ƶ��ѧϵͳ���ڴ��û�!������û���¼ƽ̨�������޸�����!");
                        throw e;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        request.setAttribute("result","ͬ����ѧ��Ƶ��ѧϵͳ����!���ܴ��û����ܵ�¼��ѧ��Ƶ��ѧϵͳ!���Ժ����ԣ�");
                        //throw new ULMSSysException("ͬ����ѧ��Ƶ��ѧϵͳ����",null,e);
                }
                
                int userID = userDAO.addUser(uf);
                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                int leibie = 0;
                                List organList = orgDao.getUserAsp(uf.getCatalogID());
                                LogUtil.debug("user", "[UserDAOOracle]====================pwd is : " + pwd);
                                LogUtil.debug("user", "[UserDAOOracle]====================uf.getPassword : " + uf.getPassword());
                                // LogUtil.debug("user", "[UserDAOOracle]====================the details.getCatalogID() is : " + uf.getCatalogID());
                                OrganForm organForm = (OrganForm) organList.get(organList.size() - 1);
                                if (organForm.getIsAsp() == 1)
                                {
                                        int jiefoorgID = organForm.getOrgID();
                                        JieFoClerkForm jf = new JieFoClerkForm();
                                        jf.setUserID(userID);
                                        jf.setOrganID(jiefoorgID);
                                        jf.setClerk_name(uf.getLoginName());
                                        jf.setName(uf.getName());
                                        //jf.setClerk_pwd(uf.getPassword());
                                        jf.setClerk_pwd(pwd);
                                        if (uf.getSex().equals("1"))
                                        {
                                                jf.setClerk_sex("��");
                                        }
                                        else
                                        {
                                                jf.setClerk_sex("Ů");
                                        }
                                        jf.setClerk_job(StringUtil.nullToStr(uf.getDesc1()));
                                        jf.setClerk_address(StringUtil.nullToStr(uf.getAddress()));
                                        jf.setClerk_telephone(StringUtil.nullToStr(uf.getPhone()));
                                        jf.setClerk_email(StringUtil.nullToStr(uf.getMail()));
                                        jf.setClerk_BM(StringUtil.nullToStr(oof.getOrgName()));
                                        jf.setClerk_xl(StringUtil.nullToStr(uf.getEduLevel()));
                                        jf.setClerk_WorkType(StringUtil.nullToStr(uf.getDescription()));
                                        jf.setClerk_SFZNumber(StringUtil.nullToStr(uf.getCard()));
                                        jf.setClerk_post(StringUtil.nullToStr(uf.getPostalcode()));
                                        jf.setLeibie(leibie);
                                        userDAO.addJieFoUser(jf);
                                }
                        }


                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                OrgUserForm ouf = new OrgUserForm();
                if (userID != 0)
                {
                        ouf.setOrgID(uf.getCatalogID());
                        ouf.setUserID(userID);
                        orgDao.addOrganUser(ouf);
                        UserRoleForm urf = new UserRoleForm();
                        urf.setUserID(userID);
                        urf.setRoleID(roleID);
                        urf.setRelationID(orgID);
                        urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                        //urf.setRelationID(SecurityConstants.DEFAULT_RELATIONID_PLATFORM);
                        //urf.setType(SecurityConstants.USER_CERTIFICATE_RELATION);
                        secDao.addUserRole(urf);
                        new UserAccountHelper().addMaccount(userID, name, loginName);
                        //���ӵ���ѵ��
                        int certificateID_int= NumberUtils.toInt(certificateID);
                        if(certificateID_int>0)
                        {
                             System.out.println("=========certificateID_int         start= " + certificateID_int);
                             CourseUserHelper.addCourseUser(certificateID_int, SecurityConstants.USER_CERTIFICATE_RELATION, userID, CourseKeys.COURSE_USER_APPLY_STATE,
                                        SecurityConstants.ROLE_COURSR_STUDENT);
                             System.out.println("=========certificateID_int          end = " + certificateID_int);
                        }
                }
                //��Ӽҳ��ʺ�
            String popName ="ע��ɹ�����ȴ�������";
            String projectName = com.ulearning.ulms.core.util.Config.getProjectName();
            if (projectName.equals("XLN"))  //������Ŀ��ũ��У
            {
                popName ="ע��ɹ�����ȴ�������ֱ����ϵ��ע���ѧУ��";
                if(roleID==SecurityConstants.ROLE_COURSR_STUDENT)
                {
                     String jz_name="jz_"+uf.getLoginName();
                     uf.setLoginName(jz_name);
                     uf.setAvailable("1");
                     uf.setPassword(pwd);
                     uf.setName(uf.getRemark8());
                     userID = userDAO.addUser(uf);
                     if (userID != 0)
                     {
                                ouf.setOrgID(uf.getCatalogID());
                                ouf.setUserID(userID);
                                orgDao.addOrganUser(ouf);
                                UserRoleForm urf = new UserRoleForm();
                                urf.setUserID(userID);
                                urf.setRoleID(SecurityConstants.ROLE_STUPARENT);
                                urf.setRelationID(orgID);
                                urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                                //urf.setRelationID(SecurityConstants.DEFAULT_RELATIONID_PLATFORM);
                                //urf.setType(SecurityConstants.USER_CERTIFICATE_RELATION);
                                secDao.addUserRole(urf);
                     }
                    popName=popName+"�ҳ��ʺ�Ϊ"+uf.getLoginName()+", ����ͬѧ����¼���룡";
                }
            }


//                                        if(userID != 0)
//                                        {
//                                                String userMail = uf.getMail();
//                                                String title = "�µ�ע���û�";
//                                                //String url = Config.getulmsURL();//+"/UserActive.do?userID ="+userID;
//
//                                                String body = "�û���ע����Ϣ�� :<br> " +
//                                                        " �û���: "+uf.getLoginName()+"<br>" +
//                                                        " �ܡ���: "+pwd+ "<br>" +
//                                                        " �û���<a href=\""+Config.getulmsURL()+"/user/reguser.jsp?userID="+userID+"\" target=_blank >��ϸ��Ϣ</a>"+
//                                                        " ���ھ� <a href=\""+Config.getulmsURL()+"/UserActive.do?userID="+userID+"\" target=_blank>����</a>ע���û�";
//                                                ///System.out.println("============url========================="+url);
//                                                //System.out.println("============body========================="+body);
//                                                String formUser = Config.getSmtpUser();
//                                                        if (userMail != null && !userMail.equals(""))
//                                                        {
//                                                                List list = new ArrayList();
//                                                                list.add(new SysConfigHelper().getSysConfig().getMasterEmail());
//
//                                                                       SmtpModel sm = new SmtpModel();
//                                        sm.setSubject(title);
//
//                                        sm.setBody(body);
//
//                                        sm.setSendTo(list);
//                                        sm.setSendFrom(formUser);
//                                        EmailServices.sendMail(sm);
//                                }
//
//
//                }
                if (userID != 0)
                {
                        request.setAttribute("returnPortal", "returnPortal");
                        String type = request.getParameter("type");
                        if(type!=null&&type.equals("forCourseUser"))
                        {
                             request.setAttribute("forCourseUser", "forCourseUser");
                        }
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO,popName);
                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }
                return mapping.findForward(resultScreen);

        }
}
