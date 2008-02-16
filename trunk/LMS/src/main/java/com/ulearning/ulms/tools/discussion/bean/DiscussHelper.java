/**
 * DiscussHelper.java.
 * User: huangsb  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.bean;

import com.ulearning.ulms.tools.discussion.dao.DiscussDAO;
import com.ulearning.ulms.tools.discussion.dao.DiscussDAOFactory;
import com.ulearning.ulms.tools.discussion.exceptions.DiscussDAOSysException;
import com.ulearning.ulms.tools.discussion.form.DiscussForm;

import java.util.List;


public class DiscussHelper
{
        /**
         * Wrapping the get discuss method for JSP and  the other modules
         *
         * @param discussID
         * @return the admin modle according to the discussID
         */
        public DiscussForm getDiscuss(int discussID)
        {
                DiscussForm ff = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        ff = discussDao.getDiscuss(discussID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ff;
        }

        /**
         * 获得一个板块的最新的一个主题
         *
         * @param forumID
         * @return
         */
        public DiscussForm getlastDiscuss(int forumID)
        {
                DiscussForm ff = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        ff = discussDao.getlastDiscuss(forumID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ff;
        }

        /**
         * Wrapping the get discussList method for JSP and  the other modules
         *
         * @param discussID
         * @param parentID
         * @return the discuss list according to the discussID
         */
        public List getDiscussList(int discussID, int parentID)
        {
                List discussList = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        discussList = discussDao.getDiscussList(discussID, parentID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return discussList;
        }

        /**
         * Wrapping the get discussList method for JSP and  the other modules
         *
         * @param parentID
         * @return the discuss list according to the discussID
         */
        public List getDiscussTree(int parentID)
        {
                List discussList = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        discussList = discussDao.getDiscussTree(parentID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return discussList;
        }

        /**
         * Wrapping the get discussList method for JSP and  the other modules
         *
         * @param parentID
         * @param forumID
         * @return the discuss list according to the discussID
         */
        public List getDiscussTree(int forumID, int parentID)
        {
                List discussList = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        discussList = discussDao.getDiscussTree(forumID, parentID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return discussList;
        }

        /**
         * 获得一个板块的所有主题的回复记录数
         *
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public int getDiscussRevert(int parentID) throws DiscussDAOSysException
        {
                int revert_num = 0;

                try
                {
                        DiscussDAO discussDAO = DiscussDAOFactory.getDAO();
                        revert_num = discussDAO.getDiscussRevert(parentID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return revert_num;
        }

        /**
         * 查询符合条件的记录
         *
         * @param forumID
         * @param title
         * @return
         * @throws DiscussDAOSysException
         */
        public List selectDiscuss(int forumID, String title)
                throws DiscussDAOSysException
        {
                List discussList = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        discussList = discussDao.selectDiscuss(forumID, title);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return discussList;
        }

        /**
         * 精华帖子
         *
         * @param forumID
         * @return
         * @throws DiscussDAOSysException
         */
        public List selecttopDiscuss(int forumID) throws DiscussDAOSysException
        {
                List discussList = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        discussList = discussDao.selecttopDiscuss(forumID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return discussList;
        }

        /**
         * 我发表或参与的帖子
         *
         * @param UserID
         * @param is_topic
         * @return
         * @throws DiscussDAOSysException
         */
        public List myDiscuss(int UserID, int is_topic)
                throws DiscussDAOSysException
        {
                List discussList = null;

                try
                {
                        DiscussDAO discussDao = DiscussDAOFactory.getDAO();
                        discussList = discussDao.myDiscuss(UserID, is_topic);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return discussList;
        }

        /**
         * 获得某个人发表的帖子数
         *
         * @param UserID
         * @return
         * @throws DiscussDAOSysException
         */
        public int getmydiscuss_num(int UserID) throws DiscussDAOSysException
        {
                int mydiscuss_num = 0;

                try
                {
                        DiscussDAO discussDAO = DiscussDAOFactory.getDAO();
                        mydiscuss_num = discussDAO.getmydiscuss_num(UserID);
                }
                catch (DiscussDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return mydiscuss_num;
        }

        public List getBest(int forumID) throws DiscussDAOSysException
        {
                return DiscussDAOFactory.getDAO().getBest(forumID);
        }
}
