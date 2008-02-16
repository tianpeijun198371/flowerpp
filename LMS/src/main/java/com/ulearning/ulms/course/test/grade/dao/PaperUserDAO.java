/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-16
 * Time: 11:17:43
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.PaperModel;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;

import java.util.List;


public interface PaperUserDAO
{
        /**
         * @param questionID
         * @param score
         * @throws GradeDAOSysException
         */
        public void modifyStudentQuestionScore(int questionID, float score,
                                               int userID, int paperID) throws GradeDAOSysException;

        public String getStudentAnswer(int questionID, int userID, int paperID)
                throws GradeDAOSysException;

        /**
         * @param paperUserForm
         * @throws GradeDAOSysException
         */
        public void modifyPaperUser(PaperUserForm paperUserForm)
                throws GradeDAOSysException;

        /**
         * @param paperUserForm
         * @throws GradeDAOSysException
         */
        public void addPaperUser(PaperUserForm paperUserForm)
                throws GradeDAOSysException;

        /**
         * @param questionID
         * @return
         * @throws GradeDAOSysException
         */
        public BaseForm getQuestionInfo(int questionID) throws GradeDAOSysException;

        /**
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public List getPaperUserList(int paperID) throws GradeDAOSysException;

        /**
         * Get paperUser info in a paper
         *
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public List getPaperUserListOrder(int paperID) throws GradeDAOSysException;

        public boolean delPaperUserByUserID(int userID) throws GradeDAOSysException;

        /**
         * @param paperID
         * @param userID
         * @return
         * @throws GradeDAOSysException
         */
        public PaperUserForm getPaperUser(int paperID, int userID, String Grade)
                throws GradeDAOSysException;

        public PaperUserForm getPaperUser(int paperID, int userID)
                throws GradeDAOSysException;

        /**
         * Get Paper info via paperID
         *
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public PaperModel getPaperInfo(int paperID, int type)
                throws GradeDAOSysException;

        /**
         * @param paperID
         * @return
         * @throws GradeDAOSysException
         */
        public List getQuetiontListOfPaper(int paperID) throws GradeDAOSysException;

        /**
         * @param paperID
         * @param questionType
         * @return
         * @throws GradeDAOSysException
         */
        public float getScoreOfOneTypeInPaper(int paperID, String questionType)
                throws GradeDAOSysException;

        public List scoreSearch(int courseID, String paperName, String userName)
                throws GradeDAOSysException;

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
                throws GradeDAOSysException;

        //end: add for 统计参与人数
        public int getIsUnUser(int paperID) throws GradeDAOSysException;
}
