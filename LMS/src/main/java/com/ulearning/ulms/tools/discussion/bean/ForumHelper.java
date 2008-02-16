/**
 * ForumHelper.java.
 * User: huangsb  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.bean;

import com.ulearning.ulms.tools.discussion.dao.ForumDAO;
import com.ulearning.ulms.tools.discussion.dao.ForumDAOFactory;
import com.ulearning.ulms.tools.discussion.exceptions.ForumDAOSysException;
import com.ulearning.ulms.tools.discussion.form.ForumForm;

import java.util.List;


public class ForumHelper
{
        /**
         * Wrapping the get forum method for JSP and  the other modules
         *
         * @param forumID
         * @return the admin modle according to the forumID
         */
        public ForumForm getForum(int forumID)
        {
                ForumForm ff = null;

                try
                {
                        ForumDAO forumDao = ForumDAOFactory.getDAO();
                        ff = forumDao.getForum(forumID);
                }
                catch (ForumDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ff;
        }

        /**
         * Get userID by loginName
         *
         * @param title
         * @return int value if exist, 0 otherwise
         */
        public static int getForumID(String title)
        {
                int forumID = 0;

                try
                {
                        ForumDAO forumDao = ForumDAOFactory.getDAO();
                        forumID = forumDao.getForumID(title);
                }
                catch (ForumDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return forumID;
        }

        /**
         * Get total by loginName
         *
         * @param forumID
         * @return int value if exist, 0 otherwise
         */
        public static int getTotalDiscuss(int forumID)
        {
                int total = 0;

                try
                {
                        ForumDAO forumDao = ForumDAOFactory.getDAO();
                        total = forumDao.getTotalDisucss(forumID);
                }
                catch (ForumDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return total;
        }

        /**
         * 获得一个板块的所有主题的回复记录数
         *
         * @param forumID
         * @return
         */
        public static int getTotalRevert(int forumID)
        {
                int total = 0;

                try
                {
                        ForumDAO forumDao = ForumDAOFactory.getDAO();
                        total = forumDao.getTotalRevert(forumID);
                }
                catch (ForumDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return total;
        }

        /**
         * Wrapping the get forumList method for JSP and  the other modules
         *
         * @param courseID
         * @return the Forum list according to the courseID
         */
        public List getForumList(int courseID)
        {
                List paperList = null;

                try
                {
                        ForumDAO forumDao = ForumDAOFactory.getDAO();
                        paperList = forumDao.getForumList(courseID);
                }
                catch (ForumDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperList;
        }
}
