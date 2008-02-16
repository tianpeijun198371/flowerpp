/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-16
 * Time: 11:07:30
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.bean;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAO;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAOFactory;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.PaperModel;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.paper.bean.PaperAnswerHelper;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAOFactory;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.exceptions.BaseDAOSysException;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;

import java.util.List;


public class PaperUserHelper
{
        /**
         * @param paperID
         * @return
         */
        public static List getPaperUserList(int paperID)
        {
                List uf = null;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        uf = paperUserDao.getPaperUserList(paperID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return uf;
        }

        public static PaperUserForm getPaperUserList(int paperID, int userID)
        {
                PaperUserForm uf = null;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        uf = paperUserDao.getPaperUser(paperID, userID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return uf;
        }

        /**
         * @param paperID
         * @return
         */
        public static List getPaperUserListOrder(int paperID)
        {
                List uf = null;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        uf = paperUserDao.getPaperUserListOrder(paperID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return uf;
        }

        /**
         * @param paperID
         * @return
         */
        public static List getQuetiontListOfPaper(int paperID)
        {
                List questionList = null;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        questionList = paperUserDao.getQuetiontListOfPaper(paperID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return questionList;
        }

        /**
         * @param paperID
         * @param type
         * @return
         */
        public static PaperModel getPaperInfo(int paperID, int type)
        {
                PaperModel paperModel = null;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        paperModel = paperUserDao.getPaperInfo(paperID, type);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperModel;
        }

        /**
         * @param questionID
         * @param userID
         * @param paperID
         * @return
         */
        public static String getStudentAnswer(int questionID, int userID,
                                              int paperID)
        {
                String answer = "";

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        answer = paperUserDao.getStudentAnswer(questionID, userID, paperID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return answer;
        }

        /**
         * Get one type question total score in a paper
         *
         * @param paperID
         * @param questionType
         * @return
         */
        public static float getScoreOfOneTypeInPaper(int paperID,
                                                     String questionType)
        {
                float temp = 0;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        temp = paperUserDao.getScoreOfOneTypeInPaper(paperID, questionType);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return temp;
        }

        public boolean delPaperUserByUserID(int userID)
        {
                boolean temp = false;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        temp = paperUserDao.delPaperUserByUserID(userID);
                }
                catch (GradeDAOSysException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                return temp;
        }

        /**
         * @param paperID
         * @param userID
         * @return
         */
        public static PaperUserForm getPaperUser(int paperID, int userID)
        {
                PaperUserForm puf = null;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        puf = paperUserDao.getPaperUser(paperID, userID);
                }
                catch (GradeDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return puf;
        }

        public static List scoreSearch(int courseID, String paperName,
                                       String userName)
        {
                List returnList = null;

                try
                {
                        PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();
                        returnList = paperUserDao.scoreSearch(courseID, paperName, userName);
                }
                catch (GradeDAOSysException e)
                {
                        e.printStackTrace();
                }

                return returnList;
        }

        //start: add for 统计参与人数
        /**
         * 综合查询
         *
         * @param paperID -1：不包含此条件
         * @return
         */
        public static int count(int paperID) throws GradeDAOSysException
        {
                PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();

                return paperUserDao.count(paperID, -1, -1);
        }

        //end: add for 统计参与人数

        /**
         * Wrapping the get GradeList method for JSP and  the other modules
         *
         * @param paperID
         * @return
         */
        public static List autoCorrectPaper(int paperID)
        {
                List paperList = null;
                BaseForm bf = null;
                PaperAnswerForm paf = null;
                PaperUserForm puf = null;
                float score = 0;

                try
                {
                        BaseDAO bdao = BaseDAOFactory.getDAO();
                        PaperAnswerDAO padao = PaperAnswerDAOFactory.getDAO();

                        //单选
                        List listSingle = padao.getPaperAnswerList(paperID,
                                Integer.parseInt(baseConstants.QUESTION_SINGLE_SELECT_TYPE));

                        for (int i = 0; (listSingle != null) && (i < listSingle.size());
                             i++)
                        {
                                paf = (PaperAnswerForm) listSingle.get(i);
                                bf = bdao.getBase(paf.getQuestionID());

                                if ((paf != null) && (bf != null) && (paf.getAnswer() != null) &&
                                        (bf.getCorrectAnswer() != null) &&
                                        paf.getAnswer().toUpperCase()
                                                .equals(bf.getCorrectAnswer().toUpperCase()))
                                {
                                        paf.setGrade(bf.getScore());
                                }
                                else
                                {
                                        paf.setGrade(0);
                                }

                                padao.updatePaperAnswer(paf);
                        }

                        //多选
                        List listMany = padao.getPaperAnswerList(paperID,
                                Integer.parseInt(baseConstants.QUESTION_MANY_SELECT_TYPE));

                        for (int i = 0; (listMany != null) && (i < listMany.size()); i++)
                        {
                                paf = (PaperAnswerForm) listMany.get(i);
                                bf = bdao.getBase(paf.getQuestionID());

                                if ((paf != null) && (bf != null) && (paf.getAnswer() != null) &&
                                        (bf.getCorrectAnswer() != null) &&
                                        paf.getAnswer().toUpperCase()
                                                .equals(bf.getCorrectAnswer().toUpperCase()))
                                {
                                        paf.setGrade(bf.getScore());
                                }
                                else
                                {
                                        paf.setGrade(0);
                                }

                                padao.updatePaperAnswer(paf);
                        }

                        //判断
                        List listJudge = padao.getPaperAnswerList(paperID,
                                Integer.parseInt(baseConstants.QUESTION_JUDGE_TYPE));

                        for (int i = 0; (listJudge != null) && (i < listJudge.size());
                             i++)
                        {
                                paf = (PaperAnswerForm) listJudge.get(i);
                                bf = bdao.getBase(paf.getQuestionID());

                                if ((paf != null) && (bf != null) && (paf.getAnswer() != null) &&
                                        (bf.getCorrectAnswer() != null) &&
                                        paf.getAnswer().toUpperCase()
                                                .equals(bf.getCorrectAnswer().toUpperCase()))
                                {
                                        paf.setGrade(bf.getScore());
                                }
                                else
                                {
                                        paf.setGrade(0);
                                }

                                padao.updatePaperAnswer(paf);
                                score += paf.getGrade();

                                //puf = PaperUserHelper.getPaperUser()
                        }
                }
                catch (BaseDAOSysException e)
                {
                        e.printStackTrace();
                }
                catch (PaperDAOSysException e)
                {
                        e.printStackTrace();
                }

                return paperList;
        }

        /**
         * 修改总成绩的值
         *
         * @param paperID
         * @param userIDs
         * @param updscore
         */
        public static void updateScores(int paperID, String[] userIDs,
                                        String[] Grades, String[] updscore)
                throws GradeDAOSysException, ULMSAppException
        {
                PaperUserDAO dao = PaperUserDAOFactory.getDAO();
                PaperUserForm puf = null;

                if (updscore != null)
                {
                        for (int i = 0; i < updscore.length; i++)
                        {
                                //System.out.println("updscore["+i+"]="+updscore[i]);
                                if ((updscore[i] != null) && !updscore[i].equals(""))
                                {
                                        if (userIDs != null)
                                        {
                                                puf = dao.getPaperUser(paperID,
                                                        StringUtil.parseInt(userIDs[i]), Grades[i]);

                                                if (puf != null)
                                                {
                                                        //                                                        System.out.println("paperID="+puf.getPaperID());
                                                        //                                                        System.out.println("userID="+puf.getUserID());
                                                        //                                                        System.out.println("oldgrade="+puf.getGrade());
                                                        puf.setDesc1(updscore[i]);
                                                        //                                                        System.out.println("newgrade="+puf.getGrade());
                                                        dao.modifyPaperUser(puf);
                                                }
                                                else
                                                {
                                                        //throw new ULMSAppException("用户"+userIDs[i]+"不存在！");
                                                }
                                        }
                                }
                        }
                }
        }

        public static int getIsUnUser(int paperID) throws GradeDAOSysException
        {
                PaperUserDAO paperUserDao = PaperUserDAOFactory.getDAO();

                return paperUserDao.getIsUnUser(paperID);
        }
}
