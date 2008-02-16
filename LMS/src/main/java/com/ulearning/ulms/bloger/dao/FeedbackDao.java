/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.exception.*;

import java.util.*;


/**
 * Inteface of all operations on feedback table.
 *
 * @author Huaxia
 */
public interface FeedbackDao
{
        // for browse:
        public Feedback getFeedback(int feedbackId) throws QueryException;

        List getFeedbacks(int articleId) throws QueryException;

        void createFeedback(Feedback feedback) throws CreateException;

        // for management:
        void deleteFeedback(int feedbackId) throws DeleteException;
}
