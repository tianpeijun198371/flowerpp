/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public class Category implements Validator
{
        private static String[] names = {"category", "gallery", "favorite"};
        public static final int TYPE_ARTICLES = 0;
        public static final int TYPE_IMAGES = 1;
        public static final int TYPE_LINKS = 2;
        private int accountId; // foreign key
        private int categoryId; // primary key
        private boolean visible = true;
        private String title = "";
        private int type = 0;
        private String description = "";

        public boolean isForArticles()
        {
                return type == TYPE_ARTICLES;
        }

        public boolean isForImages()
        {
                return type == TYPE_IMAGES;
        }

        public boolean isForLinks()
        {
                return type == TYPE_LINKS;
        }

        public String getName()
        {
                return names[type];
        }

        public int getAccountId()
        {
                return accountId;
        }

        public void setAccountId(int accountId)
        {
                this.accountId = accountId;
        }

        public int getCategoryId()
        {
                return categoryId;
        }

        public void setCategoryId(int categoryId)
        {
                this.categoryId = categoryId;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public boolean getVisible()
        {
                return visible;
        }

        public void setVisible(boolean visible)
        {
                this.visible = visible;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public void validate() throws ValidateException
        {
                if ((title == null) || title.equals(""))
                {
                        throw new ValidateException(INVALID_CATEGORY_TITLE);
                }

                if ((type < TYPE_ARTICLES) || (type > TYPE_LINKS))
                {
                        throw new ValidateException(INVALID_CATEGORY_TYPE);
                }
        }
}
