package com.ulearning.ulms.tools.newdocument.model;


import com.ulearning.ulms.core.util.Config;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 */
public class NewDocumentModel1 implements Serializable
{


        private int docID;
        private String docName;
        private int aspID;
        private String type;
        private int parentID;
        private String content;
        private String contentClob;
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
        private String tempClobString;


        public NewDocumentModel1()
        {
        }

        public NewDocumentModel1(String docName, int aspID, int parentID, int relationID, String isContent,
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

        public String getDocNamePIC()
        {
                if (((new Date()).getTime() - this.createDate.getTime()) < (60 * 60 * 24 * 1000))
                {
                        return "<img src='"+ Config.getContextRoot()+"/images/portal/blank.gif' width='29' height='9' border='0'>";
                }
                else
                {
                        return "<img src='"+ Config.getContextRoot()+"/images/portal/new.gif' border='0'>";
                }
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

        public String getContentClob()
        {
                return contentClob;
        }

        public void setContentClob(String contentClob)
        {
                this.contentClob = contentClob;
        }

        public String getContentClobString()
        {
                return this.contentClob;
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

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public int getContentSize()
        {
                return contentSize;
        }

        public void setContentSize(int contentSize)
        {
                this.contentSize = contentSize;
        }

        public int getDownloadTimes()
        {
                return downloadTimes;
        }

        public void setDownloadTimes(int downloadTimes)
        {
                this.downloadTimes = downloadTimes;
        }

        public String getTempClobString()
        {
                return tempClobString;
        }

        public void setTempClobString(String tempClobString)
        {
                this.tempClobString = tempClobString;
        }
}

