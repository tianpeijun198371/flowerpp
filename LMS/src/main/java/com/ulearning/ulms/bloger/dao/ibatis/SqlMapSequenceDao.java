/*
 * Created on 2004-9-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.SequenceDao;

import java.sql.SQLException;


/**
 * @author Huaxia
 */
public final class SqlMapSequenceDao implements SequenceDao
{
        private static final int ID_ACCOUNT = 0;
        private static final int ID_CATEGORY = 1;
        private static final int ID_ARTICLE = 2;
        private static final int ID_FEEDBACK = 3;
        private static final int ID_LINK = 4;
        private static final int ID_IMAGE = 5;
        private static final int ID_MESSAGE = 6;
        private SqlMapClient sqlMap;
        private int nextAccountId = 0;
        private int nextCategoryId = 0;
        private int nextArticleId = 0;
        private int nextFeedbackId = 0;
        private int nextLinkId = 0;
        private int nextImageId = 0;
        private int nextMessageId = 0;
        private int stepAccount = 0;
        private int stepCategory = 0;
        private int stepArticle = 0;
        private int stepFeedback = 0;
        private int stepLink = 0;
        private int stepImage = 0;
        private int stepMessage = 0;

        public SqlMapSequenceDao()
        {
                sqlMap = SqlConfig.getSqlMapInstance();

                try
                {
                        // this statement do 2 things:
                        // 1. set nextId = 0
                        // 2. set nextId = (select max(id) from [TABLE])
                        //    where sequenceId=## and (select max(id) from [TABLE]) is not null
                        // to make sure the next id will never conflict.
                        sqlMap.update("setMaxId", null);
                        sqlMap.update("setMaxId0", null);
                        sqlMap.update("setMaxId1", null);
                        sqlMap.update("setMaxId2", null);
                        sqlMap.update("setMaxId3", null);
                        sqlMap.update("setMaxId4", null);
                        sqlMap.update("setMaxId5", null);
                        sqlMap.update("setMaxId6", null);
                }
                catch (SQLException sqle)
                {
                        sqle.printStackTrace();
                }
        }

        private int getNextId(int sequenceId)
        {
                try
                {
                        Integer seq = new Integer(sequenceId);
                        sqlMap.update("updateNextId", seq);

                        Integer I = (Integer) sqlMap.queryForObject("getNextId", seq);
                        System.out.println("[INFO] Next id = " + I.intValue());

                        return I.intValue();
                }
                catch (SQLException sqle)
                {
                        sqle.printStackTrace();
                        throw new RuntimeException("Runtime Exception caused by: " + sqle);
                }
        }

        public synchronized int getNextAccountId()
        {
                if (stepAccount == 0)
                {
                        nextAccountId = getNextId(ID_ACCOUNT);
                        stepAccount = 9;

                        return (nextAccountId - stepAccount);
                }

                stepAccount--;

                return nextAccountId - stepAccount;
        }

        public synchronized int getNextCategoryId()
        {
                if (stepCategory == 0)
                {
                        nextCategoryId = getNextId(ID_CATEGORY);
                        stepCategory = 9;

                        return (nextCategoryId - stepCategory);
                }

                stepCategory--;

                return nextCategoryId - stepCategory;
        }

        public synchronized int getNextArticleId()
        {
                if (stepArticle == 0)
                {
                        nextArticleId = getNextId(ID_ARTICLE);
                        stepArticle = 9;

                        return (nextArticleId - stepArticle);
                }

                stepArticle--;

                return nextArticleId - stepArticle;
        }

        public synchronized int getNextFeedbackId()
        {
                if (stepFeedback == 0)
                {
                        nextFeedbackId = getNextId(ID_FEEDBACK);
                        stepFeedback = 9;

                        return (nextFeedbackId - stepFeedback);
                }

                stepFeedback--;

                return nextFeedbackId - stepFeedback;
        }

        public synchronized int getNextLinkId()
        {
                if (stepLink == 0)
                {
                        nextLinkId = getNextId(ID_LINK);
                        stepLink = 9;

                        return (nextLinkId - stepLink);
                }

                stepLink--;

                return nextLinkId - stepLink;
        }

        public synchronized int getNextImageId()
        {
                if (stepImage == 0)
                {
                        nextImageId = getNextId(ID_IMAGE);
                        stepImage = 9;

                        return (nextImageId - stepImage);
                }

                stepImage--;

                return nextImageId - stepImage;
        }

        public synchronized int getNextMessageId()
        {
                if (stepMessage == 0)
                {
                        nextMessageId = getNextId(ID_MESSAGE);
                        stepMessage = 9;

                        return (nextMessageId - stepMessage);
                }

                stepMessage--;

                return nextMessageId - stepMessage;
        }
}
