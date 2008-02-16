/*
 * Created on 2004-9-30
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.util.TestUtil;

import junit.framework.TestCase;

import java.util.*;


/**
 * @author Huaxia
 */
public class SqlMapFeedbackDaoTest extends TestCase
{
        private static final int TEST_ACCOUNT_ID = 90773350;
        private static final String TEST_ACCOUNT_USERNAME = "test4feedback";
        private static final int TEST_CATEGORY_ID = 90773310;
        private static final int TEST_ARTICLE_ID = 90773100;
        private static final int TEST_FEEDBACK_ID = 90773600;
        private SqlMapFeedbackDao dao = new SqlMapFeedbackDao();

        protected void setUp() throws Exception
        {
                super.setUp();

                // create an account:
                Account account = new Account();
                account.setAccountId(TEST_ACCOUNT_ID);
                account.setUsername(TEST_ACCOUNT_USERNAME);
                account.setPassword("justtest");
                account.setCreatedDate(new Date());
                account.setMaxPerPage(10);
                account.setTitle("test feedback");
                account.validate();

                try
                {
                        new SqlMapAccountDao().createAccount(account);
                }
                catch (Exception e)
                {
                }

                // create a category:
                Category category = new Category();
                category.setAccountId(TEST_ACCOUNT_ID);
                category.setCategoryId(TEST_CATEGORY_ID);
                category.setTitle("test feedback");
                category.setType(Category.TYPE_ARTICLES);
                category.setVisible(true);
                category.validate();

                try
                {
                        new SqlMapCategoryDao().createCategory(category);
                }
                catch (Exception e)
                {
                }

                // create an article:
                Article article = new Article();
                article.setArticleId(TEST_ARTICLE_ID);
                article.setCategoryId(TEST_CATEGORY_ID);
                article.setAccountId(TEST_ACCOUNT_ID);
                article.setTitle("test 4 feedback");
                article.setContent("some content for testing feedback");
                article.setCreatedDate(new Date());
                article.validate();

                try
                {
                        new SqlMapArticleDao().createArticle(article);
                }
                catch (Exception e)
                {
                }

                // delete all feedbacks with articleId = TEST_ARTICLE_ID:
                TestUtil.executeSQL("delete from Feedback where articleId=" +
                        TEST_ARTICLE_ID);
        }

        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        public void testGetFeedbacks()
        {
                testCreateFeedback();

                // get all:
                List list = dao.getFeedbacks(TEST_ARTICLE_ID);
                assertTrue(list.size() == 15);
        }

        public void testCreateFeedback()
        {
                Feedback feedback = new Feedback();
                feedback.setArticleId(TEST_ARTICLE_ID);
                feedback.setUrl("http://from.test.com?id=12092");
                feedback.setCreatedDate(new Date());

                for (int i = 0; i < 15; i++)
                {
                        feedback.setFeedbackId(TEST_FEEDBACK_ID + i);
                        feedback.setContent("feedback of " + i);
                        feedback.validate();
                        dao.createFeedback(feedback);
                }

                int count = new SqlMapAccountDao().getAccount(TEST_ACCOUNT_ID)
                        .getFeedbacks();
                assertTrue(count == 15);
                count = new SqlMapArticleDao().getArticle(TEST_ARTICLE_ID).getFeedbacks();
                assertTrue(count == 15);
        }

        public void testDeleteFeedback()
        {
                testCreateFeedback();
                // delete 1, 3, 5:
                dao.deleteFeedback(TEST_FEEDBACK_ID);
                dao.deleteFeedback(TEST_FEEDBACK_ID + 2);
                dao.deleteFeedback(TEST_FEEDBACK_ID + 4);

                int count = new SqlMapAccountDao().getAccount(TEST_ACCOUNT_ID)
                        .getFeedbacks();
                assertTrue(count == 12);
                count = new SqlMapArticleDao().getArticle(TEST_ARTICLE_ID).getFeedbacks();
                assertTrue(count == 12);
        }
}
