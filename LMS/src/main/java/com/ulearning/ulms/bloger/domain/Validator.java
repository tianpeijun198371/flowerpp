/*
 * Created on 2004-10-1
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;


/**
 * Validator provide a method validate() to make sure the user's input is valid.
 *
 * @author Huaxia
 */
public interface Validator
{
        static final int INVALID_ACCOUNT_USERNAME = 1000; //"Invalid username: must 6-12 characters.";
        static final int INVALID_ACCOUNT_PASSWORD = 1001;
        static final int INVALID_ACCOUNT_TITLE = 1002;
        static final int INVALID_CATEGORY_TITLE = 2000;
        static final int INVALID_CATEGORY_TYPE = 2001;
        static final int INVALID_ARTICLE_TITLE = 3000;
        static final int INVALID_ARTICLE_CONTENT = 3001;
        static final int INVALID_FEEDBACK_USERNAME = 4000;
        static final int INVALID_FEEDBACK_URL = 4001;
        static final int INVALID_FEEDBACK_CONTENT = 4002;
        static final int INVALID_LINK_TITLE = 5000;
        static final int INVALID_LINK_URL = 5001;
        static final int INVALID_LINK_RSS = 5002;
        static final int APPLICATION_ERROR = 909091;

        void validate() throws ValidateException;
}
