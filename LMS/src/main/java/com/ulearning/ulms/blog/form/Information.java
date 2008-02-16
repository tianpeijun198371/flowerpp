/**
 * Information.java.
 * User: keyh  Date: 2005-5-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.blog.form;

public class Information
{
        private int allAccounts = 0;
        private int allArticles = 0;
        private int allFeedbacks = 0;
        private int dayArticles = 0;
        private int dayFeedbacks = 0;

        public Information()
        {
        }

        public int getAllAccounts()
        {
                return allAccounts;
        }

        public void setAllAccounts(int allAccounts)
        {
                this.allAccounts = allAccounts;
        }

        public int getAllArticles()
        {
                return allArticles;
        }

        public void setAllArticles(int allArticles)
        {
                this.allArticles = allArticles;
        }

        public int getAllFeedbacks()
        {
                return allFeedbacks;
        }

        public void setAllFeedbacks(int allFeedbacks)
        {
                this.allFeedbacks = allFeedbacks;
        }

        public int getDayArticles()
        {
                return dayArticles;
        }

        public void setDayArticles(int dayArticles)
        {
                this.dayArticles = dayArticles;
        }

        public int getDayFeedbacks()
        {
                return dayFeedbacks;
        }

        public void setDayFeedbacks(int dayFeedbacks)
        {
                this.dayFeedbacks = dayFeedbacks;
        }
}
