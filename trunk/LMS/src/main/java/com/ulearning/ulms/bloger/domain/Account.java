/*
 * Created on 2004-8-22
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;

import java.util.Date;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public class Account implements Validator
{
        public static final int STATE_ACTIVE = 0;
        public static final int STATE_INACTIVE = 1;
        public static final int STATE_LOCKED = 2;

        // blogger login info:
        private int accountId;
        private String username;
        private String password;
        private String email = "";
        private Date createdDate;

        // personal setting:
        private String firstName = "";
        private String lastName = "";
        private boolean gender = false;

        // blog config:
        private String title;
        private String subtitle = "";
        private String news = "";
        private String description = "";

        // display:
        private int maxPerPage = 10;
        private String css = "";
        private int skinId = 0;

        // state: ACTIVE, INACTIVE, LOCKED
        private int state = STATE_ACTIVE;

        // statistics:
        private int articles = 0;
        private int feedbacks = 0;

        public int getAccountId()
        {
                return accountId;
        }

        public void setAccountId(int accountId)
        {
                this.accountId = accountId;
        }

        public Date getCreatedDate()
        {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate)
        {
                this.createdDate = createdDate;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getEmail()
        {
                return email;
        }

        public void setEmail(String email)
        {
                this.email = email;
        }

        public boolean getGender()
        {
                return gender;
        }

        public void setGender(boolean gender)
        {
                this.gender = gender;
        }

        public String getFirstName()
        {
                return firstName;
        }

        public void setFirstName(String name)
        {
                this.firstName = name;
        }

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getSubtitle()
        {
                return subtitle;
        }

        public void setSubtitle(String subtitle)
        {
                this.subtitle = subtitle;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getUsername()
        {
                return username;
        }

        public void setUsername(String username)
        {
                if (username != null)
                {
                        this.username = username.toLowerCase();
                }
        }

        public String getLastName()
        {
                return lastName;
        }

        public void setLastName(String lastName)
        {
                this.lastName = lastName;
        }

        public String getNews()
        {
                return news;
        }

        public void setNews(String news)
        {
                this.news = news;
        }

        public int getArticles()
        {
                return articles;
        }

        public void setArticles(int articles)
        {
                this.articles = articles;
        }

        public int getFeedbacks()
        {
                return feedbacks;
        }

        public void setFeedbacks(int feedbacks)
        {
                this.feedbacks = feedbacks;
        }

        public int getState()
        {
                return state;
        }

        public void setState(int state)
        {
                if ((state == STATE_ACTIVE) || (state == STATE_INACTIVE) ||
                        (state == STATE_LOCKED))
                {
                        this.state = state;
                }
                else
                {
                        this.state = STATE_INACTIVE;
                }
        }

        public String getCss()
        {
                return css;
        }

        public void setCss(String css)
        {
                this.css = css;
        }

        public int getMaxPerPage()
        {
                return maxPerPage;
        }

        public void setMaxPerPage(int maxPerPage)
        {
                if ((maxPerPage >= 10) && (maxPerPage <= 40))
                {
                        this.maxPerPage = maxPerPage;
                }
                else
                {
                        this.maxPerPage = 10;
                }
        }

        public int getSkinId()
        {
                return skinId;
        }

        public void setSkinId(int skinId)
        {
                this.skinId = skinId;
        }

        public void debug()
        {
                System.out.println("\n-- Account Info --");
                System.out.println("  accountId = " + this.accountId);
                System.out.println("   username = " + this.username);
                System.out.println("   password = " + this.password);
                System.out.println("      email = " + this.email);
                System.out.println("  firstName = " + this.firstName);
                System.out.println("   lastName = " + this.lastName);
                System.out.println("     gender = " + this.gender);
                System.out.println("      title = " + this.title);
                System.out.println("   subtitle = " + this.subtitle);
                System.out.println("createdDate = " + this.createdDate);
                System.out.println("      state = " + this.state);
                System.out.println("       news = " + this.news);
                System.out.println("     skinId = " + this.skinId);
                System.out.println(" maxPerPage = " + this.maxPerPage);
                System.out.println("        css = " + this.css);
                System.out.println("description = " + this.description);
                System.out.println("-- Account Info --");
        }

        public void validate() throws ValidateException
        {
                if (accountId < 0)
                {
                        throw new ValidateException(APPLICATION_ERROR);
                }

                if (username == null)
                {
                        throw new ValidateException(INVALID_ACCOUNT_USERNAME);
                }

                if (!username.matches("[a-z0-9_]{2,20}"))
                {
                        throw new ValidateException(INVALID_ACCOUNT_USERNAME);
                }

                if ((password == null) || !password.matches("[a-zA-Z0-9]{2,40}"))
                {
                        throw new ValidateException(INVALID_ACCOUNT_PASSWORD);
                }

                if ((title == null) || title.equals(""))
                {
                        throw new ValidateException(INVALID_ACCOUNT_TITLE);
                }
        }
}
