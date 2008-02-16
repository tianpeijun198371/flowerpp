/** * DocumentForm.java.
 * User: xiejh  Date: 2004-4-23 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.document.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.doc.document.exceptions.DocumentDAOSysException;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;
import com.ulearning.ulms.tools.doc.document.model.DocumentModel;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.model.ItermModel;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;

import java.io.Serializable;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class DocumentDAOImpl implements DocumentDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        public Serializable addDocument(DocumentForm details)
                throws DocumentDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getDocumentModel());
                        System.out.println("details.getOpenGuest();==========" + details.getOpenGuest());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                return s;
        }

        public void updateDocument(DocumentForm details)
                throws DocumentDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getDocumentModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }
        }

        /**
         * Remove the document from database by the documentID
         *
         * @param docID
         * @throws DocumentDAOSysException
         */
        public void removeDocument(String docID) throws DocumentDAOSysException
        {
                removeParentDocument(docID);

                String hql = " from DocumentModel where DocID = " + docID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }
        }

        public void removeParentDocument(String docID)
                throws DocumentDAOSysException
        {
                String hql = " from DocumentModel where ParentID = " + docID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }
        }

        /**
         * Get the document info via the unique documentID
         *
         * @param docID
         * @return the prepared documentForm, default is null
         * @throws DocumentDAOSysException
         */
        public DocumentForm getDocument(int docID) throws DocumentDAOSysException
        {
                DocumentForm bf = new DocumentForm();
                DocumentForm res = null;
                List tmList = null;
                String hql = " from DocumentModel where DocID = " + docID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        DocumentModel bm = (DocumentModel) tmList.get(0);
                        res = bf.getDocumentForm(bm);
                }

                return res;
        }

        /**
         * Get the document list by the parentID
         *
         * @param docType
         * @return The prepared arraylist object,default size is 0
         * @throws DocumentDAOSysException
         */
        public List getDocumentList(int docType) throws DocumentDAOSysException
        {
                DocumentForm bf = new DocumentForm();
                DocumentModel bm = null;
                ArrayList documentList = new ArrayList();
                List tmList = null;
                String hql = " from DocumentModel where DocType = '" + docType +
                        "' and parentID=0";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (DocumentModel) tmList.get(i);
                        documentList.add(bf.getDocumentForm(bm));
                }

                return documentList;
        }

        /*
        *  get Faq
        */
        public List getDocumentList(int content, int docType, int relationID)
                throws DocumentDAOSysException
        {
                DocumentForm bf = new DocumentForm();
                DocumentModel bm = null;
                ArrayList documentList = new ArrayList();
                List tmList = null;
                String tmp = "";

                if (content != -1)
                { //-1 is all
                        tmp = tmp + " and isContent='" + content + "'";
                }

                String hql = " from DocumentModel " + " where docType = '" + docType +
                        "'" + " and relationID=" + relationID + " " + tmp +
                        " order by isContent,orderIndex,docID";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (DocumentModel) tmList.get(i);
                        documentList.add(bf.getDocumentForm(bm));
                }

                return documentList;
        }

        public List getDocumentViewList(int content, int docType, int relationID)
                throws DocumentDAOSysException
        {
                DocumentForm bf = new DocumentForm();
                DocumentModel bm = null;
                ArrayList documentList = new ArrayList();
                List tmList = null;
                String tmp = "";

                if (content != -1)
                { //-1 is all
                        tmp = tmp + " and isContent='" + content + "'";
                }

                String hql = " from DocumentModel " + " where docType = '" + docType +
                        "'" + " and relationID=" + relationID + " and isView ='1'" + " " +
                        tmp + " order by isContent,orderIndex,docID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (DocumentModel) tmList.get(i);
                        documentList.add(bf.getDocumentForm(bm));
                }

                return documentList;
        }

        public List getDocumentListByView(int relationID, int docType, int isView)
                throws DocumentDAOSysException
        {
                DocumentForm bf = new DocumentForm();
                DocumentModel bm = null;
                ArrayList documentList = new ArrayList();
                List tmList = null;
                String tmp = "";

                if (isView != -1)
                { //-1 is all
                        tmp = tmp + " and isView='" + isView + "'";
                }

                String hql = " from DocumentModel " + " where docType = '" + docType +
                        "'" + " and relationID=" + relationID + " " + tmp +
                        " order by orderIndex,docID";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (DocumentModel) tmList.get(i);
                        documentList.add(bf.getDocumentForm(bm));
                }

                return documentList;
        }

        public int getMaxCertNumber(int parentID)
                throws DocumentDAOSysException
        {
                int count = 0;
                String hql = " select count(*) from DocumentModel " + " where parentID = " + parentID ;

                try
                {
                        count = HibernateDAO.count(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                return count;
        }

        public int getCertNumberbyUser(int userID,int courseID)throws DocumentDAOSysException
        {
                int count = 0;
                String hql = " from DocumentModel " + " where userID = " + userID + " and relationID=" + courseID ;
                try
                {
                        List certs = HibernateDAO.find(hql);
                        if((certs!=null)&&(certs.size() > 0)){
                            count = 1;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }

                return count;
        }

        public String getIsOpenGuest(int parentID, int relationID, int userID) throws MatchDaoSysException
        {
                 String isOpenGuest = "";

               List list = new ArrayList();
                String sql = "from DocumentModel where parentID=" + parentID +" and relationID = "+ relationID +" and userID = "+userID;
                System.out.println("sql = " + sql);
                try
                {
                        list = HibernateDAO.find(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocumentDAOSysException("" + e);
                }
                isOpenGuest=((DocumentModel) list.get(0)).getIsOpenGuest();
               

                //isOpenGuest = ((DocumentModel) list.get(0)).getIsOpenGuest();

                 return isOpenGuest;
        }
        /**
         * Convert the resultSet object to documentForm
         * @param df  the DocumentForm which its docID is null  and  insert into db  lasttime
         * @return the wanted docID from db
         */

        /*
          public int getDocID(DocumentForm df) throws DocumentDAOSysException
          {
                  DocumentForm dbdf=null;
                  int docID = 0;
                  String sqlStr = "select DocID from T_Document_Tab where "
                            + "DocName = '" + df.getDocName()
                            + "' and ParentID="+df.getParentID()
                            //+ " and relationID = " + df.getRelationID()
                            //+ " and docType = " + df.getDocType()
                            + " order by DocID desc ";
                  {
                  }
                  try
                  {
                          LogUtil.debug("system","[DocumentDAOImpl]====================the sql string is : " + sqlStr);
                          if (rs.next())
                          {
                                  docID=rs.getInt("DocID");
                          }
                  }
                  catch (SQLException se)
                  {
                          se.printStackTrace();
                          throw new DocumentDAOSysException("SQLException while getDocID " + "document; Serial = " + df.getDocName() + " :\n" + se);
                  }
                  catch (ULMSSysException se)
                  {
                          se.printStackTrace();
                          throw new DocumentDAOSysException("SQLException while getDocID " + "document; Serial = " + df.getDocName() + " :\n" + se);
                  }
                  finally
                  {
                          try
                          {
                          }
                          catch (SQLException se)
                          {
                                  se.printStackTrace();
                          }
                  }
                  return docID;
          }
        */

        /**
         * Convert the resultSet object to documentForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted documentForm
         */

        /*
          private DocumentForm convertRs2Form(ResultSet rs)
          {
                  DocumentForm df = new DocumentForm();
                  int rsIndex = 1;
                  try
                  {
                          df.setDocID(rs.getInt(rsIndex++));
                          df.setDocName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                          df.setParentID(rs.getInt(rsIndex++));
                          df.setUserful(StringUtil.nullToStr(rs.getString(rsIndex++)));
                          df.setOpenGuest(StringUtil.nullToStr(rs.getString(rsIndex++)));
                          df.setView(StringUtil.nullToStr(rs.getString(rsIndex++)));
                          df.setContent(StringUtil.nullToStr(rs.getString(rsIndex++)));
                          df.setDocType(rs.getInt(rsIndex++));
                          df.setRelationID(rs.getInt(rsIndex++));
                          df.setUserID(rs.getInt(rsIndex++));
                          df.setDepth(rs.getInt(rsIndex++));
                          df.setOrderIndex(rs.getInt(rsIndex++));
                          df.setCreateDate(rs.getDate(rsIndex++));
                          df.setModifyDate(rs.getDate(rsIndex++));
                          df.setDocStatus(StringUtil.nullToStr(rs.getString(rsIndex++)));
                  }
                  catch (SQLException sql)
                  {
                          sql.printStackTrace();
                  }
                  return df;
          }
        */

        public static void main(String args[])
        {
//                DocumentDAOImpl aj = new  DocumentDAOImpl();
                
//                String a =aj.getIsOpenGuest(540,3427,3840042);

//                System.out.println("aaaaaaaaaaaa = " + a);
       
        }
}
