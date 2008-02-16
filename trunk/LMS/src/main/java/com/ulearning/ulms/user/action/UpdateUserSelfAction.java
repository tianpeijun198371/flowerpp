/**
 * UpdateUserSelfAction.java. 
 * User: huangsb  Date: 2004-5-21 
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. 
 */
package com.ulearning.ulms.user.action;

import com.ulearning.ulms.bbs.helper.MvnMemberHelper;
import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingClient;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingStub;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
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

public class UpdateUserSelfAction extends Action
{
        protected static Log logger = LogFactory.getLog(UpdateUserSelfAction.class);

        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                UserForm uf = (UserForm) form;
                UserDAO dao = UserDAOFactory.getDAO();
                String lastloginDateStr = request.getParameter("lastloginDateStr");
                if ((lastloginDateStr != null) && (lastloginDateStr.trim().length() != 0))
                {
                        String[] tmp = StringUtil.splitString(lastloginDateStr, "-");
                        uf.setLastloginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0", "0", "0"));
                }

                String pwd = uf.getPassword();
                try
                {
                        //�����Ժ��װ���м��
                        //�ж��Ƿ�������������Ŀ
                        if (Config.isXLNProject() && ((uf.getPassword() != null && uf.getPassword().length() >= 1)))
                        {
                                UserForm uf_old = dao.getUser(String.valueOf(uf.getUserID()));
                                logger.info("ͬ����������Ŀѧ���û�!");
                                logger.info("uf.getPassword=" + uf.getPassword() + "<");
                                logger.info("uf_old.getPlainPassword()=" + uf_old.getPlainPassword() + "<");

                                boolean isNewUser=false;
                                if (uf_old.getExternalSystemUserID() == null || uf_old.getExternalSystemUserID().intValue() < 0)
                                {
                                        //������ͬ���û�
                                        int result_int = -1;
                                        SetMeetingStub.UserInformation useInfo = SetMeetingClient.getUserInfoByAccount(uf_old.getLoginName());
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
                                                        logger.info("ͬ����ѧ��Ƶ��ѧϵͳ����");
                                                        throw new ULMSSysException("ͬ����ѧ��Ƶ��ѧϵͳ����", (String) null);
                                                }
                                                isNewUser=true;
                                        }
                                        else
                                        {
                                                uf.setExternalSystemUserID(new Integer(useInfo.getUserId()));
                                        }
                                }

                                if(!isNewUser)
                                {//����û���½�,���޸�����
                                        boolean result = SetMeetingClient.changePwd(uf.getLoginName(), uf.getPassword(), uf_old.getPlainPassword());
                                        request.setAttribute("xuechuang_syn_rsult", String.valueOf(result));

                                        if (!result)
                                        {
                                                throw new ULMSAppException("ͬ����ѧ��Ƶ��ѧϵͳ����!", (String) null);
                                        }
                                }
                        }
                        else
                        {
                                logger.info("��ͬ����������Ŀѧ���û�����!");
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        request.setAttribute("result","ͬ����ѧ��Ƶ��ѧϵͳ����!���ܴ��û����ܵ�¼��ѧ��Ƶ��ѧϵͳ!���Ժ����ԣ�");
                        //throw new ULMSAppException("ͬ����ѧ��Ƶ��ѧϵͳ����!", null, e);
                }

                UserForm userForm = UserHelper.getUser(new Integer(uf.getUserID()).toString());
                if (userForm.getRegisterDate() == null)
                {
                        uf.setRegisterDate(new Date(System.currentTimeMillis()));
                }
                else
                {
                        uf.setRegisterDate(userForm.getRegisterDate());
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
                        if (tmp != null && !tmp.equals(""))
                        {
                                uf.setRemark6(tmp);
                        }


                }
                //�޸�BBS��Ӧ��Ա����
                if(uf.getPassword()!=null)
                {
                    System.out.println("==============ulms passwd ============== " + uf.getPassword());
                    MvnMemberHelper bbsHelper=new MvnMemberHelper();
                    bbsHelper.updateMvnPass(String.valueOf(uf.getUserID()),uf.getPassword());
                }            
                dao.updateUser(uf);
                LogUtil.info("user", "[UpdateUserSelfAction]===========resultScreen = " + resultScreen);
                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
