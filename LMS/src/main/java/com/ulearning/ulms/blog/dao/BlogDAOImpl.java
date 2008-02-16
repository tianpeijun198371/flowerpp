package com.ulearning.ulms.blog.dao;

import com.ulearning.ulms.blog.exceptions.BlogSysException;
import com.ulearning.ulms.blog.form.AccountForm;
import com.ulearning.ulms.blog.form.ArticleForm;
import com.ulearning.ulms.blog.form.Information;
import com.ulearning.ulms.blog.helper.BlogConstants;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.DebugUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public class BlogDAOImpl implements BlogDAO
{
        public AccountForm get(String loginName) throws BlogSysException
        {
                Session session = null;
                String hql = "select from AccountForm af where af.username='" +
                        loginName + "'";
                AccountForm af = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List list = session.find(hql);

                        if ((list != null) && (list.size() > 0))
                        {
                                af = (AccountForm) list.get(0);

                                return af;
                        }

                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return null;
        }

        public AccountForm get(int accountId) throws BlogSysException
        {
                Session session = null;
                AccountForm af = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Object object = session.load(AccountForm.class,
                                new Integer(accountId));
                        session.flush();
                        session.connection().commit();

                        if (object != null)
                        {
                                af = (AccountForm) object;
                        }

                        return af;
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return null;
        }

        public int addAccount(AccountForm af) throws BlogSysException
        {
                Session session = null;
                String key = "0";

                try
                {
                        session = HibernateUtil.getSession();

                        if (get(af.getUsername()) != null)
                        {
                                deleteAccount(af.getUsername());
                        }

                        af.setCreatedDate(new Date());
                        key = session.save(af).toString();
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return Integer.parseInt(key);
        }

        public void deleteAccount(String loginName) throws BlogSysException
        {
                Session session = null;
                String hql = "from AccountForm af where af.username='" + loginName +
                        "'";

                try
                {
                        session = HibernateUtil.getSession();
                        session.delete(hql);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        public void updateAccount(AccountForm af) throws BlogSysException
        {
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        af.setCreatedDate(new Date());
                        session.saveOrUpdate(af);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        public void deleteAllAccount() throws BlogSysException
        {
                Session session = null;
                String hql = "delete AccountForm af ";

                try
                {
                        session = HibernateUtil.getSession();
                        session.delete(hql);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        /*       public int getNextID() throws BlogSysException
          {
                  Connection conn = null;
                  PreparedStatement ps = null;
                  ResultSet rs = null;
                  int nextID = 0;
                  try
                  {
                          //String sql = "select  max(accountid) from account";
                          String sql = "SELECT IDENT_CURRENT('account') as maxid";
                          conn = DBUtil.getConnection();
                          ps = conn.prepareStatement(sql);
                          rs = ps.executeQuery();
                          while (rs.next())
                          {
                                  nextID = rs.getInt("maxid");
                          }
                  }
                  catch (SQLException e)
                  {
                          e.printStackTrace();
                  }
                  finally
                  {
                          DBUtil.closeResultSet(rs);
                          DBUtil.closeStatement(ps);
                          DBUtil.closeConnection(conn);
                  }
                  return nextID;
          }
        */
        public List getAccountByASP(int orgID, int orderBy, int first, int max)
                throws BlogSysException
        {
                Session session = null;
                String hql = "select  from AccountForm af ,UserModel u,  OrganModel  org where af.articles >=0 and af.username=u.loginname ";

                if (orgID >= 0)
                {
                        hql += ("and u.catalogid=org.orgid and org.aspid=" + orgID);
                }
                else
                {
                        hql += "and u.catalogid=org.orgid and org.aspid>=0";
                }

                if (orderBy == BlogConstants.ORDER_BY_ARTICLES)
                {
                        hql += " order by af.articles desc , af.feedbacks desc";
                }
                else if (orderBy == BlogConstants.ORDER_BY_FEEDBACKS)
                {
                        hql += " order by af.feedbacks desc ,af.articles desc";
                }
                else if (orderBy == BlogConstants.ORDER_BY_DATETIME)
                {
                        hql += " order by af.createdDate desc";
                }

                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setFirstResult(first);
                        query.setMaxResults(max);

                        List tmplist = query.list();
                        DebugUtil.print("getAccountByASP(),hql=" + hql);

                        for (Iterator iter = tmplist.iterator(); iter.hasNext();)
                        {
                                Object[] record = (Object[]) iter.next();
                                AccountForm af = (AccountForm) record[0];
                                list.add(af);
                        }

                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public List getArticlesByFeedBack(int category, int max)
                throws BlogSysException
        {
                Session session = null;
                String hql = "select from ArticleForm af  order by af.feedBacks desc";

                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setMaxResults(max);
                        list = query.list();
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        /**
         * @param categoryId
         * @param art        :if want invoke this as a search method , set a articleForm
         * @param order
         * @param first
         * @param max
         * @return
         * @throws BlogSysException
         */
        public List getArticles(int categoryId, int type, ArticleForm art,
                                int order, int first, int max) throws BlogSysException
        {
                Session session = null;
                String orderby = "";

                if (order == BlogConstants.ORDER_BY_DATETIME)
                {
                        orderby = " order by af.createdDate desc  , af.feedBacks ";
                }
                else if (order == BlogConstants.ORDER_BY_FEEDBACKS)
                {
                        orderby = " order by af.feedBacks desc , af.createdDate  desc  ";
                }

                String cond = "";

                if (art != null)
                {
                        if ((art.getTitle() != null) &&
                                (art.getTitle().trim().length() > 0))
                        {
                                cond += (" and af.title LIKE '%" + art.getTitle().trim() +
                                        "%'");
                        }

                        if ((art.getContent() != null) &&
                                (art.getContent().trim().length() > 0))
                        {
                                cond += (" and af.content LIKE '%" + art.getContent().trim() +
                                        "%'");
                        }

                        if ((art.getSummary() != null) &&
                                (art.getSummary().trim().length() > 0))
                        {
                                cond += (" and af.summary LIKE '%" + art.getSummary().trim() +
                                        "%'");
                        }
                }

                if (categoryId > 0)
                {
                        cond += (" and af.categoryId = " + categoryId);
                }

                if (type > 0)
                {
                        cond += (" and af.type = " + type);
                }

                String hql = "select from ArticleForm af where af.articleId>0 " + cond +
                        orderby;
                DebugUtil.print("getArticles,hql=" + hql);

                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setFirstResult(first);
                        query.setMaxResults(max);
                        list = query.list();
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public List getAccountByCourse(int relationId, int[] state, int[] roles,
                                       int max)
        {
                return null;
        }

        public Information getInfo(int aspID) throws BlogSysException
        {
                Information info = new Information();

                Session session = null;
                int num = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = "select Count(*) from AccountForm ";
                        num = ((Integer) session.iterate(hql).next()).intValue();
                        info.setAllAccounts(num);
                        session.flush();
                        session.connection().commit();

                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }

                        session = HibernateUtil.getSession();

                        hql = "select Count(*) from ArticleForm ";
                        num = ((Integer) session.iterate(hql).next()).intValue();
                        info.setAllArticles(num);
                        session.flush();
                        session.connection().commit();

                        hql = "select  Count(*) from FeedbackForm ";
                        num = ((Integer) session.iterate(hql).next()).intValue();
                        info.setAllFeedbacks(num);
                        session.flush();
                        session.connection().commit();

                        Calendar calB = Calendar.getInstance();
                        calB.set(Calendar.HOUR_OF_DAY, 0);
                        calB.set(Calendar.MINUTE, 0);
                        calB.set(Calendar.SECOND, 0);

                        Calendar calE = Calendar.getInstance();
                        calE.set(Calendar.HOUR_OF_DAY, 23);
                        calE.set(Calendar.MINUTE, 59);
                        calE.set(Calendar.SECOND, 59);

                        hql = "from  ArticleForm where" + " createdDate>= :beginDate " +
                                " and createdDate<=:endDate ";

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", calB.getTime());
                        query.setParameter("endDate", calE.getTime());

                        List list = query.list();
                        num = 0;

                        if (list != null)
                        {
                                num = list.size();
                        }

                        info.setDayArticles(num);
                        session.flush();
                        session.connection().commit();

                        hql = "from  FeedbackForm where" + " createdDate>= :beginDate " +
                                " and createdDate<=:endDate ";
                        query = session.createQuery(hql);
                        query.setParameter("beginDate", calB.getTime());
                        query.setParameter("endDate", calE.getTime());
                        list = query.list();
                        num = 0;

                        if (list != null)
                        {
                                num = list.size();
                        }

                        info.setDayFeedbacks(num);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return info;
        }

        public List getCategorys(int type) throws BlogSysException
        {
                Session session = null;
                String cond = "";

                if (type >= 0)
                {
                        cond = " and type=" + type;
                }

                String hql = new StringBuffer().append(
                        "select from CategoryForm cf where cf.categoryId>0 ")
                        .append(cond).toString();
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();
                        list = session.find(hql);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public int countArticle(int accountId, int type, Calendar beginDate,
                                Calendar endDate) throws BlogSysException
        {
                Session session = null;
                int num = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        String cond = "";

                        if (accountId > 0)
                        {
                                cond += (" and af.accountId=" + accountId);
                        }

                        if (type > 0)
                        {
                                cond += (" and af.type=" + type);
                        }

                        String hql = "select Count(*) from ArticleForm af where af.articleId>0 " +
                                cond;
                        num = ((Integer) session.iterate(hql).next()).intValue();
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return num;
        }

        private AccountForm RS2AccountForm(ResultSet rs) throws SQLException
        {
                AccountForm af = new AccountForm();
                af.setAccountId(rs.getInt("accountId"));
                af.setUsername(rs.getString("username"));
                af.setPassword(rs.getString("password"));
                af.setEmail(rs.getString("email"));
                af.setFirstName(rs.getString("firstname"));
                af.setLastName(rs.getString("lastname"));
                af.setGender(rs.getInt("gender"));
                af.setTitle(rs.getString("title"));
                af.setSubTitle(rs.getString("subtitle"));
                af.setCreatedDate(rs.getDate("createdDate"));
                af.setState(rs.getInt("state"));
                af.setNews(rs.getString("news"));
                af.setDescription(rs.getString("description"));
                af.setArticles(rs.getInt("articles"));
                af.setFeedbacks(rs.getInt("feedbacks"));
                af.setMaxPerPage(rs.getInt("maxperpage"));
                af.setCss(rs.getString("css"));
                af.setSkinId(rs.getInt("skinid"));

                return af;
        }

        private ArticleForm RS2ArticleFrom(ResultSet rs) throws SQLException
        {
                ArticleForm af = new ArticleForm();
                af.setArticleId(rs.getInt("articleId"));
                af.setCategoryId(rs.getInt("categoryId"));
                af.setAccountId(rs.getInt("accountId"));
                af.setTitle(rs.getString("title"));
                af.setSummary(rs.getString("summary"));
                af.setVisiable(rs.getInt("visible"));
                af.setCreatedDate(rs.getDate("createdDate"));
                af.setUpdatedDate(rs.getDate("updatedDate"));
                af.setWebVisit(rs.getInt("webVisits"));
                af.setRssVisit(rs.getInt("rssVisits"));

                af.setFeedBacks(rs.getInt("feedBacks"));
                af.setContent(rs.getString("content"));

                return af;
        }
}
