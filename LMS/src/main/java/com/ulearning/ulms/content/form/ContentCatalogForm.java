package com.ulearning.ulms.content.form;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.struts.action.ActionForm;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentCatalogForm extends ActionForm implements Serializable
{
        /**
         * identifier field
         */
        private int contentCatalogID;

        /**
         * persistent field
         */
        private int parentID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * persistent field
         */
        private String title;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * nullable persistent field
         */
        private Date createDate;

        /**
         * nullable persistent field
         */
        private Date lastModDate;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        /**
         * nullable persistent field
         */
        private String status;

        /**
         * nullable persistent field
         */
        private Date displayBeginDate;

        /**
         * nullable persistent field
         */
        private Date displayEndDate;

        /**
         * nullable persistent field
         */
        private int orderIndex;

        /**
         * nullable persistent field
         */
        private String isUserful;

        /**
         * nullable persistent field
         */
        private String isOpenGuest;

        /**
         * nullable persistent field
         */
        private String isView;

        /**
         * nullable persistent field
         */
        private int depth;

        /**
         * full constructor
         */
        public ContentCatalogForm(int parentID, int relationID, String type,
                                  String title, String remark, Date createDate, Date lastModDate,
                                  String remark1, String remark2, String remark3, String status,
                                  Date displayBeginDate, Date displayEndDate, int orderIndex,
                                  String isUserful, String isOpenGuest, String isView, int depth)
        {
                this.parentID = parentID;
                this.relationID = relationID;
                this.type = type;
                this.title = title;
                this.remark = remark;
                this.createDate = createDate;
                this.lastModDate = lastModDate;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.status = status;
                this.displayBeginDate = displayBeginDate;
                this.displayEndDate = displayEndDate;
                this.orderIndex = orderIndex;
                this.isUserful = isUserful;
                this.isOpenGuest = isOpenGuest;
                this.isView = isView;
                this.depth = depth;
        }

        /**
         * default constructor
         */
        public ContentCatalogForm()
        {
        }

        /**
         * minimal constructor
         */
        public ContentCatalogForm(int parentID, int relationID, String title)
        {
                this.parentID = parentID;
                this.relationID = relationID;
                this.title = title;
        }

        public ContentCatalogForm(int contentCatalogID, String title)
        {
                this.contentCatalogID = contentCatalogID;
                this.title = title;
        }

        public int getContentCatalogID()
        {
                return this.contentCatalogID;
        }

        public void setContentCatalogID(int contentCatalogID)
        {
                this.contentCatalogID = contentCatalogID;
        }

        public int getParentID()
        {
                return this.parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastModDate()
        {
                return this.lastModDate;
        }

        public void setLastModDate(Date lastModDate)
        {
                this.lastModDate = lastModDate;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getStatus()
        {
                return this.status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public Date getDisplayBeginDate()
        {
                return this.displayBeginDate;
        }

        public void setDisplayBeginDate(Date displayBeginDate)
        {
                this.displayBeginDate = displayBeginDate;
        }

        public Date getDisplayEndDate()
        {
                return this.displayEndDate;
        }

        public void setDisplayEndDate(Date displayEndDate)
        {
                this.displayEndDate = displayEndDate;
        }

        public int getOrderIndex()
        {
                return this.orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getIsUserful()
        {
                return this.isUserful;
        }

        public void setIsUserful(String isUserful)
        {
                this.isUserful = isUserful;
        }

        public String getIsOpenGuest()
        {
                return this.isOpenGuest;
        }

        public void setIsOpenGuest(String isOpenGuest)
        {
                this.isOpenGuest = isOpenGuest;
        }

        public String getIsView()
        {
                return this.isView;
        }

        public void setIsView(String isView)
        {
                this.isView = isView;
        }

        public int getDepth()
        {
                return this.depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("contentCatalogID",
                        getContentCatalogID()).toString();
        }
}
