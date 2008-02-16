/*
 * Created on 2004-10-14
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.util;

import com.ulearning.ulms.bloger.dao.*;
import com.ulearning.ulms.bloger.domain.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * Create test database.
 *
 * @author Huaxia
 */
public class SetupDatabase implements Controller
{
        private String[] strs = {
                "abc ", "xyz ", "101 ", "msn ", "sun ", "bea ", "ops ", "ha! ",
                "yes ", "[6] ", "ssb ", "~*~ ", "($) ", "'h' ", "kaa ", "555 ",
                "INT ", "JTA ", "RU? ", "w3c ", "thx ", "007 ", "P2p ", "afs ",
                "Lxf "
        };
        private Account account;
        private Category category;
        private Article article;
        private AccountDao accountDao;
        private CategoryDao categoryDao;
        private ArticleDao articleDao;

        public SetupDatabase()
        {
                // for account:
                account = new Account();
                account.setCss("body { font-size: 10pt; }");
                account.setDescription("this is used for test.");
                account.setEmail("test@sometest.com");
                account.setFirstName("first");
                account.setLastName("lastName");
                account.setGender(true);
                account.setMaxPerPage(20);
                account.setNews("no news is good news.");
                account.setPassword("guessmypassword");
                account.setSkinId(1);
                account.setSubtitle("just for test");

                // for category:
                category = new Category();
                category.setDescription("just a test.");
                category.setTitle("Test Category");
                category.setType(0);
                category.setVisible(true);

                // for article:
                article = new Article();
                article.setSummary("summary here: this is a test, so just ignore it.");
                article.setVisible(true);
        }

        public void setAccountDao(AccountDao accountDao)
        {
                this.accountDao = accountDao;
        }

        public void setArticleDao(ArticleDao articleDao)
        {
                this.articleDao = articleDao;
        }

        public void setCategoryDao(CategoryDao categoryDao)
        {
                this.categoryDao = categoryDao;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                setup();

                return null;
        }

        private void setup()
        {
                TestUtil.executeSQL("delete from [Feedback]");
                TestUtil.executeSQL("delete from [Article]");
                TestUtil.executeSQL("delete from [Image]");
                TestUtil.executeSQL("delete from [Link]");
                TestUtil.executeSQL("delete from [Category]");
                TestUtil.executeSQL("delete from [Message]");
                TestUtil.executeSQL("delete from [Account]");

                int accountId;
                int categoryId;
                int articleId;

                for (accountId = 1; accountId <= 1000; accountId++)
                {
                        createAccount(accountId);

                        int start = (accountId * 10) - 9;
                        int end = accountId * 10;

                        for (categoryId = start; categoryId <= end; categoryId++)
                        {
                                createCategory(accountId, categoryId);

                                int a_start = (categoryId * 10) - 9;
                                int a_end = categoryId * 10;

                                for (articleId = a_start; articleId <= a_end; articleId++)
                                {
                                        createArticle(accountId, categoryId, articleId);
                                }
                        }
                }
        }

        private void createAccount(int accountId)
        {
                System.out.println("create an account: " + accountId);
                account.setAccountId(accountId);
                account.setUsername("testof" + accountId);
                account.setCreatedDate(new Date());
                account.setTitle("Test blog with " + accountId);
                accountDao.createAccount(account);
        }

        private void createCategory(int accountId, int categoryId)
        {
                System.out.println("create a category: " + categoryId);
                category.setAccountId(accountId);
                category.setCategoryId(categoryId);
                category.setTitle("TEST" + categoryId);
                categoryDao.createCategory(category);
        }

        private void createArticle(int accountId, int categoryId, int articleId)
        {
                System.out.println("create an article: " + articleId);
                article.setAccountId(accountId);
                article.setCategoryId(categoryId);
                article.setArticleId(articleId);
                article.setCreatedDate(new Date());
                article.setTitle("Article in " + categoryId + " of " + articleId);
                article.setContent(generateContent());
                articleDao.createArticle(article);
        }

        private String generateContent()
        {
                StringBuffer sb = new StringBuffer(10000);

                for (int i = 0; i < 2500; i++)
                {
                        double d = Math.random() * 23;
                        int n = (int) Math.round(d);
                        sb.append(strs[n]);
                }

                return sb.toString();
        }
}
