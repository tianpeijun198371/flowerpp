/** * DocumentForm.java.
 * User: xiejh  Date: 2004-4-23 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.document.dao;

import com.ulearning.ulms.tools.doc.document.exceptions.DocumentDAOSysException;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;

import java.io.Serializable;

import java.util.List;


public interface DocumentDAO
{
        public Serializable addDocument(DocumentForm details)
                throws DocumentDAOSysException;

        public void updateDocument(DocumentForm details)
                throws DocumentDAOSysException;

        public void removeDocument(String docID) throws DocumentDAOSysException;

        public DocumentForm getDocument(int docID) throws DocumentDAOSysException;

        public List getDocumentList(int docType) throws DocumentDAOSysException;

        public List getDocumentList(int content, int docType, int relationID)
                throws DocumentDAOSysException;

        public List getDocumentViewList(int content, int docType, int relationID)
                throws DocumentDAOSysException;

        public List getDocumentListByView(int relationID, int docType, int isView)
                throws DocumentDAOSysException;

        public int getMaxCertNumber(int parentID) throws DocumentDAOSysException;

        //public int getDocID(DocumentForm df) throws DocumentDAOSysException;

        public int getCertNumberbyUser(int userID,int courseID) throws DocumentDAOSysException;

        public String getIsOpenGuest (int parentID,int relationID,int userID) throws DocumentDAOSysException;
}
