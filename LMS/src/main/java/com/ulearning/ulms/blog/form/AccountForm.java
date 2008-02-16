package com.ulearning.ulms.blog.form;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.user.form.UserForm;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public class AccountForm implements Serializable
{
        private int accountId;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private int gender;
        private String title;
        private String subTitle;
        private Date createdDate;
        private int state;
        private String news;
        private String description;
        private int articles;
        private int feedbacks;
        private int maxPerPage;
        private String css;
        private int skinId;

        public AccountForm()
        {
        }

        public AccountForm(int accountId, String username, String password,
                           String email, String firstName, String lastName, int gender,
                           String title, String subTitle, Date createdDate, int state,
                           String news, String description, int articles, int feedbacks,
                           int maxPerPage, String css, int skinId)
        {
                this.accountId = accountId;
                this.username = username;
                this.password = password;
                this.email = email;
                this.firstName = firstName;
                this.lastName = lastName;
                this.gender = gender;
                this.title = title;
                this.subTitle = subTitle;
                this.createdDate = createdDate;
                this.state = state;
                this.news = news;
                this.description = description;
                this.articles = articles;
                this.feedbacks = feedbacks;
                this.maxPerPage = maxPerPage;
                this.css = css;
                this.skinId = skinId;
        }

        public AccountForm(UserForm uf)
        {
                this.setUsername(uf.getLoginName());
                this.setPassword(uf.getPassword());
                this.setFirstName(uf.getName());
                this.setLastName(uf.getName());
                this.setEmail(uf.getMail());
                this.setGender(Integer.parseInt(uf.getSex()));
                this.setTitle(uf.getName());
                this.setSubTitle(uf.getName() + "欢迎您的光临");
                this.setCreatedDate(new Date());

                if (Integer.parseInt(uf.getAvailable()) == 1)
                {
                        this.setState(0);
                }
                else
                {
                        this.setState(1);
                }

                this.setNews("我的Blog新闻");
                this.setDescription("This account was created by system.");
                this.setArticles(0);
                this.setFeedbacks(0);
                this.setMaxPerPage(10);
                this.setCss("MYCSS");
                this.setSkinId(1);
        }

        public int getAccountId()
        {
                return accountId;
        }

        public void setAccountId(int accountId)
        {
                this.accountId = accountId;
        }

        public String getUsername()
        {
                return username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getEmail()
        {
                return email;
        }

        public void setEmail(String email)
        {
                this.email = email;
        }

        public String getFirstName()
        {
                return firstName;
        }

        public void setFirstName(String firstName)
        {
                this.firstName = firstName;
        }

        public String getLastName()
        {
                return lastName;
        }

        public void setLastName(String lastName)
        {
                this.lastName = lastName;
        }

        public int getGender()
        {
                return gender;
        }

        public void setGender(int gender)
        {
                this.gender = gender;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getSubTitle()
        {
                return subTitle;
        }

        public void setSubTitle(String subTitle)
        {
                this.subTitle = subTitle;
        }

        public Date getCreatedDate()
        {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate)
        {
                this.createdDate = createdDate;
        }

        public int getState()
        {
                return state;
        }

        public void setState(int state)
        {
                this.state = state;
        }

        public String getNews()
        {
                return news;
        }

        public void setNews(String news)
        {
                this.news = news;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
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

        public int getMaxPerPage()
        {
                return maxPerPage;
        }

        public void setMaxPerPage(int maxPerPage)
        {
                this.maxPerPage = maxPerPage;
        }

        public String getCss()
        {
                return css;
        }

        public void setCss(String css)
        {
                this.css = css;
        }

        public int getSkinId()
        {
                return skinId;
        }

        public void setSkinId(int skinId)
        {
                this.skinId = skinId;
        }
}
