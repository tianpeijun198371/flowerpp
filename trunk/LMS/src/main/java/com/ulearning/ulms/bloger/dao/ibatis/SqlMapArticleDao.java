/*
 * Created on 2004-9-29
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.ArticleDao;
import com.ulearning.ulms.bloger.domain.Article;
import com.ulearning.ulms.bloger.exception.CreateException;
import com.ulearning.ulms.bloger.exception.DeleteException;
import com.ulearning.ulms.bloger.exception.QueryException;
import com.ulearning.ulms.bloger.exception.UpdateException;

import java.sql.SQLException;

import java.util.*;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public class SqlMapArticleDao implements ArticleDao
{
        private SqlMapClient sqlMap = SqlConfig.getSqlMapInstance();

        public Article getArticle(int articleId) throws QueryException
        {
                try
                {
                        return (Article) sqlMap.queryForObject("getArticle",
                                new Integer(articleId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get aritcle failed: " + sqle);
                }
        }

        public Article getArticleInfo(int articleId) throws QueryException
        {
                try
                {
                        return (Article) sqlMap.queryForObject("getArticleInfo",
                                new Integer(articleId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get aritcle info failed: " + sqle);
                }
        }

        public List getRecentArticlesInfo(int num) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getRecentArticlesInfo", null, 0, num);
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get recent aritcles info failed: " +
                                sqle);
                }
        }

        public List getArticles(int accountId, int num, int page)
                throws QueryException
        {
                //assert page>0 : "page must > 0!";
                //assert num>=10 && num<=40 : "num must between [10, 40]!";
                try
                {
                        return sqlMap.queryForList("getArticles", new Integer(accountId),
                                num * (page - 1), num);
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get aritcles failed: " + sqle);
                }
        }

        public List getArticlesByCategory(int categoryId) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getArticlesByCategory",
                                new Integer(categoryId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get aritcles by category failed: " +
                                sqle);
                }
        }

        public List getArticlesByCategory(int categoryId, int num, int page)
                throws QueryException
        {
                //assert page>0 : "page must > 0!";
                //assert num>=10 && num<=40 : "num must between [10, 40]!";
                try
                {
                        return sqlMap.queryForList("getArticlesByCategory",
                                new Integer(categoryId), num * (page - 1), num);
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get aritcles by category failed: " +
                                sqle);
                }
        }

        public List searchArticle(String keyword, int num, int page)
                throws QueryException
        {
                // TODO Auto-generated method stub
                return null;
        }

        public void createArticle(Article article) throws CreateException
        {
                try
                {
                        sqlMap.insert("createArticle", article);
                        sqlMap.update("updateAccountWhenArticleChanged",
                                new Integer(article.getAccountId()));
                }
                catch (SQLException sqle)
                {
                        throw new CreateException("Create aritcle failed: " + sqle);
                }
        }

        public void updateArticle(Article article) throws UpdateException
        {
                try
                {
                        sqlMap.update("updateArticle", article);
                }
                catch (SQLException sqle)
                {
                        throw new UpdateException("Update aritcle failed: " + sqle);
                }
        }

        public void updateArticleInfo(Article article) throws UpdateException
        {
                try
                {
                        sqlMap.update("updateArticleInfo", article);
                }
                catch (SQLException sqle)
                {
                        throw new UpdateException("Update aritcle info failed: " + sqle);
                }
        }

        public void deleteArticle(int articleId) throws DeleteException
        {
                // delete article and all feedbacks belong to:
                try
                {
                        Article article = getArticle(articleId);
                        sqlMap.delete("deleteArticle", new Integer(articleId));
                        sqlMap.update("updateAccountWhenArticleChanged",
                                new Integer(article.getAccountId()));
                        sqlMap.update("updateAccountWhenFeedbackChanged", article);
                }
                catch (SQLException sqle)
                {
                        throw new DeleteException("Delete article failed: " + sqle);
                }
        }

        public int getArticlesCount(int categoryId) throws QueryException
        {
                try
                {
                        return ((Integer) sqlMap.queryForObject("getArticlesCount",
                                new Integer(categoryId))).intValue();
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get aritcles count by category failed: " +
                                sqle);
                }
        }
}
