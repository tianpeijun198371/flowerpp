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
 * 封装了所有的课程和证书用户的业务逻辑。
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
         * 审批用户
         *
         * @param userIDs    需要审批的用户列表
         * @param states     用户的审批状态
         * @param relationID
         * @param type
         * @throws ULMSSysException
         */
        public static void confirmApply(List userIDs, List states, int relationID,
                                        int type) throws ULMSSysException
        {
                System.out.println(
                        "[CourseUserHelper]confirmApply=========== relationID：" +
                                relationID);
                System.out.println("[CourseUserHelper]confirmApply=========== type：" +
                        type);
                System.out.println(
                        "[CourseUserHelper]confirmApply=========== userIDs.size：" +
                                userIDs.size());
                courseUserDAO.confirmApply(userIDs, states, relationID, type);

                int userID = 0;
                int state;

                // 当为培训班时，同时也更新用户相应课程的状态
                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        List courseList = null;

                        //获得培训班下的课程列表
                        CertDAO certDAO = CertDAOFactory.getDAO();
                        int courseID = 0;
                        int roleID = SecurityConstants.ROLE_COURSR_STUDENT;
                        courseList = certDAO.getCourseListFromCert(String.valueOf(
                                relationID), LMSConstants.LEARNING_TUTORIAL);

                        if (courseList != null)
                        {
                                System.out.println(
                                        "[CourseUserHelper]confirmApply=========== 培训班包含课程数：" +
                                                courseList.size());
                        }
                        else
                        {
                                System.out.println(
                                        "[CourseUserHelper]confirmApply=========== 培训班包含课程数：为空");
                        }

                        for (int j = 0; (courseList != null) && (j < courseList.size());
                             j++)
                        {
                                courseID = ((CourseModel) courseList.get(j)).getCourseID();
                                System.out.println(
                                        "[CourseUserHelper]confirmApply=========== 用户" + userID +
                                                " 课程" + courseID);

                                ArrayList tempUsers = new ArrayList();
                                ArrayList tempState = new ArrayList();

                                for (int k = 0; (userIDs != null) && (k < userIDs.size());
                                     k++)
                                {
                                        userID = ((Integer) userIDs.get(k)).intValue();
                                        state = ((Integer) states.get(k)).intValue();

                                        System.out.println(
                                                "[CourseUserHelper]confirmApply=========== userID：" +
                                                        userID);
                                        System.out.println(
                                                "[CourseUserHelper]confirmApply=========== courseID：" +
                                                        courseID);
                                        System.out.println(
                                                "[CourseUserHelper]confirmApply=========== state：" +
                                                        state);

                                        if (isExisitCourseUserRole(userID, roleID, courseID,
                                                SecurityConstants.USER_COURSE_RELATION))
                                        {
                                                System.out.println(
                                                        "[CourseUserHelper]confirmApply 开始更新课程学习档案状态");
                                                tempUsers.add(new Integer(userID));
                                                tempState.add(new Integer(state));
                                                //当为培训班是同时更新学习档案培训班课程的状态
                                                HistoryHelper.update(userID, courseID,
                                                        SecurityConstants.USER_COURSE_RELATION, state,
                                                        new Date(), new Date());
                                        }
                                        else
                                        {
                                                System.out.println(
                                                        "[CourseUserHelper]confirmApply 角色不是学生，不用更新课程学习档案状态");
                                        }
                                }

                                //更新用户证书中课程的状态
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
                                "[CourseUserHelper]confirmApply=========== userID：" + userID);
                        System.out.println(
                                "[CourseUserHelper]confirmApply=========== relationID：" +
                                        relationID);
                        System.out.println(
                                "[CourseUserHelper]confirmApply=========== state：" + state);

                        if (state == CourseKeys.COURSE_USER_AVAILABLE_STATE)
                        {
                                inform(userID, relationID, type, true, isPass);
                        }

                        System.out.println("[CourseUserHelper]confirmApply 开始更新学习档案状态");
                        // add course or certificate rcord to history
                        HistoryHelper.update(userID, relationID, type, state, new Date(),
                                new Date());
                }
        }

        /**
         * 返回一条课程用户信息。
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
         * 修改用户
         *
         * @param cum
         * @throws ULMSException
         */
        public static void updateCourseUser(CourseUserModel cum)
                throws ULMSException
        {
                //判断用户是否存在
                if (!UserHelper.isExistUser(String.valueOf(cum.getUserID())))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]updateCourseUser ====================== 用户不存在!");

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "用户不存在!");
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

                        //todo:培训班的学分暂时不处理。
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
                        //判断学习档案是否还有纪录，若有纪录则置状态为COURSE_USER_NOTAVAILABLE_STATE,此状态表示此纪录不会再显示。
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
         * 修改用户，如果修改状态为结束，则设置结束时间 。
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
         * 增加课程或者证书用户.<br>
         * 当 为证书时,会增加用户到期下面的证书中.
         *
         * @param cum
         * @throws ULMSException
         */
        public static void addCourseUser(CourseUserModel cum)
                throws ULMSException
        {
                //判断用户是否存在
                if (!UserHelper.isExistUser(cum.getUserID()))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ====================== 用户不存在! userID=" +
                                        cum.getUserID());

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "此用户不存在!");
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
                        //发送自动选课通知-Sucess
                        if (cum.getState() == CourseKeys.COURSE_USER_AVAILABLE_STATE)
                        {
                                CourseUserHelper.inform(cum.getUserID(), cum.getRelationID(),
                                        cum.getType(), false, true);
                        }

                        //todo :培训班的学分暂时不处理。
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
         * 批量添加申请培训班的用户
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
         * 判断此用户在这个培训班中是否存在，如果存在返回true否则返回false
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
         * 增加课程或者证书用户.<br>
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
         * 增加课程或者证书用户
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
         * 增加课程或者证书用户
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

                //判断用户是否存在
                if (!UserHelper.isExistUser(userID))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ====================== 用户不存在! userID=" +
                                        userID);

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "此用户不存在!");
                        uneae.setErrorKey("error.course.userNotExisit");
                        throw uneae;
                }

                //判断用户是否存在
                if (!UserHelper.isExistUser(userID))
                {
                        LogUtil.debug("course",
                                "[CourseUserHelper]addCourseUser ====================== 用户不存在! userID=" +
                                        userID);

                        UserNotExistAppException uneae = new UserNotExistAppException(
                                "有用户不存在!");
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
                        //发送自动选课通知-Sucess
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
         * 删除课程用户
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
         * 判断是否为创建者。
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
         * 返回所有相关用户列表
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
         * return courseuser state's resource key．
         * 在这里，正常状态显示＂可用＂
         * 主要用于除学习档案外的地方，如在选课管理里，判断学员的状态
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
         * return courseuser study process state's resource key．
         * 在这里，正常状态显示＂学习中＂，因为在学习档案里都是学生．
         * 主要用于在学习档案里，判断学员的状态
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
         * 返回学员总数，不包含待审批的学员
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
         * 判断课程用户是否是学生.
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
         * 给学生选课结果发送自动通知
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
                        String orgID = "0"; //机构ID,现定死为0;

                        //默认type为学生申请课程被批准通知
                        int messageType = SysConfigConstants.SYS_CONFIG_APPLY_COURSE_SUC_EVENT;

                        if (!isPass) //否则type为学生申请课程被拒绝通知
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
                        String title = "选课通知";

                        String relationName = "";
                        String name = null;
                        String certModuleName = "培训班"; //Config.getCERT_I18N_NAME();
                        String courseModulename = "课程";

                        //Config.getCOURSE_I18N_NAME();
                        if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                relationName = CertHelper.getCert(relationID).getName();
                                name = certModuleName;
                                title += ("[" + name + "：" + relationName + "]");
                        }
                        else if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                relationName = CourseHelper.getCourse(relationID).getName();
                                name = courseModulename;
                                title += ("[" + name + "：" + relationName + "]");
                        }

                        String content = null;

                        if (isMsg)
                        {
                                LogUtil.debug("course", "[CourseUserHelper]inform--发送消息!");
                                LogUtil.debug("course",
                                        "[CourseUserHelper]inform--reciever=" + reciever);
                                content = getInformTemplate(relationID, type, recieverName,
                                        isNeedConfirm, isPass, true);

                                mh.insertMessage(sender, recieverName, reciever, title,
                                        content, type);
                        }
                        else
                        {
                                LogUtil.debug("course", "[CourseUserHelper]inform--不发送消息!");
                        }

                        if (isMail)
                        {
                                String userMail = user.getMail();
                                LogUtil.debug("course", "[CourseUserHelper]inform--发送Mail!");
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
                                LogUtil.debug("course", "[CourseUserHelper]inform--不发送Mail!");
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
         * 当type=5时给课程用户发送作业通知，type=6时发送考试通知
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
                        title = "作业通知";
                        content = "您好，" + recieverName + "<br><br>" + "您加入的课程--" +
                                relationName + "-布置了新的作业！<br><br>" + "请尽快完成作业！";
                }
                else if (type == 6)
                {
                        title = "考试通知";
                        content = "您好，" + recieverName + "<br><br>" + "您加入的课程--" +
                                relationName + "-发布了新的考试！<br><br>" + "请尽快完成考试！";
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
                String certModuleName = "培训班"; //Config.getCERT_I18N_NAME();
                String courseModulename = "课程";

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
                content += ("您好，" + recieverName + "<br><br>");

                if (isNeedConfirm)
                {
                        if (isPass)
                        {
                                if (isMessage)
                                {
                                        if (type == SecurityConstants.USER_COURSE_RELATION)
                                        {
                                                /*                                                content += "您申请的" + name + "-" +
                                           relationName +
                                           "，已经被" +
                                           "批准！<br><br>" +
                                           "请即刻进入该<a href=\"" + Config.getulmsURL() +
                                           "/mystudy/index.jsp?courseID=" + relationID +
                                           "\" target=_parent>" + name + "</a>学习！";*/
                                                content += ("您申请的" + name + "-" + relationName +
                                                        "，已经被" + "批准！<br><br>" +
                                                        "请即刻进入<a href=javascript:openwindow(" + relationID +
                                                        ")><u>" + name + "</u></a>学习！");
                                        }
                                        else
                                        {
                                                content += ("您申请的" + name + "-" + relationName +
                                                        "，已经被" + "批准！<br><br>" + "请即刻进入该" + name + "学习！");
                                        }
                                }
                                else
                                {
                                        content += ("您申请的" + name + "-" + relationName + "，已经被" +
                                                "批准！<br><br>" + "请即刻<a href=\"" + Config.getulmsURL() +
                                                "\" target=_blank><u>登陆平台</u></a>进入该" + name + "学习！");
                                }
                        }
                        else
                        {
                                content += ("对不起，您申请的" + name + "-" + relationName + "，已经被拒绝！");
                        }
                }
                else
                {
                        if (isMessage)
                        {
                                if (type == SecurityConstants.USER_COURSE_RELATION)
                                {
                                        content += ("您已经加入" + name + "-" + relationName +
                                                "成功！<br><br>" + "请即刻进入该<a href=javascript:enterCourse(" +
                                                relationID + ")><u>" + name + "</u></a>学习！");
                                }
                                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                                {
                                        content += ("您已经加入" + name + "-" + relationName +
                                                "成功！<br><br>" + "请即刻进入该<a href=javascript:enterCert(" +
                                                relationID + ")><u>" + name + "</u></a>学习！");
                                }
                                else
                                {
                                        content += ("您已经加入" + name + "-" + relationName +
                                                "成功！<br><br>" + "请即刻进入该<u>" + name + "</u>学习！");
                                }
                        }
                        else
                        {
                                content += ("您已经加入" + name + "-" + relationName +
                                        "成功！<br><br>" + "请即刻<a href=\"" + Config.getulmsURL() +
                                        "\" target=_blank><u>登陆平台</u></a>进入该" + name + "学习！");
                        }
                }

                //String aspName = CourseHelper.getASPName(relationID, type);
                String aspName = CourseHelper.getASPName(relationID, type);
                content += "<br><br><br><br><hr align=\"left\" width=\"80%\" noshade>";
                content += ("<a href=\"" + Config.getulmsURL() +
                        "\" target=_blank><u>" + aspName + "</u></a><br>");
                content += DateTimeUtil.FormatDateToWeb2(new Date());

                content += "<br></p><p style=\"font-size:9pt; color:#808080\">此信息为系统自动生成，请不要回复。</p>";
                LogUtil.debug("course", "[CourseUserHelper]----content=" + content);

                return content;
        }

        /**
         * 判断课程/证书角色是否合法 .<br>
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
         * 判断课程/证书编号是否合法.<br>
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
         * 判断该课程/证书用户角色是否存在.<br>
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
         * 判断该课程/证书用户角色是否存在.<br>
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
         * 判断用户是否在此ASP存在.<br>
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
                        //todo 应该写成和userID以及courseID关联
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

                        //计算作业次数所占成绩
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

                        //计算作业成绩所占比例
                        if (StringUtil.parseInt(certificateForm.getCZuoyeGrade()) == 0)
                        {
                                ittemp = 0;
                        }
                        else
                        {
                                if (submitAssign.size() != 0)
                                {
                                        //计算已交作业成绩
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

                                //补上未交作业的成绩
                                if (weijiaoZuoyeChenji > 0)
                                {
                                        for (int assi = 0;
                                             assi < (assignmentList.size() -
                                                     submitAssign.size()); assi++)
                                        {
                                                //判断总成绩是否为0
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

                                                //拿到真正的考试成绩
                                                it += (papergrade / allscore * 100);

                                                //如果是属于多次考试，有一次高于60，该课程的成绩就是60分
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
                                //判断默认成绩是否为100
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
         * 返回课程的学生
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
