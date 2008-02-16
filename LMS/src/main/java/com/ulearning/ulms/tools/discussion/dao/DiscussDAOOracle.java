/**
 * DiscussDAOOracle.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.discussion.exceptions.DiscussDAOSysException;
import com.ulearning.ulms.tools.discussion.form.DiscussForm;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;


public class DiscussDAOOracle extends DiscussDAOImpl
{
        /**
         * publish a new discuss
         *
         * @param discussForm the new discuss
         * @throws com.ulearning.ulms.tools.discussion.exceptions.DiscussDAOSysException
         *          If an lmsSys error has occurred.
         */
        public void insert(DiscussForm discussForm) throws DiscussDAOSysException
        {
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String dateTime = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String sqlStr = "insert into C_Discuss_Tab values (ArticleID.nextval," +
                        discussForm.getForumID() + "," + discussForm.getUserID() + "," +
                        discussForm.getParentID() + ",'" + discussForm.getTitle() + "','" +
                        discussForm.getContent() + "'," + dateTime + ",'" +
                        discussForm.getFileLink() + "'," + discussForm.getLinkType() + "," +
                        discussForm.getIsLock() + "," + discussForm.getIsRead() + "," +
                        discussForm.getLayer() + ",'" + discussForm.getDescription() +
                        "')";
                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("system",
                                "[Discuss DiscussDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while updating " +
                                "Discuss; Serial = " + discussForm.getArticleID() + " :\n" +
                                se, se);
                }
        }

        /**
         * update a discuss
         *
         * @param discussForm
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void update(DiscussForm discussForm) throws DiscussDAOSysException
        {
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String dateTime = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String strStr = "update C_Discuss_Tab set " + " ForumID = " +
                        discussForm.getForumID() + ",UserID = " + discussForm.getUserID() +
                        ",ParentID = " + discussForm.getParentID() + ",Title = '" +
                        discussForm.getTitle() + "',Content = '" +
                        discussForm.getContent() + "',DateTime = " + dateTime +
                        ",FileLink = '" + discussForm.getFileLink() + "',LinkType = " +
                        discussForm.getLinkType() + ",IsLock = " + discussForm.getIsLock() +
                        ",IsRead = " + discussForm.getIsRead() + ",Layer = " +
                        discussForm.getLayer() + ",Description = '" +
                        discussForm.getDescription() + "' where ArticleID = " +
                        discussForm.getArticleID();

                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("test",
                                "[PaperAnswerDAOOracle]====================the sql string is : " +
                                        strStr);
                        operateDB.exeupdate(strStr);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while update " +
                                "PaperAnswer; Serial = " + discussForm.getArticleID() + " :\n" +
                                se, se);
                }
        }

        /**
         * update a discuss
         *
         * @param discussForm
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void updateDetail(DiscussForm discussForm)
                throws DiscussDAOSysException
        {
                String strStr = "update C_Discuss_Tab set " + "IsLock = " +
                        discussForm.getIsLock() + ",IsRead = " + discussForm.getIsRead() +
                        " where ArticleID = " + discussForm.getArticleID();

                OperateDB operateDB = new OperateDB();

                try
                {
                        List discussTree = getDiscussTree(discussForm.getArticleID());

                        for (int i = 0; i < discussTree.size(); i++)
                        {
                                DiscussForm df = (DiscussForm) discussTree.get(i);
                                String sql = "update C_Discuss_Tab set " + " IsLock = " +
                                        discussForm.getIsLock() + " where ArticleID = " +
                                        df.getArticleID();
                                LogUtil.debug("test",
                                        "[DiscussDAOOracle]====================the sql string is : " +
                                                sql);
                                operateDB.exeupdate(sql);
                        }

                        LogUtil.debug("test",
                                "[DiscussDAOOracle]====================the sql string is : " +
                                        strStr);
                        operateDB.exeupdate(strStr);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while update " +
                                "DiscussDAO; Serial = " + discussForm.getArticleID() + " :\n" +
                                se, se);
                }
        }
}
