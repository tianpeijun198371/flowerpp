/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.dao;

import com.ulearning.ulms.cert.Certificate.exceptions.CertificateDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public class CertificateDAOFactory
{
        public static CertificateDAO getDAO() throws CertificateDAOSysException
        {
                CertificateDAO dao = null;

                try
                {
                        dao = new CertificateDAOImpl();
                }
                catch (Exception se)
                {
                        throw new CertificateDAOSysException(
                                "CertificateDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
