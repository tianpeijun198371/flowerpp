/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.dao;

import com.ulearning.ulms.GCertificate.exceptions.GCertificateDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060111
 * Time: 110302
 */
public class GCertificateDAOFactory
{
        public static GCertificateDAO getDAO() throws GCertificateDAOSysException
        {
                GCertificateDAO dao = null;

                try
                {
                        dao = new GCertificateDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GCertificateDAOSysException(
                                "GCertificateDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
