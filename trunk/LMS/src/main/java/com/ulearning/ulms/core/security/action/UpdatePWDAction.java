/**
 * UpdatePWDAction.java.
 * User: dengj  Date: 2004-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.bbs.helper.MvnMemberHelper;
import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingClient;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingStub;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdatePWDAction extends Action
{
        protected static Log logger = LogFactory.getLog(UpdatePWDAction.class);

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String passwd = request.getParameter("passwd");
                SecurityDAO dao = SecurityDAOFactory.getDAO();
                Object userID = request.getSession()
                        .getAttribute(LMSConstants.USERID_KEY);

                if (userID == null)
                {
                        return mapping.findForward(LMSConstants.NOSESSION_PAGE);
                }
                
                //�����Ժ��װ���м��
                //�ж��Ƿ�������������Ŀ
                try
                {
                        if (Config.isXLNProject())
                        {
                                UserDAO userDAO = UserDAOFactory.getDAO();
                                UserForm uf = userDAO.getUser(userID.toString());
                        
                                logger.info("ͬ����������Ŀѧ���û�����!");
                                logger.info("passwd1=" + passwd + "<");
                                logger.info("uf.getPlainPassword()=" + uf.getPlainPassword() + "<");

                                if(uf.getExternalSystemUserID()==null || uf.getExternalSystemUserID().intValue()<0)
                                {
                                        //������ͬ���û�
                                        int result_int=-1;
                                        SetMeetingStub.UserInformation useInfo= SetMeetingClient.getUserInfoByAccount(uf.getLoginName());
                                        System.out.println("useInfo = " + useInfo);
                                        if(useInfo==null)
                                        {
                                                result_int=SetMeetingClient.userRegister(uf.getLoginName(), uf.getName(), uf.getPlainPassword());

                                                logger.info("result_int="+result_int+"<");
                                                if(result_int>0)
                                                {
                                                        uf.setExternalSystemUserID(new Integer(result_int));
                                                }
                                                else
                                                {
                                                        logger.info("ͬ����ѧ��Ƶ��ѧϵͳ����");
                                                        throw new ULMSSysException("ͬ����ѧ��Ƶ��ѧϵͳ����",(String)null);
                                                }
                                        }
                                        else
                                        {
                                                uf.setExternalSystemUserID(new Integer(useInfo.getUserId()));
                                        }
                                        //���޸�����Ͳ�ѯ��
                                        uf.setPassword(null);
                                        uf.setPwdAnswer(null);
                                        UserHelper.updateUser(uf);
                                }
                                boolean result=SetMeetingClient.changePwd(uf.getLoginName(), passwd, uf.getPlainPassword());
                                request.setAttribute("xuechuang_syn_rsult",String.valueOf(result));
                                logger.info("result="+result+"<");
                                if(!result)
                                {
                                        throw new ULMSAppException("ͬ����ѧ��Ƶ��ѧϵͳ����",(String)null);
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        request.setAttribute("result","ͬ����ѧ��Ƶ��ѧϵͳ����!���ܴ��û����ܵ�¼��ѧ��Ƶ��ѧϵͳ!���Ժ����ԣ�");
                        //throw new ULMSAppException("ͬ����ѧ��Ƶ��ѧϵͳ����!",null,e);
                }
                logger.info("==============ulms passwd ============== " + passwd);
                MvnMemberHelper bbsHelper=new MvnMemberHelper();
                bbsHelper.updateMvnPass(userID.toString(),passwd);

                dao.updatePWD(userID.toString(), passwd);

                int lmsUserID = Integer.parseInt((String) request.getSession()
                        .getAttribute(LMSConstants.USERID_KEY));
                int lmsOrgID = Integer.parseInt((String) request.getSession()
                        .getAttribute(LMSConstants.USER_ORGID_KEY));

                SysConfigHelper helper = new SysConfigHelper();
                String orgID = (String) request.getSession()
                        .getAttribute(LMSConstants.USER_ORGID_KEY);
                SysConfigForm sysConfigForm = helper.getSysConfig("0");

                if (sysConfigForm.getIsLogLogin().equals("1"))
                {
                        LmslogForm lmslogForm = new LmslogForm();
                        lmslogForm.setLogTypeID(LmslogConstants.LOGTYPE_USER);
                        lmslogForm.setUserID(lmsUserID);
                        lmslogForm.setOrgID(lmsOrgID);
                        lmslogForm.setUserIP(request.getRemoteAddr());
                        lmslogForm.setOperationTypeID(LmslogConstants.OPERATION_SECURETY_CHANGPWD);
                        lmslogForm.setOperationTable("LMSDB");
                        lmslogForm.setOperationObjectID(lmsUserID);
                        lmslogForm.setDescription("�û��޸�����ʱ������¼����־.");

                        LmslogDAO logdao = LmslogDAOFactory.getDAO();
                        logdao.insert(lmslogForm);
                }

                LogUtil.info("admin",
                        "[AddPlanAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
