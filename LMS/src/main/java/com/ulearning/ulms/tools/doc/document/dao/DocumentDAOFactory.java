/** * DocumentForm.java.
 * User: xiejh  Date: 2004-4-23 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.document.dao;

import com.ulearning.ulms.tools.doc.document.exceptions.DocumentDAOSysException;
import com.ulearning.ulms.util.DBUtil;

public class DocumentDAOFactory {

    /**
     * This method instantiates a particular subclass implementing
     * the DAO methods based on the information obtained from the
     * deployment descriptor
     */
    public static DocumentDAO getDAO() throws DocumentDAOSysException
    {
        DocumentDAO dao = null;
        try
        {
                dao = new DocumentDAOImpl();
                /*
                if (DBUtil.getWhichDatabase() == 0) {
                dao = new DocumentDAOOracle();
            } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
            {
                //dao = new DocumentDAOSQLServer();
            }
            */
        } catch (Exception se)
        {
            throw new DocumentDAOSysException("DocumentDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
        }

        return dao;
    }


}
