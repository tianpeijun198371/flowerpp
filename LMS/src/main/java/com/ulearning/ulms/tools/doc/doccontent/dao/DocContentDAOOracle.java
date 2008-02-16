/** * DocumentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.doccontent.dao;

import com.ulearning.ulms.tools.doc.doccontent.exceptions.DocContentDAOSysException;


public class DocContentDAOOracle extends DocContentDAOImpl
{
        /**
         * Insert a new docContent record to database
         * @param docContentForm   the value object to be added
         * @throws DocContentDAOSysException
         */

        /*
          public void addDocContent(DocContentForm docContentForm) throws DocContentDAOSysException
          {
                  String sqlStr = "insert into T_DocContent_Tab values(" +
                          docContentForm.getDocID() + ",'" +
                          StringUtil.checkInputTextArea(docContentForm.getDocContent()) + "','" +
                          StringUtil.checkInputText(docContentForm.getDocLinkTitle()) + "','" +
                          docContentForm.getDocLink() + "')";
                  {
                  }
                  try
                  {
                          LogUtil.debug("system", "[DocContentDAOOracle]====================the sql string is : " + sqlStr);
                  }
                  catch (ULMSSysException se)
                  {
                          throw new DocContentDAOSysException("SQLException while updating " + "docContent; Serial = " + docContentForm.getDocID() + " :\n" + se);
                  }
          }
        */

        /**
         * Update docContentInfo by the new Form
         * @param docContentForm   value object for changed
         * @throws DocContentDAOSysException
         */

        /*
          public void updateDocContent(DocContentForm docContentForm) throws DocContentDAOSysException
          {
              String sqlStr = "update T_DocContent_Tab set " +
                      "DocContent = '" + StringUtil.checkInputTextArea(docContentForm.getDocContent()) + "', " +
                      "DocLinkTitle = '" + StringUtil.checkInputText(docContentForm.getDocLinkTitle()) + "', " +
                      "DocLink = '" + docContentForm.getDocLink() + "' " +
                      "where DocID = " + docContentForm.getDocID();
              {
              }
              try
              {
                      LogUtil.debug("system", "[DocContentDAOOracle]====================the sql string is : " + sqlStr);
              }
              catch (ULMSSysException se)
              {
                      throw new DocContentDAOSysException("SQLException while updating " + "account; Serial = " + docContentForm.getDocID() + " :\n" + se);
              }
          }
        */
}
