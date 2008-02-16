/**
 * CourseUserHelper.java.
 * User: fengch  Date: 2004-9-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.helper;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.sysconfig.bean.AutoInformHelper;
import com.ulearning.ulms.admin.sysconfig.bean.SysConfigConstants;
import com.ulearning.ulms.admin.sysconfig.form.AutoInformForm;
import com.ulearning.ulms.cert.Certificate.bean.CertificateImpl;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;
import com.ulearning.ulms.common.dao.ScoreManageDAO;
import com.ulearning.ulms.common.dao.ScoreManageDAOFactory;
import com.ulearning.ulms.common.exceptions.ScoreSysException;
import com.ulearning.ulms.common.helper.HistoryHelper;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.mail.EmailServices;
import com.ulearning.ulms.core.mail.SmtpModel;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseUserDAO;
import com.ulearning.ulms.course.dao.CourseUserDAOFactory;
import com.ulearning.ulms.course.dao.CourseUserDAOImpl;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.exceptions.UserNotExistAppException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.test.grade.bean.PaperUserHelper;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.paper.bean.PaperHelper;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOImpl;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.testbase.PaperBaseConstants;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.evaluate.model.ERecordPointConversionModel;
import com.ulearning.ulms.evaluate.webimpls.EvaluateManageWebImpl;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAOImpl;
import com.ulearning.ulms.tools.assignment.assignprocess.model.AssignProcessModel;
import com.ulearning.ulms.tools.assignment.bean.AssignmentHelper;
import com.ulearning.ulms.tools.assignment.form.AssignmentForm;
import com.ulearning.ulms.tools.message.bean.MessageDAOHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * ��װ�����еĿγ̺�֤���û���ҵ���߼���
 */
public class CourseUserHelper
{
        private static CourseUserDAO courseUserDAO;
        private static ScoreManageDAO scoreManageDAO;
        private static SecurityDAO securityDAO;

