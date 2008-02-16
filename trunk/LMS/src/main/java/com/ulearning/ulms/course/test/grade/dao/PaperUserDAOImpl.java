/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-18
 * Time: 13:38:16
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.PaperModel;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.grade.model.PaperUserModel;
import com.ulearning.ulms.course.test.paper.bean.PaperHelper;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.question.base.bean.BaseHelper;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class PaperUserDAOImpl implements PaperUserDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        /**
         * Modify student answer score
         *
         * @param questionID
         * @param score
         * @param userID
         * @throws GradeDAOSysException
         */
        public void modifyStudentQuestionScore(int questionID, float score,
                                               int userID, int paperID) throws GradeDAOSysException
        {
                String strSql = "update T_Answer_Tab set Grade = " + score +
                        " where questionID = " + questionID + " and userID = " + userID +
                        " and paperID = " + paperID;

                try
                {
                        LogUtil.debug("test",
                                "[PaperUserAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);
                }
                catch (SQLException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while modify studentAnswer ;  :\n" + e);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public String getStudentAnswer(int questionID, int userID, int paperID)
                throws GradeDAOSysException
        {
                String studentAnswer = "";
                String strSql = "select * from T_Answer_Tab where questionID = " +
                        questionID + " and userID = " + userID + " and paperID = " +
                        paperID;

                try
                {
                        LogUtil.debug("PaperUser",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                studentAnswer = StringUtil.nullToStr(rs.getString("Answer"));
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query studentAnswer info; userID = " +
                                        userID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return studentAnswer;
        }

        public void modifyPaperUser(PaperUserForm paperUserForm)
                throws GradeDAOSysException
        {
                String strSql = "update T_PaperUser_Tab set Subjective = " +
                        paperUserForm.getSubjective() + " , Objective = " +
                        paperUserForm.getObjective() + " , Grade = " +
                        paperUserForm.getGrade() + " , Description = " +
                        paperUserForm.getDescription() + " , Status = " +
                        paperUserForm.getStatus() + " where paperID = " +
                        paperUserForm.getPaperID() + " and UserID = " +
                        paperUserForm.getUserID() + " and Grade = " +
                        paperUserForm.getDesc1();

                try
                {
                        LogUtil.debug("test",
                                "[modifyPaperUser()]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);
                }
                catch (SQLException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while modify PaperUser ;  :\n" + e);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * add a new grade info
         *
         * @param details
         * @throws GradeDAOSysException
         */
        public void addPaperUser(PaperUserForm details) throws GradeDAOSysException
        {
                try
                {
                        HibernateDAO.add(details.getPaperUserModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }

                // return gradeID;
        }

        //判断试卷是否还有没有批改主观题的学生
        public int getIsUnUser(int paperID) throws GradeDAOSysException
        {
                int count = 0;
                String strSql = "select count(*) as count from t_paperuser_tab " +
                        "where paperid= " + paperID + " and description is null";

                try
                {
                        LogUtil.debug("test",
                                "[PaperUserDAOImpl]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                count = rs.getInt("count");
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query  info; paperID = " + paperID +
                                        " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return count;
        }

        //public void addPaperUser(PaperUserForm paperUserForm) throws GradeDAOSysException
        //        {
        //
        //
        //                strSql = "insert into T_PaperUser_Tab values("
        //                        + paperUserForm.getPaperID()
        //                        + "," + paperUserForm.getUserID()
        //                        + "," + paperUserForm.getType()
        //                        + "," + paperUserForm.getSubjective()
        //                        + "," + paperUserForm.getObjective()
        //                        + "," + paperUserForm.getGrade()
        //                        + "," + paperUserForm.getStatus()
        //                        + "," + paperUserForm.getStartTime()
        //                        + "," + paperUserForm.getEndTime()
        //                        + ",'" + paperUserForm.getDescription()
        //                        + "','" + paperUserForm.getDesc1()
        //                        + "','" + paperUserForm.getDesc2()
        //                        + "','" + paperUserForm.getDesc3()
        //                        + "','" + paperUserForm.getDesc4()
        //                        + "')";
        //                try
        //                {
        //                        LogUtil.debug("test", "[PaperUserAOOracle]====================the sql string is : " + strSql);
        //                }
        //                catch (ULMSSysException e)
        //                {
        //                        throw new GradeDAOSysException("SQLException while insert a info to  PaperUser ;  :\n" + e);
        //                }
        //        }

        /**
         * @param questionID
         * @return
         * @throws GradeDAOSysException
         */
        public BaseForm getQuestionInfo(int questionID) throws GradeDAOSysException
        {
                BaseForm bf = null;
                BaseHelper baseHelper = new BaseHelper();
                bf = baseHelper.getBase(questionID);

                return bf;
        }

        /**
         * Get paperUser info in a paper
         *
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public List getPaperUserList(int paperID) throws GradeDAOSysException
        {
                ArrayList paperUserList = new ArrayList();
                PaperUserForm puf = null;
                String strSql = "select * from T_PaperUser_Tab where paperID =" +
                        paperID;

                try
                {
                        LogUtil.debug("PaperUser",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                if (((UserForm) UserHelper.getUser(rs.getString(2))) != null)
                                {
                                        puf = convertRs2Form(rs);
                                        paperUserList.add(puf);
                                }
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return paperUserList;
        }

        /**
         * Get paperUser info in a paper
         *
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public List getPaperUserListOrder(int paperID) throws GradeDAOSysException
        {
                ArrayList paperUserList = new ArrayList();
                PaperUserForm puf = null;
                String strSql = "select * from T_PaperUser_Tab where paperID =" +
                        paperID + "  order by grade desc";

                try
                {
                        LogUtil.debug("course",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                if (((UserForm) UserHelper.getUser(rs.getString(2))) != null)
                                {
                                        puf = convertRs2Form(rs);
                                        paperUserList.add(puf);
                                }
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return paperUserList;
        }

        public boolean delPaperUserByUserID(int userID) throws GradeDAOSysException
        {
                boolean result = false;
                String strSql = "delete t_paperuser_tab where userID =  " + userID;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);
                        result = true;
                }
                catch (SQLException se)
                {
                        result = false;
                        throw new GradeDAOSysException(
                                "SQLException while delete  PaperUser by UserID= " + userID +
                                        " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return result;
        }

        public PaperUserForm getPaperUser(int paperID, int userID, String grade)
                throws GradeDAOSysException
        {
                PaperUserForm puf = null;
                String hql = "from PaperUserModel where paperID =" + paperID +
                        " and userID =" + userID + " and grade=" + grade;
                List tmpList = new ArrayList();

                try
                {
                        LogUtil.debug("course",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        hql);
                        tmpList = HibernateDAO.find(hql);

                        if (tmpList != null)
                        {
                                for (int i = 0; i < tmpList.size(); i++)
                                {
                                        PaperUserModel pum = (PaperUserModel) tmpList.get(i);
                                        puf = new PaperUserForm(pum);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while getPaperUser(int paperID, int userID, String grade) " +
                                        "Paper; " + "paperID = " + paperID + " :\n" + e);
                }

                return puf;
        }

        public PaperUserForm getPaperUser(int paperID, int userID)
                throws GradeDAOSysException
        {
                PaperUserForm puf = null;
                String strSql = "select * from T_PaperUser_Tab where paperID =" +
                        paperID + " and userID =" + userID + "  order by grade desc";

                try
                {
                        LogUtil.debug("PaperUser",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                puf = convertRs2Form(rs);
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return puf;
        }

        /**
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public PaperModel getPaperInfo(int paperID, int type)
                throws GradeDAOSysException
        {
                PaperModel pm = new PaperModel();
                float objective = 0;
                float subjective = 0;
                float totleScore = 0;
                String title = "";
                String paperTypeName = "";

                //objective question score
                String questionType = baseConstants.QUESTION_SINGLE_SELECT_TYPE;
                objective += getScoreOfOneTypeInPaper(paperID, questionType);
                questionType = baseConstants.QUESTION_MANY_SELECT_TYPE;
                objective += getScoreOfOneTypeInPaper(paperID, questionType);
                questionType = baseConstants.QUESTION_JUDGE_TYPE;
                objective += getScoreOfOneTypeInPaper(paperID, questionType);

                //subject question score
                questionType = baseConstants.QUESTION_FILL_TYPE;
                subjective += getScoreOfOneTypeInPaper(paperID, questionType);
                questionType = baseConstants.QUESTION_ANSWER_TYPE;
                subjective += getScoreOfOneTypeInPaper(paperID, questionType);
                questionType = baseConstants.QUESTION_MATCH_TYPE;
                subjective += getScoreOfOneTypeInPaper(paperID, questionType);
                //System.out.println("subjective="+subjective);
                //System.out.println("objective="+objective);

                //total question score
                totleScore = objective + subjective;

                //System.out.println("totleScore="+totleScore);
                PaperHelper paperHelper = new PaperHelper();
                PaperForm paperForm = paperHelper.getPaper(paperID);
                title = paperForm.getTitle();

                int paperType = paperForm.getType();

                switch (paperType)
                {
                        case 1:
                                paperTypeName = "练习题";

                                break;

                        case 2:
                                paperTypeName = "自测题";

                                break;

                        case 3:
                                paperTypeName = "考试题";
                }

                pm.setPaperID(paperID);
                pm.setSubjective(subjective);
                pm.setObjective(objective);
                pm.setTitle(title);
                pm.setTypeName(paperTypeName);
                pm.setType(paperType);
                pm.setTotalGrade(totleScore);

                if (type == 1)
                {
                        List questionList = getQuetiontListOfPaper(paperID);
                        pm.setQuestionList(questionList);
                }

                return pm;
        }

        /**
         * Get Question list of paper vai paperID
         *
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public List getQuetiontListOfPaper(int paperID) throws GradeDAOSysException
        {
                ArrayList questionList = new ArrayList();
                BaseForm bf = null;
                String strSql = "select q.* from T_PaperQuestion_Tab pq,T_Question_Tab q where" +
                        " pq.QuestionID = q.QuestionID and pq.paperID = " + paperID +
                        " order by q.type";

                try
                { //System.out.println("strSql = "+strSql);
                        LogUtil.debug("PaperUser",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                bf = convertRs2PaperForm(rs);
                                questionList.add(bf);
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return questionList;
        }

        /**
         * @param paperID
         * @param questionType
         * @return
         * @throws GradeDAOSysException
         */
        public float getScoreOfOneTypeInPaper(int paperID, String questionType)
                throws GradeDAOSysException
        {
                float score = 0;
                String strSql = "select sum(pq.score) from T_PaperQuestion_Tab pq,T_Question_Tab q " +
                        " where pq.QuestionID = q.QuestionID and q.Type ='" + questionType +
                        "' and pq.paperID =" + paperID;

                try
                {
                        LogUtil.debug("PaperUser",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                score = rs.getFloat(1);
                        }
                }
                catch (SQLException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return score;
        }

        //start: add for 统计参与人数
        /**
         * 综合查询
         *
         * @param paperID -1：不包含此条件
         * @param userID  -1：不包含此条件
         * @param type    -1：不包含此条件
         * @return
         * @throws GradeDAOSysException
         */
        public int count(int paperID, int userID, int type)
                throws GradeDAOSysException
        {
                int num = 0;
                String strSql = "select count(pu.paperID) from T_PaperUser_Tab pu where 1=1";

                if (paperID != -1)
                {
                        strSql += (" and pu.paperID=" + paperID);
                }

                if (userID != -1)
                {
                        strSql += (" and pu.userID=" + userID);
                }

                if (type != -1)
                {
                        strSql += (" and pu.type=" + type);
                }

                try
                {
                        LogUtil.debug("PaperUser",
                                "[PaperUserDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                num = rs.getInt(1);
                        }
                }
                catch (SQLException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query PaperUser info; paperID = " +
                                        paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return num;
        }

        //end: add for 统计参与人数
        public List scoreSearch(int courseID, String paperName, String userName)
                throws GradeDAOSysException
        {
                List returnList = new ArrayList();
                String hql = "";
                hql = "select pu,pm,um from PaperUserModel pu , PaperModel pm , UserModel um" +
                        " where pu.comp_id.paperid = pm.paperid " +
                        " and um.userid = pu.comp_id.userid" + " and pm.type = 3 " +
                        " and pm.courseid = " + courseID;

                if ((paperName != null) && !paperName.equals(""))
                {
                        hql += (" and pm.title like '%" + paperName + "%'");
                }

                if ((userName != null) && !userName.equals(""))
                {
                        hql += (" and (um.loginname like '%" + userName +
                                "%' or um.name like '%" + userName + "%')");
                }

                try
                {
                        System.out.println("hql =====================" + hql);
                        LogUtil.info("PaperUserDAOImpl", "scoreSearch where hql = " + hql);
                        returnList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }

                return returnList;
        }

        /**
         * conver rs to PaperUserForm
         *
         * @param rs
         * @return
         * @throws GradeDAOSysException
         */
        private PaperUserForm convertRs2Form(ResultSet rs)
                throws GradeDAOSysException
        {
                PaperUserForm puf = new PaperUserForm();
                int rsIndex = 1;

                try
                {
                        puf.setPaperID(rs.getInt(rsIndex++));
                        puf.setUserID(rs.getInt(rsIndex++));
                        puf.setType(rs.getInt(rsIndex++));
                        puf.setSubjective(rs.getFloat(rsIndex++));
                        puf.setObjective(rs.getFloat(rsIndex++));
                        puf.setGrade(rs.getFloat(rsIndex++));
                        puf.setStatus(rs.getInt(rsIndex++));
                        puf.setStartTime(DateTimeUtil.toDate(rs.getDate(rsIndex),
                                rs.getTime(rsIndex++)));
                        puf.setEndTime(DateTimeUtil.toDate(rs.getDate(rsIndex),
                                rs.getTime(rsIndex++)));
                        puf.setDescription(rs.getString(rsIndex++));
                        puf.setDesc1(rs.getString(rsIndex++));
                        puf.setDesc2(rs.getString(rsIndex++));
                        puf.setDesc3(rs.getString(rsIndex++));
                        puf.setDesc4(rs.getString(rsIndex++));
                        puf.setUserName(((UserForm) UserHelper.getUser(rs.getString(2))).getLoginName());
                }
                catch (SQLException sql)
                {
                        throw new GradeDAOSysException("SQLException while query :\n" +
                                sql);
                }

                return puf;
        }

        /**
         * @param rs
         * @return
         * @throws GradeDAOSysException
         */
        private BaseForm convertRs2PaperForm(ResultSet rs)
                throws GradeDAOSysException
        {
                BaseForm bf = new BaseForm();
                int rsIndex = 1;

                try
                {
                        bf.setQuestionID(rs.getInt(rsIndex++));
                        bf.setTitle(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setIsContent(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setHardLevel(rs.getInt(rsIndex++));
                        bf.setKey(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setParentID(rs.getInt(rsIndex++));
                        bf.setChapter(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setScope(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setPoint(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setObject(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setCourseID(rs.getInt(rsIndex++));
                        bf.setScore(rs.getFloat(rsIndex++));
                        bf.setCorrectReply(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setIncorrectReply(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setCorrectAnswer(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setLink(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setCreateTime(rs.getDate(rsIndex++));
                        bf.setUpdateTime(rs.getDate(rsIndex++));
                        bf.setDescription(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setRemark(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setDesc1(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setDesc2(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setDesc3(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setDesc4(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setDesc5(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setDesc6(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setDesc7(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return bf;
        }
}
