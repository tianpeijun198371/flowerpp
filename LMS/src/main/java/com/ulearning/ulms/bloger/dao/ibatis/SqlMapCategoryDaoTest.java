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
 * Test SqlMapCategoryDao
 *
 * @author Huaxia
 */
public class SqlMapCategoryDaoTest extends TestCase
{
        private static final int TEST_CATEGORY_ID = 90192099;
        private static final String TEST_CATEGORY_TITLE = "test-category-";
        private static final int TEST_ACCOUNT_ID = 99279029;
        private static final String TEST_ACCOUNT_USERNAME = "test4category";
        private SqlMapCategoryDao dao = new SqlMapCategoryDao();

        // we create an new account before test:
        protected void setUp() throws Exception
        {
                super.setUp();

                // create an account:
                Account account = new Account();
                account.setAccountId(TEST_ACCOUNT_ID);
                account.setUsername(TEST_ACCOUNT_USERNAME);
                account.setPassword("12345678");
                account.setCreatedDate(new java.util.Date());
                account.setTitle("test4category");
                account.validate();

                try
                {
                        new SqlMapAccountDao().createAccount(account);
                }
                catch (Exception e)
                {
                }

                // before create a category, delete exist one:
                TestUtil.executeSQL("delete from [Category] where accountId=" +
                        TEST_ACCOUNT_ID);
        }

        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        public void testGetCategories()
        {
                // first create 2 categories:
                testCreateCategory();

                // get them:
                List list = dao.getCategories(TEST_ACCOUNT_ID);
                assertTrue(list.size() == 10);
        }

        public void testGetCategoriesOfArticle()
        {
                testCreateCategory();

                // get:
                List list = dao.getCategoriesOfArticle(TEST_ACCOUNT_ID);
                assertTrue(list.size() == 5);
        }

        public void testGetCategory()
        {
                // first create a new category:
                testCreateCategory();

                // then get it:
                Category c = dao.getCategory(TEST_CATEGORY_ID);
                assertTrue(c.getAccountId() == TEST_ACCOUNT_ID);
        }

        public void testCreateCategory()
        {
                // now we create a category:
                Category c = new Category();
                c.setAccountId(TEST_ACCOUNT_ID);
                c.setVisible(true);
                c.setDescription("no description...");

                for (int i = 0; i < 10; i++)
                {
                        c.setType(i % 2);
                        c.setCategoryId(TEST_CATEGORY_ID + i);
                        c.setTitle(TEST_CATEGORY_TITLE + i);
                        c.validate();
                        dao.createCategory(c);
                }
        }

        public void testUpdateCategory()
        {
                testCreateCategory();

                // and get it:
                Category c = dao.getCategory(TEST_CATEGORY_ID);

                // test update:
                String new_title = "test_update_category";
                c.setTitle(new_title);
                c.validate();
                dao.updateCategory(c);

                // now check:
                Category new_c = dao.getCategory(TEST_CATEGORY_ID);
                assertTrue(new_c.getTitle().equals(new_title));
        }

        public void testDeleteCategory()
        {
                testCreateCategory();
                // now delete category:
                dao.deleteCategory(TEST_CATEGORY_ID);

                List list = dao.getCategories(TEST_ACCOUNT_ID);
                assertTrue(list.size() == 9);
        }
}
