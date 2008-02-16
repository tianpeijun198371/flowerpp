/**
 * NewDocumentForm.java.
 * User: Administrator  Date: 2005-3-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.form;


import com.ulearning.ulms.tools.newdocument.model.NewDocumentCatalogModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.core.util.ClobUtil;
import org.apache.struts.action.ActionForm;

import java.util.Date;
import java.sql.Clob;

public class NewDocumentForm extends UploadForm
{
        private int docID;
        private String docName;
        private int aspID;
        private String type;
        private int parentID;
        private String content;
        private String contentClobString;
        private String linkTitle;
        private String link;
        private int relationID;
        private String relationName;
        private String isUserful;
        private String isOpenGuest;
        private String isView;
        private String isContent;
        private int depth;
        private int orderIndex;
        private String status;
        private Date displayBeginDate;
        private Date displayEndDate;
        private int userID;
        private Date createDate;
        private Date modifyDate;
        private String userName;
        private int contentSize;
        private int downloadTimes;
        private String remark;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String remark5;
        private String remark6;

        private String beginDate;
        private String endDate;
        private String parentType;

        public NewDocumentModel getNewDocumentModel()
        {
                NewDocumentModel newDocumentModel = new NewDocumentModel();
                newDocumentModel.setAspID(this.aspID);
                newDocumentModel.setContent(this.content);
                newDocumentModel.setContentClob(ClobUtil.getClob(this.contentClobString));
                newDocumentModel.setTempClobString(this.contentClobString);
                newDocumentModel.setContentSize(this.contentSize);
                newDocumentModel.setCreateDate(this.createDate);
                newDocumentModel.setDepth(this.depth);
                newDocumentModel.setDisplayBeginDate(this.displayBeginDate);
                newDocumentModel.setDisplayEndDate(this.displayEndDate);
                newDocumentModel.setDocID(this.docID);
                newDocumentModel.setDocName(this.docName);
                newDocumentModel.setDownloadTimes(this.downloadTimes);
                newDocumentModel.setIsContent(this.isContent);
                newDocumentModel.setIsOpenGuest(this.isOpenGuest);
                newDocumentModel.setIsUserful(this.isUserful);
                newDocumentModel.setIsView(this.isView);
                newDocumentModel.setLink(this.link);
                newDocumentModel.setLinkTitle(this.linkTitle);
                newDocumentModel.setOrderIndex(this.orderIndex);
                newDocumentModel.setParentID(this.parentID);
                newDocumentModel.setType(this.type);
                newDocumentModel.setStatus(this.status);
                newDocumentModel.setUserID(this.userID);
                newDocumentModel.setUserName(this.userName);
                newDocumentModel.setRelationID(this.relationID);
                newDocumentModel.setRelationName(this.relationName);
                newDocumentModel.setRemark(this.remark);
                newDocumentModel.setRemark1(this.remark1);
                newDocumentModel.setRemark2(this.remark2);
                newDocumentModel.setRemark3(this.remark3);
                newDocumentModel.setRemark4(this.remark4);
                newDocumentModel.setRemark5(this.remark5);
                newDocumentModel.setRemark6(this.remark6);
                return newDocumentModel;
        }

        public NewDocumentForm()
        {
        }

        public NewDocumentForm getNewDocumentForm(NewDocumentModel newDocumentModel)
        {
                NewDocumentForm newDocumentForm = new NewDocumentForm();
                String string = ClobUtil.getString(newDocumentModel.getContentClob());

                newDocumentForm.setAspID(newDocumentModel.getAspID());
                newDocumentForm.setContent(newDocumentModel.getContent());
                newDocumentForm.setContentClobString(string);
                newDocumentForm.setCreateDate(newDocumentModel.getCreateDate());
                newDocumentForm.setDepth(newDocumentModel.getDepth());
                newDocumentForm.setDownloadTimes(newDocumentModel.getDownloadTimes());
                newDocumentForm.setDisplayBeginDate(newDocumentModel.getDisplayBeginDate());
                newDocumentForm.setDisplayEndDate(newDocumentModel.getDisplayEndDate());
                newDocumentForm.setDocID(newDocumentModel.getDocID());
                newDocumentForm.setDocName(newDocumentModel.getDocName());
                newDocumentForm.setIsContent(newDocumentModel.getIsContent());
                newDocumentForm.setIsOpenGuest(newDocumentModel.getIsOpenGuest());
                newDocumentForm.setIsUserful(newDocumentModel.getIsUserful());
                newDocumentForm.setIsView(newDocumentModel.getIsView());
                newDocumentForm.setLink(newDocumentModel.getLink());
                newDocumentForm.setLinkTitle(newDocumentModel.getLinkTitle());
                newDocumentForm.setModifyDate(newDocumentModel.getModifyDate());
                newDocumentForm.setOrderIndex(newDocumentModel.getOrderIndex());
                newDocumentForm.setParentID(newDocumentModel.getParentID());
                newDocumentForm.setRelationID(newDocumentModel.getRelationID());
                newDocumentForm.setRelationName(newDocumentModel.getRelationName());
                newDocumentForm.setRemark(newDocumentModel.getRemark());
                newDocumentForm.setRemark1(newDocumentModel.getRemark1());
                newDocumentForm.setRemark2(newDocumentModel.getRemark2());
                newDocumentForm.setRemark3(newDocumentModel.getRemark3());
                newDocumentForm.setRemark4(newDocumentModel.getRemark4());
                newDocumentForm.setRemark5(newDocumentModel.getRemark5());
                newDocumentForm.setRemark6(newDocumentModel.getRemark6());
                newDocumentForm.setContentSize(newDocumentModel.getContentSize());
                newDocumentForm.setStatus(newDocumentModel.getStatus());
                newDocumentForm.setUserName(newDocumentModel.getUserName());
                newDocumentForm.setUserID(newDocumentModel.getUserID());
                newDocumentForm.setIsUserful(newDocumentModel.getIsUserful());
                newDocumentForm.setType(newDocumentModel.getType());


                return newDocumentForm;
        }

        public NewDocumentForm(String docName, int aspID, int parentID, int relationID, String isContent,
                               int contentSize, String content, String type)
        {
                this.docName = docName;
                this.aspID = aspID;
                this.parentID = parentID;
                this.relationID = relationID;
                this.isContent = isContent;
                this.contentSize = contentSize;
                this.content = content;
                this.type = type;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public int getDownloadTimes()
        {
                return downloadTimes;
        }

        public void setDownloadTimes(int downloadTimes)
        {
                this.downloadTimes = downloadTimes;
        }

        public int getContentSize()
        {
                return contentSize;
        }

        public void setContentSize(int contentSize)
        {
                this.contentSize = contentSize;
        }

        public int getDocID()
        {
                return docID;
        }

        public void setDocID(int docID)
        {
                this.docID = docID;
        }

        public String getDocName()
        {
                return docName;
        }

        public void setDocName(String docName)
        {
                this.docName = docName;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public String getContent()
        {
                return content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }

        public String getContentClobString()
        {
                return contentClobString;
        }

        public void setContentClobString(String contentClobString)
        {
                this.contentClobString = contentClobString;
        }

        public Clob getContentClob()
        {
                return ClobUtil.getClob(this.contentClobString);
        }

        public String getLinkTitle()
        {
                return linkTitle;
        }

        public void setLinkTitle(String linkTitle)
        {
                this.linkTitle = linkTitle;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getRelationName()
        {
                return relationName;
        }

        public void setRelationName(String relationName)
        {
                this.relationName = relationName;
        }

        public String getIsUserful()
        {
                return isUserful;
        }

        public void setIsUserful(String isUserful)
        {
                this.isUserful = isUserful;
        }

        public String getIsOpenGuest()
        {
                return isOpenGuest;
        }

        public void setIsOpenGuest(String isOpenGuest)
        {
                this.isOpenGuest = isOpenGuest;
        }

        public String getIsView()
        {
                return isView;
        }

        public void setIsView(String isView)
        {
                this.isView = isView;
        }

        public String getIsContent()
        {
                return isContent;
        }

        public void setIsContent(String isContent)
        {
                this.isContent = isContent;
        }

        public int getDepth()
        {
                return depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public int getOrderIndex()
        {
                return orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getStatus()
        {
                return status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public Date getDisplayBeginDate()
        {
                return displayBeginDate;
        }

        public void setDisplayBeginDate(Date displayBeginDate)
        {
                this.displayBeginDate = displayBeginDate;
        }

        public Date getDisplayEndDate()
        {
                return displayEndDate;
        }

        public void setDisplayEndDate(Date displayEndDate)
        {
                this.displayEndDate = displayEndDate;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String getRemark6()
        {
                return remark6;
        }

        public void setRemark6(String remark6)
        {
                this.remark6 = remark6;
        }

        public String getBeginDate()
        {
                return beginDate;
        }

        public void setBeginDate(String beginDate)
        {
                this.beginDate = beginDate;
        }

        public String getEndDate()
        {
                return endDate;
        }

        public void setEndDate(String endDate)
        {
                this.endDate = endDate;
        }

        public String getParentType()
        {
                return parentType;
        }

        public void setParentType(String parentType)
        {
                this.parentType = parentType;
        }

}

