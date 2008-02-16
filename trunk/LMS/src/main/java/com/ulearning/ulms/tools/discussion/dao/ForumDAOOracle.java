/**
 * ForumDAOOracle.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.discussion.exceptions.ForumDAOSysException;
import com.ulearning.ulms.tools.discussion.form.ForumForm;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;


public class ForumDAOOracle extends ForumDAOImpl
{
        /**
         * publish a new forum
         *
         * @param forumForm the new forum
         * @throws com.ulearning.ulms.tools.discussion.exceptions.ForumDAOSysException
         *          If an lmsSys error has occurred.
         */
        public void insert(ForumForm forumForm) throws ForumDAOSysException
        {
                String sqlStr = "insert into C_Forum_Tab values (ForumID.nextval," +
                        forumForm.getCourseID() + "," + forumForm.getOrderIndex() + ",'" +
                        forumForm.getTitle() + "'," + forumForm.getIsAllowAnn() + "," +
                        forumForm.getIsUpdateAft() + "," + forumForm.getIsDeleteAft() +
                        "," + forumForm.getIsAccessory() + "," + forumForm.getIsNewItem() +
                        ",'" + forumForm.getDescription() + "')";
                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("system",
                                "[forum ForumDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new ForumDAOSysException("SQLException while updating " +
                                "forumForm; Serial = " + forumForm.getForumID() + " :\n" + se,
                                se);
                }
        }

        /**
         * update a Forum
         *
         * @param forumForm
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void update(ForumForm forumForm) throws ForumDAOSysException
        {
                String strStr = "update C_Forum_Tab set " + " CourseID = " +
                        forumForm.getCourseID() + ",OrderIndex = " +
                        forumForm.getOrderIndex() + ",Title = '" + forumForm.getTitle() +
                        "',IsAllowAnn = " + forumForm.getIsAllowAnn() + ",IsUpdateAft = " +
                        forumForm.getIsUpdateAft() + ",IsDeleteAft = " +
                        forumForm.getIsDeleteAft() + ",IsAccessory = " +
                        forumForm.getIsAccessory() + ",IsNewItem = " +
                        forumForm.getIsNewItem() + ",Description = '" +
                        forumForm.getDescription() + "' where ForumID = " +
                        forumForm.getForumID();

                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("system",
                                "[ForumDAOOracle]====================the sql string is : " +
                                        strStr);
                        operateDB.exeupdate(strStr);
                }
                catch (ULMSSysException se)
                {
                        throw new ForumDAOSysException("SQLException while update " +
                                "ForumDAO; Serial = " + forumForm.getForumID() + " :\n" + se);
                }
        }
}
