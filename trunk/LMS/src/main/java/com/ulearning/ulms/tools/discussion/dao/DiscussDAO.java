/**
 * DiscussDAO.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.tools.discussion.exceptions.DiscussDAOSysException;
import com.ulearning.ulms.tools.discussion.form.DiscussForm;

import java.util.ArrayList;
import java.util.List;


public interface DiscussDAO
{
        /**
         * publish a new discuss
         *
         * @param discussForm the new discuss
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void insert(DiscussForm discussForm) throws DiscussDAOSysException;

        /**
         * delete some discusss
         *
         * @param discussIDs IDs of discuss
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void remove(String[] discussIDs) throws DiscussDAOSysException;

        /**
         * delete some discusss
         *
         * @param discussID IDs of discuss
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void remove(int discussID) throws DiscussDAOSysException;

        /**
         * update a discuss
         *
         * @param discussForm
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void update(DiscussForm discussForm) throws DiscussDAOSysException;

        /**
         * update a discuss
         *
         * @param discussForm
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void updateDetail(DiscussForm discussForm)
                throws DiscussDAOSysException;

        /**
         * get a piece of information
         *
         * @param discussID
         * @return a string contains discuss's basic information
         * @throws DiscussDAOSysException If an ulmsSys error has occurred.
         */
        public DiscussForm getDiscuss(int discussID) throws DiscussDAOSysException;

        /**
         * Get all the Discuss according to the forumID ,parentID
         *
         * @param forumID
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussList(int forumID, int parentID)
                throws DiscussDAOSysException;

        /**
         * Get all the Discuss according to the forumID ,parentID
         *
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussList(int parentID) throws DiscussDAOSysException;

        /**
         * Get all the Discuss according to the parentID
         *
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussTree(int parentID) throws DiscussDAOSysException;

        /**
         * Get all the Discuss according to the forumID ,parentID
         *
         * @param parentID
         * @param forumID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussTree(int forumID, int parentID)
                throws DiscussDAOSysException;

        /**
         * ���һ���������µ�һ������
         *
         * @param forumID
         * @return
         * @throws DiscussDAOSysException
         */
        public DiscussForm getlastDiscuss(int forumID)
                throws DiscussDAOSysException;

        /**
         * ���һ��������������Ļظ���¼��
         *
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public int getDiscussRevert(int parentID) throws DiscussDAOSysException;

        /**
         * ��ѯ���������ļ�¼
         *
         * @param forumID
         * @param title
         * @return
         * @throws DiscussDAOSysException
         */
        public List selectDiscuss(int forumID, String title)
                throws DiscussDAOSysException;

        /**
         * ��������
         *
         * @param forumID
         * @return
         * @throws DiscussDAOSysException
         */
        public List selecttopDiscuss(int forumID) throws DiscussDAOSysException;

        /**
         * �ҷ������������
         *
         * @param UserID
         * @param is_topic
         * @return
         * @throws DiscussDAOSysException
         */
        public List myDiscuss(int UserID, int is_topic)
                throws DiscussDAOSysException;

        /**
         * ���ĳ���˷����������
         *
         * @param UserID
         * @return
         * @throws DiscussDAOSysException
         */
        public int getmydiscuss_num(int UserID) throws DiscussDAOSysException;

        /**
         * �õ���������
         *
         * @param forumID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getBest(int forumID) throws DiscussDAOSysException;
}