        static
        {
                try
                {
                        courseUserDAO = CourseUserDAOFactory.getDAO();
                        scoreManageDAO = ScoreManageDAOFactory.getDAO();
                        securityDAO = SecurityDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * �����û�
         *
         * @param userIDs    ��Ҫ�������û��б�
         * @param states     �û�������״̬
         * @param relationID
         * @param type
         * @throws ULMSSysException
         */
        public static void confirmApply(List userIDs, List states, int relationID,
                                        int type) throws ULMSSysException
        {
                System.out.println(
                        "[CourseUserHelper]confirmApply=========== relationID��" +
                                relationID);
                System.out.println("[CourseUserHelper]confirmApply=========== type��" +
                        type);
                System.out.println(
                        "[CourseUserHelper]confirmApply=========== userIDs.size��" +
                                userIDs.size());
                courseUserDAO.confirmApply(userIDs, states, relationID, type);

                int userID = 0;
                int state;

                // ��Ϊ��ѵ��ʱ��ͬʱҲ�����û���Ӧ�γ̵�״̬
                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        List courseList = null;

                        //�����ѵ���µĿγ��б�
                        CertDAO certDAO = CertDAOFactory.getDAO();
                        int courseID = 0;
                        int roleID = SecurityConstants.ROLE_COURSR_STUDENT;
                        courseList = certDAO.getCourseListFromCert(String.valueOf(
                                relationID), LMSConstants.LEARNING_TUTORIAL);

                        if (courseList != null)
                        {
                                System.out.println(
                                        "[CourseUserHelper]confirmApply=========== ��ѵ������γ�����" +
                                                courseList.size());
                        }
                        else
                        {
                                System.out.println(
                                        "[CourseUserHelper]confirmApply=========== ��ѵ������γ�����Ϊ��");
                        }

                        for (int j = 0; (courseList != null) && (j < courseList.size());
                             j++)
                        {
                                courseID = ((CourseModel) courseList.get(j)).getCourseID();
                                System.out.println(
                                        "[CourseUserHelper]confirmApply=========== �û�" + userID +
                                                " �γ�" + courseID);

                                ArrayList tempUsers = new ArrayList();
                                ArrayList tempState = new ArrayList();

                                for (int k = 0; (userIDs != null) && (k < userIDs.size());
                                     k++)
                                {
                                        userID = ((Integer) userIDs.get(k)).intValue();
                                        state = ((Integer) states.get(k)).intValue();

                                        System.out.println(
                                                "[CourseUserHelper]confirmApply=========== userID��" +
                                                        userID);
                                        System.out.println(
                                                "[CourseUserHelper]confirmApply=========== courseID��" +
                                                        courseID);
                                        System.out.println(
                                                "[CourseUserHelper]confirmApply=========== state��" +
                                                        state);

                                        if (isExisitCourseUserRole(userID, roleID, courseID,
                                                SecurityConstants.USER_COURSE_RELATION))
                                        {
                                                System.out.println(
                                                        "[CourseUserHelper]confirmApply ��ʼ���¿γ�ѧϰ����״̬");
                                                tempUsers.add(new Integer(userID));
                                                tempState.add(new Integer(state));
                                                //��Ϊ��ѵ����ͬʱ����ѧϰ������ѵ��γ̵�״̬
                                                HistoryHelper.update(userID, courseID,
                                                        SecurityConstants.USER_COURSE_RELATION, state,
                                                        new Date(), new Date());
                                        }
                                        else
                                        {
                                                System.out.println(
                                                        "[CourseUserHelper]confirmApply ��ɫ����ѧ�������ø��¿γ�ѧϰ����״̬");
                                        }
                                }

                                //�����û�֤���пγ̵�״̬
                                courseUserDAO.confirmApply(tempUsers, tempState, courseID,
                                        SecurityConstants.USER_COURSE_RELATION);
                        }
                }

                int size = userIDs.size();
                boolean isPass = true;

                for (int i = 0; i < size; i++)
                {
                        userID = ((Integer) (userIDs.get(i))).intValue();
                        state = ((Integer) (states.get(i))).intValue();
                        System.out.println(
                                "[CourseUserHelper]confirmApply=========== userID��" + userID);
                        System.out.println(
                                "[CourseUserHelper]confirmApply=========== relationID��" +
                                        relationID);
                        System.out.println(
                                "[CourseUserHelper]confirmApply=========== state��" + state);

                        if (state == CourseKeys.COURSE_USER_AVAILABLE_STATE)
                        {
                                inform(userID, relationID, type, true, isPass);
                        }

                        System.out.println("[CourseUserHelper]confirmApply ��ʼ����ѧϰ����״̬");
                        // add course or certificate rcord to history
                        HistoryHelper.update(userID, relationID, type, state, new Date(),
                                new Date());
                }
        }

        /**
         * ����һ���γ��û���Ϣ��
         *
         * @param relationID
         * @param type
         * @param userID
         * @return
         * @throws CourseSysException
         */
        public static CourseUserModel getCourseUser(int relationID, int type,
                                                    int userID) throws CourseSysException
        {
                return courseUserDAO.getCourseUser(relationID, type, userID);
        }

       /* public static  CourseUserListModel getCourseStudents(int courseID,int type,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseStudents(courseID,type, pageNo,pageSize);
        } */

        /**
         * �޸��û�
         *
         * @param cum
         * @throws ULMSException
         */
        public static void updateCourseUser(CourseUserModel cum)
                throws ULMSException
        {
                //�ж��û��Ƿ����
                if (!UserHelper.isExistUser(String.valueOf(cum.getUserID())))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]updateCourseUser ====================== �û�������!");

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "�û�������!");
                        throw uneae;
                }

                try
                {
                        courseUserDAO.updateCourseUser(cum);

                        if (Config.getIsIntegrateJieFo())
                        {
                                int rolIDJie;
                                CourseUserDAO dao = CourseUserDAOFactory.getDAO();
                                JieFoTrainClerkForm jff = new JieFoTrainClerkForm();
                                ArrayList roles = cum.getCourseRoles().getRoles();

                                for (int i = 0; i < roles.size(); i++)
                                {
                                        rolIDJie = ((CourseRoleModel) roles.get(i)).getRoleID();

                                        try
                                        {
                                                if ((rolIDJie == 7) || (rolIDJie == 8))
                                                {
                                                        jff.setTrainID(cum.getRelationID());
                                                        jff.setClerkID(cum.getUserID());
                                                        jff.setTrainClerkType(rolIDJie);
                                                        dao.updateJieFoTrainClerk(jff);
                                                        UserHelper.updateLeibie(cum.getUserID(), 5);
                                                }
                                                else
                                                {
                                                        String[] userIDs = {cum.getUserID() + ""};
                                                        dao.deleteJieFoTrainClerk(cum.getRelationID(),
                                                                userIDs);
                                                }
                                        }
                                        catch (Exception e)
                                        {
                                                e.printStackTrace();
                                        }
                                }
                        }
                }
                catch (Exception ex)
                {
                }

                if (isStudent(cum))
                {
                        // update course or certificate rcord to history
                        LogUtil.debug("course",
                                "[CourseUserHelper] ===================  update course or certificate rcord to history");

                        //todo:��ѵ���ѧ����ʱ������
                        if (cum.getType() == SecurityConstants.USER_COURSE_RELATION)
                        {
                                CourseModel cm = CourseHelper.getCourse(cum.getCourseID());
                                cum.setPeriod(cm.getPeriod());
                                cum.setCredit(cm.getCredit());
                        }

                        HistoryHelper.update(cum.getUserID(), cum.getRelationID(),
                                cum.getType(), cum.getScore(), cum.getState(), 0,
                                cum.getPeriod(), cum.getCredit(), cum.getIsPass());
                }
                else
                {
                        //�ж�ѧϰ�����Ƿ��м�¼�����м�¼����״̬ΪCOURSE_USER_NOTAVAILABLE_STATE,��״̬��ʾ�˼�¼��������ʾ��
                        HistoryHelper.update(cum.getUserID(), cum.getRelationID(),
                                cum.getType(), CourseKeys.COURSE_USER_NOTAVAILABLE_STATE);
                }

                if (cum.getType() == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        //add course in cert
                        LogUtil.debug("course",
                                "[CourseUserHelper] ===================  add course in cert ");

                        List courseList = CourseHelper.getCourseList(cum.getRelationID(),
                                cum.getType());
                        LogUtil.info("course",
                                "[CourseUserHelper]===========courseList size = " +
                                        courseList.size());

                        try
                        {
                                if (courseList != null)
                                {
                                        CourseUserModel cum1 = null;
                                        CourseModel courseModel = null;
                                        CourseRoleListModel courseRoles = cum.getCourseRoles();

                                        Date joinTime = new Date();
                                        Date applyTime = new Date();
                                        Date finishedTime = null;

                                        for (int i = 0; i < courseList.size(); i++)
                                        {
                                                LogUtil.info("course",
                                                        "[CourseUserHelper]===========Child courseName = " +
                                                                ((CourseModel) courseList.get(i)).getMasterName());
                                                courseModel = (CourseModel) courseList.get(i);
                                                cum1 = new CourseUserModel(courseModel.getCourseID(),
                                                        SecurityConstants.USER_COURSE_RELATION,
                                                        cum.getUserID(), cum.getState(), "",
                                                        cum.getLoginName(), courseRoles, joinTime,
                                                        applyTime, finishedTime);
                                                cum1.setIsPass(cum.getIsPass());

                                                try
                                                {
                                                        CourseUserHelper.updateCourseUser(cum1);
                                                }
                                                catch (Exception e)
                                                {
                                                        continue;
                                                }
                                        }
                                }
                        }
                        catch (Exception cae)
                        {
                        }
                }
        }

        /**
         * �޸��û�������޸�״̬Ϊ�����������ý���ʱ�� ��
         *
         * @param userID
         * @param relationID
         * @param type
         * @param finishedTime
         * @param state
         * @throws ULMSException
         */
        public static void updateCourseUser(int userID, int relationID, int type,
                                            Date finishedTime, int state) throws ULMSException
        {
                courseUserDAO.updateCourseUser(userID, relationID, type, finishedTime,
                        state);

                //only the user must student
                // add course or certificate rcord to history
                HistoryHelper.update(userID, relationID, type, state, new Date(),
                        new Date());

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        //add course in cert or project
                        List courseList = CourseHelper.getCourseList(relationID, type);
                        LogUtil.info("course",
                                "[CourseUserHelper]addCourseUser===========courseList size = " +
                                        courseList.size());

                        try
                        {
                                if (courseList != null)
                                {
                                        CourseModel courseModel = null;

                                        for (int i = 0; i < courseList.size(); i++)
                                        {
                                                LogUtil.info("course",
                                                        "[CourseUserHelper]===========Child courseName = " +
                                                                ((CourseModel) courseList.get(i)).getMasterName());
                                                courseModel = (CourseModel) courseList.get(i);

                                                try
                                                {
                                                        CourseUserHelper.updateCourseUser(userID,
                                                                courseModel.getCourseID(),
                                                                SecurityConstants.USER_COURSE_RELATION,
                                                                finishedTime, state);
                                                }
                                                catch (Exception e)
                                                {
                                                        continue;
                                                }
                                        }
                                }
                        }
                        catch (Exception cae)
                        {
                        }
                }
        }

        /**
         * ���ӿγ̻���֤���û�.<br>
         * �� Ϊ֤��ʱ,�������û����������֤����.
         *
         * @param cum
         * @throws ULMSException
         */
        public static void addCourseUser(CourseUserModel cum)
                throws ULMSException
        {
                //�ж��û��Ƿ����
                if (!UserHelper.isExistUser(cum.getUserID()))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ====================== �û�������! userID=" +
                                        cum.getUserID());

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "���û�������!");
                        uneae.setErrorKey("error.course.userNotExisit");
                        throw uneae;
                }

                try
                {
                        courseUserDAO.addCourseUser(cum);

                        //add jiefo CourseUser
                        if (Config.getIsIntegrateJieFo())
                        {
                                int rolIDJie;
                                JieFoTrainClerkForm jff = new JieFoTrainClerkForm();
                                CourseUserDAO dao = CourseUserDAOFactory.getDAO();
                                ArrayList roles = cum.getCourseRoles().getRoles();

                                for (int i = 0; i < roles.size(); i++)
                                {
                                        rolIDJie = ((CourseRoleModel) roles.get(i)).getRoleID();

                                        if ((rolIDJie == 7) || (rolIDJie == 8))
                                        {
                                                jff.setTrainID(cum.getRelationID());
                                                jff.setClerkID(cum.getUserID());
                                                jff.setTrainClerkType(rolIDJie);
                                                dao.addJieFoTrainClerk(jff);
                                                UserHelper.updateLeibie(cum.getUserID(), 5);
                                        }
                                }
                        }
                }
                catch (Exception ex)
                {
                }

                if (isStudent(cum))
                {
                        //�����Զ�ѡ��֪ͨ-Sucess
                        if (cum.getState() == CourseKeys.COURSE_USER_AVAILABLE_STATE)
                        {
                                CourseUserHelper.inform(cum.getUserID(), cum.getRelationID(),
                                        cum.getType(), false, true);
                        }

                        //todo :��ѵ���ѧ����ʱ������
                        if (cum.getType() == SecurityConstants.USER_COURSE_RELATION)
                        {
                                CourseModel cm = CourseHelper.getCourse(cum.getCourseID());
                                cum.setPeriod(cm.getPeriod());
                                cum.setCredit(cm.getCredit());
                        }

                        // add course or certificate rcord to history
                        HistoryHelper.update(cum.getUserID(), cum.getRelationID(),
                                cum.getType(), cum.getScore(), cum.getState(), 0,
                                cum.getPeriod(), cum.getCredit(), cum.getIsPass());
                }

                if (cum.getType() == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        //add course in cert or project
                        List courseList = CourseHelper.getCourseList(cum.getRelationID(),
                                cum.getType());
                        LogUtil.info("course",
                                "[CourseUserHelper]===========courseList size = " +
                                        courseList.size());

                        if (courseList != null)
                        {
                                CourseUserModel cum1 = null;
                                CourseModel courseModel = null;
                                CourseRoleListModel courseRoles = cum.getCourseRoles();

                                Date joinTime = new Date();
                                Date applyTime = new Date();
                                Date finishedTime = null;

                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        LogUtil.info("course",
                                                "[CourseUserHelper]===========Child courseName = " +
                                                        ((CourseModel) courseList.get(i)).getMasterName());
                                        courseModel = (CourseModel) courseList.get(i);
                                        cum1 = new CourseUserModel(courseModel.getCourseID(),
                                                SecurityConstants.USER_COURSE_RELATION,
                                                cum.getUserID(), cum.getState(), "",
                                                cum.getLoginName(), courseRoles, joinTime,
                                                applyTime, finishedTime);
                                        cum1.setIsPass(cum.getIsPass());

                                        try
                                        {
                                                CourseUserHelper.addCourseUser(cum1);
                                        }
                                        catch (Exception e)
                                        {
                                                LogUtil.debug("course",
                                                        "[CourseUserHelper]1 =================== in cert addCourseUser error,continue!");
                                                e.printStackTrace();

                                                continue;
                                        }
                                }
                        }
                }
        }

        /**
         * �������������ѵ����û�
         *
         * @param cum
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static void addCertUser(CourseUserModel cum)
        {
                courseUserDAO.addCertUser(cum);
        }

        /**
         * �жϴ��û��������ѵ�����Ƿ���ڣ�������ڷ���true���򷵻�false
         *
         * @param userID
         * @param relationID
         * @param type
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static boolean isaddCertUser(int userID, int relationID, int type)
        {
                boolean iscertuser = false;
                iscertuser = courseUserDAO.isaddCertUser(userID, relationID, type);

                return iscertuser;
        }

        /**
         * ���ӿγ̻���֤���û�.<br>
         *
         * @param relationCode
         * @param type
         * @param loginName
         * @param roleName
         * @param aspID
         * @throws ULMSException
         */
        public static void addCourseUser(String relationCode, int type,
                                         String loginName, String roleName, int aspID) throws ULMSException
        {
                int relationID = 0;
                int userID = UserHelper.getUserID(loginName);
                int roleID = securityDAO.getRoleID(roleName, 3);

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        relationID = CertHelper.getCertID(relationCode, aspID);
                }
                else if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        relationID = (CourseHelper.getCourse(aspID, relationCode)).getCourseID();
                }

                addCourseUser(relationID, type, userID, roleID);

                //addCourseUser( relationID,  type,  userID, CourseKeys.COURSE_USER_AVAILABLE_STATE,  roleID);
        }

        /**
         * ���ӿγ̻���֤���û�
         *
         * @param relationID
         * @param type
         * @param userID
         * @param roleID
         * @throws ULMSException
         */
        public static void addCourseUser(int relationID, int type, int userID,
                                         int roleID) throws ULMSException
        {
                addCourseUser(relationID, type, userID,
                        CourseKeys.COURSE_USER_AVAILABLE_STATE, roleID);
        }

        /**
         * ���ӿγ̻���֤���û�
         *
         * @param relationID
         * @param type
         * @param userID
         * @param state
         * @param roleID
         * @throws ULMSException
         */
        public static void addCourseUser(int relationID, int type, int userID,
                                         int state, int roleID) throws ULMSException
        {
                LogUtil.debug("course",
                        "[CourseUserHelper]addCourseUser ===================addCourseUser start!");

                //�ж��û��Ƿ����
                if (!UserHelper.isExistUser(userID))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ====================== �û�������! userID=" +
                                        userID);

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "���û�������!");
                        uneae.setErrorKey("error.course.userNotExisit");
                        throw uneae;
                }

                //�ж��û��Ƿ����
                if (!UserHelper.isExistUser(userID))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ====================== �û�������! userID=" +
                                        userID);

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "���û�������!");
                        uneae.setErrorKey("error.course.userNotExisit");
                        throw uneae;
                }

                try
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ===================insert c_user_tab start!");
                        courseUserDAO.addCourseUser(relationID, type, userID, state, roleID);
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ===================insert c_user_tab end!");

                        //add jiefo CourseUser
                        if (Config.getIsIntegrateJieFo())
                        {
                                int rolIDJie;
                                JieFoTrainClerkForm jff = new JieFoTrainClerkForm();
                                CourseUserDAO dao = CourseUserDAOFactory.getDAO();

                                rolIDJie = roleID;

                                if ((rolIDJie == 7) || (rolIDJie == 8))
                                {
                                        jff.setTrainID(relationID);
                                        jff.setClerkID(userID);
                                        jff.setTrainClerkType(rolIDJie);

                                        dao.addJieFoTrainClerk(jff);
                                        UserHelper.updateLeibie(userID, 5);
                                }
                        }
                }
                catch (Exception ex)
                {
                }

                if (roleID == SecurityConstants.ROLE_COURSR_STUDENT)
                {
                        //�����Զ�ѡ��֪ͨ-Sucess
                        if (state == CourseKeys.COURSE_USER_AVAILABLE_STATE)
                        {
                                CourseUserHelper.inform(userID, relationID, type, false, true);
                        }

                        // add course or certificate rcord to history
                        HistoryHelper.update(userID, relationID, type, state, new Date(),
                                new Date());
                }

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        //add course in cert or project
                        List courseList = CourseHelper.getCourseList(relationID, type);
                        LogUtil.info("course",
                                "[CourseUserHelper]addCourseUser===========courseList size = " +
                                        courseList.size());

                        if (courseList != null)
                        {
                                CourseModel courseModel = null;

                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        LogUtil.info("course",
                                                "[CourseUserHelper]2===========in cert Child courseName = " +
                                                        ((CourseModel) courseList.get(i)).getMasterName());
                                        courseModel = (CourseModel) courseList.get(i);

                                        try
                                        {
                                                CourseUserHelper.addCourseUser(courseModel.getCourseID(),
                                                        SecurityConstants.USER_COURSE_RELATION, userID,
                                                        state, roleID);
                                        }
                                        catch (Exception e)
                                        {
                                                LogUtil.debug("course",
                                                        "[CourseUserHelper]2 =================== in cert addCourseUser error,continue!");
                                                e.printStackTrace();

                                                continue;
                                        }
                                }
                        }
                }
        }

        /**
         * ɾ���γ��û�
         *
         * @param userID
         * @param relationID
         * @param type
         * @throws CourseSysException
         */
        public static void deleteCourseUser(int userID, int relationID, int type)
                throws CourseSysException
        {
                try
                {
                        courseUserDAO.deleteCourseUser(userID, relationID, type);
                        scoreManageDAO.delete(userID, relationID, type);

                        //delete jiefo trainclerk
                        if (Config.getIsIntegrateJieFo())
                        {
                                CourseUserModel cum = courseUserDAO.getCourseUser(relationID,
                                        SecurityConstants.USER_COURSE_RELATION, userID);
                                CourseRoleListModel crl = cum.getCourseRoles();
                                ArrayList roleList = crl.getRoles();

                                for (int j = 0; j < roleList.size(); j++)
                                {
                                        int roleJef = Integer.parseInt((String) roleList.get(j));

                                        if ((roleJef == 7) || (roleJef == 8))
                                        {
                                                String[] str = {String.valueOf(userID)};

                                                courseUserDAO.deleteJieFoTrainClerk(relationID, str);
                                        }
                                }
                        }
                }
                catch (Exception e)
                {
                        //e.printStackTrace();
                }

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        //add course in cert or project
                        List courseList = CourseHelper.getCourseList(relationID, type);
                        LogUtil.info("course",
                                "[CourseUserHelper]addCourseUser===========courseList size = " +
                                        courseList.size());

                        try
                        {
                                if (courseList != null)
                                {
                                        CourseModel courseModel = null;

                                        for (int i = 0; i < courseList.size(); i++)
                                        {
                                                LogUtil.info("course",
                                                        "[CourseUserHelper]===========Child courseName = " +
                                                                ((CourseModel) courseList.get(i)).getMasterName());
                                                courseModel = (CourseModel) courseList.get(i);

                                                try
                                                {
                                                        courseUserDAO.deleteCourseUser(userID,
                                                                courseModel.getCourseID(),
                                                                SecurityConstants.USER_COURSE_RELATION);
                                                        scoreManageDAO.delete(userID,
                                                                courseModel.getCourseID(),
                                                                SecurityConstants.USER_COURSE_RELATION);
                                                }
                                                catch (Exception e)
                                                {
                                                        continue;
                                                }
                                        }
                                }
                        }
                        catch (Exception cae)
                        {
                        }
                }
        }

        public static void delJieFoCourseUser(int courseID, String[] userIDs)
                throws CourseSysException, CourseAppException
        {
                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                courseUserDAO.deleteJieFoTrainClerk(courseID, userIDs);
                        }
                }
                catch (Exception cae)
                {
                }
        }

        /**
         * �ж��Ƿ�Ϊ�����ߡ�
         *
         * @param RelationID
         * @param Type
         * @param UserID
         * @return
         * @throws CourseSysException
         */
        public static boolean isCreator(int RelationID, int Type, int UserID)
                throws CourseSysException
        {
                return courseUserDAO.isCreator(RelationID, Type, UserID);
        }

        /**
         * ������������û��б�
         *
         * @param relationID
         * @param type
         * @return
         * @throws CourseSysException
         */
        public static CourseUserListModel getCourseAllUsers(int relationID, int type)
                throws CourseSysException
        {
                int pageNo = 0;
                int pageSize = 100000;

                return courseUserDAO.getCourseAllUsers(relationID, type, pageNo,
                        pageSize);
        }

        /**
         * return the course user list by state and role.
         *
         * @throws CourseSysException
         */
        public static CourseUserListModel getCourseUsers(int relationID, int type,
                                                         int role, int state) throws CourseSysException
        {
                return courseUserDAO.getCourseUsers(relationID, type, role, state);
        }

        /**
         * return courseuser state's resource key��
         * ���������״̬��ʾ�����ã�
         * ��Ҫ���ڳ�ѧϰ������ĵط�������ѡ�ι�����ж�ѧԱ��״̬
         */
        public static String getCourseUserStateKey(int state)
        {
                String courseStateKey = null;

                if (state == CourseKeys.COURSE_USER_AVAILABLE_STATE)
                {
                        courseStateKey = "message.status.available";
                }
                else if (state == CourseKeys.COURSE_USER_UNAVAILABLE_STATE)
                {
                        courseStateKey = "message.status.unavailable";
                }
                else if (state == CourseKeys.COURSE_USER_APPLY_STATE)
                {
                        courseStateKey = "message.status.applying";
                }
                else if (state == CourseKeys.COURSE_USER_FINISH_STATE)
                {
                        courseStateKey = "message.status.finished";
                }
                else if (state == CourseKeys.COURSE_USER_NOT_CHECK_STATE)
                {
                        courseStateKey = "message.status.confirming";
                }
                else if (state == CourseKeys.COURSE_USER_NOT_CHECK_STATE)
                {
                        courseStateKey = "message.status.changeuser";
                }
                else
                {
                        LogUtil.info("course", "[CourseUserHelper]-----state=" + state);
                        LogUtil.info("course",
                                "[CourseUserHelper]-----no relational key,so defaulr is unavailable key");
                        courseStateKey = "message.status.unavailable";
                }

                return courseStateKey;
        }

        /**
         * return courseuser study process state's resource key��
         * ���������״̬��ʾ��ѧϰ�У�����Ϊ��ѧϰ�����ﶼ��ѧ����
         * ��Ҫ������ѧϰ������ж�ѧԱ��״̬
         */
        public static String getCourseUserProcessStateKey(int state)
        {
                String courseStateKey = null;

                if (state == CourseKeys.COURSE_USER_AVAILABLE_STATE)
                {
                        courseStateKey = "message.status.available_student";
                }
                else if (state == CourseKeys.COURSE_USER_UNAVAILABLE_STATE)
                {
                        courseStateKey = "message.status.unavailable";
                }
                else if (state == CourseKeys.COURSE_USER_APPLY_STATE)
                {
                        courseStateKey = "message.status.applying";
                }
                else if (state == CourseKeys.COURSE_USER_FINISH_STATE)
                {
                        courseStateKey = "message.status.finished";
                }
                else if (state == CourseKeys.COURSE_USER_NOT_CHECK_STATE)
                {
                        courseStateKey = "message.status.confirming";
                }
                else
                {
                        LogUtil.info("course", "[CourseUserHelper]-----state=" + state);
                        LogUtil.info("course",
                                "[CourseUserHelper]-----no relational key,so defaulr is unavailable key");
                        courseStateKey = "message.status.unavailable";
                }

                return courseStateKey;
        }

        /**
         * ����ѧԱ��������������������ѧԱ
         *
         * @param relationID
         * @param type
         * @return
         * @throws CourseSysException
         */
        public static int getTotalNumberByCourseStudent(int relationID, int type)
                throws CourseSysException
        {
                return courseUserDAO.getTotalNumberByCourseStudent(relationID, type);
        }

        /**
         * �жϿγ��û��Ƿ���ѧ��.
         *
         * @param cum
         * @return boolean
         */
        public static boolean isStudent(CourseUserModel cum)
        {
                CourseRoleListModel courseRoles = cum.getCourseRoles();
                ArrayList roles = courseRoles.getRoles();
                CourseRoleModel crm = null;
                int roleID;

                for (int i = 0; i < roles.size(); i++)
                {
                        crm = (CourseRoleModel) roles.get(i);
                        roleID = crm.getRoleID();

                        if (roleID == SecurityConstants.ROLE_COURSR_STUDENT)
                        {
                                return true;
                        }
                }

                return false;
        }

        /**
         * ��ѧ��ѡ�ν�������Զ�֪ͨ
         *
         * @param userID
         * @param relationID
         * @param type
         * @param isNeedConfirm
         * @param isPass
         */
        public static void inform(int userID, int relationID, int type,
                                  boolean isNeedConfirm, boolean isPass)
        {
                try
                {
                        String orgID = "0"; //����ID,�ֶ���Ϊ0;

                        //Ĭ��typeΪѧ������γ̱���׼֪ͨ
                        int messageType = SysConfigConstants.SYS_CONFIG_APPLY_COURSE_SUC_EVENT;

                        if (!isPass) //����typeΪѧ������γ̱��ܾ�֪ͨ
                        {
                                messageType = SysConfigConstants.SYS_CONFIG_APPLY_COURSE_FAIL_EVENT;
                        }

                        AutoInformHelper autoInformHelper = new AutoInformHelper();
                        AutoInformForm autoInformForm = autoInformHelper.getAutoInformByIDandType(orgID,
                                messageType);
                        boolean isMsg = (StringUtil.parseInt(autoInformForm.getIsMSG()) == 1);
                        boolean isMail = (StringUtil.parseInt(autoInformForm.getIsMail()) == 1);

                        MessageDAOHelper mh = new MessageDAOHelper();
                        int sender = -1;
                        UserForm user = UserHelper.getUser(String.valueOf(userID));
                        String recieverName = user.getLoginName();
                        int reciever = userID;
                        String title = "ѡ��֪ͨ";

                        String relationName = "";
                        String name = null;
                        String certModuleName = "��ѵ��"; //Config.getCERT_I18N_NAME();
                        String courseModulename = "�γ�";

                        //Config.getCOURSE_I18N_NAME();
                        if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                relationName = CertHelper.getCert(relationID).getName();
                                name = certModuleName;
                                title += ("[" + name + "��" + relationName + "]");
                        }
                        else if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                relationName = CourseHelper.getCourse(relationID).getName();
                                name = courseModulename;
                                title += ("[" + name + "��" + relationName + "]");
                        }

                        String content = null;

                        if (isMsg)
                        {
                                LogUtil.debug("course", "[CourseUserHelper]inform--������Ϣ!");
                                LogUtil.debug("course",
                                        "[CourseUserHelper]inform--reciever=" + reciever);
                                content = getInformTemplate(relationID, type, recieverName,
                                        isNeedConfirm, isPass, true);

                                mh.insertMessage(sender, recieverName, reciever, title,
                                        content, type);
                        }
                        else
                        {
                                LogUtil.debug("course", "[CourseUserHelper]inform--��������Ϣ!");
                        }

                        if (isMail)
                        {
                                String userMail = user.getMail();
                                LogUtil.debug("course", "[CourseUserHelper]inform--����Mail!");
                                LogUtil.debug("course",
                                        "[CourseUserHelper]inform--userMail=" + userMail);
                                content = getInformTemplate(relationID, type, recieverName,
                                        isNeedConfirm, isPass, false);

                                if ((userMail != null) && !userMail.equals(""))
                                {
                                        List list = new ArrayList();
                                        list.add(userMail);

                                        SmtpModel sm = new SmtpModel();
                                        sm.setSubject(title);
                                        sm.setBody(content);

                                        sm.setSendTo(list);
                                        sm.setSendFrom("ulms@edu-edu.com.cn");
                                        EmailServices.sendMail(sm);
                                }
                        }
                        else
                        {
                                LogUtil.debug("course", "[CourseUserHelper]inform--������Mail!");
                        }
                }
                catch (Exception e)
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]inform-----error:" + e.getMessage());
                        e.printStackTrace();
                }
        }

        /**
         * ��type=5ʱ���γ��û�������ҵ֪ͨ��type=6ʱ���Ϳ���֪ͨ
         *
         * @param userID
         * @param relationID
         * @param type
         */
        public static void informhomework(int userID, int relationID, int type)
                throws CourseSysException
        {
                int sender = -1;
                UserForm user = UserHelper.getUser(String.valueOf(userID));
                String recieverName = user.getLoginName();
                int reciever = userID;
                String title = null;
                String content = null;
                String relationName = CourseHelper.getCourse(relationID).getName();

                if (type == 5)
                {
                        title = "��ҵ֪ͨ";
                        content = "���ã�" + recieverName + "<br><br>" + "������Ŀγ�--" +
                                relationName + "-�������µ���ҵ��<br><br>" + "�뾡�������ҵ��";
                }
                else if (type == 6)
                {
                        title = "����֪ͨ";
                        content = "���ã�" + recieverName + "<br><br>" + "������Ŀγ�--" +
                                relationName + "-�������µĿ��ԣ�<br><br>" + "�뾡����ɿ��ԣ�";
                }

                MessageDAOHelper mh = new MessageDAOHelper();
                mh.insertMessage(sender, recieverName, reciever, title, content, type);
        }

        /**
         * gererate the inform-template doc.<br>
         *
         * @param relationID
         * @param type
         * @param recieverName
         * @param isNeedConfirm
         * @param isPass
         * @return
         * @throws CourseSysException
         */
        private static String getInformTemplate(int relationID, int type,
                                                String recieverName, boolean isNeedConfirm, boolean isPass,
                                                boolean isMessage) throws CourseSysException
        {
                String relationName = "";
                String name = null;
                String certModuleName = "��ѵ��"; //Config.getCERT_I18N_NAME();
                String courseModulename = "�γ�";

                //Config.getCOURSE_I18N_NAME();
                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        relationName = CertHelper.getCert(relationID).getName();
                        name = certModuleName;
                }
                else if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        relationName = CourseHelper.getCourse(relationID).getName();
                        name = courseModulename;
                }

                String content = "<p style=\"font-size:10pt;\">";
                content += ("���ã�" + recieverName + "<br><br>");

                if (isNeedConfirm)
                {
                        if (isPass)
                        {
                                if (isMessage)
                                {
                                        if (type == SecurityConstants.USER_COURSE_RELATION)
                                        {
                                                /*                                                content += "�������" + name + "-" +
                                           relationName +
                                           "���Ѿ���" +
                                           "��׼��<br><br>" +
                                           "�뼴�̽����<a href=\"" + Config.getulmsURL() +
                                           "/mystudy/index.jsp?courseID=" + relationID +
                                           "\" target=_parent>" + name + "</a>ѧϰ��";*/
                                                content += ("�������" + name + "-" + relationName +
                                                        "���Ѿ���" + "��׼��<br><br>" +
                                                        "�뼴�̽���<a href=javascript:openwindow(" + relationID +
                                                        ")><u>" + name + "</u></a>ѧϰ��");
                                        }
                                        else
                                        {
                                                content += ("�������" + name + "-" + relationName +
                                                        "���Ѿ���" + "��׼��<br><br>" + "�뼴�̽����" + name + "ѧϰ��");
                                        }
                                }
                                else
                                {
                                        content += ("�������" + name + "-" + relationName + "���Ѿ���" +
                                                "��׼��<br><br>" + "�뼴��<a href=\"" + Config.getulmsURL() +
                                                "\" target=_blank><u>��½ƽ̨</u></a>�����" + name + "ѧϰ��");
                                }
                        }
                        else
                        {
                                content += ("�Բ����������" + name + "-" + relationName + "���Ѿ����ܾ���");
                        }
                }
                else
                {
                        if (isMessage)
                        {
                                if (type == SecurityConstants.USER_COURSE_RELATION)
                                {
                                        content += ("���Ѿ�����" + name + "-" + relationName +
                                                "�ɹ���<br><br>" + "�뼴�̽����<a href=javascript:enterCourse(" +
                                                relationID + ")><u>" + name + "</u></a>ѧϰ��");
                                }
                                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                                {
                                        content += ("���Ѿ�����" + name + "-" + relationName +
                                                "�ɹ���<br><br>" + "�뼴�̽����<a href=javascript:enterCert(" +
                                                relationID + ")><u>" + name + "</u></a>ѧϰ��");
                                }
                                else
                                {
                                        content += ("���Ѿ�����" + name + "-" + relationName +
                                                "�ɹ���<br><br>" + "�뼴�̽����<u>" + name + "</u>ѧϰ��");
                                }
                        }
                        else
                        {
                                content += ("���Ѿ�����" + name + "-" + relationName +
                                        "�ɹ���<br><br>" + "�뼴��<a href=\"" + Config.getulmsURL() +
                                        "\" target=_blank><u>��½ƽ̨</u></a>�����" + name + "ѧϰ��");
                        }
                }

                //String aspName = CourseHelper.getASPName(relationID, type);
                String aspName = CourseHelper.getASPName(relationID, type);
                content += "<br><br><br><br><hr align=\"left\" width=\"80%\" noshade>";
                content += ("<a href=\"" + Config.getulmsURL() +
                        "\" target=_blank><u>" + aspName + "</u></a><br>");
                content += DateTimeUtil.FormatDateToWeb2(new Date());

                content += "<br></p><p style=\"font-size:9pt; color:#808080\">����ϢΪϵͳ�Զ����ɣ��벻Ҫ�ظ���</p>";
                LogUtil.debug("course", "[CourseUserHelper]----content=" + content);

                return content;
        }

        /**
         * �жϿγ�/֤���ɫ�Ƿ�Ϸ� .<br>
         *
         * @param rolename
         * @return boolean
         */
        public static boolean isValidateCourseRole(String rolename)
        {
                int roleID = SecurityHelper.getRoleID(rolename,
                        SecurityConstants.USER_COURSE_RELATION);

                if (roleID > 0)
                {
                        return true;
                }

                return false;
        }

        /**
         * �жϿγ�/֤�����Ƿ�Ϸ�.<br>
         *
         * @param relationCode
         * @param type
         * @return boolean
         */
        public static boolean isValidateRelationCode(String relationCode, int type,
                                                     int aspID)
        {
                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        int retID = CertHelper.getCertID(relationCode, aspID);

                        if (retID == 0)
                        {
                                return false;
                        }
                }
                else if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        try
                        {
                                CourseModel cm = CourseHelper.getCourse(aspID, relationCode);

                                if (cm == null)
                                {
                                        return false;
                                }
                        }
                        catch (CourseSysException e)
                        {
                                e.printStackTrace();
                        }
                }

                return true;
        }

        /**
         * �жϸÿγ�/֤���û���ɫ�Ƿ����.<br>
         *
         * @param relationCode
         * @param type
         * @return boolean
         */
        public static boolean isExisitCourseUserRole(String loginName,
                                                     String roleName, String relationCode, int type, int aspID)
        {
                int userID = UserHelper.getUserID(loginName);
                int roleID = SecurityHelper.getRoleID(roleName, type);
                int relationID = 0;

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        relationID = CertHelper.getCertID(relationCode, aspID);
                }
                else if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        try
                        {
                                relationID = CourseHelper.getCourse(aspID, relationCode)
                                        .getCourseID();
                        }
                        catch (CourseSysException e)
                        {
                                e.printStackTrace();
                        }
                }

                return SecurityHelper.isExisitCourseUserRole(userID, roleID,
                        relationID, SecurityConstants.USER_COURSE_RELATION);
        }

        /**
         * �жϸÿγ�/֤���û���ɫ�Ƿ����.<br>
         *
         * @param userID
         * @param roleID
         * @param relationID
         * @param type
         * @return boolean
         */
        public static boolean isExisitCourseUserRole(int userID, int roleID,
                                                     int relationID, int type)
        {
                return SecurityHelper.isExisitCourseUserRole(userID, roleID,
                        relationID, type);
        }

        /**
         * �ж��û��Ƿ��ڴ�ASP����.<br>
         *
         * @param loginName
         * @param aspID
         * @return boolean
         */
        public static boolean isValidateUser(String loginName, int aspID)
        {
                return UserHelper.isExistUser(loginName, aspID);
        }

        /**
         * delete relational user data.
         *
         * @param userID
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static void deleteByUserID(int userID)
                throws CourseSysException, CourseAppException
        {
                try
                {
                        scoreManageDAO.deleteByUserID(userID);
                }
                catch (ScoreSysException ex)
                {
                        throw new CourseSysException(ex);
                }

                if (Config.getIsIntegrateJieFo())
                {
                        courseUserDAO.deleteJieFoTrainByClerk(String.valueOf(userID));
                }

                courseUserDAO.deleteByUserID(userID);
        }

        public static int countAllCourseUsers(int relationID, int type)
                throws CourseSysException
        {
                return courseUserDAO.countAllCourseUsers(relationID, type);
        }

        public static int countCourseUsers(int relationID, int type, int roleID,
                                           int state) throws CourseSysException
        {
                return courseUserDAO.countCourseUsers(relationID, type, roleID, state);
        }

        public static CourseUserListModel getAllCourseUsers(int relationID,
                                                            int type, int first, int max) throws CourseSysException
        {
                return courseUserDAO.getAllCourseUsers(relationID, type, first, max);
        }

        /*    public static CourseUserListModel searchCourseUsers(UserForm uf, int relationID, int type, int first, int max)
          throws CourseSysException {
          return courseUserDAO.searchCourseUsers(uf, relationID, type, first, max);
          }
        */
        public static CourseUserListModel searchCourseUsers(UserForm uf,
                                                            int relationID, int type, int first, int max) throws CourseSysException
        {
                CourseUserListModel culm = courseUserDAO.searchCourseUsers(uf,
                        relationID, type, first, max);

                if ((uf.getCatalogID() == 1) && (uf.getLoginName() != null))
                {
                        OrganDAO odao = OrganDAOFactory.getDAO();
                        List lt = odao.getOrganChildbyOrgID(uf.getCatalogID());

                        for (int i = 0; i < lt.size(); i++)
                        {
                                uf.setCatalogID(((OrganForm) lt.get(i)).getOrgID());

                                CourseUserListModel tempculm = courseUserDAO.searchCourseUsers(uf,
                                        relationID, type, first, max);
                                culm.getCourseUsers().addAll(tempculm.getCourseUsers());
                                culm.setPageCount(culm.getPageCount() +
                                        tempculm.getPageCount());
                                culm.setPageNo(culm.getPageNo() + tempculm.getPageNo());
                                culm.setPageSize(culm.getPageSize() + tempculm.getPageSize());
                        }
                }

                return culm;
        }

        public static CourseUserListModel searchCourseUsersByRole(UserForm uf,
                                                            int relationID, int roleID,int type, int first, int max) throws CourseSysException
        {
                CourseUserListModel culm = courseUserDAO.searchCourseUsersByRole(uf,
                        relationID,roleID ,type, first, max);

                if ((uf.getCatalogID() == 1) && (uf.getLoginName() != null))
                {
                        OrganDAO odao = OrganDAOFactory.getDAO();
                        List lt = odao.getOrganChildbyOrgID(uf.getCatalogID());

                        for (int i = 0; i < lt.size(); i++)
                        {
                                uf.setCatalogID(((OrganForm) lt.get(i)).getOrgID());

                                CourseUserListModel tempculm = courseUserDAO.searchCourseUsersByRole(uf,
                                        relationID, roleID,type, first, max);
                                culm.getCourseUsers().addAll(tempculm.getCourseUsers());
                                culm.setPageCount(culm.getPageCount() +
                                        tempculm.getPageCount());
                                culm.setPageNo(culm.getPageNo() + tempculm.getPageNo());
                                culm.setPageSize(culm.getPageSize() + tempculm.getPageSize());
                        }
                }

                return culm;
        }
        /**
         * Gets the graduate grade from database .
         *
         * @param userID
         * @param gradeRate the rate between the three types grade
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static double[] getCourseGrade(int userID, double[] gradeRate)
                throws CourseSysException, CourseAppException
        {
                double[] grade = new double[5];

                try
                {
                        scoreManageDAO.deleteByUserID(userID);
                }
                catch (ScoreSysException ex)
                {
                        throw new CourseSysException(ex);
                }

                return grade;
        }

        /**
         * Gets the daily grade according to the score and the rate.
         *
         * @param userID
         * @param gradeRate
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static double getDailyGrade(int courseID, int userID,
                                           double[] gradeRate) throws CourseSysException, CourseAppException
        {
                double grade = 0;

                try
                {
                        //EvaluateManageDAOImpl evaluateManageDAOImpl = new EvaluateManageDAOImpl();
                        CourseUserDAOImpl cud = new CourseUserDAOImpl();

                        //grade = evaluateManageDAOImpl.getERecord(userID);
                        EvaluateManageWebImpl evaluateManageWebImpl = new EvaluateManageWebImpl();
                        List list = evaluateManageWebImpl.getPointConversion();

                        List Uexpr = cud.getCourseUserExper(String.valueOf(courseID), "",
                                String.valueOf(userID));

                        if ((Uexpr != null) && (Uexpr.size() > 0))
                        {
                                double bestScore = ((ERecordPointConversionModel) list.get(2)).getPoint();
                                grade = Double.parseDouble((String) ((Map) Uexpr.get(0)).get(
                                        "exper"));

                                //double  bestScore = Double.parseDouble((String)((Map)Uexpr.get(0)).get("exper"));
                                if (bestScore <= grade)
                                {
                                        grade = 100;
                                }
                                else
                                {
                                        grade = (grade / bestScore) * 100;
                                }

                                grade = grade * gradeRate[0];
                        }
                        else
                        {
                                grade = 0;
                        }
                }
                catch (ScoreSysException ex)
                {
                        throw new CourseSysException(ex);
                }

                return grade;
        }

        /**
         * Gets the assignment grade according to the score and the rate.
         *
         * @param courseID
         * @param userID
         * @param gradeRate
         * @param certificateForm
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static double getAssignmentGrade(int courseID, int userID,
                                                double[] gradeRate, CertificateForm certificateForm)
                throws CourseSysException, CourseAppException
        {
                double grade = 0;
                AssignProcessDAOImpl assignProcessDAOImpl = new AssignProcessDAOImpl();

                AssignmentHelper helper = new AssignmentHelper();
                List assignmentList = helper.getRootAssignmentList(courseID);

                double rateZuoyeCishu = StringUtil.parseInt(certificateForm.getCZuyeci());
                double rateZuoyeChenji = StringUtil.parseInt(certificateForm.getCZuoyeGrade());
                double zuoyeManfen = StringUtil.parseInt(certificateForm.getCZuoyeAllGrade());
                double weijiaoZuoyeChenji = StringUtil.parseInt(certificateForm.getCZuoyeMoren());
                double totalCishu_Chenji = rateZuoyeCishu + rateZuoyeChenji;

                DebugUtil.print(
                        "[getAssignmentGrade]===================== rateZuoyeCishu = " +
                                rateZuoyeCishu);
                DebugUtil.print(
                        "[getAssignmentGrade]===================== rateZuoyeChenji = " +
                                rateZuoyeChenji);
                DebugUtil.print(
                        "[getAssignmentGrade]===================== zuoyeManfen = " +
                                zuoyeManfen);
                DebugUtil.print(
                        "[getAssignmentGrade]===================== weijiaoZuoyeChenji = " +
                                weijiaoZuoyeChenji);
                DebugUtil.print(
                        "[getAssignmentGrade]===================== totalCishu_Chenji = " +
                                totalCishu_Chenji);

                int[] assignIDs = new int[assignmentList.size()];

                for (int ik = 0; ik < assignmentList.size(); ik++)
                {
                        AssignmentForm aForm = (AssignmentForm) assignmentList.get(ik);
                        assignIDs[ik] = aForm.getAssignmentID();
                }

                double it = 0;
                double ittemp = 0;

                try
                {
                        //todo Ӧ��д�ɺ�userID�Լ�courseID����
                        List a1 = assignProcessDAOImpl.getAssignProcess(Integer.toString(
                                courseID), userID);
                        List submitAssign = new ArrayList();

                        for (int ik = 0; ik < a1.size(); ik++)
                        {
                                AssignProcessModel bb = (AssignProcessModel) a1.get(ik);

                                for (int ik1 = 0; ik1 < assignIDs.length; ik1++)
                                {
                                        if (bb.getRelationID() == assignIDs[ik1])
                                        {
                                                submitAssign.add(bb);
                                        }
                                }
                        }

                        //������ҵ������ռ�ɼ�
                        if (assignmentList.size() == 0)
                        {
                                it = 0;
                        }
                        else
                        {
                                it = (double) submitAssign.size() / ((double) assignmentList.size()) * (rateZuoyeCishu / totalCishu_Chenji * 100);
                        }

                        DebugUtil.print("[getAssignmentGrade]===================== it = " +
                                it);

                        //������ҵ�ɼ���ռ����
                        if (StringUtil.parseInt(certificateForm.getCZuoyeGrade()) == 0)
                        {
                                ittemp = 0;
                        }
                        else
                        {
                                if (submitAssign.size() != 0)
                                {
                                        //�����ѽ���ҵ�ɼ�
                                        for (int ik = 0; ik < submitAssign.size(); ik++)
                                        {
                                                AssignProcessModel bb = (AssignProcessModel) submitAssign.get(ik);

                                                if (rateZuoyeChenji == 0)
                                                {
                                                        ittemp = 0;
                                                }
                                                else
                                                {
                                                        ittemp += (StringUtil.parseInt(bb.getScore()) / zuoyeManfen * 100);
                                                }
                                        }
                                }

                                DebugUtil.print(
                                        "[getAssignmentGrade]===================== ittemp0 = " +
                                                ittemp);

                                //����δ����ҵ�ĳɼ�
                                if (weijiaoZuoyeChenji > 0)
                                {
                                        for (int assi = 0;
                                             assi < (assignmentList.size() -
                                                     submitAssign.size()); assi++)
                                        {
                                                //�ж��ܳɼ��Ƿ�Ϊ0
                                                if (zuoyeManfen == 0)
                                                {
                                                        ittemp = 0;
                                                }
                                                else
                                                {
                                                        ittemp += (weijiaoZuoyeChenji / zuoyeManfen * 100);
                                                }
                                        }
                                }
                        }

                        DebugUtil.print(
                                "[getAssignmentGrade]===================== ittemp0 = " +
                                        ittemp);

                        if ((assignmentList.size() > 0) && (totalCishu_Chenji > 0))
                        {
                                ittemp = (ittemp / assignmentList.size() * rateZuoyeChenji) / totalCishu_Chenji;
                        }
                        else
                        {
                                ittemp = 0;
                        }

                        DebugUtil.print(
                                "[getAssignmentGrade]===================== ittemp1 = " +
                                        ittemp);

                        if ((it + ittemp) > 0)
                        {
                                grade = (it + ittemp) * gradeRate[1];
                        }

                        DebugUtil.print(
                                "[getAssignmentGrade]===================== grade = " + grade);
                }
                catch (ScoreSysException ex)
                {
                        throw new CourseSysException(ex);
                }

                return grade;
        }

        /**
         * Gets the exam grade according to the score and the rate.
         *
         * @param courseID
         * @param userID
         * @param gradeRate
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static double getExamGrade(int courseID, int userID,
                                          double[] gradeRate, CertificateForm certificateForm)
                throws CourseSysException, CourseAppException
        {
                double grade = 0;
                double it = 0;

                //The total score of the paper
                float allscore = 0;

                try
                {
                        PaperHelper paperHelper = new PaperHelper();
                        List paperList = paperHelper.getPaperList(courseID,
                                StringUtil.parseInt(
                                        PaperBaseConstants.PAPER_EXAMINATION_TYPE));
                        DebugUtil.print("[getExamGrade]=================== paperList = " +
                                paperList.size());

                        //Gets the paper IDs
                        int[] paperIDs = new int[paperList.size()];

                        for (int i = 0; i < paperList.size(); i++)
                        {
                                PaperForm pf = (PaperForm) paperList.get(i);
                                paperIDs[i] = pf.getPaperID();
                        }

                        if (paperIDs.length > 0)
                        {
                                it = 0;

                                PaperQuestionDAOImpl paperQuestionDAO = new PaperQuestionDAOImpl();

                                for (int j = 0; j < paperIDs.length; j++)
                                {
                                        allscore = paperQuestionDAO.getPaperScore(paperIDs[j]);
                                        DebugUtil.print(
                                                "[getExamGrade]=================== allscore = " +
                                                        allscore);

                                        PaperUserForm paperuf = PaperUserHelper.getPaperUserList(paperIDs[j],
                                                userID);
                                        float papergrade = 0;

                                        try
                                        {
                                                if ((paperuf == null) &&
                                                        (certificateForm.getCKaoshiMoren().equals("1")))
                                                {
                                                        papergrade = allscore;
                                                }
                                                else if (paperuf != null)
                                                {
                                                        papergrade = paperuf.getGrade();
                                                }

                                                DebugUtil.print(
                                                        "[getExamGrade]=================== papergrade = " +
                                                                papergrade);

                                                //�õ������Ŀ��Գɼ�
                                                it += (papergrade / allscore * 100);

                                                //��������ڶ�ο��ԣ���һ�θ���60���ÿγ̵ĳɼ�����60��
                                                /*if (paperIDs.length > 1)
                                                {
                                                    if (it > 0.6)
                                                    {
                                                        it = 0.6;
                                                        break;
                                                    }
                                                }*/
                                        }
                                        catch (Exception e)
                                        {
                                                throw new CourseSysException(e);
                                        }
                                }

                                it = it / paperIDs.length;
                        }
                        else
                        {
                                //�ж�Ĭ�ϳɼ��Ƿ�Ϊ100
                                if (certificateForm.getCKaoshiMoren().equals("1"))
                                {
                                        it = 100;
                                }
                                else
                                {
                                        it = 0;
                                }
                        }

                        DebugUtil.print("[getExamGrade]=================== it = " + it);
                }
                catch (ScoreSysException ex)
                {
                        throw new CourseSysException(ex);
                }

                DebugUtil.print("[getExamGrade]=================== gradeRate[2] = " +
                        gradeRate[2]);

                BigDecimal bit = new BigDecimal(Double.toString(it));
                BigDecimal brate = new BigDecimal(Double.toString(gradeRate[2]));

                grade = bit.multiply(brate).doubleValue();
                DebugUtil.print("[getExamGrade]=================== grade = " + grade);

                return grade;
        }

        /**
         * Gets the daily grade according to the score and the rate.
         *
         * @param userID
         * @param courseID
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public static int getCoursePaper(int userID, int courseID)
                throws CourseSysException, CourseAppException
        {
                int times = 0;

                try
                {
                        PaperHelper paperHelper = new PaperHelper();
                        List paperList = paperHelper.getPaperList(courseID,
                                StringUtil.parseInt(
                                        PaperBaseConstants.PAPER_EXAMINATION_TYPE));

                        if ((paperList == null) || (paperList.size() == 0))
                        {
                                return times;
                        }

                        for (int j = 0; j < paperList.size(); j++)
                        {
                                PaperUserForm paperuf = PaperUserHelper.getPaperUserList(((PaperForm) paperList.get(
                                        j)).getPaperID(), userID);

                                if (paperuf != null)
                                {
                                        times++;
                                }
                        }
                }
                catch (ScoreSysException ex)
                {
                        throw new CourseSysException(ex);
                }

                return times;
        }

        /**
         * ���ؿγ̵�ѧ��
         * @param courseID
         * @param pageNo
         * @param pageSize
         * @return
         * @throws CourseSysException
         */
        public static CourseUserListModel getCourseStudents(int courseID,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseStudents(courseID,
                        SecurityConstants.USER_COURSE_RELATION,pageNo, pageSize);
        }
}
