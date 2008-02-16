/**
 * AnswerQuestionDaoImpl.java.
 * User: mouzb  Date: 2005-4-11
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionSysException;
import com.ulearning.ulms.tools.answerquestion.model.AQCatalogModel;
import com.ulearning.ulms.tools.answerquestion.model.AnswerQuestionModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AnswerQuestionDAOImpl implements AnswerQuestionDAO
{
        /**
         * 读取一条记录
         *
         * @param aqID
         * @return
         * @throws AnswerQuestionSysException
         */
        public AnswerQuestionModel getQuestion(int aqID)
                throws AnswerQuestionSysException
        {
                AnswerQuestionModel qModel = null;
                List qList = null;
                String sql_str = "from AnswerQuestionModel where aqID = " + aqID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] sql_str=" + sql_str);
                        qList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                for (int i = 0; i < qList.size(); i++)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] listSize=" + qList.size());
                        qModel = (AnswerQuestionModel) qList.get(i);
                }

                return qModel;
        }

        /**
         * 读取所有常见问题
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAll(int type, int relationID)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 按照公共或私人属性读取问题
         *
         * @param type
         * @param relationID
         * @param status
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllAttribute(int type, int relationID, String status)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and qm.status='" +
                        status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 读取常见问题已回复或未回复
         *
         * @param type
         * @param isReply
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionReply(int type, String isReply, int relationID)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.isReply='" + isReply + "' and relationID=" +
                        relationID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 我的问题
         *
         * @param type
         * @param submmiterID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestion(int type, int submmiterID, int relationID)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and submmiterID=" + submmiterID + " and relationID=" +
                        relationID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 我的已回复或未回复问题
         *
         * @param type
         * @param submmiterID
         * @param isReply
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionIsReply(int type, int submmiterID, String isReply,
                                         int relationID) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.submmiterID=" + submmiterID + " and qm.isReply='" +
                        isReply + "' and qm.relationID=" + relationID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 根据关键字查询
         *
         * @param type
         * @param questionKey
         * @param relationID
         * @param status      null 表示 此条件无效
         * @param isReply     null 表示 此条件无效
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionKey(int type, String[] questionKey, int relationID,
                                   String status, String isReply) throws AnswerQuestionSysException
        {
                String sql_str = null;
                List qList = null;

                if (questionKey != null)
                {
                        sql_str = "from AnswerQuestionModel as qm where qm.type='" + type +
                                "' and relationID=" + relationID +
                                " and (qm.questionKey like '%" + questionKey[0] + "%'";

                        for (int i = 1; i < questionKey.length; i++)
                        {
                                sql_str += (" or qm.questionKey like '%" + questionKey[i] +
                                        "%'");
                        }

                        sql_str += ")";

                        //当status == null 时，忽略此条件
                        if ((status != null) && !status.equals("null"))
                        {
                                sql_str += (" and qm.status='" + status + "'");
                        }

                        //当isReply == null 时，忽略此条件
                        if ((isReply != null) && !isReply.equals("null"))
                        {
                                sql_str += (" and qm.isReply='" + isReply + "'");
                        }
                }

                if (sql_str == null)
                {
                        return null;
                }

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionKey] sql_str=" + sql_str);
                        qList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:insertQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 添加一条问题
         *
         * @param addQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void insertQuestion(AnswerQuestionModel addQuestionModel)
                throws AnswerQuestionSysException
        {
                try
                {
                        HibernateDAO.add(addQuestionModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:insertQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 修改一条问题
         *
         * @param updQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void updateQuestiont(AnswerQuestionModel updQuestionModel)
                throws AnswerQuestionSysException
        {
                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:insertQuestion] getDepth=" +
                                        updQuestionModel.getDepth());
                        HibernateDAO.update(updQuestionModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:insertQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 删除一或多条问题
         *
         * @param aqIDs
         * @throws AnswerQuestionSysException
         */
        public void deleteQuestiont(List aqIDs) throws AnswerQuestionSysException
        {
                String sql_str = null;

                if (aqIDs.size() > 0)
                {
                        sql_str = "from AnswerQuestionModel as qm where qm.aqID = " +
                                aqIDs.get(0);

                        for (int i = 1; i < aqIDs.size(); i++)
                        {
                                sql_str += (" or qm.aqID = " + aqIDs.get(i) + "");
                        }

                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:deleteQuestiont] sql_str=" + sql_str);
                }

                if (sql_str == null)
                {
                        return;
                }

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 读取一个问题类别
         *
         * @param catalogID
         * @return
         * @throws AnswerQuestionSysException
         */
        public AQCatalogModel getCatalog(int catalogID)
                throws AnswerQuestionSysException
        {
                AQCatalogModel cModel = new AQCatalogModel();
                List cList = null;
                String sql_str = "from AQCatalogModel as cm where cm.catalogID=" +
                        catalogID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getCatalog] sql_str=" + sql_str);
                        cList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                for (int i = 0; i < cList.size(); i++)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] listSize=" + cList.size());
                        cModel = (AQCatalogModel) cList.get(i);
                }

                return cModel;
        }

        /**
         * 根据类别名称读取一个问题类别
         *
         * @param name
         * @return
         * @throws AnswerQuestionSysException
         */
        public AQCatalogModel getCatalog(String name)
                throws AnswerQuestionSysException
        {
                AQCatalogModel cModel = new AQCatalogModel();
                List cList = null;
                String sql_str = "from AQCatalogModel as cm where cm.catalogName=" +
                        name;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getCatalog] sql_str=" + sql_str);
                        cList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                for (int i = 0; i < cList.size(); i++)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] listSize=" + cList.size());
                        cModel = (AQCatalogModel) cList.get(i);
                }

                return cModel;
        }

        /**
         * 读取所有问题类别
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getAllCatalog(int type) throws AnswerQuestionSysException
        {
                List cList = null;
                String sql_str = "from AQCatalogModel as cm where cm.type='" + type +
                        "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getCatalog] sql_str=" + sql_str);
                        cList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return cList;
        }

        /**
         * 添加一个问题类别
         *
         * @param addCatalogModel
         * @throws AnswerQuestionSysException
         */
        public void insertCatalog(AQCatalogModel addCatalogModel)
                throws AnswerQuestionSysException
        {
                try
                {
                        LogUtil.debug("course", "[AnswerQuestionDAOImpl:insertCatalog] ");
                        HibernateDAO.add(addCatalogModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:insertCatalog] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 修改问题类别
         *
         * @param updCatalogModel
         * @throws AnswerQuestionSysException
         */
        public void updateCatalog(AQCatalogModel updCatalogModel)
                throws AnswerQuestionSysException
        {
                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionAction*updCatalog]getCatalogID=" +
                                        updCatalogModel.getCatalogID());
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl*updCatalog]cModel getCatalogName=" +
                                        updCatalogModel.getCatalogName());
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl*updCatalog]cModel getType=" +
                                        updCatalogModel.getType());
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl*updCatalog]cModel getDescription=" +
                                        updCatalogModel.getDescription());
                        LogUtil.debug("course", "[AnswerQuestionDAOImpl:getCatalog] ");
                        HibernateDAO.update(updCatalogModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 删除问题类别
         *
         * @param catalogIDs
         * @throws AnswerQuestionSysException
         */
        public void deleteCatalog(List catalogIDs)
                throws AnswerQuestionSysException
        {
                String sql_str = null;

                if (catalogIDs.size() > 0)
                {
                        sql_str = "from AQCatalogModel as qm where qm.catalogID = " +
                                catalogIDs.get(0);

                        for (int i = 1; i < catalogIDs.size(); i++)
                        {
                                sql_str += (" or qm.catalogID = " + catalogIDs.get(i) + "");
                        }

                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:deleteCatalog] sql_str=" + sql_str);
                }

                if (sql_str == null)
                {
                        return;
                }

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 读取一个类别下的所有问题
         *
         * @param parent
         * @return
         * @throws AnswerQuestionSysException
         */
        public int getParent(int parent) throws AnswerQuestionSysException
        {
                AnswerQuestionModel qModel = null;
                List qList = null;
                String sql_str = "from AnswerQuestionModel as qm where qm.parentID = " +
                        parent;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] sql_str=" + sql_str);
                        qList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                parent = qList.size();

                return parent;
        }

        /**
         * 根据问题标题模糊查询
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllTitle(int type, int relationID,
                                        String questionTitle) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.questionTitle" + " like " + "'%" + questionTitle + "%'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 根据问题内容模糊查询
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllContent(int type, int relationID,
                                          String questionContent) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and " +
                        "qm.questionContent" + " like " + "'%" + questionContent + "%'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 根据问题提交人查询问题
         *
         * @param type
         * @param relationID
         * @param submmiterName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllSubmmiterName(int type, int relationID,
                                                String submmiterName) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.submmiterName=" + "'" + submmiterName + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 根据问题回复人查询问题
         *
         * @param type
         * @param relationID
         * @param replyerName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllReplyerName(int type, int relationID,
                                              String replyerName) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.replyerName=" + "'" + replyerName + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * 根据类别查询问题
         *
         * @param type
         * @param relationID
         * @param parentID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllCatalog(int type, int relationID, int parentID)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and qm.parentID=" +
                        parentID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * @param type
         * @param relationID
         * @param questionTitle
         * @param status
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllTitleAttribute(int type, int relationID,
                                                 String questionTitle, String status) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.questionTitle" + " like " + "'%" + questionTitle + "%'" +
                        " and qm.status=" + "'" + status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllContentAttribute(int type, int relationID,
                                                   String questionContent, String status)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and " +
                        "qm.questionContent" + " like " + "'%" + questionContent + "%'" +
                        " and qm.status=" + "'" + status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllSubmmiterNameAttribute(int type, int relationID,
                                                         String submmiterName, String status) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.submmiterName=" + "'" + submmiterName + "'" +
                        " and qm.status=" + "'" + status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllReplyerNameAttribute(int type, int relationID,
                                                       String replyerName, String status) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.replyerName=" + "'" + replyerName + "'" +
                        " and qm.status=" + "'" + status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllCatalogAttribute(int type, int relationID,
                                                   int parentID, String status) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and qm.parentID=" +
                        parentID + " and qm.status=" + "'" + status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllTitleReply(int type, int relationID,
                                             String questionTitle, String isReply) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.questionTitle" + " like " + "'%" + questionTitle + "%'" +
                        " and qm.isReply='" + isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllContentReply(int type, int relationID,
                                               String questionContent, String isReply)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and " +
                        "qm.questionContent" + " like " + "'%" + questionContent + "%'" +
                        " and qm.isReply='" + isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllSubmmiterNameReply(int type, int relationID,
                                                     String submmiterName, String isReply) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.submmiterName=" + "'" + submmiterName + "'" +
                        " and qm.isReply='" + isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllReplyerNameReply(int type, int relationID,
                                                   String replyerName, String isReply) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.replyerName=" + "'" + replyerName + "'" +
                        " and qm.isReply='" + isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllCatalogReply(int type, int relationID,
                                               int parentID, String isReply) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and qm.parentID=" +
                        parentID + " and qm.isReply='" + isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllAttributeReply(int type, String isReply,
                                                 int relationID, String status) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.isReply='" + isReply + "' and relationID=" +
                        relationID + " and qm.status=" + "'" + status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllTitleReplyAttribute(int type, int relationID,
                                                      String questionTitle, String isReply, String status)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.questionTitle" + " like " + "'%" + questionTitle + "%'" +
                        " and qm.isReply='" + isReply + "' and qm.status=" + "'" + status +
                        "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllContentReplyAttribute(int type, int relationID,
                                                        String questionContent, String isReply, String status)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and " +
                        "qm.questionContent" + " like " + "'%" + questionContent + "%'" +
                        " and qm.isReply='" + isReply + "' and qm.status=" + "'" + status +
                        "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllSubmmiterNameReplyAttribute(int type,
                                                              int relationID, String submmiterName, String isReply, String status)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.submmiterName=" + "'" + submmiterName + "'" +
                        " and qm.isReply='" + isReply + "' and qm.status=" + "'" + status +
                        "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllReplyerNameReplyAttribute(int type,
                                                            int relationID, String replyerName, String isReply, String status)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.replyerName=" + "'" + replyerName + "'" +
                        " and qm.isReply='" + isReply + "' and qm.status=" + "'" + status +
                        "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getQuestionAllCatalogReplyAttribute(int type, int relationID,
                                                        int parentID, String isReply, String status)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and qm.parentID=" +
                        parentID + " and qm.isReply='" + isReply + "' and qm.status=" +
                        "'" + status + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionCatalog(int type, int relationID, int parentID,
                                         int submmiterID) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and submmiterID=" + submmiterID + " and relationID=" +
                        relationID + " and qm.parentID=" + parentID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionTitle(int type, int relationID,
                                       String questionTitle, int submmiterID)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.questionTitle" + " like " + "'%" + questionTitle + "%'" +
                        " and qm.submmiterID=" + submmiterID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionContent(int type, int relationID,
                                         String questionContent, int submmiterID)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and " +
                        "qm.questionContent" + " like " + "'%" + questionContent + "%'" +
                        " and qm.submmiterID=" + submmiterID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionReplyerName(int type, int relationID,
                                             String replyerName, int submmiterID) throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.replyerName=" + "'" + replyerName + "'" +
                        " and qm.submmiterID=" + submmiterID;

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionReplyerNameIsReply(int type, int relationID,
                                                    String replyerName, int submmiterID, String isReply)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.replyerName=" + "'" + replyerName + "'" +
                        " and qm.submmiterID=" + submmiterID + " and qm.isReply='" +
                        isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionContentIsReply(int type, int relationID,
                                                String questionContent, int submmiterID, String isReply)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID + " and " +
                        "qm.questionContent" + " like " + "'%" + questionContent + "%'" +
                        " and qm.submmiterID=" + submmiterID + " and qm.isReply='" +
                        isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionTitleIsReply(int type, int relationID,
                                              String questionTitle, int submmiterID, String isReply)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and qm.relationID=" + relationID +
                        " and qm.questionTitle" + " like " + "'%" + questionTitle + "%'" +
                        " and qm.submmiterID=" + submmiterID + " and qm.isReply='" +
                        isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        public List getMyQuestionCatalogIsReply(int type, int relationID,
                                                int parentID, int submmiterID, String isReply)
                throws AnswerQuestionSysException
        {
                List qList = null;
                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "' and submmiterID=" + submmiterID + " and relationID=" +
                        relationID + " and qm.parentID=" + parentID + " and qm.isReply='" +
                        isReply + "'";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        qList = HibernateDAO.find(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return qList;
        }

        /**
         * add:yangds
         * 系统首页列表查询-----公共问题或个人问题中的匿名登陆者的问题(submiterID=0)
         *
         * @param type        类型 0：常见问题    1：问题解答  2：课程答疑
         * @param relationID  -1表示 此条件忽略
         * @param submmiterID 提问人
         * @param replyerID   答复人
         * @param status      null表示 此条件忽略    1:表示常见问题，0：表示非常柬问题
         * @param title       null表示 此条件忽略    问题标题
         * @param content     null表示 此条件忽略    问题内容
         * @param parentID    -1表示 此条件忽略      类型ID
         * @param isReply     null表示 此条件忽略    1:表示已回复问题，0：表示未回复问题
         * @param isUserful   null表示 此条件忽略  1:表示常见问题，0：表示非常柬问题
         * @param startDate   null表示 此条件忽略  时间上限
         * @param endDate     null表示 此条件忽略    时间下限
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionOnShow(int type, int relationID, int submmiterID,
                                        String status, String title, String content, int parentID,
                                        String isReply, String isUserful, int replyerID, Date startDate,
                                        Date endDate) throws AnswerQuestionSysException
        {
                Session session = null;
                List qList = null;

                if ((title == null) || title.equals("null"))
                {
                        title = "";
                }

                if ((content == null) || content.equals("null"))
                {
                        content = "";
                }

                String str_sql = "from AnswerQuestionModel as qm where qm.type='" +
                        type + "'";

                //当relationID == -1时，此条件不考虑
                if (relationID != -1)
                {
                        str_sql += (" and qm.relationID=" + relationID);
                }

                //当title == null时，此条件不考虑，
                if ((title != null) && !title.equals("null") && !title.equals(""))
                {
                        str_sql += (" and qm.questionTitle like '%" + title + "%'");
                }

                //当status == null时，此条件不考虑，
                if ((content != null) && !content.equals("null") &&
                        !content.equals(""))
                {
                        str_sql += (" and qm.questionContent like '%" + content + "%'");
                }

                //当parentID == -1时，此条件不考虑
                if (parentID != -1)
                {
                        str_sql += (" and qm.parentID=" + parentID);
                }

                //当status == null时，此条件不考虑，
                if ((status != null) && !status.equals("null") && !status.equals(""))
                {
                        str_sql += (" and qm.status='" + status + "'");
                }

                //当submmiterID == -1时，此条件不考虑，
                if (submmiterID != -1)
                {
                        str_sql += (" and qm.submmiterID=" + submmiterID);
                }

                //当isReply == null时,不考虑此条件
                if ((isReply != null) && !isReply.equals("null") &&
                        !isReply.equals(""))
                {
                        if (isReply.equals("1"))
                        {
                                str_sql += (" and (qm.isReply='" + isReply +
                                        "' or qm.isReply='2')");
                        }
                        else
                        {
                                str_sql += (" and qm.isReply='" + isReply + "'");
                        }
                }

                //当IsUserful == null时,不考虑此条件
                if ((isUserful != null) && !isUserful.equals("null") &&
                        !isUserful.equals(""))
                {
                        str_sql += (" and qm.isUserful='" + isUserful + "'");
                }

                //当replyerID == -1时,不考虑此条件
                if (replyerID != -1)
                {
                        str_sql += (" and qm.replyerID=" + replyerID);
                }

                //当startDate == null时,不考虑此条件
                if ((startDate != null) && !startDate.equals("null") &&
                        !startDate.equals(""))
                {
                        str_sql += " and qm.createDate >= :beginDate";
                }

                //当startDate == null时,不考虑此条件
                if ((endDate != null) && !endDate.equals("null") &&
                        !endDate.equals(""))
                {
                        str_sql += " and qm.createDate <= :endDate";
                }

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(str_sql);

                        if ((startDate != null) && !startDate.equals("null"))
                        {
                                query.setParameter("beginDate", startDate);
                        }

                        if ((endDate != null) && !endDate.equals("null"))
                        {
                                query.setParameter("endDate", endDate);
                        }

                        //System.out.println(str_sql);
                        qList = query.list();
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                        throw new AnswerQuestionSysException(
                                "HibernateException while find:" + str_sql + " \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new AnswerQuestionSysException(
                                        "HibernateException while Hibernate update:  " + e, e);
                        }
                }

                return qList;
        }

        public List countAnswer(String startDate, String stopDate)
                throws AnswerQuestionSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                ArrayList qList = new ArrayList();
                String str_sql = "select distinct replyerID,count(aqID) from T_ANSWERQUESTION_TAB where type='2' and replyerID > 0";

                //当startDate == null时,不考虑此条件
                if ((startDate != null) && !startDate.equals("null") &&
                        !startDate.equals(""))
                {
                        str_sql += (" and createDate >= to_Date('" + startDate +
                                "','YYYY-MM-DD')");
                }

                //当startDate == null时,不考虑此条件
                if ((stopDate != null) && !stopDate.equals("null") &&
                        !stopDate.equals(""))
                {
                        str_sql += (" and createDate <= to_Date('" + stopDate +
                                "','YYYY-MM-DD')");
                }

                str_sql += " group by replyerID";

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionAll] sql_str=" + str_sql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(str_sql);

                        ArrayList list = null;

                        while (rs.next())
                        {
                                list = new ArrayList();
                                list.add(new Integer(rs.getInt(1)));
                                list.add(new Integer(rs.getInt(2)));
                                qList.add(list);
                        }

                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] sql_str=" + str_sql);
                        System.out.println(str_sql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionDAOImpl:getQuestionReply] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new AnswerQuestionSysException(
                                "HibernateException while find:" + str_sql + " \n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return qList;
        }
}
