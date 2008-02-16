/*
 * Created on 2004-8-22
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;


/**
 * @author Huaxia
 */
public class Link implements Validator
{
        private int categoryId; // foreign key
        private int linkId; // primary key
        private String title;
        private String url;
        private String rss = "";
        private boolean newWindow = true;

        public int getCategoryId()
        {
                return categoryId;
        }

        public void setCategoryId(int categoryId)
        {
                this.categoryId = categoryId;
        }

        public int getLinkId()
        {
                return linkId;
        }

        public void setLinkId(int linkId)
        {
                this.linkId = linkId;
        }

        public boolean getNewWindow()
        {
                return newWindow;
        }

        public void setNewWindow(boolean newWindow)
        {
                this.newWindow = newWindow;
        }

        public String getRss()
        {
                return rss;
        }

        public void setRss(String rss)
        {
                this.rss = rss;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getUrl()
        {
                return url;
        }

        public void setUrl(String url)
        {
                this.url = url;
        }

        public void validate() throws ValidateException
        {
                if ((title == null) || title.equals(""))
                {
                        throw new ValidateException(INVALID_LINK_TITLE);
                }

                if ((url == null) || url.equals("") || !url.matches("http://[^<>]*"))
                {
                        throw new ValidateException(INVALID_LINK_URL);
                }

                if ((rss != null) && !rss.equals(""))
                {
                        if (!rss.matches("http://[^<>]*"))
                        {
                                throw new ValidateException(INVALID_LINK_RSS);
                        }
                }
        }
}
