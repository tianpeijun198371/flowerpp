/**
 * ForumForm.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.form;

import com.ulearning.ulms.tools.discussion.model.ForumModel;

import org.apache.struts.action.ActionForm;


public class ForumForm extends ActionForm
{
        private int forumID = 0;
        private int courseID = 0;
        private int orderIndex = 0;
        private String title = null;
        private int isAllowAnn = 0;
        private int isUpdateAft = 0;
        private int isDeleteAft = 0;
        private int isAccessory = 0;
        private int isNewItem = 0;
        private String description = null;

        public ForumForm()
        {
        }

        public ForumForm(ForumModel fm)
        {
                if (fm != null)
                {
                        this.forumID = fm.getForumid();
                        this.courseID = fm.getCourseid();
                        this.orderIndex = fm.getOrderindex();
                        this.title = fm.getTitle();
                        this.isAllowAnn = Integer.parseInt(fm.getIsallowann());
                        this.isUpdateAft = Integer.parseInt(fm.getIsupdateaft());
                        this.isDeleteAft = Integer.parseInt(fm.getIsdeleteaft());
                        this.isAccessory = Integer.parseInt(fm.getIsaccessory());
                        this.isNewItem = Integer.parseInt(fm.getIsnewitem());
                        this.description = fm.getDescription();
                }
        }

        public int getForumID()
        {
                return forumID;
        }

        public void setForumID(int forumID)
        {
                this.forumID = forumID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getOrderIndex()
        {
                return orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public int getIsAllowAnn()
        {
                return isAllowAnn;
        }

        public void setIsAllowAnn(int allowAnn)
        {
                isAllowAnn = allowAnn;
        }

        public int getIsUpdateAft()
        {
                return isUpdateAft;
        }

        public void setIsUpdateAft(int updateAft)
        {
                isUpdateAft = updateAft;
        }

        public int getIsDeleteAft()
        {
                return isDeleteAft;
        }

        public void setIsDeleteAft(int deleteAft)
        {
                isDeleteAft = deleteAft;
        }

        public int getIsAccessory()
        {
                return isAccessory;
        }

        public void setIsAccessory(int accessory)
        {
                isAccessory = accessory;
        }

        public int getIsNewItem()
        {
                return isNewItem;
        }

        public void setIsNewItem(int newItem)
        {
                isNewItem = newItem;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public ForumModel getForumModel()
        {
                ForumModel fm = new ForumModel();
                fm.setForumid(this.forumID);
                fm.setCourseid(this.courseID);
                fm.setOrderindex(this.orderIndex);
                fm.setTitle(this.title);
                fm.setIsallowann(new Integer(this.isAllowAnn).toString());
                fm.setIsupdateaft(new Integer(this.isUpdateAft).toString());
                fm.setIsdeleteaft(new Integer(this.isDeleteAft).toString());
                fm.setIsaccessory(new Integer(this.isAccessory).toString());
                fm.setIsnewitem(new Integer(this.isNewItem).toString());
                fm.setDescription(this.description);

                return fm;
        }
}
