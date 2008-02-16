/*
 * Created on 2004-10-4
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.util.TestUtil;

import junit.framework.TestCase;

import java.util.*;


/**
 * Test SqlMapLinkDao.
 *
 * @author Huaxia
 */
public class SqlMapLinkDaoTest extends TestCase
{
        private static final int TEST_ACCOUNT_ID = 97708660;
        private static final String TEST_ACCOUNT_USERNAME = "test4link";
        private static final int TEST_CATEGORY_ID = 97708660;
        private static final String TEST_CATEGORY_TITLE = "link_test";
        private static final int TEST_LINK_ID = 97708660;
        private static final String TEST_LINK_TITLE = "my link-";
        private SqlMapLinkDao dao = new SqlMapLinkDao();

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
                account.setTitle("test link");
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
                category.setTitle(TEST_CATEGORY_TITLE + 1);
                category.setType(Category.TYPE_LINKS);
                category.setVisible(true);

                try
                {
                        for (int i = 0; i < 3; i++)
                        {
                                category.setCategoryId(TEST_CATEGORY_ID + i);
                                category.setTitle(TEST_CATEGORY_TITLE + i);
                                category.validate();
                                new SqlMapCategoryDao().createCategory(category);
                        }
                }
                catch (Exception e)
                {
                }

                // delete all links with the TEST_ACCOUNT_ID:
                TestUtil.executeSQL(
                        "delete from [Link] where categoryId in (select categoryId from [Category] where accountId=" +
                                TEST_ACCOUNT_ID + ")");
        }

        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        public void testGetAllLinks()
        {
                testCreateLink();

                List list = dao.getAllLinks(TEST_ACCOUNT_ID);

                assertTrue(list.size() == 20);
                assertTrue(((Link) list.get(9)).getCategoryId() == TEST_CATEGORY_ID);
                assertTrue(((Link) list.get(10)).getCategoryId() == (TEST_CATEGORY_ID +
                        1));
        }

        public void testGetLinksCount()
        {
                testCreateLink();

                int n = dao.getLinksCount(TEST_CATEGORY_ID);
                assertTrue(n == 10);
        }

        public void testCreateLink()
        {
                Link link = new Link();
                link.setUrl("http://test.cactusblog.org/");
                link.setRss("http://rss.cactusblog.org/");
                link.setNewWindow(true);
                link.setCategoryId(TEST_CATEGORY_ID);

                for (int i = 0; i < 10; i++)
                {
                        link.setLinkId(TEST_LINK_ID + i);
                        link.setTitle(TEST_LINK_TITLE + i);
                        link.validate();
                        dao.createLink(link);
                }

                link.setCategoryId(TEST_CATEGORY_ID + 1);

                for (int i = 10; i < 20; i++)
                {
                        link.setLinkId(TEST_LINK_ID + i);
                        link.setTitle(TEST_LINK_TITLE + i);
                        link.validate();
                        dao.createLink(link);
                }
        }

        public void testUpdateLink()
        {
                testCreateLink();

                // get from db:
                List list = dao.getAllLinks(TEST_ACCOUNT_ID);
                Link link = (Link) list.get(0);

                // update:
                String new_url = "http://new.url.org/";
                link.setUrl(new_url);
                dao.updateLink(link);

                // get again:
                List list2 = dao.getAllLinks(TEST_ACCOUNT_ID);
                Link link2 = (Link) list.get(0);
                assertTrue(link2.getUrl().endsWith(new_url));
        }

        public void testDeleteLink()
        {
                testCreateLink();
                // delete 3 links:
                dao.deleteLink(TEST_LINK_ID + 5);
                dao.deleteLink(TEST_LINK_ID + 8);
                dao.deleteLink(TEST_LINK_ID + 15);

                // get from db:
                List list = dao.getAllLinks(TEST_ACCOUNT_ID);
                assertTrue(list.size() == 17);
        }
}
