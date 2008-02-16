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
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DocumentDAOOracle extends DocumentDAOImpl
{
        /**
         * Insert a new document record to database
         * @param documentForm   the value object to be added
         * @throws DocumentDAOSysException
         */

        /*
          public void addDocument(DocumentForm documentForm) throws DocumentDAOSysException
          {
                 // int documentID = 0;
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  String createDate  = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                  String modifyDate  =   createDate;
                  String sqlStr = "insert into T_Document_Tab values(DocID.nextval,'" +
                          StringUtil.checkInputText(documentForm.getDocName()) + "'," +
                          documentForm.getParentID() + ",'" +
                          documentForm.getUserful() + "'," +
                          documentForm.getOpenGuest() + "," +
                          documentForm.getView() + "," +
                          documentForm.getContent() + ",'" +
                          documentForm.getDocType() + "','" +
                          documentForm.getRelationID() + "'," +
                          documentForm.getUserID() + "," +
                          documentForm.getDepth() + "," +
                          documentForm.getOrderIndex() + "," +
                          createDate + "," +
                          modifyDate+ ",'" +
                          documentForm.getDocStatus() + "')";
                   System.out.println("sqlStr = "+sqlStr);
                  {
                  }
                  try
                  {
                          LogUtil.debug("system", "[DocumentDAOOracle]====================the sql string is : " + sqlStr);
                          //sqlStr = "Select DocID.currval as docID FROM DUAL";
                          //if(rs != null && rs.next())
                          //{
                          //        documentID = rs.getInt("docID");
                          //}
                  }
                  catch (ULMSSysException se)
                  {
                          se.printStackTrace();
                          throw new DocumentDAOSysException("SQLException while updating " + "document; Serial = " + documentForm.getDocName() + " :\n" + se);
                  }
                 //return documentID;
          }
        */

        /**
         * Update documentInfo by the new Form
         * @param documentForm   value object for changed
         * @throws DocumentDAOSysException
         */

        /*
          public void updateDocument(DocumentForm documentForm) throws DocumentDAOSysException
          {
                  java.sql.Date nowday = new java.sql.Date(System.currentTimeMillis());
                  String modifyDate = "to_date('" + nowday + "','yyyy-mm-dd')";
                  String sqlStr = "update T_Document_Tab set " +
                      "DocName = '" + StringUtil.checkInputText(documentForm.getDocName()) + "', " +
                      "ParentID = " + documentForm.getParentID() + ", " +
                      "IsUserful = '" + documentForm.getUserful() + "', " +
                      "IsOpenGuest = '" + documentForm.getOpenGuest() + "', " +
                      "IsView = '" + documentForm.getView() + "', " +
                      "IsContent = '" + StringUtil.checkInputTextArea(documentForm.getContent()) + "', " +
                      "DocType = " + documentForm.getDocType() + ", " +
                      "RelationID = " + documentForm.getRelationID() + ", " +
                      "UserID = " + documentForm.getUserID() + ", " +
                      "Depth = " + documentForm.getDepth() + ", " +
                      "OrderIndex = " + documentForm.getOrderIndex() + ", " +
                      "ModifyDate = " + modifyDate + ", " +
                      "DocStatus = '" + documentForm.getDocStatus() + "' " +
                      "  where DocID = " + documentForm.getDocID();
                  {
                  }
                  try
                  {
                          LogUtil.debug("system", "[DocumentDAOOracle]====================the sql string is : " + sqlStr);
                  }
                  catch (ULMSSysException se)
                  {
                          se.printStackTrace();
                          throw new DocumentDAOSysException("SQLException while updating " + "account; Serial = " + documentForm.getDocName() + " :\n" + se);
                  }
          }
        */
}
