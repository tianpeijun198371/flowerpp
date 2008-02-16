/** * DocumentHelper.java.
 * Document: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.document.bean;

import com.ulearning.ulms.tools.doc.document.dao.DocumentDAO;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAOFactory;
import com.ulearning.ulms.tools.doc.document.exceptions.DocumentDAOSysException;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;

import java.util.List;


public class DocumentHelper
{
        /**
         * Wrapping the get document method for JSP and  the other modules
         *
         * @param docID
         * @return the document modle according to the documentID
         */
        public DocumentForm getDocument(int docID)
        {
                DocumentForm df = null;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        df = documentDao.getDocument(docID);
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return df;
        }

        /**
         * Wrapping the get documentList method for JSP and  the other modules
         *
         * @param parentID
         * @return the document list according to the catalog
         */
        public List getDocumentList(int parentID)
        {
                List documentList = null;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        documentList = documentDao.getDocumentList(parentID);
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return documentList;
        }

        public List getDocumentList(int content, int docType, int relationID)
        {
                List documentList = null;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        documentList = documentDao.getDocumentList(content, docType,
                                relationID);
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return documentList;
        }
        public int getCertNumberbyUser(int userID,int courseID)
        {
                int count = 0;
                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        count = documentDao.getCertNumberbyUser(userID, courseID);
                }
                catch(DocumentDAOSysException udse)
                {
                       udse.printStackTrace();
                }
                return count;
        }



        public static String getMaxCertNumber(int parentID)
        {
                String certNum = "";
                int count = 0;

                try
                {
                        DocumentDAO documentDao = DocumentDAOFactory.getDAO();
                        count = documentDao.getMaxCertNumber(parentID);
                        System.out.println("cournt === " + count);
                        if(count==0)
                        {
                            certNum="000001";
                        }
                        if (count > 0)
                        {
                                count = count + 1;
                                if (count < 10)
                                {
                                        certNum = "00000" + count;
                                }
                                else
                                {
                                        if (count < 100)
                                        {
                                                certNum = "0000" + count;
                                        }
                                        else
                                        {
                                                if (count < 1000)
                                                {
                                                        certNum = "000" + count;
                                                }
                                                else
                                                {
                                                        if (count < 10000)
                                                        {
                                                                certNum = "00" + count;
                                                        }
                                                        else
                                                        {
                                                                if (count < 100000)
                                                                {
                                                                        certNum = "0" + count;
                                                                }
                                                                else
                                                                {
                                                                        if (count < 1000000)
                                                                        {
                                                                                certNum = "" + count;
                                                                        }
                                                                }
                                                        }
                                                }

                                        }
                                }
                        }
                        System.out.println("cournt === " + certNum);
                }
                catch (DocumentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return certNum;
        }

        public static void main(String a[])
        {


                System.out.println(" ==== " +  DocumentHelper.getMaxCertNumber(540));
        }

}
