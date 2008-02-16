package com.ulearning.ulms.blog.helper;

import com.ulearning.ulms.blog.dao.BlogDAO;
import com.ulearning.ulms.blog.dao.BlogDAOFactory;
import com.ulearning.ulms.blog.exceptions.BlogSysException;
import com.ulearning.ulms.blog.form.AccountForm;
import com.ulearning.ulms.blog.form.ArticleForm;
import com.ulearning.ulms.blog.form.Information;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;

import java.util.Calendar;
import java.util.List;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public class BlogHelper
{
        public static boolean hasBlog = false;

        static
        {
                if ((Config.get("is-integrate-blog") != null) &&
                        Config.get("is-integrate-blog").toLowerCase().equals("true"))
                {
                        hasBlog = true;
                }
        }

        public static AccountForm get(String loginName) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.get(loginName);
                }

                return null;
        }

        public static AccountForm get(int accountId) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.get(accountId);
                }

                return null;
        }

        public static void synchronizationAspAccount(List list)
                throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        for (int i = 0; i < list.size(); i++)
                        {
                                dao.addAccount(new AccountForm((UserForm) list.get(i)));
                        }
                }
        }

        public static void synchronizationAllAccount() throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();
                        dao.deleteAllAccount();

                        List aspList = OrganHelper.getOrganListBy(0);

                        for (int aspIndex = 0; aspIndex < aspList.size(); aspIndex++)
                        {
                                OrganForm organForm = (OrganForm) aspList.get(aspIndex);
                                synchronizationAspAccount(UserHelper.getAspUserList(
                                        organForm.getOrgID()));
                        }
                }
        }

        public static void addAccount(UserForm uf) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();
                        dao.addAccount(new AccountForm(uf));
                }
        }

        public static void deleteAccount(String loginName)
                throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();
                        dao.deleteAccount(loginName);
                }
        }

        public static void updateAccount(UserForm uf) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();
                        dao.updateAccount(new AccountForm(uf));
                }
        }

        public static void deleteAllAccount() throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();
                        dao.deleteAllAccount();
                }
        }

        public static List getAccountByASP(int orgID, int orderBy, int first,
                                           int max) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.getAccountByASP(orgID, orderBy, first, max);
                }

                return null;
        }

        public List getArticlesByFeedBack(int category, int max)
                throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.getArticlesByFeedBack(category, max);
                }

                return null;
        }

        public static List getArticles(int categoryId, int type, ArticleForm art,
                                       int order, int first, int max) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.getArticles(categoryId, type, art, order, first, max);
                }

                return null;
        }

        public static List getAccountByCourse(int relationId, int[] state,
                                              int[] roles, int max) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.getAccountByCourse(relationId, state, roles, max);
                }

                return null;
        }

        public static Information getInfo(int aspID) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.getInfo(aspID);
                }

                return null;
        }

        public static List getCategorys(int type) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.getCategorys(type);
                }

                return null;
        }

        public static int countArticle(int accountId, int type, Calendar beginDate,
                                       Calendar endDate) throws BlogSysException
        {
                if (hasBlog)
                {
                        BlogDAO dao = BlogDAOFactory.getDAO();

                        return dao.countArticle(accountId, type, beginDate, endDate);
                }

                return 0;
        }
}
