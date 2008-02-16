package com.ulearning.ulms.blog.dao;

import com.ulearning.ulms.blog.exceptions.BlogSysException;
import com.ulearning.ulms.blog.form.AccountForm;
import com.ulearning.ulms.blog.form.ArticleForm;
import com.ulearning.ulms.blog.form.Information;
import com.ulearning.ulms.blog.helper.BlogConstants;
import com.ulearning.ulms.blog.helper.BlogHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public class BlogDAOTest extends TestCase
{
        BlogDAO dao = null;

        public BlogDAOTest()
        {
                super();
        }

        public BlogDAOTest(String s)
        {
                super(s);
        }

        protected void setUp() throws Exception
        {
                super.setUp();
        }

        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        public void testGet()
        {
                try
                {
                        AccountForm isExist = getDAO().get("lmsadmin");

                        if (isExist != null)
                        {
                                System.out.print("Y");
                        }
                        else
                        {
                                System.out.print("N");
                        }
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                }
        }

        public void testGet1()
        {
                try
                {
                        AccountForm isExist = getDAO().get(2);

                        if (isExist != null)
                        {
                                System.out.print("Y");
                                System.out.println(isExist.getTitle());
                        }
                        else
                        {
                                System.out.print("N");
                        }
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                }
        }

        public void testAdd()
        {
                UserForm user = UserHelper.getUser("3");

                try
                {
                        int id = getDAO().addAccount(new AccountForm(user));
                        System.out.print(id);
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void testGetByAsp()
        {
                try
                {
                        List list = getDAO().getAccountByASP(101, 1, 1, 100);

                        for (int i = 0; i < list.size(); i++)
                        {
                                AccountForm af = (AccountForm) list.get(i);
                                System.out.println(af.getUsername());
                        }

                        System.out.println(list.size());
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void synchronize() throws BlogSysException
        {
                //getDAO().deleteAllAccount();
                BlogHelper.synchronizationAllAccount();
        }

        public void testDelAll()
        {
                try
                {
                        getDAO().deleteAllAccount();
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void testGetArticleByDate()
        {
                try
                {
                        ArticleForm art1 = new ArticleForm();
                        art1.setTitle("ме╤с");

                        List list = getDAO()
                                .getArticles(0, 0, art1,
                                        BlogConstants.ORDER_BY_DATETIME, 0, 20);

                        for (int i = 0; i < list.size(); i++)
                        {
                                ArticleForm art = (ArticleForm) list.get(i);
                                System.out.println(art.getTitle() + "," + art.getCreatedDate());
                        }
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void testGetArticleByFeedbacks()
        {
                try
                {
                        List list = getDAO().getArticlesByFeedBack(0, 5);

                        for (int i = 0; i < list.size(); i++)
                        {
                                ArticleForm art = (ArticleForm) list.get(i);
                                System.out.println(art.getTitle());
                        }
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void testGetInfo()
        {
                Information info = new Information();

                try
                {
                        info = getDAO().getInfo(0);
                        System.out.println("getAllAccounts=" + info.getAllAccounts());
                        System.out.println("getAllArticles=" + info.getAllArticles());
                        System.out.println("getAllFeedbacks=" + info.getAllFeedbacks());
                        System.out.println("getDayArticles=" + info.getDayArticles());
                        System.out.println("getDayFeedbacks=" + info.getDayFeedbacks());
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void testCountArticle()
        {
                try
                {
                        int num = getDAO().countArticle(0, 255, null, null);
                }
                catch (BlogSysException e)
                {
                        e.printStackTrace();
                }
        }

        public BlogDAO getDAO()
        {
                if (dao == null)
                {
                        return BlogDAOFactory.getDAO();
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(BlogDAOTest.class);
        }
}
