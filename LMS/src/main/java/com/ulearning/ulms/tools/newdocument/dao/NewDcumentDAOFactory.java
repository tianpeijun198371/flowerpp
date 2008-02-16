/**
 * NewDcumentDAOFactory.java.
 * User: Administrator  Date: 2005-3-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.dao;


import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentSysException;

public class NewDcumentDAOFactory
{
        public static NewDocumentDAO getDAO() throws NewDocumentSysException
        {
                NewDocumentDAO dao = null;
                try
                {
                        dao = new NewDocumentDAOImpl();
                }
                catch (Exception se)
                {
                        throw new NewDocumentSysException("NewDocumentDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}