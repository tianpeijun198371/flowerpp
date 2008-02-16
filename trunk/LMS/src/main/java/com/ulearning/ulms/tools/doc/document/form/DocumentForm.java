/** * DocumentForm.java.
 * User: xiejh  Date: 2004-4-23 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.document.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.doc.document.model.DocumentModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class DocumentForm extends ActionForm
{
        private int docID = 0;
        private String docName = null;
        private int parentID = 0;
        private String isUserful = null;
        private String isOpenGuest = null;
        private String isView = null;
        private String isContent = null;
        private int docType = 0;
        private int relationID = 0;
        private int userID = 0;
        private int depth = 0;
        private int orderIndex = 0;
        private Date createDate = null;
        private Date modifyDate = null;
        private String createDateStr = null;
        private String docStatus = null;
        private DocumentModel documentModel = null;

        public DocumentModel getDocumentModel()
        {
                documentModel = new DocumentModel();
                documentModel.setDocID(this.docID);
                documentModel.setDocName(this.docName);
                documentModel.setParentID(this.parentID);
                documentModel.setIsUserful(this.isUserful);
                documentModel.setIsOpenGuest(this.isOpenGuest);
                documentModel.setIsView(this.isView);
                documentModel.setIsContent(this.isContent);
                documentModel.setDocType(String.valueOf(this.docType));
                documentModel.setRelationID(this.relationID);
                documentModel.setUserID(this.userID);
                documentModel.setDepth(this.depth);
                documentModel.setOrderIndex(this.orderIndex);
                documentModel.setCreateDate(this.createDate);
                documentModel.setModifyDate(this.modifyDate);
                documentModel.setDocStatus(this.docStatus);

                return documentModel;
        }

        public DocumentForm getDocumentForm(DocumentModel documentModel)
        {
                DocumentForm document = new DocumentForm();
                document.setDocID(documentModel.getDocID());
                document.setDocName(StringUtil.nullToStr(documentModel.getDocName()));
                document.setParentID(documentModel.getParentID());
                document.setUserful(documentModel.getIsUserful());
                document.setOpenGuest(documentModel.getIsOpenGuest());
                document.setView(documentModel.getIsView());
                document.setContent(StringUtil.nullToStr(documentModel.getIsContent()));
                document.setDocType(Integer.parseInt(documentModel.getDocType()));
                document.setRelationID(documentModel.getRelationID());
                document.setUserID(documentModel.getUserID());
                document.setDepth(documentModel.getDepth());
                document.setOrderIndex(documentModel.getOrderIndex());
                document.setCreateDate(documentModel.getCreateDate());
                document.setModifyDate(documentModel.getModifyDate());
                document.setDocStatus(documentModel.getDocStatus());

                return document;
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

        public String getCreateDateStr()
        {
                return createDateStr;
        }

        public void setCreateDateStr(String createDateStr)
        {
                this.createDateStr = createDateStr;
        }

        public String getDocStatus()
        {
                return docStatus;
        }

        public void setDocStatus(String docStatus)
        {
                this.docStatus = docStatus;
        }
}
