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
 * TODO Description here...
 *
 * @author Huaxia
 */
public class SqlMapImageDaoTest extends TestCase
{
        private static final int TEST_ACCOUNT_ID = 91018850;
        private static final String TEST_ACCOUNT_USERNAME = "test4image";
        private static final int TEST_CATEGORY_ID = 91018850;
        private static final int TEST_IMAGE_ID = 91018850;
        private static final String TEST_IMAGE_TITLE = "test-image-";
        private SqlMapImageDao dao = new SqlMapImageDao();

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
                account.setTitle("test image");
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
                category.setTitle("test image");
                category.setType(Category.TYPE_IMAGES);
                category.setVisible(true);
                category.validate();

                try
                {
                        new SqlMapCategoryDao().createCategory(category);
                }
                catch (Exception e)
                {
                }

                // delete all images:
                TestUtil.executeSQL("delete from [Image] where categoryId = " +
                        TEST_CATEGORY_ID);
        }

        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        public void testGetImage()
        {
                testCreateImage();

                // get it:
                for (int i = 0; i < 10; i++)
                {
                        Image image = dao.getImage(TEST_IMAGE_ID + i);
                        assertTrue(image.getTitle().equals(TEST_IMAGE_TITLE + i));
                }
        }

        public void testGetImages()
        {
                testCreateImage();

                // get all:
                List list = dao.getImages(TEST_CATEGORY_ID);
                assertTrue(list.size() == 10);

                List list2 = dao.getImages(TEST_CATEGORY_ID, 5, 1);
                assertTrue(list2.size() == 5);
        }

        public void testCreateImage()
        {
                Image image = new Image();
                image.setCategoryId(TEST_CATEGORY_ID);
                image.setVisible(true);

                for (int i = 0; i < 10; i++)
                {
                        image.setImageId(TEST_IMAGE_ID + i);
                        image.setTitle(TEST_IMAGE_TITLE + i);
                        dao.createImage(image);
                }
        }

        public void testDeleteImage()
        {
                testCreateImage();
                // delete 2:
                dao.deleteImage(TEST_IMAGE_ID + 1);
                dao.deleteImage(TEST_IMAGE_ID + 3);

                // get all:
                List list = dao.getImages(TEST_CATEGORY_ID, 100, 1);
                assertTrue(list.size() == 8);
        }
}
