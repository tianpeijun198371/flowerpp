/**
 * ForumDAO.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.tools.discussion.exceptions.ForumDAOSysException;
import com.ulearning.ulms.tools.discussion.form.ForumForm;

import java.util.ArrayList;
import java.util.List;


public interface ForumDAO
{
        /**
         * publish a new forum
         *
         * @param forumForm the new Forum
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void insert(ForumForm forumForm) throws ForumDAOSysException;

        /**
         * update a discuss
         *
         * @param forumForm
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void update(ForumForm forumForm) throws ForumDAOSysException;

        /**
         * delete some Forum
         *
         * @param forumID of Forum
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void remove(int forumID) throws ForumDAOSysException;

        /**
         * delete some forum
         *
         * @param forumIDs IDs of forum
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void remove(String[] forumIDs) throws ForumDAOSysException;

        /**
         * get a piece of information
         *
         * @param forumID
         * @return a string contains user's basic email information
         * @throws ForumDAOSysException If an ulmsSys error has occurred.
         */
        public ForumForm getForum(int forumID) throws ForumDAOSysException;

        /**
         * Get all the Forum according to the forumID
         *
         * @param courseID
         * @return
         * @throws ForumDAOSysException
         */
        public List getForumList(int courseID) throws ForumDAOSysException;

        /**
         * Get all the ForumID according to the title
         *
         * @param title
         * @return
         * @throws ForumDAOSysException
         */
        public int getForumID(String title) throws ForumDAOSysException;

        /**
         * Get all the ForumID according to the title
         *
         * @param forumID
         * @return
         * @throws ForumDAOSysException
         */
        public int getTotalDisucss(int forumID) throws ForumDAOSysException;

        /**
         * 获得一个板块的所有主题的回复记录数
         *
         * @param forumID
         * @return
         * @throws ForumDAOSysException
         */
        public int getTotalRevert(int forumID) throws ForumDAOSysException;
}
