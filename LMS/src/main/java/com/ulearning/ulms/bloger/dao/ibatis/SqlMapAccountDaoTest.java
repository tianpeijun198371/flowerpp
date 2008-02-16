/*
 * Created on 2004-9-29
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ulearning.ulms.bloger.domain.Account;
import com.ulearning.ulms.bloger.exception.CreateException;
import com.ulearning.ulms.bloger.util.TestUtil;

import junit.framework.TestCase;


public class SqlMapAccountDaoTest extends TestCase
{
        private static final int TEST_ACCOUNT_ID = 90299989;
        private static final String TEST_ACCOUNT_USERNAME = "test4account";
        private static final String TEST_ACCOUNT_PASSWORD = "a1b2c3d4e5f6";
        private SqlMapAccountDao dao = new SqlMapAccountDao();

        protected void setUp() throws Exception
        {
                super.setUp();
                // delete the exist account for testing createAccount():
                TestUtil.executeSQL("delete from [Account] where [accountId]=" +
                        TEST_ACCOUNT_ID);
        }

        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        public void testGetAccountString()
        {
                testCreateAccount();

                Account account = dao.getAccount(TEST_ACCOUNT_USERNAME);
                assertTrue(account.getAccountId() == TEST_ACCOUNT_ID);
        }

        public void testGetAccountint()
        {
                testCreateAccount();

                Account account = dao.getAccount(TEST_ACCOUNT_ID);
                assertTrue(account.getUsername().equals(TEST_ACCOUNT_USERNAME));
        }

        public void testCreateAccount()
        {
                // new account for test createAccount():
                Account account = new Account();
                account.setAccountId(TEST_ACCOUNT_ID);
                account.setUsername(TEST_ACCOUNT_USERNAME);
                account.setPassword(TEST_ACCOUNT_PASSWORD);
                account.setFirstName("richeal");
                account.setLastName("green");
                account.setCreatedDate(new java.util.Date());
                account.setEmail("test@cactusblog.org");
                account.setTitle("test blog");
                account.validate();

                dao.createAccount(account);

                try
                {
                        account.setAccountId(TEST_ACCOUNT_ID + 1);
                        dao.createAccount(account);
                        // should throw a CreateException because of the
                        // duplicate primary key:
                        assertTrue(false);
                }
                catch (CreateException ce)
                {
                }
        }

        public void testUpdateAccount()
        {
                testCreateAccount();

                Account account = dao.getAccount(TEST_ACCOUNT_ID);
                String new_email = "update@newemail.com";
                account.setEmail(new_email);
                account.validate();
                // update it!
                dao.updateAccount(account);

                // check to see if updated:
                Account account2 = dao.getAccount(TEST_ACCOUNT_ID);
                assertTrue(account2.getEmail().equals(new_email));
        }

        public void testLoginAccount()
        {
                testCreateAccount();

                // login using the right information:
                try
                {
                        int id = dao.loginAccount(TEST_ACCOUNT_USERNAME,
                                TEST_ACCOUNT_PASSWORD);
                        assertTrue(id == TEST_ACCOUNT_ID);
                }
                catch (Exception e)
                {
                        assertTrue(false);
                }

                // login using the wrong information:
                try
                {
                        int id = dao.loginAccount(TEST_ACCOUNT_USERNAME,
                                TEST_ACCOUNT_PASSWORD + "invalid");
                        assertTrue(false);
                }
                catch (Exception e)
                {
                        // should catch the exception!
                }
        }

        public void testDeleteAccount()
        {
        }

        public void testLockAccount()
        {
        }

        public void testSendMessage()
        {
        }
}
