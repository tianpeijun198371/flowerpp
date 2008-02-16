/*
 * Created on 2004-9-30
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.FeedbackDao;
import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.exception.*;

import java.sql.SQLException;

import java.util.List;


/**
 * @author Huaxia
 */
public class SqlMapFeedbackDao implements FeedbackDao
{
        private SqlMapClient sqlMap = SqlConfig.getSqlMapInstance();

        public Feedback getFeedback(int feedbackId) throws QueryException
        {
                try
                {
                        return (Feedback) sqlMap.queryForObject("getFeedback",
                                new Integer(feedbackId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Cannot get feedback: " + sqle);
                }
        }

        public List getFeedbacks(int articleId) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getFeedbacks", new Integer(articleId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException("Cannot get feedbacks: " + sqle);
                }
        }

        public void createFeedback(Feedback feedback) throws CreateException
        {
                try
                {
                        sqlMap.insert("createFeedback", feedback);

                        Integer articleId = new Integer(feedback.getArticleId());
                        Article article = (Article) sqlMap.queryForObject("getArticle",
                                articleId);
                        sqlMap.update("updateAccountWhenFeedbackChanged", article);
                        sqlMap.update("updateArticleWhenFeedbackChanged", articleId);
                }
                catch (SQLException sqle)
                {
                        throw new CreateException("Cannot create feedback: " + sqle);
                }
        }

        public void deleteFeedback(int feedbackId) throws DeleteException
        {
                try
                {
                        Integer articleId = new Integer(getFeedback(feedbackId)
                                .getArticleId());
                        Article article = (Article) sqlMap.queryForObject("getArticle",
                                articleId);
                        sqlMap.delete("deleteFeedback", new Integer(feedbackId));
                        sqlMap.update("updateAccountWhenFeedbackChanged", article);
                        sqlMap.update("updateArticleWhenFeedbackChanged", articleId);
                }
                catch (SQLException sqle)
                {
                        throw new DeleteException("Cannot delete feedback: " + sqle);
                }
        }
}
