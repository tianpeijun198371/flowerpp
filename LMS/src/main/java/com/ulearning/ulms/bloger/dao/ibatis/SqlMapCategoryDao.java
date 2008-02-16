/*
 * Created on 2004-9-30
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.CategoryDao;
import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.exception.*;

import java.sql.SQLException;

import java.util.*;


/**
 * @author Huaxia
 */
public class SqlMapCategoryDao implements CategoryDao
{
        private SqlMapClient sqlMap = SqlConfig.getSqlMapInstance();

        public List getCategories(int accountId) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getCategories", new Integer(accountId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get categories failed: " + sqle);
                }
        }

        public List getCategoriesOfArticle(int accountId) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getCategoriesOfArticle",
                                new Integer(accountId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get categories of article failed: " +
                                sqle);
                }
        }

        public List getCategoriesOfType(int accountId, int type)
                throws QueryException
        {
                try
                {
                        Map map = new HashMap();
                        map.put("accountId", new Integer(accountId));
                        map.put("type", new Integer(type));

                        return sqlMap.queryForList("getCategoriesOfType", map);
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get categories of type failed: " + sqle);
                }
        }

        public Category getCategory(int categoryId) throws QueryException
        {
                try
                {
                        Category category = (Category) sqlMap.queryForObject("getCategory",
                                new Integer(categoryId));

                        return category;
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Get category failed: " + sqle);
                }
        }

        public void createCategory(Category category) throws CreateException
        {
                try
                {
                        sqlMap.insert("createCategory", category);
                }
                catch (SQLException sqle)
                {
                        throw new CreateException("Create category failed: " + sqle);
                }
        }

        public void updateCategory(Category category) throws UpdateException
        {
                try
                {
                        sqlMap.update("updateCategory", category);
                }
                catch (SQLException sqle)
                {
                        throw new UpdateException("Update category failed: " + sqle);
                }
        }

        public void deleteCategory(int categoryId) throws DeleteException
        {
                try
                {
                        //sqlMap.startBatch();
                        //List list = sqlMap.queryForList("getArticlesByCategory", new Integer(categoryId));
                        //Iterator it = list.iterator();
                        //while(it.hasNext()) {
                        //    Article article = (Article)it.next();
                        //    sqlMap.delete("deleteArticle", new Integer(article.getArticleId()));
                        //}
                        sqlMap.delete("deleteCategory", new Integer(categoryId));

                        //sqlMap.executeBatch();
                }
                catch (SQLException sqle)
                {
                        throw new DeleteException("Delete category failed: " + sqle);
                }
        }
}
