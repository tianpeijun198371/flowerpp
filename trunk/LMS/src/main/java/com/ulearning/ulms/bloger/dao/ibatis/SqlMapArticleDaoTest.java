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
public class SqlMapArticleDaoTest extends TestCase
{
        private static final int TEST_ACCOUNT_ID = 98086608;
        private static final String TEST_ACCOUNT_USERNAME = "test4article";
        private static final int TEST_CATEGORY_ID = 98006608;
        private static final String TEST_CATEGORY_TITLE = "article_test";
        private static final int TEST_ARTICLE_ID = 98006608;
        private static final String TEST_ARTICLE_TITLE = "my article-";
        private SqlMapArticleDao dao = new SqlMapArticleDao();

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
                account.setTitle("test article");
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
                category.setTitle("test4article");
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

                // delete all articles with the TEST_ACCOUNT_ID:
                TestUtil.executeSQL("delete from Article where accountId=" +
                        TEST_ACCOUNT_ID);
        }

        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        public void testGetArticle()
        {
                testCreateArticle();

                Article article = dao.getArticle(TEST_ARTICLE_ID);
                assertTrue(article.getCategoryId() == TEST_CATEGORY_ID);
                assertTrue(article.getAccountId() == TEST_ACCOUNT_ID);
        }

        public void testGetArticleInfo()
        {
                testCreateArticle();

                Article article = dao.getArticle(TEST_ARTICLE_ID);
                assertTrue(article.getCategoryId() == TEST_CATEGORY_ID);
                assertTrue(article.getAccountId() == TEST_ACCOUNT_ID);
        }

        public void testGetArticles()
        {
                testCreateArticle();

                // get 10 articles of page 1:
                List list = dao.getArticles(TEST_ACCOUNT_ID, 10, 1);
                assertTrue(list.size() == 10);
                // get 10 articles of page 3:
                list = dao.getArticles(TEST_ACCOUNT_ID, 10, 3);
                assertTrue(list.size() == 5);
                // get all:
                list = dao.getArticles(TEST_ACCOUNT_ID, 40, 1);
                assertTrue(list.size() == 25);
        }

        public void testGetArticlesByCategory()
        {
                testCreateArticle();

                // get 10 articles of page 1:
                List list = dao.getArticlesByCategory(TEST_CATEGORY_ID, 10, 1);
                assertTrue(list.size() == 10);
                // get 10 articles of page 3:
                list = dao.getArticlesByCategory(TEST_CATEGORY_ID, 10, 3);
                assertTrue(list.size() == 5);
                // get all:
                list = dao.getArticlesByCategory(TEST_CATEGORY_ID, 40, 1);
                assertTrue(list.size() == 25);
        }

        public void testSearchArticle()
        {
        }

        public void testCreateArticle()
        {
                // now we create 25 articles:
                Article article = new Article();
                article.setAccountId(TEST_ACCOUNT_ID);
                article.setCategoryId(TEST_CATEGORY_ID);

                for (int i = 0; i < 25; i++)
                {
                        article.setArticleId(TEST_ARTICLE_ID + i);
                        article.setTitle(TEST_ARTICLE_TITLE + i);
                        article.setCreatedDate(new Date());
                        article.setContent("write some content... " + i);
                        article.validate();
                        dao.createArticle(article);
                }

                int count = new SqlMapAccountDao().getAccount(TEST_ACCOUNT_ID)
                        .getArticles();
                assertTrue(count == 25);
        }

        public void testUpdateArticle()
        {
                testCreateArticle();

                Article article = dao.getArticle(TEST_ARTICLE_ID);

                // update:
                String new_title = "new-article-title";
                article.setTitle(new_title);
                article.validate();
                dao.updateArticle(article);

                // get it:
                Article a2 = dao.getArticle(TEST_ARTICLE_ID);
                // check:
                assertTrue(a2.getTitle().equals(new_title));
        }

        public void testUpdateArticleInfo()
        {
                testCreateArticle();

                Article article = dao.getArticle(TEST_ARTICLE_ID);

                // update:
                String new_title = "new-article-title";
                article.setTitle(new_title);
                article.validate();
                dao.updateArticle(article);

                // get it:
                Article a2 = dao.getArticle(TEST_ARTICLE_ID);
                // check:
                assertTrue(a2.getTitle().equals(new_title));
        }

        public void testDeleteArticle()
        {
                testCreateArticle();
                // delete one:
                dao.deleteArticle(TEST_ARTICLE_ID);

                int count = new SqlMapAccountDao().getAccount(TEST_ACCOUNT_ID)
                        .getArticles();
                assertTrue(count == 24);
        }
}
