/**
 * Created by IntelliJ IDEA.
 * Choice: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choice.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOImpl;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.choice.exceptions.ChoiceDAOSysException;
import com.ulearning.ulms.course.test.question.choice.form.ChoiceForm;
import com.ulearning.ulms.course.test.question.choiceitem.dao.ChoiceItemDAOImpl;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ChoiceDAOImpl implements ChoiceDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public int addChoice(ChoiceForm choiceForm) throws ChoiceDAOSysException
        {
                int questionID = 0;
                BaseForm baseForm = choiceForm.getBaseForm();
                ArrayList list = choiceForm.getList();
                BaseDAOImpl baseDAOImpl = new BaseDAOImpl();
                ChoiceItemDAOImpl choiceItemDAOImpl = new ChoiceItemDAOImpl();

                //BaseDAOImpl baseDAOImpl=new BaseDAOImpl();
                try
                {
                        questionID = baseDAOImpl.addBase(baseForm);
                        //questionID=baseDAOImpl.getQuestionID(baseForm);
                        LogUtil.debug("system",
                                "[ChoiceDAOOracle.addChoice]====================");

                        if (questionID != 0)
                        {
                                for (Iterator it = list.iterator(); it.hasNext();)
                                {
                                        ChoiceItemForm cim = (ChoiceItemForm) it.next();
                                        cim.setQuestionID(questionID);
                                        choiceItemDAOImpl.addChoiceItem(cim);
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new ChoiceDAOSysException(
                                "SQLException while addChoice ; Serial =  :\n" + se);
                }

                return questionID;
        }

        public void updateChoice(ChoiceForm choiceForm)
                throws ChoiceDAOSysException
        {
                BaseForm baseForm = choiceForm.getBaseForm();
                ArrayList list = choiceForm.getList();
                BaseDAOImpl baseDAOImpl = new BaseDAOImpl();
                ChoiceItemDAOImpl choiceItemDAOImpl = new ChoiceItemDAOImpl();

                try
                {
                        baseDAOImpl.updateBase(baseForm);
                        LogUtil.debug("system",
                                "[ChoiceDAOOracle.addChoice]====================");
                        choiceItemDAOImpl.removeQuestionChoiceItem(baseForm.getQuestionID());

                        for (Iterator it = list.iterator(); it.hasNext();)
                        {
                                ChoiceItemForm cim = (ChoiceItemForm) it.next();
                                choiceItemDAOImpl.addChoiceItem(cim);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new ChoiceDAOSysException(
                                "SQLException while addChoice ; Serial =  :\n" + se);
                }
        }

        /**
         * Remove the choice from database by the questionID
         *
         * @param questionID
         * @throws ChoiceDAOSysException
         */
        public void removeQuestionAndChoice(String[] questionID)
                throws ChoiceDAOSysException
        {
                String sqlStr1 = "delete from T_ChoiceItem_Tab where ";
                String sqlStr2 = "delete from T_Question_Tab where ";
                String condition = "";

                for (int i = 0; i < questionID.length; i++)
                {
                        condition = condition + " or questionID=" + questionID[i];
                }

                if (!condition.equals(""))
                {
                        condition = condition.substring(4);
                        sqlStr1 = sqlStr1 + condition;
                        sqlStr2 = sqlStr2 + condition;

                        try
                        {
                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(sqlStr1);
                                rs = stmt.executeQuery(sqlStr2);
                        }
                        catch (SQLException se)
                        {
                                throw new ChoiceDAOSysException(
                                        "SQLException while removeQuestionAndChoice " +
                                                "base; sqlStr = " + sqlStr2 + " :\n" + se);
                        }
                        finally
                        {
                                DBUtil.closeResultSet(rs);
                                DBUtil.closeStatement(stmt);
                                DBUtil.closeConnection(conn);
                        }
                }
        }

        /**
         * Get the choice info via the unique questionID
         *
         * @param questionID
         * @return the prepared choiceForm, default is null
         * @throws ChoiceDAOSysException
         */
        public ChoiceForm getChoice(int questionID) throws ChoiceDAOSysException
        {
                ChoiceForm cf = new ChoiceForm();
                String sqlStr = "select q.*,i.*,i.title as itemTitle from T_Question_Tab q,T_ChoiceItem_Tab i " +
                        "where q.QuestionID=i.QuestionID and q.QuestionID = " + questionID;

                try
                {
                        LogUtil.debug("system",
                                "[ChoiceDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        BaseForm bf = null;
                        ChoiceItemForm cif = null;
                        BaseDAOImpl baseDAOImpl = new BaseDAOImpl();
                        ChoiceItemDAOImpl choiceItemDAOImpl = new ChoiceItemDAOImpl();
                        String alreadyFindQuestionIDStr = "/";
                        String tmpQuestionID = "";
                        ArrayList list = new ArrayList();

                        while (rs.next())
                        {
                                tmpQuestionID = rs.getString("QuestionID");

                                if (alreadyFindQuestionIDStr.indexOf("/" + tmpQuestionID + "/") < 0)
                                {
                                        bf = baseDAOImpl.convertRs2Form(rs);
                                }

                                cif = new ChoiceItemForm();
                                cif.setQuestionID(rs.getInt("QuestionID"));
                                cif.setChoiceItemID(rs.getInt("ChoiceItemID"));
                                cif.setItemTitle(StringUtil.nullToStr(rs.getString("itemTitle")));
                                cif.setLink(StringUtil.nullToStr(rs.getString("Link")));
                                list.add(cif);
                        }

                        cf.setBaseForm(bf);
                        cf.setList(list);
                }
                catch (SQLException se)
                {
                        throw new ChoiceDAOSysException("SQLException while getChoice " +
                                "choice; questionID = " + questionID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new ChoiceDAOSysException("SQLException while getChoice " +
                                "choice; questionID = " + questionID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return cf;
        }

        public List getChoiceList(List questionID) throws ChoiceDAOSysException
        {
                ArrayList allList = new ArrayList();
                ChoiceForm cf = new ChoiceForm();
                String sqlStr = "select * from T_Question_Tab q,T_ChoiceItem_Tab i " +
                        "where q.QuestionID=i.QuestionID  ";
                String condition = "";
                String tmp = null;

                for (int qn = 0; qn < questionID.size(); qn++)
                {
                        tmp = (String) questionID.get(qn);

                        if (!tmp.trim().equals(""))
                        {
                                condition = condition + "," + tmp;
                        }
                }

                if (!condition.equals(""))
                {
                        condition = " and q.questionID in(" + condition.substring(1) +
                                ") ";
                }
                else
                {
                        return null;
                }

                sqlStr = sqlStr + condition +
                        " order by  q.QuestionID desc,ChoiceItemID";

                try
                {
                        LogUtil.debug("system",
                                "[ChoiceDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        BaseForm bf = null;
                        ChoiceItemForm cif = null;
                        BaseDAOImpl baseDAOImpl = new BaseDAOImpl();
                        ChoiceItemDAOImpl choiceItemDAOImpl = new ChoiceItemDAOImpl();
                        String alreadyFindQuestionIDStr = "/";
                        String tmpQuestionID = "";
                        String lastQuestion = "";
                        ArrayList list = new ArrayList();

                        while (rs.next())
                        {
                                tmpQuestionID = rs.getString("QuestionID");

                                if (!lastQuestion.equals("") &&
                                        !lastQuestion.equals(tmpQuestionID))
                                {
                                        cf.setBaseForm(bf);
                                        cf.setList(list);
                                        allList.add(cf);
                                        bf = null;
                                        list = new ArrayList();
                                        cf = new ChoiceForm();
                                }

                                if (alreadyFindQuestionIDStr.indexOf("/" + tmpQuestionID + "/") < 0)
                                {
                                        //bf = baseDAOImpl.convertRs2Form(rs);
                                }

                                //cif=choiceItemDAOImpl.convertRs2Form(rs);
                                list.add(cif);
                                lastQuestion = tmpQuestionID;
                        }

                        if (!tmpQuestionID.equals(""))
                        {
                                cf.setBaseForm(bf);
                                cf.setList(list);
                                allList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new ChoiceDAOSysException(
                                "SQLException while getChoiceList  :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new ChoiceDAOSysException(
                                "SQLException while getChoiceList  :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return allList;
        }
}
