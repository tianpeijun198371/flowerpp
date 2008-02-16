/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.exception.*;

import java.util.List;


/**
 * Inteface of all operations on article table.
 *
 * @author Huaxia
 */
public interface ArticleDao
{
        // for browse:
        Article getArticle(int articleId) throws QueryException;

        Article getArticleInfo(int articleId) throws QueryException;

        List getRecentArticlesInfo(int num) throws QueryException;

        List getArticles(int accountId, int num, int page)
                throws QueryException;

        List getArticlesByCategory(int categoryId) throws QueryException;

        List getArticlesByCategory(int categoryId, int num, int page)
                throws QueryException;

        int getArticlesCount(int categoryId) throws QueryException;

        List searchArticle(String keyword, int num, int page)
                throws QueryException;

        // for management:
        void createArticle(Article article) throws CreateException;

        void updateArticle(Article article) throws UpdateException;

        void updateArticleInfo(Article article) throws UpdateException;

        void deleteArticle(int articleId) throws DeleteException;
}
