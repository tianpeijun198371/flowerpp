/** * DocumentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.doccontent.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.doc.doccontent.exceptions.DocContentDAOSysException;
import com.ulearning.ulms.tools.doc.doccontent.form.DocContentForm;
import com.ulearning.ulms.tools.doc.doccontent.model.DocContentModel;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class DocContentDAOImpl implements DocContentDAO
{
        public void addDocContent(DocContentForm details)
                throws DocContentDAOSysException
        {
                try
                {
                        HibernateDAO.add(details.getDocContentModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocContentDAOSysException("" + e);
                }
        }

        public void updateDocContent(DocContentForm details)
                throws DocContentDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getDocContentModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocContentDAOSysException("" + e);
                }
        }

        /**
         * Remove the doccontent from database by the docID
         *
         * @param docID
         * @throws DocContentDAOSysException
         */
        public void removeDocContent(String docID) throws DocContentDAOSysException
        {
                String hql = " from DocContentModel where DocID = " + docID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocContentDAOSysException("" + e);
                }
        }

        /**
         * Get the doccontent info via the unique docID
         *
         * @param docID
         * @return the prepared doccontentForm, default is null
         * @throws DocContentDAOSysException
         */
        public DocContentForm getDocContent(int docID)
                throws DocContentDAOSysException
        {
                DocContentForm bf = new DocContentForm();
                DocContentForm res = null;
                List tmList = null;
                String hql = " from DocContentModel where DocID = " + docID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocContentDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        DocContentModel bm = (DocContentModel) tmList.get(0);
                        res = bf.getDocContentForm(bm);
                }

                return res;
        }

        /**
         * Get the doccontent list by the docID
         *
         * @param DocID
         * @return The prepared arraylist object,default size is 0
         * @throws DocContentDAOSysException
         */
        public List getDocContentList(int DocID) throws DocContentDAOSysException
        {
                DocContentForm bf = new DocContentForm();
                DocContentModel bm = null;
                ArrayList doccontentList = new ArrayList();
                List tmList = null;
                String hql = " from DocContentModel where DocID = " + DocID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DocContentDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (DocContentModel) tmList.get(i);
                        doccontentList.add(bf.getDocContentForm(bm));
                }

                return doccontentList;
        }

        /**
         * Convert the resultSet object to doccontentForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted doccontentForm
         */

        /*
          private DocContentForm convertRs2Form(ResultSet rs)
          {
              DocContentForm bf = new DocContentForm();
              int rsIndex = 1;
              try
              {
                      String tmp=null;
                      bf.setDocID(rs.getInt(rsIndex++));
                      tmp = rs.getString(rsIndex++) ;
                      tmp=((tmp==null||tmp.equals("null"))?"":tmp);
                      bf.setDocContent(tmp.trim());
                      tmp = rs.getString(rsIndex++) ;
                      tmp=((tmp==null||tmp.equals("null"))?"":tmp);
                      bf.setDocLinkTitle(tmp.trim());
                      tmp = rs.getString(rsIndex++) ;
                      tmp=((tmp==null||tmp.equals("null"))?"":tmp);
                      bf.setDocLink(tmp.trim());
              } catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return bf;
          }
        */
}
