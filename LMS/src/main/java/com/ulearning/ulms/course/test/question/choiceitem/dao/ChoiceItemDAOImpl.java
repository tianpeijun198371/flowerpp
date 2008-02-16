/**
 * Created by IntelliJ IDEA.
 * ChoiceItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choiceitem.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.question.choiceitem.exceptions.ChoiceItemDAOSysException;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;
import com.ulearning.ulms.course.test.question.choiceitem.model.ChoiceItemModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class ChoiceItemDAOImpl implements ChoiceItemDAO
{
        public void addChoiceItem(ChoiceItemForm details)
                throws ChoiceItemDAOSysException
        {
                try
                {
                        HibernateDAO.add(details.getChoiceItemModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ChoiceItemDAOSysException("" + e);
                }
        }

        public void updateChoiceItem(ChoiceItemForm details)
                throws ChoiceItemDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getChoiceItemModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ChoiceItemDAOSysException("" + e);
                }
        }

        /**
         * Remove the choiceitem from database by the choiceitemID
         *
         * @param choiceitemID
         * @throws ChoiceItemDAOSysException
         */
        public void removeChoiceItem(String choiceitemID)
                throws ChoiceItemDAOSysException
        {
                String hql = " from ChoiceItemModel where ChoiceItemID = " +
                        choiceitemID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ChoiceItemDAOSysException("" + e);
                }
        }

        public void removeQuestionChoiceItem(int questionID)
                throws ChoiceItemDAOSysException
        {
                String hql = " from ChoiceItemModel where QuestionID = " + questionID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ChoiceItemDAOSysException("" + e);
                }
        }

        public void removeQuestionAndChoiceItem(String[] questionID)
                throws ChoiceItemDAOSysException
        {
                String sqlStr1 = " from ChoiceItemModel  where ";
                String sqlStr2 = " from QuestionModel where ";
                String condition = "";

                for (int i = 0; i < questionID.length; i++)
                {
                        condition = condition + " or choiceitemID=" + questionID[i];
                }

                if (!condition.equals(""))
                {
                        condition = condition.substring(4);
                        sqlStr1 = sqlStr1 + condition;
                        sqlStr2 = sqlStr2 + condition;

                        try
                        {
                                HibernateDAO.delete(sqlStr1);
                                HibernateDAO.delete(sqlStr2);
                        }
                        catch (ULMSSysException se)
                        {
                                se.printStackTrace();
                                throw new ChoiceItemDAOSysException(
                                        "SQLException while removeQuestionAndChoiceItem " +
                                                "base; sqlStr = " + sqlStr2 + " :\n" + se);
                        }
                }
        }

        /**
         * Get the choiceitem info via the unique choiceitemID
         *
         * @param choiceitemID
         * @return the prepared choiceitemForm, default is null
         * @throws ChoiceItemDAOSysException
         */
        public ChoiceItemForm getChoiceItem(int choiceitemID)
                throws ChoiceItemDAOSysException
        {
                ChoiceItemForm bf = new ChoiceItemForm();
                ChoiceItemForm res = null;
                List tmList = null;
                String hql = " from ChoiceItemModel where ChoiceItemID = " +
                        choiceitemID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ChoiceItemDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        ChoiceItemModel bm = (ChoiceItemModel) tmList.get(0);
                        res = bf.getChoiceItemForm(bm);
                }

                return res;
        }

        /**
         * Get the choiceitem list by the catalogID
         *
         * @param questionID
         * @return The prepared arraylist object,default size is 0
         * @throws ChoiceItemDAOSysException
         */
        public List getChoiceItemList(int questionID)
                throws ChoiceItemDAOSysException
        {
                ChoiceItemForm bf = new ChoiceItemForm();
                ChoiceItemModel bm = null;
                ArrayList choiceItemList = new ArrayList();
                List tmList = null;
                String hql = " from ChoiceItemModel where questionID=" + questionID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ChoiceItemDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (ChoiceItemModel) tmList.get(i);
                        choiceItemList.add(bf.getChoiceItemForm(bm));
                }

                return choiceItemList;
        }

        /**
         * Convert the resultSet object to choiceitemForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted choiceitemForm
         */

        /*
          public ChoiceItemForm convertRs2Form(ResultSet rs)
          {
              ChoiceItemForm bf = new ChoiceItemForm();
              int rsIndex = 1;
              try
              {
                      bf.setQuestionID(rs.getInt("QuestionID"));
                      bf.setChoiceItemID(rs.getInt("ChoiceItemID"));
                      bf.setItemTitle(StringUtil.nullToStr(rs.getString("Title")));
                      bf.setLink(StringUtil.nullToStr(rs.getString("Link")));
              }
              catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return bf;
          }
              public static void main(String[] args)
              {
              }
        */
}
