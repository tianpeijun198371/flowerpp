/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;

import com.ulearning.ulms.bloger.domain.Account;
import com.ulearning.ulms.bloger.exception.*;

import java.util.List;


/**
 * Inteface of all operations on account table.
 *
 * @author Huaxia
 */
public interface AccountDao
{
        // for browse:
        Account getAccount(String username) throws QueryException;

        Account getAccount(int accountId) throws QueryException;

        Account getAccountByCategory(int categoryId) throws QueryException;

        Account getAccountByArticle(int articleId) throws QueryException;

        List getRecentAccounts(int num) throws QueryException;

        void sendMessage(int accountId, String sender, String address,
                         String subject, String msg);

        // for management:
        int loginAccount(String username, String password)
                throws AuthorizationException;

        void createAccount(Account account) throws CreateException;

        void updateAccount(Account account) throws UpdateException;

        void deleteAccount(int accountId) throws DeleteException;

        void lockAccount(int accountId) throws UpdateException;

        void changePassword(int accountId, String password)
                throws UpdateException;
}
