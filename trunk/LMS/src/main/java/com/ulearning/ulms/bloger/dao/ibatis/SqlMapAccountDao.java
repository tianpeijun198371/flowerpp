/*
 * Created on 2004-9-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.AccountDao;
import com.ulearning.ulms.bloger.domain.Account;
import com.ulearning.ulms.bloger.exception.*;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Huaxia
 */
public class SqlMapAccountDao implements AccountDao
{
        private SqlMapClient sqlMap = SqlConfig.getSqlMapInstance();

        public com.ulearning.ulms.bloger.domain.Account getAccount(String username)
                throws QueryException
        {
                try
                {
                        return (Account) sqlMap.queryForObject("getAccountByUsername",
                                username);
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public com.ulearning.ulms.bloger.domain.Account getAccount(int accountId)
                throws com.ulearning.ulms.bloger.exception.QueryException
        {
                try
                {
                        return (Account) sqlMap.queryForObject("getAccount",
                                new Integer(accountId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public List getRecentAccounts(int num) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getRecentAccounts", null, 0, num);
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public Account getAccountByArticle(int articleId) throws QueryException
        {
                try
                {
                        return (Account) sqlMap.queryForObject("getAccountByArticle",
                                new Integer(articleId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public Account getAccountByCategory(int categoryId)
                throws QueryException
        {
                try
                {
                        return (Account) sqlMap.queryForObject("getAccountByCategory",
                                new Integer(categoryId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public void createAccount(Account account) throws CreateException
        {
                try
                {
                        sqlMap.insert("createAccount", account);
                }
                catch (SQLException sqle)
                {
                        throw new CreateException(sqle);
                }
        }

        public void updateAccount(Account account) throws UpdateException
        {
                try
                {
                        sqlMap.update("updateAccount", account);
                }
                catch (SQLException sqle)
                {
                        throw new UpdateException(sqle);
                }
        }

        public void deleteAccount(int accountId) throws DeleteException
        {
                // TODO Auto-generated method stub
                throw new RuntimeException("Code is not finished.");
        }

        public void lockAccount(int accountId) throws UpdateException
        {
                // TODO Auto-generated method stub
                throw new RuntimeException("Code is not finished.");
        }

        public void changePassword(int accountId, String password)
                throws UpdateException
        {
                try
                {
                        Map map = new HashMap();
                        map.put("accountId", new Integer(accountId));
                        map.put("password", password);
                        sqlMap.update("changePassword", map);
                }
                catch (SQLException sqle)
                {
                        throw new RuntimeException("Change password failed: " + sqle);
                }
        }

        public void sendMessage(int accountId, String sender, String address,
                                String subject, String msg)
        {
                // TODO Auto-generated method stub
                throw new RuntimeException("Code is not finished.");
        }

        public int loginAccount(String username, String password)
                throws AuthorizationException
        {
                Integer I;

                try
                {
                        Map map = new HashMap();
                        map.put("username", username);
                        map.put("password", password);
                        I = (Integer) sqlMap.queryForObject("login", map);

                        if (I == null)
                        {
                                throw new AuthorizationException(
                                        "Login failed: Invalid username or password.");
                        }

                        return I.intValue();
                }
                catch (SQLException sqle)
                {
                        throw new AuthorizationException("Login failed: " + sqle);
                }
        }
}
