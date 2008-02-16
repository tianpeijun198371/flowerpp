package com.ulearning.ulms.tools.doc.document.model;

import com.ulearning.ulms.tools.doc.doccontent.model.DocContentModel;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class DocumentModel implements Serializable
{
        /**
         * identifier field
         */
        private int docID;

        /**
         * nullable persistent field
         */
        private String docName;

        /**
         * persistent field
         */
        private int parentID;

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
         * persistent field
         */
        private String isContent;

        /**
         * persistent field
         */
        private String docType;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * nullable persistent field
         */
        private int userID;

        /**
         * nullable persistent field
         */
        private int depth;

        /**
         * nullable persistent field
         */
        private int orderIndex;

        /**
         * nullable persistent field
         */
        private Date createDate;

        /**
         * nullable persistent field
         */
        private Date modifyDate;

        /**
         * nullable persistent field
         */
        private String docStatus;

        //private DocContentModel docContentModel;

        /**
         * full constructor
         */
        public DocumentModel(String docName, int parentID, String isUserful,
                             String isOpenGuest, String isView, String isContent, String docType,
                             int relationID, int userID, int depth, int orderIndex, Date createDate,
                             Date modifyDate, String docStatus)
        {
                this.docName = docName;
                this.parentID = parentID;
                this.isUserful = isUserful;
                this.isOpenGuest = isOpenGuest;
                this.isView = isView;
                this.isContent = isContent;
                this.docType = docType;
                this.relationID = relationID;
                this.userID = userID;
                this.depth = depth;
                this.orderIndex = orderIndex;
                this.createDate = createDate;
                this.modifyDate = modifyDate;
                this.docStatus = docStatus;
        }

        /**
         * default constructor
         */
        public DocumentModel()
        {
        }

        /**
         * minimal constructor
         */
        public DocumentModel(int parentID, String isContent, String docType,
                             int relationID)
        {
                this.parentID = parentID;
                this.isContent = isContent;
                this.docType = docType;
                this.relationID = relationID;
        }

        public int getDocID()
        {
                return this.docID;
        }

        public void setDocID(int docID)
        {
                this.docID = docID;
        }

        public String getDocName()
        {
                return this.docName;
        }

        public void setDocName(String docName)
        {
                this.docName = docName;
        }

        public int getParentID()
        {
                return this.parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
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

        public String getIsContent()
        {
                return this.isContent;
        }

        public void setIsContent(String isContent)
        {
                this.isContent = isContent;
        }

        public String getDocType()
        {
                return this.docType;
        }

        public void setDocType(String docType)
        {
                this.docType = docType;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getDepth()
        {
                return this.depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public int getOrderIndex()
        {
                return this.orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getModifyDate()
        {
                return this.modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public String getDocStatus()
        {
                return this.docStatus;
        }

        public void setDocStatus(String docStatus)
        {
                this.docStatus = docStatus;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("docID", getDocID()).toString();
        }

        /*
          public DocContentModel getDocContentModel() {
              return this.docContentModel;
          }
          public void setDocContentModel(DocContentModel docContentModel) {
              this.docContentModel = docContentModel;
          }
        */
}
