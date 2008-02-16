/** * DocumentContentForm.java.
 * User: xiejh  Date: 2004-4-26 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.documentcontent.form;

import com.ulearning.ulms.tools.doc.doccontent.form.DocContentForm;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class DocumentContentForm extends ActionForm
{
        private String docContent = null;
        private String docLinkTitle = null;
        private String docLink = null;
        private int docID = 0;
        private String docName = null;
        private int parentID = 0;
        private String isUserful = null;
        private String isOpenGuest = null;
        private String isView = "0";
        private String isContent = null;
        private int docType = 0;
        private int relationID = 0;
        private int userID = 0;
        private int depth = 0;
        private int orderIndex = 0;
        private Date createDate = null;
        private String createDateStr = null;
        private Date modifyDate = null;
        private String docStatus = null;

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

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public String getUserful()
        {
                return isUserful;
        }

        public void setUserful(String userful)
        {
                isUserful = userful;
        }

        public String getOpenGuest()
        {
                return isOpenGuest;
        }

        public void setOpenGuest(String openGuest)
        {
                isOpenGuest = openGuest;
        }

        public String getView()
        {
                return isView;
        }

        public void setView(String view)
        {
                isView = view;
        }

        public String getContent()
        {
                return isContent;
        }

        public void setContent(String content)
        {
                isContent = content;
        }

        public int getDocType()
        {
                return docType;
        }

        public void setDocType(int docType)
        {
                this.docType = docType;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
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

        public String getDocStatus()
        {
                return docStatus;
        }

        public void setDocStatus(String docStatus)
        {
                this.docStatus = docStatus;
        }

        public String getDocContent()
        {
                return docContent;
        }

        public void setDocContent(String docContent)
        {
                this.docContent = docContent;
        }

        public String getDocLinkTitle()
        {
                return docLinkTitle;
        }

        public void setDocLinkTitle(String docLinkTitle)
        {
                this.docLinkTitle = docLinkTitle;
        }

        public String getDocLink()
        {
                return docLink;
        }

        public void setDocLink(String docLink)
        {
                this.docLink = docLink;
        }

        public String getCreateDateStr()
        {
                return createDateStr;
        }

        public void setCreateDateStr(String createDateStr)
        {
                this.createDateStr = createDateStr;
        }

        public DocumentForm getDocumentForm()
        {
                DocumentForm documentForm = new DocumentForm();

                documentForm.setDocID(getDocID());
                documentForm.setDocName(getDocName());
                documentForm.setParentID(getParentID());
                documentForm.setUserful(getUserful());
                documentForm.setOpenGuest(getOpenGuest());
                documentForm.setView(getView());
                documentForm.setContent(getContent());
                documentForm.setDocType(getDocType());
                documentForm.setRelationID(getRelationID());
                documentForm.setUserID(getUserID());
                documentForm.setDepth(getDepth());
                documentForm.setOrderIndex(getOrderIndex());
                documentForm.setCreateDate(getCreateDate());
                documentForm.setCreateDateStr(getCreateDateStr());
                documentForm.setModifyDate(getModifyDate());
                documentForm.setDocStatus(getDocStatus());

                return documentForm;
        }

        public DocContentForm getDocContentForm()
        {
                DocContentForm docContentForm = new DocContentForm();

                docContentForm.setDocID(getDocID());
                docContentForm.setDocContent(getDocContent());
                docContentForm.setDocLinkTitle(getDocLinkTitle());
                docContentForm.setDocLink(getDocLink());

                return docContentForm;
        }
}
