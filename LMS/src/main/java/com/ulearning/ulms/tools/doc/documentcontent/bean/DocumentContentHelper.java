/** * DocumentContentHelper.java.
 * User: xiejh  Date: 2004-4-26 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.documentcontent.bean;

import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAO;
import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAOFactory;
import com.ulearning.ulms.tools.doc.doccontent.exceptions.DocContentDAOSysException;
import com.ulearning.ulms.tools.doc.doccontent.form.DocContentForm;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAO;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAOFactory;
import com.ulearning.ulms.tools.doc.document.exceptions.DocumentDAOSysException;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;
import com.ulearning.ulms.tools.doc.documentcontent.form.DocumentContentForm;

import java.util.ArrayList;
import java.util.List;


public class DocumentContentHelper
{
        /**
         * Wrapping the get document method for JSP and  the other modules
         *
         * @param docID
         * @return the document modle according to the documentID
         */
        public DocumentContentForm getDocumentContent(int docID)
        {
                DocumentForm df1 = null;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        df1 = documentDao.getDocument(docID);
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                DocContentForm dcf1 = null;

                try
                {
                        DocContentDAO doccontentDao = DocContentDAOFactory.getDAO();
                        dcf1 = doccontentDao.getDocContent(docID);
                }
                catch (DocContentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return getDocumentContentForm(df1, dcf1);
        }

        public List getDocumentContentList(int relationID, int DocType)
        {
                ArrayList DocumentContentList = new ArrayList();
                List DocumentList = null;
                DocumentForm df = null;
                DocContentForm dc = null;
                DocumentContentForm dcf = null;
                int docID = 0;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        DocContentDAO doccontentDao = DocContentDAOFactory.getDAO();
                        DocumentList = documentDao.getDocumentList(-1, DocType, relationID);

                        if (DocumentList != null)
                        {
                                for (int i = 0; i < DocumentList.size(); i++)
                                {
                                        df = (DocumentForm) DocumentList.get(i);
                                        docID = df.getDocID();
                                        dc = doccontentDao.getDocContent(docID);
                                        dcf = getDocumentContentForm(df, dc);
                                        DocumentContentList.add(dcf);
                                }
                        }
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                catch (DocContentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return DocumentContentList;
        }

        public List getDocumentContentViewList(int relationID, int DocType)
        {
                ArrayList DocumentContentList = new ArrayList();
                List DocumentList = null;
                DocumentForm df = null;
                DocContentForm dc = null;
                DocumentContentForm dcf = null;
                int docID = 0;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        DocContentDAO doccontentDao = DocContentDAOFactory.getDAO();
                        DocumentList = documentDao.getDocumentViewList(-1, DocType,
                                relationID);

                        if (DocumentList != null)
                        {
                                for (int i = 0; i < DocumentList.size(); i++)
                                {
                                        df = (DocumentForm) DocumentList.get(i);
                                        docID = df.getDocID();
                                        dc = doccontentDao.getDocContent(docID);
                                        dcf = getDocumentContentForm(df, dc);
                                        DocumentContentList.add(dcf);
                                }
                        }
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                catch (DocContentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return DocumentContentList;
        }

        public List getDocumentContentList(int relationID, int DocType, int isView)
        {
                ArrayList DocumentContentList = new ArrayList();
                List DocumentList = null;
                DocumentForm df = null;
                DocContentForm dc = null;
                DocumentContentForm dcf = null;
                int docID = 0;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        DocContentDAO doccontentDao = DocContentDAOFactory.getDAO();
                        DocumentList = documentDao.getDocumentListByView(relationID,
                                DocType, isView);

                        if (DocumentList != null)
                        {
                                for (int i = 0; i < DocumentList.size(); i++)
                                {
                                        df = (DocumentForm) DocumentList.get(i);
                                        docID = df.getDocID();
                                        dc = doccontentDao.getDocContent(docID);
                                        dcf = getDocumentContentForm(df, dc);
                                        DocumentContentList.add(dcf);
                                }
                        }
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                catch (DocContentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return DocumentContentList;
        }

         public static String getIsOpenGuest(int parentID, int relationID, int userID)
        {
                String isOpenGuest = "";
                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        isOpenGuest = documentDao.getIsOpenGuest(parentID, relationID,userID);
                }
                catch(DocumentDAOSysException udse)
                {
                       udse.printStackTrace();
                }
                return isOpenGuest;
        }

        public DocumentContentForm getDocumentContentForm(DocumentForm df,
                                                          DocContentForm dcf)
        {
                DocumentContentForm d = new DocumentContentForm();

                if (df != null)
                {
                        d.setDocID(df.getDocID());
                        d.setDocName(df.getDocName());
                        d.setParentID(df.getParentID());
                        d.setUserful(df.getUserful());
                        d.setOpenGuest(df.getOpenGuest());
                        d.setView(df.getView());
                        d.setContent(df.getContent());
                        d.setDocType(df.getDocType());
                        d.setRelationID(df.getRelationID());
                        d.setUserID(df.getUserID());
                        d.setDepth(df.getDepth());
                        d.setOrderIndex(df.getOrderIndex());
                        d.setCreateDate(df.getCreateDate());
                        d.setModifyDate(df.getModifyDate());
                        d.setDocStatus(df.getDocStatus());
                        d.setDocLink(dcf.getDocLink());
                }

                if (dcf != null)
                {
                        d.setDocContent(dcf.getDocContent());
                        d.setDocLinkTitle(dcf.getDocLinkTitle());
                        d.setDocLink(dcf.getDocLink());
                }
                else
                {
                        d.setDocContent("");
                        d.setDocLinkTitle("");
                        d.setDocLink("");
                }

                return d;
        }
}
