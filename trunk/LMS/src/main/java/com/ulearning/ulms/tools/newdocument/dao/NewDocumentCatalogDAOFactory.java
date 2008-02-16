/**
 * NewDocumentCatalogDAOFactory.java.
 * User: shid Date: 2005-12-6 10:05:47
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.dao;

import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentSysException;

public class NewDocumentCatalogDAOFactory
{
        public static NewDocumentCatalogDAO getDAO() throws NewDocumentSysException
        {
                NewDocumentCatalogDAO dao = null;
                try
                {
                        dao = new NewDocumentCatalogDAOImpl();
                }
                catch (Exception se)
                {
                        throw new NewDocumentSysException("NewDocumentCatalogDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}