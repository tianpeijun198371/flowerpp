/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-19
 * Time: 15:22:22
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.action;

import com.ulearning.ulms.course.test.grade.dao.PaperUserDAO;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAOFactory;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ModifyPaperUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String eNum = String.valueOf(request.getParameter("answerNum"));
                String oldgrade = request.getParameter("oldGrade");

                if (!eNum.equals("null"))
                {
                        float objective = 0;
                        float subjective = 0;
                        float totalGrade = 0;
                        int operateType = Integer.parseInt(request.getParameter(
                                "operateType"));
                        int answerNum = Integer.parseInt(request.getParameter("answerNum"));
                        int paperID = Integer.parseInt(request.getParameter("paperID"));
                        int userID = Integer.parseInt(request.getParameter("userID"));
                        String[] questionType = request.getParameterValues("questionType");
                        String[] questionGrade = request.getParameterValues("questionGrade");
                        String[] questionID = request.getParameterValues("questionID");

                        PaperUserDAO dao = PaperUserDAOFactory.getDAO();

                        for (int i = 0; i < questionID.length; i++)
                        {
                                if (!questionGrade[i].equals(""))
                                {
                                        if (questionType[i].equals(
                                                baseConstants.QUESTION_SINGLE_SELECT_TYPE) ||
                                                questionType[i].equals(
                                                        baseConstants.QUESTION_MANY_SELECT_TYPE) ||
                                                questionType[i].equals(
                                                        baseConstants.QUESTION_JUDGE_TYPE))
                                        {
                                                // Add objective grade
                                                objective += Float.parseFloat(questionGrade[i]);
                                        }
                                        else if (questionType[i].equals(
                                                baseConstants.QUESTION_ANSWER_TYPE) ||
                                                questionType[i].equals(
                                                        baseConstants.QUESTION_FILL_TYPE) ||
                                                questionType[i].equals(
                                                        baseConstants.QUESTION_MATCH_TYPE))
                                        {
                                                // Add subjective grade
                                                subjective += Float.parseFloat(questionGrade[i]);
                                        }

                                        //Add total grade
                                        totalGrade += Float.parseFloat(questionGrade[i]);
                                        //Modify T_Answer_Tab's grade
                                        dao.modifyStudentQuestionScore(Integer.parseInt(
                                                questionID[i]), Float.parseFloat(questionGrade[i]),
                                                userID, paperID);
                                }
                        }

                        PaperUserForm puf = new PaperUserForm();
                        //Modify
                        puf.setPaperID(paperID);
                        puf.setUserID(userID);
                        puf.setObjective(objective);
                        puf.setSubjective(subjective);
                        puf.setGrade(totalGrade);
                        puf.setStatus(operateType);
                        puf.setDesc1(oldgrade);
                        puf.setDescription("1");
                        dao.modifyPaperUser(puf);
                }

                return mapping.findForward(resultScreen);
        }
}
