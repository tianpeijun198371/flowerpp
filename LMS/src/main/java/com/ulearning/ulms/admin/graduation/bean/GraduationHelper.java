/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-7
 * Time: 14:36:04
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.bean;

import com.ulearning.ulms.GCertificate.dao.GCertificateDAO;
import com.ulearning.ulms.GCertificate.dao.GCertificateDAOFactory;
import com.ulearning.ulms.GCertificate.form.GCertificateForm;
import com.ulearning.ulms.admin.graduation.dao.GraduationDAO;
import com.ulearning.ulms.admin.graduation.dao.GraduationDAOFactory;
import com.ulearning.ulms.admin.graduation.exceptions.GraduationAppException;
import com.ulearning.ulms.admin.graduation.form.GraduationForm;
import com.ulearning.ulms.cert.Certificate.bean.CertificateImpl;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;
import com.ulearning.ulms.common.helper.HistoryHelper;
import com.ulearning.ulms.common.helper.ScoreHelper;
import com.ulearning.ulms.common.model.ScoreModel;
import com.ulearning.ulms.common.webimpls.ScoreManageWebImpl;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.model.CourseUserListModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.webimpls.CourseUserWebImpl;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GraduationHelper
{
        public static GraduationForm getGraduation(int RelationID, int type,
                                                   int userID)
        {
                GraduationForm cf = null;

                try
                {
                        GraduationDAO Dao = GraduationDAOFactory.getDAO();
                        cf = Dao.getGraduation(RelationID, type, userID);
                }
                catch (GraduationAppException udse)
                {
                        udse.printStackTrace();
                }

                return cf;
        }

        public static double getTotalGreditOrScore(int relationID, int type,
                                                   int userID, int courseType)
        {
                double total = 0;

                try
                {
                        GraduationDAO Dao = GraduationDAOFactory.getDAO();
                        total = Dao.getTotalGreditOrScore(relationID, type, userID,
                                courseType);
                }
                catch (GraduationAppException udse)
                {
                        udse.printStackTrace();
                }

                return total;
        }

        public static List getGraduationList(int RelationID, int type, int status)
        {
                List list = new ArrayList();

                try
                {
                        GraduationDAO Dao = GraduationDAOFactory.getDAO();
                        list = Dao.getGraduationList(RelationID, type, status);
                }
                catch (GraduationAppException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        public static List searchCert(String loginName, String name)
        {
                List list = new ArrayList();

                try
                {
                        GraduationDAO Dao = GraduationDAOFactory.getDAO();
                        list = Dao.searchCert(loginName, name);
                }
                catch (GraduationAppException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        public static void CompletCourse()
        {
                //取得所有课程列表
                CourseWebImpl cwi = new CourseWebImpl();
                int catalogID = 0;
                int aspID = 1;
                int orgID = 0;

                /*              CourseTreeModel mtm = cwi.getTree(catalogID, aspID);
             CatalogListModel catlm = mtm.getCatalogList();
             CourseListModel clm = mtm.getCourseList();
             List catalogList = catlm.getList();*/
                CourseDAO daoc = CourseDAOFactory.getDAO();
                List courseList = daoc.searchCourse("", aspID, orgID);

                CourseUserWebImpl courseUserWebImpl = new CourseUserWebImpl();
                ScoreManageWebImpl scoreManageWebImpl = new ScoreManageWebImpl();

                GCertificateDAO dao = GCertificateDAOFactory.getDAO();

                for (int i = 0; i < courseList.size(); i++)
                {
                        CourseModel cm = (CourseModel) courseList.get(i);
                        CourseUserModel cum = null;
                        int pageNo = 1;
                        int pageSize = 2000;
                        CourseUserListModel culm = courseUserWebImpl.getCourseStudents(cm.getCourseID(),
                                SecurityConstants.USER_COURSE_RELATION, pageNo, pageSize);

                        ArrayList courseUsers = culm.getCourseUsers();

                        //拿到分数比例
                        double pscj = StringUtil.parseInt(StringUtil.splitString(
                                cm.getLogo(), ":")[0]);
                        double zycj = StringUtil.parseInt(StringUtil.splitString(
                                cm.getLogo(), ":")[1]);
                        double kscj = StringUtil.parseInt(StringUtil.splitString(
                                cm.getLogo(), ":")[2]);

                        double pscj_rate = pscj / (pscj + zycj + kscj);
                        double zycj_rate = zycj / (pscj + zycj + kscj);
                        double kscj_rate = kscj / (pscj + zycj + kscj);

                        double[] gradeRate = new double[3];
                        gradeRate[0] = pscj_rate;
                        gradeRate[1] = zycj_rate;
                        gradeRate[2] = kscj_rate;

                        //course score type
                        int scoreType = cm.getScoreType();

                        //course credit
                        float course_credit = cm.getCredit();
                        int scoreLimit = Integer.parseInt(cm.getScoreLimit());
                        String scoreLimitValue = null;

                        if ((scoreType == 3) || (scoreType == 4))
                        {
                                scoreLimitValue = scoreManageWebImpl.getSpecScore(scoreLimit,
                                        scoreType);
                        }
                        else
                        {
                                scoreLimitValue = String.valueOf(scoreLimit);
                        }

                        //课程用户处理
                        DecimalFormat df = new DecimalFormat("0.0");
                        int scoreID;
                        int userID;
                        float credit;
                        int isPass;
                        int examType = 0;
                        ScoreModel sm = null;

                        //System.out.println("courseUsers.size() = " + courseUsers.size());
                        for (int j = 0; j < courseUsers.size(); j++)
                        {
                                //System.out.println("cm.getName() = " + cm.getName());
                                cum = (CourseUserModel) courseUsers.get(j);
                                userID = cum.getUserID();
                                //System.out.println("cum.getUserID() = " + cum.getUserID());
                                sm = scoreManageWebImpl.get(userID, cm.getCourseID(),
                                        SecurityConstants.USER_COURSE_RELATION);

                                if ((sm != null) && (cum.getFinishedTime() != null))
                                {
                                        scoreID = sm.getScoreID();
                                }
                                else
                                {
                                        scoreID = -1;
                                }

                                //finishDateStr = DateTime.FormatDate(cum.getFinishedTime(), pageContext);
                                CertificateImpl certificateImpl = new CertificateImpl();
                                CertificateForm certificateForm = certificateImpl.getCerCourse(cm.getCourseID());
                                double dailyScore = CourseUserHelper.getDailyGrade(cm.getCourseID(),
                                        userID, gradeRate);
                                double assignmentScore = CourseUserHelper.getAssignmentGrade(cm.getCourseID(),
                                        userID, gradeRate, certificateForm);
                                double examScore = CourseUserHelper.getExamGrade(cm.getCourseID(),
                                        userID, gradeRate, certificateForm);
                                double scoreAll = dailyScore +
                                        (int) Math.round(assignmentScore) +
                                        (int) Math.round(examScore);

                                //入库操作
                                if (scoreAll >= Float.parseFloat(scoreLimitValue))
                                {
                                        GCertificateForm tf = new GCertificateForm();
                                        tf = dao.getGCertificate(cum.getUserID(), cm.getCourseID());

                                        if (tf == null)
                                        {
                                                GCertificateForm tf2 = new GCertificateForm();
                                                tf2.setGCGrade(String.valueOf(df.format(scoreAll)));
                                                tf2.setGCourseID(cm.getCourseID());
                                                tf2.setGCourseName(cm.getName());
                                                tf2.setGUserID(cum.getUserID());
                                                tf2.setGUserName(cum.getName());
                                                tf2.setGCTime(String.valueOf(cm.getPeriod()));
                                                dao.insertGCertificate(tf2);
                                        }
                                        else
                                        {
                                                tf.setGCGrade(String.valueOf(df.format(scoreAll)));
                                                tf.setGCourseID(cm.getCourseID());
                                                tf.setGCourseName(cm.getName());
                                                tf.setGUserID(cum.getUserID());
                                                tf.setGUserName(cum.getName());
                                                tf.setGCTime(String.valueOf(cm.getPeriod()));
                                                dao.updateGCertificate(tf);
                                        }

                                        //若成绩还没有，表明这是第一次审核该学生成绩，则停止该学生学习该课程，更新其结束日期
                                        if (scoreID == -1)
                                        {
                                                CourseUserHelper.updateCourseUser(cum.getUserID(),
                                                        cm.getCourseID(),
                                                        SecurityConstants.USER_COURSE_RELATION, new Date(),
                                                        CourseKeys.COURSE_USER_FINISH_STATE);
                                        }

                                        if (scoreAll >= Integer.parseInt(scoreLimitValue))
                                        {
                                                credit = cm.getCredit();
                                                isPass = 1;
                                        }
                                        else
                                        {
                                                credit = 0;
                                                isPass = 0;
                                        }

                                        int state = CourseKeys.COURSE_USER_FINISH_STATE;
                                        Date now = new Date();
                                        sm = new ScoreModel(scoreID, cum.getUserID(),
                                                cm.getCourseID(),
                                                SecurityConstants.USER_COURSE_RELATION,
                                                String.valueOf(scoreAll), scoreType, credit,
                                                isPass, examType, now, now);
                                        ScoreHelper.insert(sm);
                                        HistoryHelper.update(cum.getUserID(), cm.getCourseID(),
                                                SecurityConstants.USER_COURSE_RELATION,
                                                String.valueOf(scoreAll), state, scoreType,
                                                cm.getPeriod(), credit, isPass);
                                }
                        }
                }
        }

        public static void main(String[] main)
        {
                String logingname = "csstu1";
                String name = "测试学员1";
                List li = GraduationHelper.searchCert(logingname, name);
                System.out.println("li.size() = " + li.size());

                for (int i = 0; i < li.size(); i++)
                {
                        GraduationForm gf = (GraduationForm) li.get(i);
                        System.out.println("gf.getLoginName() = " + gf.getLoginName());
                        System.out.println("gf.getStuName() = " + gf.getStuName());
                }
        }
}
