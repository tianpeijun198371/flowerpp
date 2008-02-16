/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.question.base.exceptions.BaseDAOSysException;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.base.model.QuestionModel;
import com.ulearning.ulms.tools.assignment.bean.AssignmentHelper;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class BaseDAOImpl implements BaseDAO
{
        public int addBase(BaseForm details) throws BaseDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getQuestionModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                return s.hashCode();
        }

        public void updateBase(BaseForm details) throws BaseDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getQuestionModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }
        }

        /**
         * Remove the base from database by the baseID
         *
         * @param questionID
         * @throws BaseDAOSysException
         */
        public void removeBase(String questionID) throws BaseDAOSysException
        {
                String hql = " from QuestionModel where QuestionID = " + questionID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }
        }

        public void removeBase(String[] questionID) throws BaseDAOSysException
        {
                String hql = " from QuestionModel where ";
                String condition = "";

                for (int i = 0; i < questionID.length; i++)
                {
                        condition = condition + " or QuestionID=" + questionID[i];
                }

                if (!condition.equals(""))
                {
                        condition = condition.substring(4);
                        hql = hql + condition;

                        try
                        {
                                HibernateDAO.delete(hql);
                        }
                        catch (ULMSSysException e)
                        {
                                e.printStackTrace();
                                throw new BaseDAOSysException("" + e);
                        }
                }
        }

        /**
         * Get the base info via the unique baseID
         *
         * @param questionID
         * @return the prepared baseForm, default is null
         * @throws BaseDAOSysException
         */
        public BaseForm getBase(int questionID) throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                BaseForm res = null;
                List tmList = null;
                String hql = " from QuestionModel where questionID = " + questionID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        QuestionModel bm = (QuestionModel) tmList.get(0);
                        res = bf.getBaseForm(bm);
                }

                return res;
        }

        /*
          public int getQuestionID(BaseForm bf) throws BaseDAOSysException
          {
                  int questionID=0;
                  String sqlStr = "select QuestionID from T_Question_Tab "
                          +" where CourseID = " + bf.getCourseID()
                          +" and ParentID="+bf.getParentID()
                          +" and Title='"+bf.getTitle()+"'"
                          +" order by QuestionID desc";
                  try
                  {
                          LogUtil.debug("system","[BaseDAOImpl]====================the sql string is : " + sqlStr);
                          if (rs.next())
                          {
                                  questionID=rs.getInt("QuestionID");
                          }
                  }
                  catch (SQLException se)
                  {
                          throw new BaseDAOSysException("SQLException while getQuestionID  :\n" + se);
                  }
                  catch (ULMSSysException se)
                  {
                          throw new BaseDAOSysException("SQLException while getQuestionID  :\n" + se);
                  }
                  finally
                  {
                          try
                          {
                          }
                          catch (SQLException se) { }
                  }
                  return questionID;
          }
        */

        /**
         * Get the base list by the catalogID
         *
         * @param courseID parentID
         * @return The prepared arraylist object,default size is 0
         * @throws BaseDAOSysException
         */
        public List getBaseList(int courseID, int parentID)
                throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where ParentID=" + parentID +
                        " and CourseID=" + courseID +
                        " order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        /**
         * 得到题库目录列表
         * Get the base list by the catalogID
         *
         * @param courseID parentID
         * @return The prepared arraylist object,default size is 0
         * @throws BaseDAOSysException
         */
        public List getBaseListExamFloder(int courseID) throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where IsContent= '0'" +
                        " and CourseID=" + courseID +
                        " and desc7='110'  order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List getBaseListExam(int courseID) throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where IsContent= '1'" +
                        " and CourseID=" + courseID +
                        " and desc7='110'  order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List getBaseListExamChapter(int courseID) throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = "select chapter from T_QUESTION_TAB distinct" +
                        " where IsContent= '1'" + " and CourseID=" + courseID +
                        " and desc7='110'";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List getBaseListExamPoint(int courseID) throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = "select point from T_QUESTION_TAB distinct" +
                        " where IsContent= '1'" + " and CourseID=" + courseID +
                        " and desc7='110'";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        /**
         * 得到题库列表
         * Get the base list by the catalogID
         *
         * @param courseID parentID
         * @return The prepared arraylist object,default size is 0
         * @throws BaseDAOSysException
         */
        public List getBaseListExam(int courseID, int parentID)
                throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where ParentID=" + parentID +
                        " and CourseID=" + courseID +
                        " and desc7='110'  order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        /**
         * 生成A卷
         *
         * @param questionID
         * @return
         * @throws BaseDAOSysException
         */
        public List getBaseAList(List questionID) throws BaseDAOSysException
        {
                String questionIDStr = "";

                if ((questionID != null) && (questionID.size() > 0))
                {
                        String tmp = null;

                        for (int i = 0; i < questionID.size(); i++)
                        {
                                tmp = (String) questionID.get(i);

                                if (!tmp.trim().equals(""))
                                {
                                        questionIDStr = questionIDStr + "," + tmp;
                                }
                        }

                        if (!questionIDStr.equals(""))
                        {
                                questionIDStr = questionIDStr.substring(1);
                        }
                }
                else
                {
                        return null;
                }

                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where questionID in (" +
                        questionIDStr + ")" + " order by type asc,questionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List getBaseList(List questionID) throws BaseDAOSysException
        {
                String questionIDStr = "";

                if ((questionID != null) && (questionID.size() > 0))
                {
                        String tmp = null;

                        for (int i = 0; i < questionID.size(); i++)
                        {
                                tmp = (String) questionID.get(i);

                                if (!tmp.trim().equals(""))
                                {
                                        questionIDStr = questionIDStr + "," + tmp;
                                }
                        }

                        if (!questionIDStr.equals(""))
                        {
                                questionIDStr = questionIDStr.substring(1);
                        }
                }
                else
                {
                        return null;
                }

                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where questionID in (" +
                        questionIDStr + ")" + " order by Type";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List getAllFolderList(int courseID) throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where isContent=0" +
                        " and CourseID=" + courseID + " order by QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List getSubFolderID(int courseID, String[] parentID)
        {
                List Flist = null;

                try
                {
                        Flist = getAllFolderList(courseID);
                }
                catch (BaseDAOSysException se)
                {
                        LogUtil.debug("system", "" + se);
                }

                int len = Flist.size();
                int[][] allID = new int[len][2];
                BaseForm bf = null;

                for (int i = 0; i < len; i++)
                {
                        bf = (BaseForm) Flist.get(i);
                        allID[i][0] = bf.getParentID();
                        allID[i][1] = bf.getQuestionID();
                }

                AssignmentHelper assignmentHelper = new AssignmentHelper();
                assignmentHelper.iniTree();
                assignmentHelper.setTree(0, allID);

                List allFolderIDList = assignmentHelper.getTree();
                String parentIDStr = "/";

                for (int i = 0; i < parentID.length; i++)
                {
                        parentIDStr = parentIDStr + parentID[i] + "/";
                }

                String pID = "0";
                String sID = "0";
                String tmp = "";

                for (int i = 0; i < allFolderIDList.size(); i++)
                {
                        tmp = (String) allFolderIDList.get(i);

                        if (!tmp.trim().equals(""))
                        {
                                List t = StringUtil.split(tmp, ",");
                                pID = (String) t.get(0);
                                sID = (String) t.get(1);

                                if ((parentIDStr.indexOf("/" + pID + "/") >= 0) &&
                                        (parentIDStr.indexOf("/" + sID + "/") < 0))
                                {
                                        parentIDStr = parentIDStr + sID + "/";
                                }
                        }
                }

                List res = StringUtil.split(parentIDStr, "/");

                return res;
        }

        public List searchBase(int courseID, int parentID, String chapter,
                               String point, int hardLevel, String type, String strscope)
                throws BaseDAOSysException
        {
                String condition = "";
                String tmp = "";

                if (parentID != -1)
                {
                        condition = condition + " and parentID=" + parentID + "";
                }

                if (type != null)
                {
                        condition = condition + " and type='" + type + "'";
                }
                else
                {
                        condition = condition + " and type<>'0' ";
                }

                if (hardLevel != 0)
                {
                        condition = condition + " and hardLevel=" + hardLevel + "";
                }

                if (!chapter.equals("-1"))
                {
                        String[] strChapter = StringUtil.splitString(chapter, "/");
                        String tmpc = "";

                        for (int xc = 0; xc < strChapter.length; xc++)
                        {
                                if (!strChapter[xc].trim().equals(""))
                                {
                                        tmpc = tmpc + " or chapter like '%" +
                                                strChapter[xc].trim() + "%'";
                                }
                        }

                        if (!tmpc.equals(""))
                        {
                                condition = condition + " and (" + tmpc.substring(4) + ")";
                        }
                }

                if (!point.equals("-1"))
                {
                        condition = condition + " and point like '%" + point + "%'";
                }

                if (strscope != null)
                {
                        condition = condition + " and scope like '%" + strscope + "%'";
                }

                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where courseID=" + courseID +
                        condition + " and desc7='110' order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List searchBase(int courseID, int parentID, String type,
                               String hardLevel, String scope, String[] chapter, String[] point,
                               String[] key) throws BaseDAOSysException
        {
                String condition = "";
                String tmp = "";

                if (parentID != -1)
                {
                        condition = condition + " and parentID=" + parentID + "";
                }

                if (type != null)
                {
                        condition = condition + " and type='" + type + "'";
                }
                else
                {
                        condition = condition + " and type<>'0' ";
                }

                if (hardLevel != null)
                {
                        if (!hardLevel.equals("0"))
                        {
                                int hardlevel = Integer.parseInt(hardLevel);
                                condition = condition + " and hardLevel=" + hardlevel + "";
                        }
                }

                if (scope != null)
                {
                        condition = condition + " and scope like '%" + scope + "%'";
                }

                if (chapter != null)
                {
                        tmp = "";

                        for (int i = 0; i < chapter.length; i++)
                        {
                                if (!chapter[i].trim().equals(""))
                                {
                                        tmp = tmp + " or chapter like '%/" + chapter[i].trim() +
                                                "/%'";
                                }
                        }

                        if (!tmp.equals(""))
                        {
                                condition = condition + " and (" + tmp.substring(4) + ")";
                        }
                }

                if (point != null)
                {
                        tmp = "";

                        for (int i = 0; i < point.length; i++)
                        {
                                if (!point[i].trim().equals(""))
                                {
                                        tmp = tmp + " or point like '%" + point[i].trim() + "%'";
                                }
                        }

                        if (!tmp.equals(""))
                        {
                                condition = condition + " and (" + tmp.substring(4) + ")";
                        }
                }

                if (key != null)
                {
                        tmp = "";

                        for (int i = 0; i < key.length; i++)
                        {
                                if (!key[i].trim().equals(""))
                                {
                                        tmp = tmp + " or key like '%" + key[i].trim() + "%'";
                                }
                        }

                        if (!tmp.equals(""))
                        {
                                condition = condition + " and (" + tmp.substring(4) + ")";
                        }
                }

                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where courseID=" + courseID +
                        condition + " and desc7='110' order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        public List searchBase(String[] parentID, boolean includeSub, int courseID,
                               String type, String hardLevel, String[] scope, String[] chapter,
                               String maxScore, String minScore, String[] point, String[] key)
                throws BaseDAOSysException
        {
                String condition = "";
                String tmp = "";

                if (parentID != null)
                {
                        if (includeSub == false)
                        {
                                tmp = "";

                                for (int i = 0; i < parentID.length; i++)
                                {
                                        if (!parentID[i].trim().equals(""))
                                        {
                                                tmp = tmp + " or parentID = " + parentID[i].trim();
                                        }
                                }

                                if (!tmp.equals(""))
                                {
                                        condition = condition + " and (" + tmp.substring(4) + ")";
                                }
                        }
                        else
                        {
                                List subList = getSubFolderID(courseID, parentID);
                                String parentIDStr = "";
                                String temp = "";

                                for (int i = 0; i < subList.size(); i++)
                                {
                                        temp = (String) subList.get(i);

                                        if (!temp.equals(""))
                                        {
                                                parentIDStr = parentIDStr + "," + temp;
                                        }
                                }

                                if (!parentIDStr.equals(""))
                                {
                                        parentIDStr = parentIDStr.substring(1);
                                        condition = condition + " and parentID in(" + parentIDStr +
                                                ")";
                                }
                        }
                }

                if (type != null)
                {
                        condition = condition + " and type='" + type + "'";
                }

                if (hardLevel != null)
                {
                        condition = condition + " and hardLevel='" + hardLevel + "'";
                }

                if (scope != null)
                {
                        tmp = "";

                        for (int i = 0; i < scope.length; i++)
                        {
                                if (!scope[i].trim().equals(""))
                                {
                                        tmp = tmp + " or scope like '%/" + scope[i].trim() + "/%'";
                                }
                        }

                        if (!tmp.equals(""))
                        {
                                condition = condition + " and (" + tmp.substring(4) + ")";
                        }
                }

                if (chapter != null)
                {
                        tmp = "";

                        for (int i = 0; i < chapter.length; i++)
                        {
                                if (!chapter[i].trim().equals(""))
                                {
                                        tmp = tmp + " or chapter like '%/" + chapter[i].trim() +
                                                "/%'";
                                }
                        }

                        if (!tmp.equals(""))
                        {
                                condition = condition + " and (" + tmp.substring(4) + ")";
                        }
                }

                if (maxScore != null)
                {
                        condition = condition + " and score<=" + maxScore;
                }

                if (minScore != null)
                {
                        condition = condition + " and score>=" + minScore;
                }

                if (point != null)
                {
                        tmp = "";

                        for (int i = 0; i < point.length; i++)
                        {
                                if (!point[i].trim().equals(""))
                                {
                                        tmp = tmp + " or point like '%" + point[i].trim() + "%'";
                                }
                        }

                        if (!tmp.equals(""))
                        {
                                condition = condition + " and (" + tmp.substring(4) + ")";
                        }
                }

                if (key != null)
                {
                        tmp = "";

                        for (int i = 0; i < key.length; i++)
                        {
                                if (!key[i].trim().equals(""))
                                {
                                        tmp = tmp + " or key like '%" + key[i].trim() + "%'";
                                }
                        }

                        if (!tmp.equals(""))
                        {
                                condition = condition + " and (" + tmp.substring(4) + ")";
                        }
                }

                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where courseID=" + courseID +
                        condition + " order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        /**
         * Convert the resultSet object to baseForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted baseForm
         */
        public BaseForm convertRs2Form(ResultSet rs)
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

        //        public static void main(String[] args)
        //        {
        //            BaseDAOImpl dm=new BaseDAOImpl();
        //            List li=dm.searchBase(88,0,"","-1",0,null,null);
        //            System.out.print(li.size());
        ////            BaseForm bf=(BaseForm)li.get(0);
        ////            System.out.print(bf.getChapter());
        //
        //        }
}
