package com.ulearning.ulms.blog.dao;

import com.ulearning.ulms.blog.exceptions.BlogSysException;
import com.ulearning.ulms.blog.form.AccountForm;
import com.ulearning.ulms.blog.form.ArticleForm;
import com.ulearning.ulms.blog.form.Information;

import java.util.Calendar;
import java.util.List;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public interface BlogDAO
{
        public AccountForm get(String LoginName) throws BlogSysException;

        public AccountForm get(int accountId) throws BlogSysException;

        public int addAccount(AccountForm af) throws BlogSysException;

        public void deleteAccount(String loginName) throws BlogSysException;

        public void updateAccount(AccountForm af) throws BlogSysException;

        public void deleteAllAccount() throws BlogSysException;

        public List getAccountByASP(int orgID, int orderBy, int first, int max)
                throws BlogSysException;

        public List getArticlesByFeedBack(int category, int max)
                throws BlogSysException;

        public List getArticles(int categoryId, int type, ArticleForm art,
                                int order, int first, int max) throws BlogSysException;

        public List getAccountByCourse(int relationId, int[] state, int[] roles,
                                       int max);

        public Information getInfo(int aspID) throws BlogSysException;

        public List getCategorys(int type) throws BlogSysException;

        public int countArticle(int accountId, int type, Calendar beginDate,
                                Calendar endDate) throws BlogSysException;
}
